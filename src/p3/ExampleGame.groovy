package src.p3

import java.awt.BorderLayout

import javax.swing.JFrame
import javax.swing.JPanel

/**
 * Changes made:
 * <ul>
 * <li>Use GStrings.</li>
 * <li>Switch to properties.</li>
 * <li>Remove unnecessarily paramtheses</li>
 * <li>General cleanup.</li>
 * </ul>
 * 
 * @author KMP, Eric Schneider
 *
 */
class ExampleGame {
	static void main(String[] args){
		def main_frame = new JFrame()
		main_frame.setTitle "Example"
		main_frame.setDefaultCloseOperation JFrame.EXIT_ON_CLOSE

		def top_panel = new JPanel()
		top_panel.setLayout new BorderLayout()
		main_frame.setContentPane top_panel

		top_panel.add new ExampleWidget(), BorderLayout.CENTER

		main_frame.pack()
		main_frame.setVisible true
	}
}
