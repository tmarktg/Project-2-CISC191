import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomeFrame implements ActionListener{

	private JFrame frame;
	private JLabel label, label2;
	private JButton button, button2;
	private JPanel panel;
	
	
	public WelcomeFrame() {
		
	frame = new JFrame();
	
	label = new JLabel("Welcome to M&M Casino!");
	label2 = new JLabel("Pick game");
	label.setFont(new Font("Serif", Font.PLAIN, 28));
	
	button = new JButton("Blackjack");
	button2 = new JButton("Craps");
	button2.setPreferredSize(new Dimension(40,40));
	
	button.addActionListener(this);
	button2.addActionListener(this);
	
	panel = new JPanel();
	panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 80, 100));
	panel.setLayout(new GridLayout(0, 1));
	panel.add(label);
	panel.add(label2);
	panel.add(button);
	panel.add(button2);
	
	frame.add(panel, BorderLayout.CENTER);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setTitle("My GUI");
	frame.pack();
	frame.setVisible(true);
	
	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String buttonPress="";
		
		if(e.getSource()==button) {
			buttonPress = "Blackjack";
		}
		
		if(e.getSource()==button2) {
			buttonPress = "Craps";
		}
		frame.dispose();
		
		new CreditFrame(buttonPress);
		
		
	}
		
		
	
}