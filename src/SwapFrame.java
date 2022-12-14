import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwapFrame implements ActionListener{

	private JFrame frame;
	private JLabel label;
	private JButton button, button2;
	private JPanel panel;
	private String bp;
	private int credit;
	PrintWriter pw;
	
	public SwapFrame(String buttonPress, int currentCre, PrintWriter printw) {
	this.pw = printw;
	this.bp = buttonPress;	
	this.credit = currentCre;
		
	frame = new JFrame();
	
	label = new JLabel("Pick your option");

	label.setFont(new Font("Serif", Font.PLAIN, 28));
	
	if (bp == "Blackjack")
	{
		this.bp = "Craps";
		button = new JButton("Play Craps");
	}
	else if (bp == "Craps")
	{
		this.bp = "Blackjack"; 
		button = new JButton("Play Blackjack");
	}
	button2 = new JButton("Exit Casino");
	button2.setPreferredSize(new Dimension(40,40));
	
	button.addActionListener(this);
	button2.addActionListener(this);
	
	panel = new JPanel();
	panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 80, 100));
	panel.setLayout(new GridLayout(0, 1));
	panel.add(label);
	panel.add(button);
	panel.add(button2);
	
	frame.add(panel, BorderLayout.CENTER);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setTitle("My GUI");
	frame.pack();
	frame.setVisible(true);
	
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
			
		if(e.getSource()== button) {
			new BetFrame(bp, credit, pw);
		} 
		
		if(e.getSource()== button2) {
			
			new ExitFrame(credit, pw);
		}
		
		frame.dispose();
	}
}