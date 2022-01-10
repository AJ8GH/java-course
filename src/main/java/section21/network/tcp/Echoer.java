package section21.network.tcp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class Echoer extends Thread {
    private static final int SLEEP_DURATION = 10000;
    private final Socket socket;

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);
            while (true) {
                String echoString = input.readLine();
                log.info("Received client input: {}", echoString);
                sleep(SLEEP_DURATION);
                if (echoString != null && echoString.equalsIgnoreCase("exit")) {
                    log.info("Shutting down server...");
                    break;
                }
                output.println(echoString);
            }
        } catch (IOException e) {
            log.error("IOException in thread: {}", e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log.error("IOException in thread: {}", e.getMessage());
            }
        }
    }


    private static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            log.error("Thread interrupted: {}", e.getMessage());
        }
    }
}
