package ex03_Server_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client extends Thread {
    private BufferedReader reader; // 서버 정보를 읽는 reader

    public Client(Socket socket) { // 서버 정보가 담긴 socket이 넘어온다.
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            while (true) {
                String message = reader.readLine(); // 서버는 클라이언트로 문장을 보낸 뒤 엔터를 누르므로 엔터를 읽는 readLine()을 사용한다.
                if (message.equals("안녕")) {
                    break;
                }
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
