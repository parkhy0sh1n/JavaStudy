package ex03_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainClass {

	public static void ex01() {
		/*
		    한국공항공사_항공기 운항정보 : 공항 코드 정보
		 	1. 서비스 URL : http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList
		 	2. 요청변수 (Request Parameter)
		 		1) ServiceKey : 인증키
		 */

		/* 
		   인코딩 키를 이용한 aipURL
			String encodingKey = "aOJrKq8Gmbtj4fbbnazrR4UFB1CRl4gcsdQLaM%2B1A%2FygF3r7EVOrIGy2LgOclgIMb2XHdGOHa9OcHBQoUx1a%2Fg%3D%3D";
			String apiURL = "http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList";
			   	   apiURL += "?serviceKey=" +  encodingKey;
		 */

		String serviceKey = "aOJrKq8Gmbtj4fbbnazrR4UFB1CRl4gcsdQLaM+1A/ygF3r7EVOrIGy2LgOclgIMb2XHdGOHa9OcHBQoUx1a/g==";
		String apiURL = "http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList";
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;


		try {

			apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET"); // 데이터를 요청할 때 쓰는 파라미터는 "GET"이다. (반드시 대문자로만 적을 것)
			con.setRequestProperty("Content=Type", "application/xml; charset=UTP-8");

			int responseCode = con.getResponseCode();

			if(responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}


			String line = null;
			StringBuilder sb = new StringBuilder();

			while((line = reader.readLine()) != null) {
				sb.append(line);
			}

			reader.close();
			con.disconnect();

			File file = new File("/Users/woomin/Documents/storage", "airportCodeInfo.xml");

			writer = new BufferedWriter(new FileWriter(file));

			writer.write(sb.toString());

			writer.close();

			System.out.println("AirportCodeInfo Creadted!");

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void ex02() {
		
		/*
	    한국공항공사_항공기 운항정보 : 국제선 운항 스케쥴
	 	1. 서비스 URL : http://openapi.airport.co.kr/service/rest/FlightStatusList/getFlightStatusList
	 	2. 요청변수 (Request Parameter)
	 		1) ServiceKey : 인증키
	 		2) &pageNo : 페이지 번호
			3) &schDate : 검색하는 날짜
			4) &schDeptCityCode : 출발 도시 코드
			5) &schArrvCityCode : 도착 도시 코드
	 */
		
		String apiURL = "http://openapi.airport.co.kr/service/rest/FlightScheduleList/getIflightScheduleList";
		String serviceKey = "aOJrKq8Gmbtj4fbbnazrR4UFB1CRl4gcsdQLaM+1A/ygF3r7EVOrIGy2LgOclgIMb2XHdGOHa9OcHBQoUx1a/g==";
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		try {
			
			apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
			apiURL += "&pageNo=1";
			apiURL += "&schDate=20230201";
			apiURL += "&schDeptCityCode=ICN";
			apiURL += "&schArrvCityCode=JFK";
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
			
			int responseCode = con.getResponseCode();
			
			if(responseCode == 200) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			StringBuilder sb = new StringBuilder();

			while((line = reader.readLine()) != null) {
				sb.append(line);
			}

			reader.close();
			con.disconnect();
			
			writer = new BufferedWriter(new FileWriter("/Users/woomin/Documents/storage/국제선운항스케쥴.xml"));
			
			writer.write(sb.toString());
			
			writer.close();
			
			System.out.println("AirportCodeInfo Creadted!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	public static void main(String[] args) {

		ex02();


	}

}
