package src.p4

import java.awt.BorderLayout

import javax.swing.JFrame
import javax.swing.JPanel

/**
 * Changes made:
 * <ul>
 * <li>Convert to a script (LIKE A BOSS).</li>
 * </ul>
 * 
 * @author KMP, Eric Schneider
 *
 */

def main_frame = new JFrame()
main_frame.setTitle "Example"
main_frame.setDefaultCloseOperation JFrame.EXIT_ON_CLOSE

def top_panel = new JPanel()
top_panel.setLayout new BorderLayout()
main_frame.setContentPane top_panel

top_panel.add new ExampleWidget(), BorderLayout.CENTER

main_frame.pack()
main_frame.setVisible true
