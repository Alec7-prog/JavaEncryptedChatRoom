import java.net.*;
import java.io.*;
import java.util.*; 

public class Client
{
    // initialize socket and input output streams
    private Socket               socket  = null;
    private BufferedReader       input   = null;
    private BufferedWriter       out     = null;
 
    // constructor to put ip address and port
    public Client(String address, int port)
    {
        Scanner s = new Scanner(System.in);
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
 
            // takes input from terminal
            input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // sends output to the socket
            out    = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
            while(true)
            {
                String line = s.nextLine();
    
                out.write(line);
                out.newLine();
                out.flush();

                System.out.println("Server: "+ input.readLine());
                
                if(line.equalsIgnoreCase("Exit")) break;
            }
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        finally {
        { try{
            input.close();
            out.close();
            socket.close();
            s.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        }
    }
    }
 
    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}