package ed.port.forward; /**
 * This program is an example from the book "Internet 
 * programming with Java" by Svetlin Nakov. It is freeware. 
 * For more information: http://www.nakov.com/books/inetjava/ 
 */
import java.io.*;
import java.net.*;

/**
 * ed.port.forward.TCPForwardServer is a simple TCP bridging software that 
 * allows a TCP port on some host to be transparently forwarded 
 * to some other TCP port on some other host. ed.port.forward.TCPForwardServer 
 * continuously accepts client connections on the listening TCP 
 * port (source port) and starts a thread (ed.port.forward.ClientThread) that 
 * connects to the destination host and starts forwarding the 
 * data between the client socket and destination socket. 
 */
public class TCPForwardServer {

    public static void main(String[] args) throws IOException {
        PortForwardConfig config = PortForwardConfig.getConfig(args);
        ServerSocket serverSocket =
                new ServerSocket(config.getSourcePort());
        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientThread clientThread =
                    new ClientThread(clientSocket, config);
            clientThread.start();
        }
    }
}

