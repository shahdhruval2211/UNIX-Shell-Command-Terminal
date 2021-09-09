package main;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import Processes.ShellProcess;
import Processes.ShellProcessImpl;
import Processes.ShellProcessImpl.OS;
import Processes.exceptions.NonMatchingOSException;
import Processes.exceptions.SProcessNotYetStartedException;

public class ClientThread implements Runnable{

	private final Socket clientSocket;
	  
    // Constructor
    public ClientThread(Socket socket)
    {
        this.clientSocket = socket;
    }
	
	@Override
	public void run() {
		PrintWriter out = null;
        BufferedReader in = null;
        try {
               
        	DataInputStream din=new DataInputStream(clientSocket.getInputStream());  
    		DataOutputStream dout=new DataOutputStream(clientSocket.getOutputStream());  
    		//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    		
    		String strCommand="";  
    		do{  
    			strCommand = din.readUTF();  
    			System.out.println("client's Command: "+strCommand);
    			
    			ShellProcessImpl execute = ShellProcessImpl.getCommandExecutor();
    			ShellProcess command = new CommandModel(strCommand, OS.UNIX);
    			
    			execute.executeCommand(command);
    			command.waitForCompletion();
    			
    			
    			/*
    			 * System.out.println("Enter Message:"); str2 = br.readLine();
    			 */  
    			System.out.println(command.getNormalOutput());
    			dout.writeUTF(command.getNormalOutput());  
    			dout.flush();  
    		}while(!strCommand.equals("quit"));
    		System.out.println("Client Disconnected " + clientSocket.getInetAddress().getHostAddress() + " at Port: " + clientSocket.getPort());
    		din.close(); 
        }
        catch (IOException | NonMatchingOSException e) {
            e.printStackTrace();
        } catch (SProcessNotYetStartedException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	

}
