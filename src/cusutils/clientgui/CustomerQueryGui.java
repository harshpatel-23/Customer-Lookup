package cusutils.clientgui;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import cusutils.cusData.*;
import cusutils.cusDb.*; // for testing purposes only

public class CustomerQueryGui {
	static Map<PhoneNum, Customer> phone_search;
	static Map<Name, List<Customer>> name_search;

	public static void main(String[] args) {
		JFrame topFrame;
		CusQueryPanel queryPanel;

		/* Instantiate the Collections */
		phone_search = new HashMap<PhoneNum, Customer>();
		name_search = new HashMap<Name, List<Customer>>();

		/* Read in and build the data structure */
		buildDataStructure();
		queryPanel = new CusQueryPanel(phone_search, name_search);

		/* Set up the JFrame */
		topFrame = new JFrame();
		topFrame.setSize(600, 200);
		topFrame.setTitle("Customer Lookup");
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topFrame.add(queryPanel);
		topFrame.setVisible(true);
	}

	private static void buildDataStructure() {
		int numCus, i;
		Customer cus;
		CustomerDb.initialize();
		phone_search = CustomerDb.readPhoneDir();
		name_search = CustomerDb.readNameDir();
	}
}