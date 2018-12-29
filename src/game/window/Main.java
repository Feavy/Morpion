package game.window;

import game.mechanics.Game;
import game.mechanics.GameListener;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.transform.SourceLocator;

import java.awt.Toolkit;
import java.io.IOException;

public class Main extends JFrame {

	public static int PLAYER_WIN = 1;
	public static int COMPUTER_WIN = 2;
	public static int STANDARD = 0;
	
	public static boolean msgShowed = false;
	
	private static Main inter;
	
	private Graphics cache;
	
	private static JPanel contentPane;
	
	public static int playerShapeNumber = 10;
	public static int computerShapeNumber = 10;
	public static String player = "";
	
	private GameListener listener;

	private static List<Integer> alreadyChoose = new LinkedList<Integer>();
	
	private int y = 70;
	private int x = 70;
	
	private int size = 50;
	
	public static void showMessage(int gameOption){
		
		System.out.println("-> "+msgShowed);
		
		if(msgShowed){
			return;
		}
		
		msgShowed = true;
		
		String message;
		int messageStyle = JOptionPane.INFORMATION_MESSAGE;
		
		if(gameOption == STANDARD){
			
			message = "Match nul !\nRejouer ?";
			
		}else{
			
			message = (gameOption == PLAYER_WIN)? "Bravo, vous avez gagné !\n Rejouer ?" : "Vous avez perdu !\nRejouer ?";
			messageStyle = (gameOption == PLAYER_WIN)? JOptionPane.QUESTION_MESSAGE : JOptionPane.WARNING_MESSAGE;
			
		}
		
		if(JOptionPane.showConfirmDialog(contentPane, message, "Message", JOptionPane.YES_NO_OPTION, messageStyle) == JOptionPane.YES_OPTION){
			
			alreadyChoose.clear();
			playerShapeNumber = 10;
			computerShapeNumber = 10;
			GameListener.finished = false;
			Game.initialize();
			inter.repaint();
			
		}
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 */
	public Main() throws UnsupportedAudioFileException, IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/ressources/morpion_ico.png")));
		
		Game.initialize();
		
		listener = new GameListener();
		
		setTitle("Morpion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		//repaint();
		
		listener.setGameInterface(this);
		
		listener.setX(x);
		listener.setY(y);
		listener.setSize(size);
		
		listener.load();
		
		addMouseListener(listener);
		
		inter = this;
		
	}
	
	public boolean isChoosed(int shape){
		
		return (alreadyChoose.contains(shape)) ? false : true;
		
	}
	
	public boolean play(int playerShape, int computerShape){
		
		if(alreadyChoose.contains(playerShape)){
			
			return false;
			
		}else{
		
			playerShapeNumber = playerShape;
			if(computerShape != -1){
				computerShapeNumber = computerShape;
			}
			
			repaint();
			
			alreadyChoose.add(computerShape);
			alreadyChoose.add(playerShape);
			return true;
		
		}
		
	}
	
	public void paint(Graphics g) {
		
		try{
			
			Image shape;
			
			shape = ImageIO.read(ClassLoader.getSystemResource("ressources/Games_Cercle.png"));
			
			switch (playerShapeNumber) {
			case 1:
				System.out.println("point 1");
				g.drawImage(shape, x, y, size, size, this);
				break;
			case 2:
				g.drawImage(shape, x+size, y, size, size, this);
				break;
			case 3:
				g.drawImage(shape, x+size+size, y, size, size, this);
				break;
			case 4:
				g.drawImage(shape, x, y+size, size, size, this);
				break;
			case 5:
				g.drawImage(shape, x+size, y+size, size, size, this);
				break;
			case 6:
				g.drawImage(shape, x+size+size, y+size, size, size, this);
				break;
			case 7:
				g.drawImage(shape, x, y+size+size, size, size, this);
				break;
			case 8:
				g.drawImage(shape, x+size, y+size+size, size, size, this);
				break;
			case 9:
				g.drawImage(shape, x+size+size, y+size+size, size, size, this);
				break;
			case 10:
				shape = ImageIO.read(ClassLoader.getSystemResource("ressources/Games_None.png"));
				System.err.println("initialize");
				g.drawImage(shape, x, y, size, size, this);
				g.drawImage(shape, x+size, y, size, size, this);
				g.drawImage(shape, x+size+size, y, size, size, this);
				g.drawImage(shape, x, y+size, size, size, this);
				g.drawImage(shape, x+size, y+size, size, size, this);
				g.drawImage(shape, x+size+size, y+size, size, size, this);
				g.drawImage(shape, x, y+size+size, size, size, this);
				g.drawImage(shape, x+size, y+size+size, size, size, this);
				g.drawImage(shape, x+size+size, y+size+size, size, size, this);
				break;
			default:
				System.err.println("error : "+playerShapeNumber);
				break;
			}
			
			shape = ImageIO.read(ClassLoader.getSystemResource("ressources/Games_Croix.png"));
			
			switch (computerShapeNumber) {
			case 1:
				System.out.println("point 1");
				g.drawImage(shape, x, y, size, size, this);
				break;
			case 2:
				g.drawImage(shape, x+size, y, size, size, this);
				break;
			case 3:
				g.drawImage(shape, x+size+size, y, size, size, this);
				break;
			case 4:
				g.drawImage(shape, x, y+size, size, size, this);
				break;
			case 5:
				g.drawImage(shape, x+size, y+size, size, size, this);
				break;
			case 6:
				g.drawImage(shape, x+size+size, y+size, size, size, this);
				break;
			case 7:
				g.drawImage(shape, x, y+size+size, size, size, this);
				break;
			case 8:
				g.drawImage(shape, x+size, y+size+size, size, size, this);
				break;
			case 9:
				g.drawImage(shape, x+size+size, y+size+size, size, size, this);
				break;
			case 10:
				shape = ImageIO.read(ClassLoader.getSystemResource("ressources/Games_None.png"));
				System.err.println("initialize");
				g.drawImage(shape, x, y, size, size, this);
				g.drawImage(shape, x+size, y, size, size, this);
				g.drawImage(shape, x+size+size, y, size, size, this);
				g.drawImage(shape, x, y+size, size, size, this);
				g.drawImage(shape, x+size, y+size, size, size, this);
				g.drawImage(shape, x+size+size, y+size, size, size, this);
				g.drawImage(shape, x, y+size+size, size, size, this);
				g.drawImage(shape, x+size, y+size+size, size, size, this);
				g.drawImage(shape, x+size+size, y+size+size, size, size, this);
				break;
			default:
				System.err.println("error : "+computerShapeNumber);
				break;
			}
			
			cache = g;
			
		}catch(Exception ex){ex.printStackTrace();}
	}
}
