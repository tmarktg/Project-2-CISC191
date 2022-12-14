import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.util.InputMismatchException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BetFrame implements ActionListener{
	
	private static int blackjackPoints, crapsPoints;
	private JFrame frame2;
	private JTextField textfield2;
	private JLabel label, label2;
	private String bp;
	private static int betValue, credit;
	PrintWriter pw;
	
	public BetFrame(String buttonPress, int currentCre, PrintWriter printw) {
		
		this.pw = printw;
		this.bp = buttonPress;
		this.credit = currentCre;
		
		frame2 = new JFrame();
		
		if (bp == "Blackjack") 
		{
		Blackjack card = new Blackjack();
		label = new JLabel("You draw " + card.getDeck());
		blackjackPoints = card.startScore();
		}
		
		if (bp == "Craps") 
		{
		Craps dice = new Craps();
		label = new JLabel("Enter bet to see roll value");
		crapsPoints = dice.rollValue();
		}
		
		label.setFont(new Font("Serif", Font.PLAIN, 28));
		label2= new JLabel("Enter bet:");
		
		textfield2 = new JTextField();
		
		JButton button2 = new JButton("Enter");
		button2.setPreferredSize(new Dimension(40,40));
		
		button2.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 80, 100));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(label2);
		panel.add(textfield2);
		panel.add(button2);
		
		frame2.add(panel, BorderLayout.CENTER);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setTitle("My GUI");
		frame2.pack();
		frame2.setVisible(true);

		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			String strTextField = textfield2.getText();
			betValue = Integer.parseInt(strTextField);
		}
		catch (NumberFormatException nfe) {
			new BetFrame(bp, credit, pw);
		}
		catch (InputMismatchException e1) {
			new BetFrame(bp, credit, pw);
		} 
		
		if(betValue <= 0 || betValue > credit) 
		{
			new ErrorFrame(bp, credit, pw);
		}
		if(betValue <= credit && bp == "Blackjack") 
		{
			new BlackjackFrame(bp, blackjackPoints, credit, pw);
		}
		if(betValue <= credit && bp == "Craps") 
		{	
			Craps craps = new Craps();
			if (craps.rule(crapsPoints) == "You win" || craps.rule(crapsPoints) == "You lost")
			{
				new ResultFrame(bp, crapsPoints, credit, pw);
			}
			if (craps.rule(crapsPoints) == "reroll")
			{
				new CrapsFrame(bp, crapsPoints, credit, pw);
			}
		}
		
		frame2.dispose();
		
	}
	
	public static int getBlackJackScore() {
		return blackjackPoints;
	}
	
	public static int getCrapsScore() {
		return crapsPoints;
	}
	public static int getBet()
	{
		return betValue;
	}

}