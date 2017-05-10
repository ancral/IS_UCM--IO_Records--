package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class TiendaView extends JFrame {

	private static final long serialVersionUID = 5963169495489228054L;
	
	public TiendaView() {
		super("I/O Records > Catálogo");
		initGUI();
	}
	
	public void initGUI() {
		JPanel main = new JPanel();
		this.setContentPane(main);
		
		BorderLayout mainLayout = new BorderLayout();
		main.setLayout(mainLayout);
		
		/* -----------------------------
		 * Toolbar de la ventana
		 * ----------------------------- */
		JToolBar toolbar = new JToolBar();
		main.add(toolbar, BorderLayout.NORTH);
		
		JPanel toolBarPanel = new JPanel();
		FlowLayout toolBarLayout = new FlowLayout();
		toolBarPanel.setLayout(toolBarLayout);
		toolbar.add(toolBarPanel);
		
		
		this.pack();
		this.setLocationRelativeTo(null); // centra la ventana
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
