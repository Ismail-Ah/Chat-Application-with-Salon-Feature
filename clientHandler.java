import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class clientHandler extends Thread {
    private Socket socket;
    public OutputStream out;
    public InputStream inp;
    public int id;
    private static ArrayList<clientHandler> clients = new ArrayList<>();

    public clientHandler(Socket socket) throws Exception {
        this.socket = socket;
        this.setInp();
        this.setOut();
        addClient(this);
    }

    private void getIdofSalon(BufferedReader bufferedReader) throws Exception {
        String line;
        while ((line = bufferedReader.readLine()) != null && !line.isEmpty()) {
            try {
                id = Integer.parseInt(line.trim());
                readfile();
                break;
            } catch (NumberFormatException e) {
            }
        }
    }

    private void readfile() throws Exception {
        FileInputStream file = new FileInputStream("salon" + id + ".txt");
        int c;
        try {
            while ((c = file.read()) != -1) {
                out.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.close();
        }
    }

    private synchronized void addClient(clientHandler client) {
        clients.add(client);
    }

    private synchronized void removeClient(clientHandler client) {
        clients.remove(client);
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inp))) {
            try {
                getIdofSalon(bufferedReader);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int c;
            while ((c = inp.read()) != -1) {
                SendMessage(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private synchronized void SendMessage(int c) {
        Iterator<clientHandler> iterator = clients.iterator();
        while (iterator.hasNext()) {
            clientHandler client = iterator.next();
            if (client != this && client.id == this.id) {
                try {
                    client.out.write(c);
                } catch (IOException e) {
                    iterator.remove(); 
                }
            }
        }
    }

    public void setOut() {
        try {
            this.out = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInp() {
        try {
            this.inp = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeAll() {
        removeClient(this);
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (inp != null) {
            try {
                inp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
