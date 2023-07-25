package WeatherForecastReport;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientHelper {

    private String data;

    public String getData() {
        return data;
    }


    public void GETRequest(String url) throws IOException {


        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpGet getRequest = new
                HttpGet(url);

        HttpResponse response = httpClient.execute(getRequest);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
        }

        HttpEntity httpEntity = response.getEntity();
        data = EntityUtils.toString(httpEntity);
    }


}
