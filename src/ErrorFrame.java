import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ErrorFrame implements ActionListener
{
	private JFrame frame;
	private JLabel label, label2;
	private JButton button;
	private JPanel panel;
	private JTextField textfield;
	private String bp;
	private static int credit;
	PrintWriter pw;
	
	public ErrorFrame(String buttonPress, int currentCre, PrintWriter printw){
	this.pw = printw;
	this.bp = buttonPress;
	this.credit = currentCre;
	
	frame = new JFrame();
	
	label = new JLabel("Invalid Input");
	label2 = new JLabel("Agree to continue");
	label.setFont(new Font("Serif", Font.PLAIN, 28));
	
	
	button = new JButton("Accept");
	button.setPreferredSize(new Dimension(40,40));
	button.addActionListener(this);
	
	panel = new JPanel();
	panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 80, 100));
	panel.setLayout(new GridLayout(0, 1));
	panel.add(label);
	panel.add(label2);
	panel.add(button);
	
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
		
		frame.dispose();
	}

}
