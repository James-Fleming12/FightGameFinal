import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {
		private Image img; 	
		private AffineTransform tx;
		private int x, y;

		public Background() {
			img = getImage("/imgs/Tokyo.png");
			tx = AffineTransform.getTranslateInstance(x, y);
			init(x, y); 
			x= 0;
			y = 0;	
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
			tx.scale(1, 1);
		}
		
		private void init(double a, double b) {
			tx.setToTranslation(a, b);
			tx.scale(1, 1);
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


}
