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
public class Zangief {
	private int health; 
	private Image img; 	
	private AffineTransform tx;
	private int x, y;
	private int speed; 
	private double fallSpeed; 
	private double gravity;
	private boolean grounded; 
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	private String character = "Zangeif";

	public Zangief() {
		img = getImage("/imgs/Zangeif.png");
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 
		health = 200; 
		x= 370;
		fallSpeed=0;
		gravity=1.5;
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
		fallSpeed+=gravity; 
		x+=speed; 
		y+=fallSpeed;
		if(gravity==0) {
			grounded = true; 
		}
	}
		
	public void jump() {
		if(grounded) {
			gravity = 1.5;
			fallSpeed=50*-0.5;
			grounded=false; 
		}
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
			URL imageURL = Zangief.class.getResource(path);
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
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
	public AffineTransform getTx() {
		return tx;
	}
	public void setTx(AffineTransform tx) {
		this.tx = tx;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public double getFallSpeed() {
		return fallSpeed;
	}
	public void setFallSpeed(double fallSpeed) {
		this.fallSpeed = fallSpeed;
	}
	public double getGravity() {
		return gravity;
	}
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	public boolean isGrounded() {
		return grounded;
	}
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
}
