// Demo.java
package EarthSim;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Demo extends JFrame {
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.processArgs(args);
		demo.run();
	}

	public Demo(){}

	private String[] args;

	private boolean ownSimThread = false,ownPresThread = false;

	public enum Initiative { MAIN_THREAD, PRES_THREAD, SIM_THREAD };
	private Initiative initiative = Initiative.MAIN_THREAD;
	private boolean rset = false, tset = false;

	private long bufferSize = 1;
	private int bufPos = -1;
	private boolean bset = false;

	//Note: processArgs ignore args that are not s,p,r,t or b as long as you provide a max of 5 input values.
	public void processArgs(String[] args){
		if(args.length > 5)
			usage();

		for(int i=0;i<args.length;i++){
			String arg = args[i];

			if("-s".equalsIgnoreCase(arg)){
				ownSimThread = true;
			}
			if("-p".equalsIgnoreCase(arg)){
				ownPresThread = true;
			}
			if("-r".equalsIgnoreCase(arg)){
				rset = true;
			}
			if("-t".equalsIgnoreCase(arg)){
				tset = true;
			}
			if("-b".equalsIgnoreCase(arg)){
				bset = true;
				bufPos = i;
			}
		}
		if(rset && tset){
			System.out.println("Cannot set both -r and -t.");
			usage();
		}
		initiative = rset ? Initiative.PRES_THREAD : Initiative.MAIN_THREAD;
		initiative = tset ? Initiative.SIM_THREAD : Initiative.MAIN_THREAD;

		if(bset){
			if(bufPos == -1 || (bufPos+1) >= args.length){
				System.out.println("-b needs a value.");
				usage();
			}
			String bufSizeString = args[bufPos+1];
			try{
				bufferSize = Integer.parseInt(bufSizeString);
			}catch(NumberFormatException nfe){
				System.out.println("Error reading -b value as an integer. Please retry.");
				usage();
			}
		}
	}

	public void usage(){
		System.out.println("Usage: java EarthSim.Demo [-s] [-p] [-r|-t] [-b #]");
		System.exit(-1);
	}

	public void run(){
		debug("Demo started with settings:");
		printSettings();
		createAndShowUI();
		debug("Demo running...");
	}

	private void printSettings(){
		debug("Simulation on own thread\t:" + ownSimThread);
		debug("Presentation on own thread\t:" + ownPresThread);
		debug("Initiative\t\t\t:" + initiative);
		debug("Buffer Size\t\t\t:" + bufferSize);
		debug("");
	}

	private void createAndShowUI(){
		setupWindow();
		add(contentsPanel());
		pack();
		setVisible(true);
	}

	private void setupWindow(){
		// setup overall app ui
		setTitle("Heated Plate Diffusion Simulation");
		setSize(300, 200);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JPanel contentsPanel(){
		return new JPanel();
	}

	private void debug(String s){
		System.out.println(s);
	}
}
