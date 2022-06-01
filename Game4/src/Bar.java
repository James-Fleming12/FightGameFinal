import java.awt.Color;
import java.awt.Graphics;

public class Bar {
	private int width;
	private int x, y;
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
	public int getWidth() {
		return width;
		}
	public void setWidth(int width) {
		this.width = width;
	}
	public Bar(int width) {
		this.width = width; 
		x = 10;
		y = 10;
	}
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, width, 40);
	}

}
