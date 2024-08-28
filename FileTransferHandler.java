package test;

import java.io.*;
import java.net.Socket;

public class FileTransferHandler implements Runnable {
    private static final int BUFFER_SIZE = 4096;

    private Socket clientSocket;
    private String filePath;

    public FileTransferHandler(Socket clientSocket, String filePath) {
        this.clientSocket = clientSocket;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try (
            BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(filePath));
            BufferedOutputStream clientOutputStream = new BufferedOutputStream(clientSocket.getOutputStream())
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                clientOutputStream.write(buffer, 0, bytesRead);
                clientOutputStream.flush();
            }

            System.out.println("File sent to: " + clientSocket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
