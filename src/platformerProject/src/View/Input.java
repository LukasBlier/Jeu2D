package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.windowVariables;
import Controller.Main;

public class Input implements KeyListener{
	
	public void keyPressed(KeyEvent arg0)
	{
		if(arg0.getKeyCode() == KeyEvent.VK_D) {
			if(Main.scene.getxPos()<0) {
				Main.scene.setxPos(0);
				Main.scene.setxFond(0);
				Main.scene.setxFond2(1420);
				
			}
			Main.scene.player.setWalk(true);
			Main.scene.player.setDirectionRight(true);
			Main.scene.setDx(4);         //si on appui sur D on deplace l'�cran vers la gauche
		}
		else if (arg0.getKeyCode() == KeyEvent.VK_Q){
			Main.scene.player.setWalk(true);
			Main.scene.player.setDirectionRight(false);
			Main.scene.setDx(-4);		//si on appui sur D on deplace l'�cran vers la droite
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			View.PanelGame.player.setSaut(true);
		}
	}
	 
	public void keyReleased(KeyEvent arg0)
	{	
		Main.scene.player.setWalk(false);
	 	Main.scene.setDx(0);  //d�s que la touche est relach� Dx vaut 0 et le personnage ne bouge plus (la sc�ne reste fixe) 
	}
	 
	public void keyTyped(KeyEvent arg0) {}
}
