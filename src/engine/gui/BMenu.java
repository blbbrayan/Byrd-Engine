package engine.gui;
import javax.swing.JFrame;


public class BMenu extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public BMenu(int width,int height){
		this.setSize(width, height);
		basicSetup();
	}
	
	private void basicSetup(){
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
	}
}
