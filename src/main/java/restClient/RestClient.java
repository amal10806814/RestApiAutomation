package restClient;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RestClient {

    public CloseableHttpResponse closeableHttpResponse;

    //Get Method
    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault(); //create http connection
        HttpGet httpget = new HttpGet(url); //http get request
        closeableHttpResponse = httpclient.execute(httpget); //hit the get url

        return closeableHttpResponse;

    }

//Post Method

    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url); // for request
        httpPost.setEntity(new StringEntity(entityString)); // for payload

        //for headers
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpPost);

        return closeableHttpResponse;
    }

}






/*
       // int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
       // System.out.println("statusCode --->" + + statuscode);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("responseJson" + " "+ responseJson);

       // ******************************************
        JSONArray ja = responseJson.getJSONArray("data");

        String s = ja.getJSONObject(0).getString("first_name");
        System.out.println(s);


        Assert.assertEquals(s, "George" , "values different");
//        for(int i = 0 ; i< ja.length(); i++)
//        {
//       String s = ja.getJSONObject(i).getString("last_name");
//            System.out.println(s);
//        }

            //******************************************

       Header[] headerArray =  closeableHttpResponse.getAllHeaders();

        HashMap<String, String> allheaders = new HashMap<String, String>();

        for (Header hr : headerArray)
        {
            allheaders.put(hr.getName(), hr.getValue());
        }

        System.out.println("Header Array --->" + " "+ allheaders);

    }
}
*/