package com.weatherapi.WeatherDemo;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class App 
{
    public static void main( String[] args )
    {
    	try {
    		callWeatherForecastApi();
    	}catch (URISyntaxException e) {
    		System.out.print("No");
    	}catch (IOException e) {
    		System.out.print("No");
    	}
    }
    
    public static void callWeatherForecastApi() throws URISyntaxException, IOException {
    	
    	URIBuilder builder = new URIBuilder("https://api.weatherbit.io/v2.0/current?lat=35.7796&lon=-78.6382&key=API_KEY&include=minutely");
    	//build object 
    	HttpGet getData = new HttpGet(builder.build());
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	CloseableHttpResponse respone = httpClient.execute(getData);
    	HttpEntity responseEntity = respone.getEntity();
    	String result = EntityUtils.toString(responseEntity);
    	System.out.println("JSON data :");
    	System.out.println(result);
    	JSONObject responseObj = new JSONObject(result);
    	JSONArray valueObj = responseObj.getJSONArray("data");
    	System.out.println("Fetching city, date, temp. etc from JSON");
    	for(int i=0;i<valueObj.length();i++) {
    		JSONObject value = valueObj.getJSONObject(i);
    		String city = value.getString("city_name");
    		Double Temp = value.getDouble("app_temp");
    		Integer Clouds = value.getInt("clouds");
    		Double Air_p = value.getDouble("pres");
    		String dateTime = value.getString("datetime");
    		System.out.println("City: "+city);
    		System.out.println("Date: "+dateTime);
    		System.out.println("Temp.: "+Temp);
    		System.out.println("Clouds: "+Clouds);
    		System.out.println("Air Pressure: "+Air_p);
    	}
    	httpClient.close();
    }
}
