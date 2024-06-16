package wafoot.becoming.wafoot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionManager {
    protected static boolean checkConnection() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("80.208.221.32", 6666), 4000);
            socket.close();
            return true;
        }

        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}