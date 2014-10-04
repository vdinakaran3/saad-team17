// GUI.java
package EarthSim;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import java.util.HashMap;

public class GUI extends JFrame implements ActionListener{

	public GUI(){
		setupWindow();
		add(contentsPanel());
		pack();
	}

	private void setupWindow(){
		// setup overall app ui
		setTitle("Heated Earth Diffusion Simulation");
		// setSize(300, 200);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JPanel contents;

	private JPanel contentsPanel() {
		// setup primary window contents panel
		JPanel contents = new JPanel(new BorderLayout());
		contents.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
		contents.setAlignmentY(Component.TOP_ALIGNMENT);

		contents.add(settingsNControls(), BorderLayout.WEST);
		contents.add(presentation(),BorderLayout.CENTER);
		contents.add(feedback(),BorderLayout.SOUTH);

		this.contents = contents;
		return contents;
	}

	private JPanel settingsNControls(){
		JPanel sncPanel = new JPanel();
		sncPanel.setLayout(new BoxLayout(sncPanel,BoxLayout.PAGE_AXIS));
		sncPanel.setAlignmentY(Component.TOP_ALIGNMENT);

		sncPanel.add(settings(), BorderLayout.WEST);
		sncPanel.add(runControls(),BorderLayout.WEST);

		return sncPanel;
	}

	private JPanel settings() {
		JPanel settingsPanel = new JPanel();
		settingsPanel.setBorder(BorderFactory.createTitledBorder("Settings"));
		settingsPanel.setLayout(new BoxLayout(settingsPanel,BoxLayout.PAGE_AXIS));
		settingsPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		// settingsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		// settingsPanel.add(prompt("Set initial conditions"));
		settingsPanel.add(inputField("Grid Spacing"));
		settingsPanel.add(inputField("Simulation Time Step"));

		return settingsPanel;
	}

	private JPanel runControls() {
		JPanel ctrlsPanel = new JPanel(new FlowLayout());
		ctrlsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		ctrlsPanel.add(button("Run"));
		ctrlsPanel.add(button("Pause"));
		ctrlsPanel.add(button("Stop"));

		return ctrlsPanel;
	}

	private JPanel presentation(){ 
		JPanel pres = new JPanel();
		pres.setBorder(BorderFactory.createLineBorder(Color.black));
		return pres;
	}

	private JPanel feedback(){
		JPanel fbPanel = new JPanel();
		fbPanel.setLayout(new BoxLayout(fbPanel,BoxLayout.PAGE_AXIS));
		fbPanel.add(displayField("Rotational Position"));
		fbPanel.add(displayField("Time"));
		fbPanel.add(displayField("Grid Spacing"));
		fbPanel.add(displayField("Simulation Time Step"));

		return fbPanel;
	}
	private HashMap<String, JTextField> inputs = new HashMap<String, JTextField>();

	private JPanel inputField(String name) {
		JPanel inputPanel = new JPanel();
		// inputPanel.setBorder(BorderFactory.createTitledBorder("blah"));
		// inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.LINE_AXIS));
		inputPanel.setLayout(new FlowLayout());
		inputPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JLabel l = new JLabel(name);
		l.setAlignmentX(Component.LEFT_ALIGNMENT);
		inputPanel.add(l);

		// inputPanel.add(Box.createHorizontalGlue());
		JTextField t = new JTextField("", 10);
		t.setAlignmentX(Component.RIGHT_ALIGNMENT);
		l.setLabelFor(t);
		inputPanel.add(t);

		inputs.put(name, t);
		return inputPanel;
	}

	private JPanel displayField(String name){
		JPanel inputPanel = new JPanel();
		// inputPanel.setBorder(BorderFactory.createTitledBorder("blah"));
		// inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.LINE_AXIS));
		inputPanel.setLayout(new FlowLayout());
		inputPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JLabel l = new JLabel(name);
		l.setAlignmentX(Component.LEFT_ALIGNMENT);
		inputPanel.add(l);

		// inputPanel.add(Box.createHorizontalGlue());
		JTextField t = new JTextField("", 10);
		t.setAlignmentX(Component.RIGHT_ALIGNMENT);
		t.setEditable(false);
		l.setLabelFor(t);
		inputPanel.add(t);

		inputs.put(name, t);
		return inputPanel;
	}

	private JButton button(String name) {
		JButton button = new JButton(name);
		button.setActionCommand(name);
		button.addActionListener(this);
		return button;
	}

	public void actionPerformed(ActionEvent e) {

	}
	
	// todo: add handler for window close

}