package tests;

import BaseClass.testBase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import restClient.RestClient;

import java.io.IOException;


public class getAPItest extends testBase {

    String s;
    String s1;
    String s2;
    CloseableHttpResponse closeableHttpResponse;

    public getAPItest() throws IOException {
        super();
    }

//    testBase testBase;

    @BeforeMethod
    public void setUp() throws IOException {
        getAPItest obj = new getAPItest();

     s = prop.getProperty("url");
    s1 = prop.getProperty("ServiceUrl");

    s2 = s+s1;
    }

    @Test
    public void callGet() throws IOException {
        RestClient obj1 = new RestClient();
        closeableHttpResponse = obj1.get(s2); //calling RestClient class method

      int statusCode =  closeableHttpResponse.getStatusLine().getStatusCode();

      Assert.assertEquals(statusCode, 200 , "values miss match");

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("responseJson" + " "+ responseJson);

        String jsonobjectAd = responseJson.getJSONObject("ad").getString("company");
        System.out.println("json object vlaue --->" + jsonobjectAd);

        JSONArray ja = responseJson.getJSONArray("data");

        String s = ja.getJSONObject(0).getString("first_name");
        System.out.println(s);


        Assert.assertEquals(s, "George" , "values different");

    }


}

