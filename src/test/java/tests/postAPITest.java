package tests;
import BaseClass.testBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import configProperties.userData;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restClient.RestClient;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class postAPITest extends testBase {

    public String mainURL ;
    public String s1;
    public String s2;
    public CloseableHttpResponse closeableHttpResponse;

    public postAPITest() throws IOException {
        super();
    }

    @BeforeMethod
      public void setUp() throws IOException {
        postAPITest obj = new postAPITest();
       s1 = prop.getProperty("url");
        s2 = prop.getProperty("ServiceUrl");
        mainURL = s1+s2;
    }

    @Test
    public void callPost() throws IOException {
        RestClient restClient = new RestClient();

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        //jacksonAPI
        ObjectMapper mapper = new ObjectMapper();

        userData user = new userData("morpheus" , "leader"); //expected user object

        //object to json file

        mapper.writeValue(new File("D:\\RestApiAutomationProjectFolder\\src\\main\\resources\\user.json"), user);

        //object to json String

        String userJsonString = mapper.writeValueAsString(user);
        System.out.println(userJsonString);

       closeableHttpResponse=  restClient.post(mainURL, userJsonString, headerMap );

       //Status Code

       int statusCode =  closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode);

        //json String

       String responseString =  EntityUtils.toString(closeableHttpResponse.getEntity() , "UTF-8");

        System.out.println("responseString--->" + responseString);

        JSONObject responseJson = new JSONObject(responseString);

        System.out.println("responseJson--->" + responseJson);

        //json to java object
        userData obj = mapper.readValue(responseString , userData.class);  //actual user object
        System.out.println(obj);

        System.out.println(user.getName().equals(obj.getName()));
        System.out.println(user.getJob().equals(obj.getJob()));








    }







}
