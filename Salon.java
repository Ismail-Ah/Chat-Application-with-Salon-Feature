import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Salon{

    private int salonId;

    public Salon(){
        createFile();
    }
    
    public void createFile(){
        try {
            BufferedReader f = new BufferedReader(new FileReader("salonData.txt"));
            salonId =(int) f.lines().count()+1;
            f.close();
            File salon = new File("salon"+salonId+".txt");
            if (salon.createNewFile()) {
                System.out.println("Salon created: " + salon.getName());
            } else {
                System.out.println("Salon already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public int getId(){
        return salonId;
    }
}