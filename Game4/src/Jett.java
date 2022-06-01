import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.geom.AffineTransform;
	import java.net.URL;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Image;
	import java.awt.Toolkit;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.geom.AffineTransform;
	import java.net.URL;
public class Jett {
	private int health; 
	private Image img; 	
	private AffineTransform tx;
	private int x, y;

	public Jett() {
		img = getImage("/imgs/test.png");
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 
		health = 200; 
		x= 370;
		y = 10;	
	}
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		update();
		//chjeck			
	}
		
		//update the picture variable location
	private void update() {
		tx.setToTranslation(x,y);
		tx.scale(0.5, 0.5);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.5, 0.5);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Jett.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
