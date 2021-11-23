import java.awt.*;
import java.awt.Dialog.ModalityType;

import javax.swing.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.*;

public class secondaryGui extends JFrame implements ActionListener {
	ArrayList<JButton> buttons;

	public static team main(ArrayList<team> teams) {
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		JFrame f = new JFrame("Frame");

		JPanel p = new JPanel();

		f.add(p);
		f.setSize(400, 400);
		JDialog d = new JDialog(f, "Dialog Box");
		Container pane = d.getContentPane();
		JButton b;
		for (int i = 0; i < teams.size(); i++) {
			b = new JButton(teams.get(i).name);
			pane.add(b, i);

			System.out.println("We Ran");

		}
		d.setAlwaysOnTop(true);
		d.setContentPane(pane);
		d.setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
		d.setSize(500, 500);
		d.setVisible(true);
		// d.getComponentListeners();
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		JComponent buttonPressed = (JComponent) e.getSource();
		JButton buttoner = (JButton) e.getSource();
		// System.out.println(buttoner.getActionCommand());
		cache.main(buttoner.getActionCommand());
		gui.newWindow = true;
		this.dispose();
	}
}
