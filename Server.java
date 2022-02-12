import java.net.*;
import java.io.*;
import java.util.*;

public class Server
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private BufferedReader  in       = null;
    private BufferedWriter  out      = null;
 
    // constructor with port
    public Server(int port)
    {
        Scanner s = new Scanner(System.in);
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");
 
            System.out.println("Waiting for a client ...");
 
            socket = server.accept();
            System.out.println("Client accepted");
 
            // takes input from the client socket
            in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

 
            // reads message from client until "Over" is sent
            while (true)
            {
                String msg = in.readLine();
    
                out.write("MSG Recieved");
                out.newLine();
                out.flush();

                System.out.println("Client: "+ msg);
                
                if(msg.equalsIgnoreCase("Exit")) break;
                
            }
            System.out.println("Closing connection");
 
            // close connection
            socket.close();
            in.close();
            s.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
 
    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}