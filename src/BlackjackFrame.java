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

public class BlackjackFrame extends Blackjack implements ActionListener{

	private JFrame frame;
	private JLabel label, label2;
	private JButton button, button2;
	
	private JPanel panel;
	private String bp;
	private static int total, oppTotal, credit;
	
	private static Blackjack card = new Blackjack();
	PrintWriter pw;
	
	public BlackjackFrame(String buttonPress, int totalS, int currentCre, PrintWriter printw) {
		this.pw = printw;
		this.bp = buttonPress; 
		this.total = totalS;
		this.credit = currentCre;
		
		frame = new JFrame();
		
		label = new JLabel("Your current point is " + total);
		label.setFont(new Font("Serif", Font.PLAIN, 28));
		label2 = new JLabel("Deal Cards");
		
	
		button = new JButton("yes");
		button2 = new JButton("no");
		button.setPreferredSize(new Dimension(40,40));
		button2.setPreferredSize(new Dimension(40,40));
		
		button.addActionListener(this);
		button2.addActionListener(this);
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,80,100));
		panel.setLayout(new GridLayout(0,1));
		panel.add(label);
		panel.add(label2);
		panel.add(button);
		panel.add(button2);
		
		
		frame.add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("My GUI");
		frame.pack();
		
		frame.setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == button) {
			getExtra();
			total = total + addScore();
			if (total > 21) 
			{
				frame.dispose();
				new ResultFrame(bp, total, credit, pw);
			}
			if (total <= 21) 
			{
			frame.dispose();
			new BlackjackFrame(bp, total, credit, pw);
			}
		}
		if(e.getSource() == button2) {
			frame.dispose();
			new ResultFrame(bp, total, credit, pw);
		}
		
	}
	
	public static int getFinal() {
		return total;
	}
	
	public static int getOppFinal() {
		
		card.getDeck();
		oppTotal = card.startScore();
		do
		{
			card.getExtra();
			oppTotal += card.addScore();
		} while (oppTotal <= total);
		
		return oppTotal;
	}
}


