import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client{
    
    private synchronized static String checkSalon(Scanner scanner) throws IOException {
        System.out.print("name of salon : ");
        String name = scanner.nextLine();
        System.out.print("password of this salon : ");
        String password = scanner.nextLine();
    
        try (BufferedReader fileReader = new BufferedReader(new FileReader("salonData.txt"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] data = line.split("\\s+");
                String id = data[0];
                String salonName = data[1];
                String salonPassword = data[2];
    
                if (salonName.equals(name) && salonPassword.equals(password)) {
                    return id; 
                }
            }
        }
    
        return null; 
    }
    
    private synchronized static int addSalon(Scanner scanner) throws IOException {
        int id;
        System.out.print("Type a name for salon : ");
        String name = scanner.nextLine();
        System.out.print("Type a password for this salon : ");
        String password = scanner.nextLine();
    
        Salon salon = new Salon();
        id = salon.getId();
    
        try (FileWriter file = new FileWriter("salonData.txt", true)) {
            file.append(id + " " + name + " " + password + "\n");
        }
    
        return id; 
    }
        public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Tapez votre nom : ");
        String nom = scanner.nextLine();
        int id;
        while(true){
            System.out.print("C : create a salon , J : join a salon\nTapez C/J : ");
            String choix = scanner.nextLine();
            if (choix.equalsIgnoreCase("C")) {
                id = addSalon(scanner);
                break;
            }
            else if(choix.equals("J")|| choix.equals("j")){
                String res =checkSalon(scanner);
                if(res!=null){
                    id =Integer.valueOf(res);
                    break;
                }
                else {
                    System.out.println("Salon not found or password incorrect.");
                }
            }
            else {
                continue;
            }
        }
        Socket socket = new Socket("localhost",8000);
        clientManage cli = new clientManage(socket, nom,id);
        cli.start();
    }
}