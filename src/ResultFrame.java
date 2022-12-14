import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultFrame implements ActionListener{
	private JFrame frame;
	private JLabel label, label2, label3, label4;
	private JButton button, button2;
	private JPanel panel;
	private String bp;
	private int oppscore;
	
	
	private static int bet = BetFrame.getBet();
	private static int credit, score;
	PrintWriter pw;
	
	public ResultFrame(String buttonPress, int score, int scoreCre, PrintWriter printw) {
		
		
		
		this.pw = printw;
		this.bp = buttonPress;
		this.score = score;
		this.credit = scoreCre;
		
		frame = new JFrame();
		Blackjack card = new Blackjack();
		Craps dice = new Craps();
		Money cred = new Money();
		
		
		
		if (bp == "Blackjack") {
			score = BlackjackFrame.getFinal();
			oppscore = BlackjackFrame.getOppFinal();
			label = new JLabel(card.rule(score, oppscore) + " " + bet + " credits");
			if (card.rule(score, oppscore) == "You lost")
			{
				credit = credit - bet;
			}
			if (card.rule(score, oppscore) == "You win")
			{	
				credit = credit + bet; 
			}
			label2 = new JLabel("Your Score: " + score + " Computer Score: "  + oppscore);
		}
		
		if (bp == "Craps") {
			score = CrapsFrame.getFinal();
			
			if (dice.rule(score) == "reroll") 
			{
				label = new JLabel("You did not win nor lose");
			}
			
			if (dice.rule(score) == "You lost")
			{	
				label = new JLabel("You lost " + bet + " credits");
				credit = credit - bet;
			}
			if (dice.rule(score) == "You win")
			{
				label = new JLabel("You win " + bet + " credits");
				credit = credit + bet;
			}
			
			label2 = new JLabel("Your final roll: " + score);
		}
		
		label.setFont(new Font("Serif", Font.PLAIN, 28));
		label3 = new JLabel("You currently have " + credit + " credits");
		label4 = new JLabel("Replay or Exit game?");
		
		button = new JButton("Replay");
		button2 = new JButton("Exit");
		button2.setPreferredSize(new Dimension(40,40));
		
		button.addActionListener(this);
		button2.addActionListener(this);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 80, 100));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(label);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(button);
		panel.add(button2);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("My GUI");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static int getBet() {
		return bet * 7;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String a = label.getText();
		
		pw.println(a + " in " + bp);
		pw.println("");
		if(credit == 0) 
		{
			frame.dispose();
			new ExitFrame(credit, pw);
		}
		
		else if(e.getSource()== button) 
		{
			frame.dispose();
			new BetFrame(bp, credit, pw);
		}
		else if(e.getSource()== button2) 
		{
			frame.dispose();
			new SwapFrame(bp, credit, pw);
		}
		
		
	}
	
	
	
}


