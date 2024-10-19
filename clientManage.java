import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class clientManage extends Thread{
    private Socket socket;
    public OutputStream out;
    private InputStream inp;
    private String nom;
    private Scanner scanner = new Scanner(System.in);
    public int id;

    public clientManage(Socket socket,String nom,int id) throws Exception{
        this.socket = socket;
        out = socket.getOutputStream();
        inp = socket.getInputStream();
        this.nom = nom;
        this.id = id;
        String mess =" "+ String.valueOf(id) + "\n";
        out.write(mess.getBytes());
        out.flush();
    }

    public void receive(){
        int c;
        while(true){
            try {
                while((c=inp.read()) != -1){
                    System.out.print((char)c);
                }
            } catch (IOException e) {
                Thread.yield();
                e.printStackTrace();
            }
        }
    }

    public void send(){
        while(true){
            try {
                    String inputString = scanner.nextLine();
                    inputString+="\n";
                    if(inputString!=null){
                        FileOutputStream file = new FileOutputStream("salon"+id+".txt",true);
                        file.write((nom+" : "+inputString).getBytes());
                        file.close();
                        out.write((nom+" : "+inputString).getBytes());
                        Thread.yield();
                    } 
            } catch (Exception e) {
                Thread.yield();
                e.printStackTrace();
            }
        }
    }

    public void run(){
        new Thread(()->{
            receive();
        }).start();
        new Thread(()->{
            send();
        }).start();
    }


}
