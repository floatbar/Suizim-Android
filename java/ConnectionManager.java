package wafoot.becoming.wafoot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionManager {
    protected static boolean checkConnection() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("0.0.0.0", 6666), 4000); // Replace with your server credentials.
            socket.close();
            return true;
        }

        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
