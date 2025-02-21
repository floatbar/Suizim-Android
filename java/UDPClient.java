package wafoot.becoming.wafoot;

import android.app.Activity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * There is better performance and less delays with my custom JSON-formatted protocol that operates over UDP, which has been written in the C language.
 * This is one of the reasons why Suizim, also called WaFoot, utilizes UDP for instant messaging.
 * Do not forget to use TCP protocol instead of UDP in your projects, if you really attach importance to security rather than performance ;)
*/

// The UDP server is ready and running at the moment.
// However, for some of the security reasons, unfortunately, Suizim's UDP server isn't used only for now.
// Instead, Firebase is now being used for message operations.
// Even so, do not worry at all! Soon, Suizim will replace Firebase with its UDP server for message operations within the app.

public class UDPClient {
    private final Activity activity;

    public UDPClient(Activity activity) {
        this.activity = activity;
    }

    protected void sendUDPDataPacket() {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress ipAddress = InetAddress.getByName(activity.getString(R.string.udp_server_ip_address));
            int PORT = Integer.parseInt(activity.getString(R.string.udp_server_port));

            String message = "Hello, Universe!";
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, PORT);
            socket.send(sendPacket);
            System.out.println("Message sent successfully!");

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Message received from UDP server: " + receivedMessage);
        }

        catch (Exception e) {
            System.out.print(e);
        }
    }
}
