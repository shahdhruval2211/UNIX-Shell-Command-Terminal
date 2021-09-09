package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) throws Exception {
		ServerSocket server = null;

		try {

			// server is listening on port 3333
			server = new ServerSocket(3333);
			server.setReuseAddress(true);

			System.out.println("Server Started and Listening at port: " + server.getLocalPort());
			// running infinite loop for getting
			// client request
			while (true) {

				// socket object to receive incoming client
				// requests
				Socket client = server.accept();
				
				// Displaying that new client is connected
				// to server
				
				System.out.println("New client connected " + client.getInetAddress().getHostAddress() + " at Port: " + client.getPort());

				// create a new thread object
				ClientThread clientSock = new ClientThread(client);

				// This thread will handle the client
				// separately
				new Thread(clientSock).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		
	}
}
