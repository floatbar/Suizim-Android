package wafoot.becoming.wafoot;

import android.app.Activity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * There is a better performance and a less delay with my custom JSON-formatted UDP protocol, which has been written in the C language.
 * This is one of the reasons why Suizim (also called WaFoot) utilizes UDP for instant messaging.
 * Do not forget to use TCP protocol instead of UDP in your projects if you really give importance to security rather than performance ;)
*/

// The UDP server is ready and running at the moment.
// However, for the server's security, unfortunately, Suizim's UDP server isn't used only for now.
// Instead, Firebase is now being used for message operations.
// Even so, do not worry. Soon, I will delete my Firebase database, and will replace Firebase with my UDP server for message operations within the app.
// Do not forget that UDP transfers data much faster than other protocols :-)

public class UDPClient {

    private final Activity activity;

    public UDPClient(final Activity activity) {
        this.activity = activity;
    }

    protected void sendUDPDataPacket() {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress ipAddress = InetAddress.getByName(activity.getString(R.string.udp_server_ip_address));
            int PORT = Integer.parseInt(activity.getString(R.string.udp_server_port));

            String message = "Hello Universe!";
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
            e.printStackTrace();
        }
    }
}
