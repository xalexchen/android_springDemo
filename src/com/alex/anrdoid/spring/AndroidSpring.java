package com.alex.anrdoid.spring;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import anrdoid.spring.R;


public class AndroidSpring extends Activity {
    private TextView mIP;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mIP = (TextView) findViewById(R.id.springmessage);
        
        AsyncTask<String,Integer,GeoIP> springTask = new AsyncTask<String,Integer,GeoIP>(){
    		@Override
    		protected GeoIP doInBackground(String... params) {
    			// Set the Accept header
    			HttpHeaders requestHeaders = new HttpHeaders();
    			requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));
    			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
    			
    	        String url = params[0];
    	        // Create a new RestTemplate instance
    	        RestTemplate restTemplate = new RestTemplate();

    	        // Add the Gson message converter
    	        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
    	        // Make the HTTP GET request, marshaling the response from JSON to GeoIP
    	        ResponseEntity<GeoIP> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, GeoIP.class);
    	        GeoIP events = responseEntity.getBody();
    	        
    			return events;
    		}
        	@Override
        	protected void onPostExecute(GeoIP result) {
        		mIP.setText(result.getIp());
        	}
        };
        springTask.execute("http://freegeoip.net/json/");
    }
    class GeoIP {
    	private String ip;
    	private String country_code;
    	private String country_name;
    	private String region_code;
    	private String region_name;
    	private String city;
    	private String zipcode;
    	private String latitude;
    	private String longitude;
    	private String metro_code;
    	private String areacode;
    	
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getCountry_code() {
			return country_code;
		}
		public void setCountry_code(String country_code) {
			this.country_code = country_code;
		}
		public String getCountry_name() {
			return country_name;
		}
		public void setCountry_name(String country_name) {
			this.country_name = country_name;
		}
		public String getRegion_code() {
			return region_code;
		}
		public void setRegion_code(String region_code) {
			this.region_code = region_code;
		}
		public String getRegion_name() {
			return region_name;
		}
		public void setRegion_name(String region_name) {
			this.region_name = region_name;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getZipcode() {
			return zipcode;
		}
		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getMetro_code() {
			return metro_code;
		}
		public void setMetro_code(String metro_code) {
			this.metro_code = metro_code;
		}
		public String getAreacode() {
			return areacode;
		}
		public void setAreacode(String areacode) {
			this.areacode = areacode;
		}
    	
    }
}