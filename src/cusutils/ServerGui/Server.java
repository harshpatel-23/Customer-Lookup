package cusutils.ServerGui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cusutils.clientgui.CusQueryPanel;
import cusutils.clientgui.CustomerQueryGui;
import cusutils.cusData.Customer;
import cusutils.cusData.Name;
import cusutils.cusData.PhoneNum;
import cusutils.cusDb.CustomerDb;

public class Server extends Thread {

	protected int serverPort = 8000;
	protected static ServerSocket serverSocket = null;
	protected Thread runningThread = null;
	protected ExecutorService threadPool = Executors.newFixedThreadPool(100);
	Socket socket;

	public static Map<PhoneNum, Customer> phone_search;
	public static Map<Name, List<Customer>> name_search;

	public Server(Socket socket) {
		this.socket = socket;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame topFrame;
		CusQueryPanel client = new CusQueryPanel(phone_search, name_search);

		topFrame = new JFrame();
		topFrame.setSize(600, 250);
		topFrame.setTitle("Customer Lookup");
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topFrame.add(client);
		topFrame.setVisible(true);

		try {
			serverSocket = new ServerSocket(8000);
			System.out.println("Listening....");
			while (true) {
				Socket scket = serverSocket.accept();
				System.out.println("Connected....");
				new Thread(new Server(scket)).start();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void run() {
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}

		try {
			int numCus, i;
			Customer cus;
			CustomerDb.initialize();
			phone_search = CustomerDb.readPhoneDir();
			name_search = CustomerDb.readNameDir();
			System.out.println("Thread" + Thread.currentThread().getName() + "" + socket.getOutputStream());
		} catch (Exception e) {
			System.out.println("Thread " + Thread.currentThread().getName() + "is finished");
		}

		while (true) {
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
				System.out.println("Accepted connection : " + clientSocket);
			} catch (IOException e) {
				if (isStopped()) {
					System.out.println("Server Stopped");
					break;
				}
			}
		}
		this.threadPool.shutdown();
		JOptionPane.showConfirmDialog(null, "Server Stopped");
	}

	private synchronized boolean isStopped() {
		// TODO Auto-generated method stub
		return false;
	}

	private void openServerSocket() {
		// TODO Auto-generated method stub
		try {
			this.serverSocket = new ServerSocket(this.serverPort);
			JOptionPane.showConfirmDialog(null, "Server Started...");
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, e);
		}
	}

}
