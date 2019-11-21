package src.p1;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Changes made:
 * <ul>
 * <li>None so far, this is basically all Java code.</li>
 * </ul>
 *
 *	@author KMP, Eric Schneider
 */
public class ExampleGame {
	public static void main(String[] args){
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Example");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);

		ExampleWidget ttt = new ExampleWidget();
		top_panel.add(ttt, BorderLayout.CENTER);

		main_frame.pack();
		main_frame.setVisible(true);		
	}
}
