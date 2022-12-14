import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreditFrame implements ActionListener{
	
	
	private JFrame frame1;
	private JTextField textfield;
	private String bp;
	private static int creditValue;
	PrintWriter pw;
	
	public CreditFrame(String buttonPress) {
		
		this.bp = buttonPress;
		
		frame1 = new JFrame();
		
		JLabel label = new JLabel("Enter a valid starting credit");
		JLabel label2 = new JLabel(buttonPress);
		
		label.setFont(new Font("Serif", Font.PLAIN, 28));
		
		textfield = new JTextField();
		
				
		JButton button2 = new JButton("Enter");
		button2.setPreferredSize(new Dimension(40,40));
		
		button2.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 80, 100));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(label);
		panel.add(label2);
		panel.add(textfield);
		panel.add(button2);
		
		
		frame1.add(panel, BorderLayout.CENTER);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setTitle("My GUI");
		frame1.pack();
		frame1.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			String strTextField = textfield.getText();
			creditValue = Integer.parseInt(strTextField);
		}
		catch (NumberFormatException nfe) {
			new CreditFrame(bp);
		}
		catch (InputMismatchException e1) {
			new CreditFrame(bp);
		} 
			try
			{
				pw = new PrintWriter("receipts.txt");
				pw.println("You entered the Casino with $" + creditValue);
				pw.println("");
			}
			catch (FileNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
		
		if(creditValue <= 0) 
		{
			new CreditFrame(bp);
		}
		if(creditValue > 0) 
		{
			new BetFrame(bp,creditValue, pw);
		}
		
		frame1.dispose();
		
	}
	
	public static int getCredit() {
		return creditValue;
	}
	
	
}