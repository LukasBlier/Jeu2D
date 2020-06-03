package View;
import javax.swing.JFrame;

import Model.windowVariables;
public class FrameGame extends JFrame {
	windowVariables dataBase;
	
	public FrameGame() {
		super();
		this.setTitle("Platformer Project"); //titre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de pouvoir fermer la fen�tre
		//this.setSize(800, 575); //taille
		this.setLocationRelativeTo(null); //centrer � l'�cran
		this.setExtendedState(this.MAXIMIZED_BOTH);// taille max
		this.setResizable(false); // interdit de la redimensionner
		this.setAlwaysOnTop(true); // fen�tre devant les autres
		
		
	}
	
	public void setDataBase(windowVariables dataBase) {
		this.dataBase = dataBase;
	}
}
