package section21.network.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

@Slf4j
public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;
    private static final int TIMEOUT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            socket.setSoTimeout(TIMEOUT);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String payload;
            String response;

            do {
                System.out.println("Enter string to be echoed:");
                payload = scanner.nextLine();
                writer.println(payload);
                if (!payload.equals("exit")) {
                    response = reader.readLine();
                    System.out.println(response);
                }
            } while (!payload.equals("exit"));

        } catch (SocketTimeoutException e) {
            log.error("Socket timeout: {}", e.getMessage());
        } catch (IOException e) {
            log.error("Client exception: {}", e.getMessage());
        }
    }
}
