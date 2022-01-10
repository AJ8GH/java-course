package section21.network.udp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

@Slf4j
public class Client {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            DatagramSocket socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            String echoString;

            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();
                byte[] buffer = echoString.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
                socket.send(packet);

                byte[] buffer2 = new byte[50];
                DatagramPacket response = new DatagramPacket(buffer2, buffer2.length);
                socket.receive(response);
                System.out.println("Text received: " + new String(buffer2, 0, response.getLength()));
            } while (!echoString.equalsIgnoreCase("exit"));

        } catch (SocketTimeoutException e) {
            log.error("Socket timed out: {}", e.getMessage());
        } catch (IOException e) {
            log.error("Client error: {}", e.getMessage());
        }
    }
}
