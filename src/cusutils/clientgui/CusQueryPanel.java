package cusutils.clientgui;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.net.Socket;
import java.util.*;
import java.util.List;

import cusutils.cusData.*;

public class CusQueryPanel extends JPanel implements ActionListener {
	private JLabel lastNameLbl = new JLabel("Last Name: ");
	private JTextField lastNameTxtField = new JTextField(20);

	private JLabel firstNameLbl = new JLabel("First Name: ");
	private JTextField firstNameTxtField = new JTextField(10);

	private JLabel phoneNumLbl = new JLabel("Phone:  (");
	private JTextField areaCodeTxtField = new JTextField(4);
	private JLabel endAreaCodeLbl = new JLabel(")");
	private JTextField localNumTxtField = new JTextField(10);

	private JButton nameSearchBtn = new JButton("Name Search");
	private JButton phoneSearchBtn = new JButton("Phone Search");

	private JPanel namePanel = new JPanel();
	private JPanel phonePanel = new JPanel();
	private JPanel btnPanel = new JPanel();

	private Map<PhoneNum, Customer> phone_search;
	private Map<Name, List<Customer>> name_search;

	public CusQueryPanel(Map<PhoneNum, Customer> phone_search, Map<Name, List<Customer>> name_search) {
		this.phone_search = phone_search;
		this.name_search = name_search;

		setLayout(new GridLayout(3, 1));

		namePanel.add(lastNameLbl);
		namePanel.add(lastNameTxtField);
		namePanel.add(firstNameLbl);
		namePanel.add(firstNameTxtField);
		add(namePanel);

		phonePanel.add(phoneNumLbl);
		phonePanel.add(areaCodeTxtField);
		phonePanel.add(endAreaCodeLbl);
		phonePanel.add(localNumTxtField);
		add(phonePanel);

		phoneSearchBtn.addActionListener(this);
		nameSearchBtn.addActionListener(this);
		btnPanel.add(nameSearchBtn);
		btnPanel.add(phoneSearchBtn);
		add(btnPanel);
	}

	private void printNameMatch() {
		Name cusName;
		Customer cus;
		String lastName, firstName;
		int i, numCus;
		boolean matchFound = false;

		lastName = lastNameTxtField.getText();
		firstName = firstNameTxtField.getText();
		cusName = new Name(lastName, firstName);

		Socket client = null;
		Name nm;
		String fullName;
		fullName = lastNameTxtField.getText() + " " + firstNameTxtField.getText();

		lastName = lastNameTxtField.getText();
		firstName = firstNameTxtField.getText();
		Name cusName1 = new Name(lastName, firstName);

		try {
			client = new Socket("localhost", 8000);
			JOptionPane.showConfirmDialog(null, "Socket and Threading Started");
			System.out.println("Socket and Threading Started");
			System.out.println("-----------------------------");

			if (name_search.containsKey(cusName1)) {
				List<Customer> lst = name_search.get(cusName1);
				System.out.println("\n***** Matches Found For: " + cusName1 + " *****");
				for (Customer list : lst) {
					System.out.println(list);

				}
				matchFound = true;
			}

			if (matchFound) {
				System.out.println("***** End of Match Query *****\n");
			} else {
				System.out.println("No match found for: " + cusName1);
			}

			lastNameTxtField.setText("");
			firstNameTxtField.setText("");
			System.out.println("-----------------------------");
			System.out.println("Socket and Threading Closed");
			JOptionPane.showConfirmDialog(null, "Socket and Threading Closed");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void printPhoneMatch() {
		Customer cus;
		PhoneNum phone;
		String areaCodeStr, localNum;
		int i, numCus, areaCode;
		boolean matchFound = false;

		areaCodeStr = areaCodeTxtField.getText();
		areaCode = Integer.parseInt(areaCodeStr);
		localNum = localNumTxtField.getText();
		phone = new PhoneNum(areaCode, localNum);

		Socket addSocket = null;

		String localnum = areaCodeTxtField.getText() + localNumTxtField.getText();

		try {
			addSocket = new Socket("localhost", 8000);
			JOptionPane.showConfirmDialog(null, "Socket and Threading Started");
			System.out.println("Socket and Threading Started");
			System.out.println("-----------------------------");

			numCus = phone_search.size();

			if (phone_search.containsKey(phone)) {
				System.out.println("Match Found: " + phone_search.get(phone));
				matchFound = true;
			}

			if (!matchFound) {
				System.out.println("No customer found with phone number: " + phone);
			} else {
				areaCodeTxtField.setText("");
				localNumTxtField.setText("");
			}

			addSocket.close();
			System.out.println("-----------------------------");
			System.out.println("Socket and Threading Closed");
			JOptionPane.showConfirmDialog(null, "Socket and Threading Closed");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void actionPerformed(ActionEvent evt) {
		JButton button;
		button = (JButton) evt.getSource();
		if (button == phoneSearchBtn) {
			printPhoneMatch();
		} else if (button == nameSearchBtn) {
			printNameMatch();
		}
	}
}