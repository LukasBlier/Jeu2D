package View;


import Model.EntityPlayer;
import Model.Pic;
import Model.Tuyau;

import javax.swing.JPanel;

// import com.sun.tools.sjavac.server.SysInfo;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;

public class PanelGame extends JPanel {
	private ImageIcon iconFond;
	private Image imageFond;
	private Image imageFond2;
	
	private ImageIcon iconPanneau;
	private Image imagePanneau;
	
	private ImageIcon iconTuyau;
	private Image imageTuyau;
	
	private int xFond;// variable : abscisse sup�rieur gauche de l'image de fond par rapport � l'ecran
	private int xFond2;
	private static int dx; // variable permettant de deplacer l'ecran (decors) horizontalement
	private static int xPos; //variable qui va rep�rer les �l�ments du jeu moins leur position en x = position absolu dans le jeu
	private static int ySol; // hauteur du sol
	private static int hauteurPlafond; // hauteur du plafond du jeu
	private  static int  longueur;
	private  static int hauteur;
	

	public Pic pic1;
	public static EntityPlayer player; //joueur
	
	
	public PanelGame() {
		super();
		//Dimension tailleMoniteur = Toolkit.getDefaultToolkit().getScreenSize();
		PanelGame.longueur = 1420; //longueur de l'�cran de l'ordinateur
		PanelGame.hauteur = 800 ; //hauteur de l'�cran d'ordinateur
		this.xFond = 0; //pour que l'image de fond d�borde de chaque c�t� de l'�cran
		this.xFond2 = 1420;
		
		this.dx = 0;
		this.xPos = -1;
		this.ySol = 690;
		this.hauteurPlafond = 0;
		iconFond = new ImageIcon(getClass().getResource("/images/fond.png"));
		this.imageFond = this.iconFond.getImage();
		this.imageFond2 = this.iconFond.getImage();
		
		iconPanneau = new ImageIcon(getClass().getResource("/images/panneau_fleche.png"));
		this.imagePanneau = this.iconPanneau.getImage();
		
		pic1 = new Pic(1200,620 , 117 ,63);
		
		player = new EntityPlayer(300, 480);
		
		//Audio.playSound("/images/musique.mp3");
		
		this.setFocusable(true); //mettre le focus pour �couter l'�cran
		this.requestFocusInWindow(); //pour �tre sur de r�cup�rer le focus
		this.addKeyListener(new Input()); //on ajoute � la scene les �v�nements clavier avec la classe input 
		
		Thread DisplayScreen = new Thread(new Display()); //on creer un thread sur l affichage de l'�cran de d�cor
		DisplayScreen.start(); //on lance le thread d'affichage de l'�cran
	}
	/*getters and setters*/
	
	
	public int getxFond() {
		return xFond;
	}

	public static  int getLongueur() {
		return longueur;
	}



	public void setxFond(int xFond) {
		this.xFond = xFond;
	}

	public int getxFond2() {
		return xFond2;
	}

	public void setxFond2(int xFond2) {
		this.xFond2 = xFond2;
	}
	
	public static int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public static int getDx() {return dx;}

	public void setDx(int dx) {this.dx = dx;}
	
	public static int getySol() {return ySol;}


	public void setySol(int ySol) {this.ySol = ySol;}


	public static int getHauteurPlafond() {return hauteurPlafond;}


	public void setHauteurPlafond(int hauteurPlafond) {this.hauteurPlafond = hauteurPlafond;}

	//***Methodes***//
	
	public void moveScreen() { //met � jour la position du fond d'�cran
		
		if(this.xPos >= 0) {
			this.xPos = this.xPos + this.dx;
		
			this.xFond = this.xFond - this.dx;
			this.xFond2 = this.xFond2 - this.dx;
		} //si xpos <0 LE PERSONNAGE ne peut pas bouger, il est bloquer par le panneau, il doit aller � droite
		
		///lorsque le personnage se deplace � droite
		if(this.xFond <= -1420) { //si le 1er fond est pass� il va � la suite du 2e fond
			this.xFond = 1420;
		}
		else if(this.xFond2 <= -1420) { //si le 2e fond est pass� on le met � la suite du 1er fond
			this.xFond2 = 1420;
		}
		///de m�me lorsque le personnage se deplace � gauche
		else if(this.xFond >= 1420) { 
			this.xFond = -1420;
		}
		else if(this.xFond2 >= 1420) { 
			this.xFond2 = -1420;
		}
	}
	

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics g2 = (Graphics2D)g; //am�liore les graphismes
		
		//this.moveScreen(); //on appel la fonction pour deplacer l'�cran � chaque boucle du run du thread
		if(this.player.contactAvant(pic1) == true) {
			this.player.setWalk(false);
			this.dx = 0;
		}
		this.moveScreen(); //on appel la fonction pour deplacer l'�cran � chaque boucle du run du thread
		this.pic1.mouvement();
		
		//System.out.println("x perso : " + this.player.getX());
		//System.out.println("x pic : " + this.pic1.getX());
		g2.drawImage(this.imageFond, this.xFond,0,null); //dessin image du fond
		g2.drawImage(this.imageFond2, this.xFond2,0,null);
		
		g2.drawImage(this.imagePanneau, 300-this.xPos,280,null);//on fixe le panneau au m�me endroit
		g2.drawImage(this.pic1.getImagePic(),this.pic1.getX(), this.pic1.getY(),this.pic1.getWidth(),this.pic1.getHeight(),null);
		//utilisation de longueur et hauteur de l'�cran pour bien placer le personnage quelque soit la taille de l'�cran de l'ordinateur
		if(this.player.isSaut() == true) {g2.drawImage(this.player.saute(),this.player.getX(),this.player.getY(),this.player.getWidth(),this.player.getHeight(),null);}
		else {g2.drawImage(this.player.walk("perso",5),this.player.getX(), this.player.getY(),this.player.getWidth(),this.player.getHeight(),null);}
		
		g2.drawRect(this.player.getX(),this.player.getY(),this.player.getWidth(),this.player.getHeight()); // drawRect(x-position, y-position, width, height)
		g2.drawRect(this.pic1.getX(),this.pic1.getY(),this.pic1.getWidth(),this.pic1.getHeight());
	}
}
