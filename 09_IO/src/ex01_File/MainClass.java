package ex01_File;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class MainClass {

	/*
		File 클래스
		1. Java.io 패키지
		2. 파일, 디렉터리(폴더)를 관리하는 클래스이다.
		3. 갹채 생성 방법
			1) new File(경로, 파일)
			2) new File(파일)
		4. 경로 구분 방법
			1) Window : \
			2) Linux 	: /
	 */
	
	public static void ex01() {
		
		// 디렉터리 관련 필수 작업
		
		LocalTime now = LocalTime.now();
		
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		
		
//		File dir = new File("Users/mong/storage");		
		// File dir = new File(C: + File.seoaratir + "storage"); 		// C드라이브 아래 storage 폴더를 의마한다. 
		File dir = new File("/Users/mong/Documents/" + hour + "/" + minute + "/" + second);

		
		// 없으면 만들고, 있으면 지운다.
		if(dir.exists() == false) {
			dir.mkdirs();
			System.out.println("Users/mong/storage 생성 완료");
		} else {
			// dir.delete(); 			// 지금 바로 지운다.
			dir.deleteOnExit(); 		// JVM 실행이 끝나면 지운다.
			System.out.println("Users/mong/storage 삭제 완료");
		}
	}
	
	public static void ex02() throws IOException {		// 예외 처리를 ex02 메소드를 호출한 곳으로 넘긴다.
		
		File dir = new File("/Users/mong/Documents" + File.separator + "storage");
		if(dir.exists() == false) {
			dir.mkdirs();
		}

		File file = new File(dir, "myfile.txt");
			if(file.exists() == false) {
				file.createNewFile();		// 반드시 예외 처리가 필요한 코드
			} else {
				file.delete();
			}
	}
	
	public static void ex03() throws IOException {
		
		File dir = new File("/Users/mong/Documents" + File.separator + "storage");
		if(dir.exists() == false) {
			dir.mkdirs();
		}

		File file = new File(dir, "myfile.txt");
		if(file.exists() == false) {
			file.createNewFile();
		}
		
		// 파일의 정보 확인
		System.out.println("파일명: " + file.getName());
		System.out.println("디렉터명: " + file.getParent());
		System.out.println("경로명: " + file.getPath());						// 파일명 + 디렉터리명
		
		System.out.println("디렉터리인가? " + file.isDirectory());
		System.out.println("파일인가? " + file.isFile());
		
		long lastModified = file.lastModified();									// 최종 수정일의 타임 스탬프
		System.out.println("최종 수정일: " + lastModified);
		
		String lastModifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(lastModified);
		System.out.println("최종 수정일: " + lastModifiedDate);
		
		long size = file.length(); 														// 파일의 크기가 바이트 단위로 저장
		long kb = (size / 1024) + (size % 1024 != 0 ? 1 : 0);
		System.out.println("파일 크기: " + kb + "KB");						// 킬로 바이트 출력(1024바이트 = 1킬로 바이트)
	}
	
	public static void ex04() {
		
		File dir = new File("/Users/mong/Documents" + File.separator + "storage");
		
		File[] files = dir.listFiles();
		
		for(int i = 0; i < files.length; i++) {
			if(files[i].isHidden() == false) {
				System.out.println(files[i].getName());			
			}
		}	
	}
	public static void main(String[] args) {

		ex01();
		
	}

}
