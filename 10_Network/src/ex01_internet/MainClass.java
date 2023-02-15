package ex01_internet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class MainClass {

	/*
	   URL (Uniform Rosource Locator)
	   1. 정형화된 자원의 경로
	   2. 실제로는 웹 주소를 의미한다.
	   3. 구성  
	      프로토콜://      호스트	   /   서버 경로  ? 파라미터 = 값 & 파라미터 = 값
	       https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90
	 	  1) 프로토콜 : 통신규약, https(secure + http : 보안 처리된 하이퍼텍스트 전송 프로토콜)
	 	  2) 호스트	: 서버의 대표 주소
	 	  3) 서버경로	: URL Mapping(URL Pattern)
	 	  4) 파라미터	: 서버로 보내는 데이터 (변수라고 생각하면 됨)
	 */

	public static void ex01() {

		String apiURL = "https://search.naver.com/search.naver?query=%EC%82%BC%EC%84%B1%EC%A0%84%EC%9E%90";
		URL url = null;

		try {
			url = new URL(apiURL); // 예외 처리가 필요한 코드

			System.out.println("프로토콜 : " + url.getProtocol());
			System.out.println("호스트 : " + url.getHost()); 
			System.out.println("파라미터 :" + url.getQuery());
			// 서버 경로는 형식은 개발자가 정해서 호출하는 메소드가 없고, 직접 계산해서 잘라내야 함.

		}catch(MalformedURLException e) {
			System.out.println("apiURL 주소 오류");
		}

	}

	public static void ex02() {

		// 웹 접속을 담당하는 HttpURLConnection

		String apiURL = "https://www.naver.com";
		URL url = null;
		HttpURLConnection con = null;

		try {

			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection(); // IOException 처리가 필요하다.

			/*
			   HTTP 응답 코드 공부하기
			   1. 200 : 정상
			   2. 40x : 잘못된 요청 (사용자가 잘못하였음)
			   3. 50x : 서버 오류 (서버가 잘못하였음)
			 */

			System.out.println("정상 응답 : " + HttpURLConnection.HTTP_OK);
			System.out.println("Not Found : " + HttpURLConnection.HTTP_NOT_FOUND);
			System.out.println("Internal Error : " + HttpURLConnection.HTTP_INTERNAL_ERROR);

			// apiURL 접속 확인
			int responseCode = con.getResponseCode();
			if(responseCode == 200) {
				System.out.println(apiURL + " 접속 완료");
			}

			// 요청 방식 (요청 메소드)
			String requestMethod = con.getRequestMethod();
			System.out.println("요청 방식 : " + requestMethod);

			// 컨텐트 타입
			String contentType = con.getContentType();
			System.out.println("컨텐트 타입 : " + contentType);

			// 요청 헤더 
			String userAgent = con.getRequestProperty("User-Agent");
			System.out.println("User-Agent : " + userAgent);

			String referer = con.getRequestProperty("Referer");
			System.out.println("Referer : " + referer);

			// 접속 종료
			con.disconnect();


		}catch (MalformedURLException e) {
			System.out.println("apiURL 주소 오류");
		}catch (IOException e) {
			System.out.println("apiURL 접속 오류");
		}

	}

	// 다음 이미지 복사하는 메소드
	public static void ex03() {

		String apiURL = "https://t1.daumcdn.net/daumtop_chanel/op/20200723055344399.png";
		URL url = null;
		HttpURLConnection con = null;
		InputStream in = null;		// 다음 로고를 읽어 들이는 입력 스트림
		FileOutputStream out = null;// storage로 내보내는 출력 스트림



		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();

			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK); {

				in = con.getInputStream();
				out = new FileOutputStream("/Users/woomin/Documents/storage" + File.separator +"daum.png");

				byte[] b = new byte[10];
				int readByte = 0;

				while((readByte = in.read(b)) != -1) {
					out.write(b, 0, readByte);
				}

				System.out.println("다운로드 완료");

				out.close();
				in.close();
				con.disconnect();


			}
		}catch(MalformedURLException e) {
			System.out.println("apiURL 주소 오류");
		}catch(IOException e) {
			// 접속 실패 or 스트림 관련 오류 발생
			e.printStackTrace();
		}

	}

	public static void ex003() {

		String apiURL = "https://t1.daumcdn.net/daumtop_chanel/op/20200723055344399.png";
		URL url = null;
		HttpURLConnection con = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			in = new BufferedInputStream(con.getInputStream());
			out = new BufferedOutputStream(new FileOutputStream("/Users/woomin/Documents/storage/daum2.png"));

			byte[] b = new byte[10];
			int readByte = 0;

			while((readByte = in.read(b)) != -1) {
				out.write(b, 0, readByte);
			}

			System.out.println("다운로드 연습 완");

			in.close();
			out.close();
			con.disconnect();

		}catch (Exception e) {
			e.printStackTrace();
		}


	}

	// 텍스트 파일을 복사하는 메소드
	public static void ex04() {

		String apiURL = "https://gdlms.cafe24.com/uflist/curri/10004/bbs/68_63d8848f7d506.txt";
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader reader = null;
		BufferedWriter writer = null;


		try {

			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			writer = new BufferedWriter(new FileWriter("/Users/woomin/Documents/storage/다운로드문서.txt"));

			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			}else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));

			}

			StringBuilder sb = new StringBuilder();
			char[] ch = new char[2];
			int readCount = 0;

			while((readCount = reader.read(ch)) != -1) {
				sb.append(ch,0,readCount);
			}

			writer.write(sb.toString());

			writer.close();
			reader.close();
			con.disconnect();

		}catch(MalformedURLException e) {
			System.out.println("접속 오류");
		}catch(IOException e2) {
			e2.printStackTrace();
		}
	}

	public static void ex004() {

		String apiURL = "https://gdlms.cafe24.com/uflist/curri/10004/bbs/68_63d8848f7d506.txt";

		URL url = null;
		HttpURLConnection con = null;
		BufferedReader in = null;
		BufferedWriter out = null;

		try {

			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();

			int responseCode = con.getResponseCode();
			String message = null;

			if(responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				out = new BufferedWriter(new FileWriter("/Users/woomin/Documents/storage/다운로드.txt"));
				message = "다운로드 성공";
			}else {
				in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				out = new BufferedWriter(new FileWriter("/Users/woomin/Documents/storage/다운로드.txt"));
				message = "다운로드 실패";
			}

			StringBuilder sb = new StringBuilder();
			char[] ch = new char[3];
			int readCount = 0;

			while((readCount = in.read(ch)) != -1) {
				sb.append(ch, 0, readCount);
			}
			sb.append(message);
			out.write(sb.toString());

			out.close();	// Writer는 닫지 않으면 문서가 flush가 되지않아 txt가 비어있음.
			in.close();
			con.disconnect();

		}catch(Exception e) {
			e.getMessage();
		}
	}

	public static void ex05() {

		/*
		   인코딩(암호화) : 원본 데이더를 UTF-8 방식으로 암호화
		   디코딩(복호화) : UTF-8 방식으로 암호화된 데이터를 복원
		 */

		String data = "한글 english 12345 !@#$%";

		try {
			// 인코딩
			String encodeData = URLEncoder.encode(data, "UTF-8");
			System.out.println(encodeData);
			// 디코딩
			String decodeData = URLDecoder.decode(encodeData, "UTF-8");
			System.out.println(decodeData);


		}catch(UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}

	}

	public static void ex06() {

		// 1시간마다 갱신되는 전국 날씨 정보
		String apiURL = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml";

		// storage에 같은 이름으로 다운로드 받기
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader in = null;
		BufferedWriter out = null;

		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			out = new BufferedWriter(new FileWriter("/Users/woomin/Documents/storage/sfc_web_map.xml"));
			
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			StringBuilder sb = new StringBuilder();
			char[] b = new char[2];
			int readByte = 0;
			
			while((readByte = in.read(b)) != -1) {
				sb.append(b,0,readByte);
			}
			
			out.write(sb.toString());
			
			
//			out.close();
			in.close();
			con.disconnect();
		}catch(IOException e) {

		}
		
		
		System.out.println("복사완료");
	}
	
	public static void ex07() {
		
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("/Users/woomin/Documents/storage/sfc_web_map.xml"))) {
			
			byte[] b = new byte[1024];
			int readByte = 0;
			StringBuilder sb = new StringBuilder();
			
			while((readByte = in.read(b)) != -1) {
				sb.append(new String(b, 0, readByte));
			}
			
			System.out.println(sb.toString());
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {

		ex01();

	}

}
