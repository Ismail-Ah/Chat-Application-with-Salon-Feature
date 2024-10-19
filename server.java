import java.net.ServerSocket;
import java.net.Socket;

public class server{
    public static void main(String[] args) throws Exception{
        ServerSocket socket = new ServerSocket(8000);
        while(true){
            Socket sock = socket.accept();
            if(sock!=null){
                clientHandler newClient = new clientHandler(sock);
                newClient.start();
            }
        }
    }
}