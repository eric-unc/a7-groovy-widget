package src.p2

import java.awt.BorderLayout

import javax.swing.JFrame
import javax.swing.JPanel

/**
 * Changes made:
 * <ul>
 * <li>Remove semicolonies.</li>
 * <li>Remove unnecessary <code>public</code> declarations</li>
 * <li>Switch to <code>def</code> instead of strong typing.</li>
 * <li>Some general cleanup</li>
 * </ul>
 * 
 * @author KMP, Eric Schneider
 *
 */
class ExampleGame {
	static void main(String[] args){
		def main_frame = new JFrame()
		main_frame.setTitle("Example")
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

		def top_panel = new JPanel()
		top_panel.setLayout(new BorderLayout())
		main_frame.setContentPane(top_panel)

		def ttt = new ExampleWidget()
		top_panel.add(ttt, BorderLayout.CENTER)

		main_frame.pack()
		main_frame.setVisible(true)
	}
}
