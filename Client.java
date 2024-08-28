package test;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "192.168.63.49";
    private static final int SERVER_PORT = 5555;
    private static final String DOWNLOAD_PATH = "C:\\Users\\DEBENDU_BEHERA\\Desktop\\received\\music.mp3";

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedInputStream serverInputStream = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(DOWNLOAD_PATH))
        ) {
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = serverInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
                fileOutputStream.flush();
            }

            System.out.println("File downloaded to: " + DOWNLOAD_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
