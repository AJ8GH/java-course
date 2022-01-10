package section21.network.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;

@Slf4j
public class Server {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            log.info("Server listening on port {}", PORT);

            while (true) {
                new Echoer(serverSocket.accept()).start();
                log.info("Client connected");
            }
        } catch (IOException e) {
            log.error("Server exception: {}", e.getMessage());
        }
    }
}
