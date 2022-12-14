import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExitFrame{

	private JFrame frame;
	private JLabel label;
	private JPanel panel;
	private static int credit;
	PrintWriter pw;
	
	public ExitFrame(int currentCre, PrintWriter printw) {
	this.pw = printw;
	this.credit = currentCre;
	
	frame = new JFrame();
	
	label = new JLabel("Thank you for playing");
	pw.println("Your final credit is: $" + credit );
	pw.println("Thank you for playing");
	pw.close();
	label.setFont(new Font("Serif", Font.PLAIN, 28));
	
	panel = new JPanel();
	panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 80, 100));
	panel.setLayout(new GridLayout(0, 1));
	panel.add(label);
	
	frame.add(panel, BorderLayout.CENTER);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setTitle("My GUI");
	frame.pack();
	frame.setVisible(true);
	
	}
}