import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements KeyListener, ActionListener, MouseListener{
	//goofy ah game
	// maybe make a way to distinguish p1 from p2 if they're the same character
	//:skull: x9
	//not bad maybe add more characters like smash
	//its no smash, but i clearly see a pattern of metas emerging.
	//pls nerf meta knight
	//P1 SHOULD BE ON THE LEFT SIDE
	// a little confusing with the players and how it works
	// very chill ig
	// more characters that can attack
	// how do you guard
	//buff gru
	// WHy are teh characters switching??? but good game
	// nice game but how do I block
	
	
	boolean startScreen = true;
	boolean characterSelect = false; 
	boolean gameplay = false;
	boolean endScreen = false;
	startScreen start = new startScreen();
	characterSelect select = new characterSelect(); 
	boolean paintp1 = false;
	boolean paintp2 = false; 
	boolean readyUp = false; 
	boolean endTime = false;
	endScreen end = new endScreen();
	boolean p1Lost = false;
	boolean p2Lost = false; 
	Blocked leftBlock = new Blocked("/imgs/blockLeft.png");
	Blocked rightBlock = new Blocked("/imgs/blockRight.png");
	selection p1Selection = new selection(30); 
	selection p2Selection = new selection(550); 
	String p1Selected = "";
	String p2Selected= "";
	Zangief p1 = new Zangief();
	Zangief p2 = new Zangief();
	counter blockLeftCounter = new counter();
	counter blockRightCounter = new counter(); 
	boolean p1Left = false; 
	boolean p2Left = false; 
	boolean p1Right = false; 
	boolean p2Right = false;
	boolean p1LeftBlock = false;
	boolean p2LeftBlock = false;
	boolean p1RightBlock = false;
	boolean p2RightBlock = false; 
	boolean left = true; 
	boolean p1Attack = false; 
	boolean p2Attack = false; 
	counter p2AttackCounter = new counter();
	counter p1AttackCounter = new counter(); 
	counter p2CooldownCounter = new counter();
	counter p1CooldownCounter = new counter(); 
	boolean p1Cooldown = false;
	boolean p2Cooldown = false;
	int p1Full = 200; 
	int p2Full = 200;
	Bar p1Health = new Bar(400);
	Background bg = new Background(); 
	int time = 99; 
	counter timeCounter = new counter();
	counter test = new counter();
	
	public Frame() {
		JFrame f = new JFrame("Fight Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,600);
		f.add(this);
		f.addKeyListener(this);
		f.addMouseListener(this);
		t = new Timer(16, this);
		t.start();
		f.setVisible(true);
		p2.changePicture("/imgs/ZangeifLeft.png");
		p1.setX(100);
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}	
	
	public void paint(Graphics g) {
		if(endScreen) {
			end.paint(g);
			g.setFont(new Font("Arial", 50, 50));
			if(p1Lost) {
				g.drawString("P2 Wins", 300, 50);
			}
			if(p2Lost) {
				g.drawString("P1 Wins", 300, 50);
			}
			if(endTime) {
				g.drawString("Draw", 40, 10);
			}
		}
		if(startScreen == true) {
			start.paint(g);
		}
		if(characterSelect == true) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 5000, 5000);
			select.paint(g);
			g.setColor(Color.white);
			if(paintp1) {
				p1Selection.paint(g);
			}
			if(paintp2) {
				p2Selection.paint(g);
			}
			g.drawString("Left Click for P1", 10, 10);
			g.drawString("Right Click for P2", 680, 10);
		    Font fontTemp = new Font("Arial", 30, 30);
		    g.setFont(fontTemp);
			g.drawString(p1Selected, 50, 270);
			g.drawString(p2Selected, 550, 270);
			if(paintp1 && paintp2) {
				readyUp = true; 
				g.setColor(Color.RED);
				g.drawString("READY", 350, 280);
			}
		}
	if(gameplay == true) {
		bg.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(5, 5, 210, 50);
		g.fillRect(565, 5, 210, 50);
		g.setColor(Color.RED);
		p1.paint(g);
		p2.paint(g);
		leftBlock.paint(g);
		rightBlock.paint(g);
		if(p1RightBlock) {
			rightBlock.setX(p1.getX()+120);
			rightBlock.setY(p1.getY());
		}
		if(p1LeftBlock) {
			leftBlock.setX(p1.getX());
			leftBlock.setY(p1.getY());
		}
		if(p2RightBlock) {
			rightBlock.setX(p2.getX()+120);
			rightBlock.setY(p2.getY());
		}
		if(p2LeftBlock){
			leftBlock.setX(p2.getX());
			leftBlock.setY(p2.getY());
		}
		if(p1.getHealth()<=0) {
			p1Lost = true;
			endScreen = true;
			gameplay = false;
		}
		if(p2.getHealth()<=0) {
			p2Lost = true;
			endScreen = true;
			gameplay = false;
		}
		if(time<=0) {
			endTime= false;
			gameplay = false;
			endScreen = true;
		}
		g.fillRect(10, 10, 200, 40);
		g.fillRect(570, 10, 200, 40);
		g.setColor(Color.white);
		g.fillRect(p1.getHealth()+10, 10, p1Full-p1.getHealth(), 40);
		g.fillRect(570, 10, p2Full-p2.getHealth(), 40);
		Font font = new Font(Font.SANS_SERIF ,Font.BOLD, 20);
		blockRightCounter.setY(blockRightCounter.getY()-1);
		blockLeftCounter.setY(blockLeftCounter.getY()-1);
		g.setFont(font);
		if(blockLeftCounter.getY()<=0) {
			p1LeftBlock = false;
			p2LeftBlock = false; 
			leftBlock.setX(10000);
		}
		if(blockRightCounter.getY()<=0) {
			p1RightBlock = false;
			p2RightBlock = false; 
			rightBlock.setX(10000);
		}
		g.setColor(Color.BLACK);
		g.drawString("TIME", 370, 20);
		Font font2 = new Font(Font.SANS_SERIF ,Font.BOLD, 50);
		g.setFont(font2);
		leftBlock.paint(g);
		rightBlock.paint(g);
		g.drawString(String.valueOf(time), 365, 70);
		if(timeCounter.getY()>=40) {
			timeCounter.setY(0);
			time--; 
		}
		if(p1.getX()>p2.getX()) {
			
			left = false; 
			//p1.changePicture("/imgs/ZangeifLeft.png");
			//p2.changePicture("/imgs/Zangeif.png");
		}if(p1.getX()<p2.getX()) {
			left = true; 
		}
		if(p1.getX()>= 630 || p1.getX()<=0) {
			p1.setSpeed(0);
		}
		if(p2.getX()>= 630 || p2.getX()<=0) {
			p2.setSpeed(0);
		}
			//p1.changePicture("/imgs/Zangeif.png");
			//p2.changePicture("/imgs/ZangeifLeft.png");
		//}
		if(p2AttackCounter.getY()<=0 && p2Attack==true) {
			p2Attack=false;
		}
		if(p1AttackCounter.getY()<=0 && p1Attack==true) {
			p1Attack=false; 
		}
		if(p1CooldownCounter.getY()<0) {
			p1Cooldown = false; 
		}
		if(p2CooldownCounter.getY()<0) {
			p2Cooldown = false; 
		}
		if(left == true && p1.getCharacter().equals("Zangief")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/Zangeif.png");
			}
		}
		if(left == true && p2.getCharacter().equals("Zangief")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/ZangeifLeft.png");
			}
		}
		if(left == false && p1.getCharacter().equals("Zangief")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/ZangeifLeft.png");
			}
		}
		if(left == false && p2.getCharacter().equals("Zangief")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/Zangeif.png");
			}
		}
		if(left == false && p1.getCharacter().equals("Boom")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/boomLeft.png");
			}
		}
		if(left == false && p2.getCharacter().equals("Boom")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/boomRight.png");
			}
		}
		if(left == true && p1.getCharacter().equals("Boom")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/boomRight.png");
			}
		}
		if(left == true && p2.getCharacter().equals("Boom")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/boomLeft.png");
			}
		}
		if(left == false && p1.getCharacter().equals("Gru")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/gruLeft.png");
			}
		}
		if(left == false && p2.getCharacter().equals("Gru")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/gruRight.png");
			}
		}
		if(left == true && p1.getCharacter().equals("Gru")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/gruRight.png");
			}
		}
		if(left == true && p2.getCharacter().equals("Gru")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/gruLeft.png");
			}
		}
		if(left == false && p1.getCharacter().equals("Purple")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/purpleLeft.png");
			}
		}
		if(left == false && p2.getCharacter().equals("Purple")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/purpleRight.png");
			}
		}
		if(left == true && p1.getCharacter().equals("Purple")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/purpleRight.png");
			}
		}
		if(left == true && p2.getCharacter().equals("Purple")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/purpleLeft.png");
			}
		}
		if(left == false && p1.getCharacter().equals("Mega")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/megaLeft.png");
			}
		}
		if(left == false && p2.getCharacter().equals("Mega")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/megaRight.png");
			}
		}
		if(left == true && p1.getCharacter().equals("Mega")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/megaRight.png");
			}
		}
		if(left == true && p2.getCharacter().equals("Mega")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/megaLeft.png");
			}
		}
		if(left == false && p1.getCharacter().equals("Jett")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/jettLeft.png");
			}
		}
		if(left == false && p2.getCharacter().equals("Jett")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/jettRight.png");
			}
		}
		if(left == true && p1.getCharacter().equals("Jett")) {
			if(p1Attack == false) {
			p1.changePicture("/imgs/jettRight.png");
			}
		}
		if(left == true && p2.getCharacter().equals("Jett")) {
			if(p2Attack == false) {
			p2.changePicture("/imgs/jettLeft.png");
			}
		}
		if(p1.getY()>=300) {
			p1.setGravity(0);
			p1.setFallSpeed(0);
		}
		if(p2.getY()>=300) {
			p2.setGravity(0);
			p2.setFallSpeed(0);
		}
		timeCounter.setY(timeCounter.getY()+1);
		p1AttackCounter.setY(p1AttackCounter.getY()-1);
		p2AttackCounter.setY(p2AttackCounter.getY()-1);
		p1CooldownCounter.setY(p1CooldownCounter.getY()-1);
		p2CooldownCounter.setY(p2CooldownCounter.getY()-1);
	}
		if(endScreen) {
			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (startScreen) {

		}
		if (gameplay) {
			System.out.println(arg0.getKeyCode());
			if (arg0.getKeyCode() == 38) {
				p1.jump();
			}
			if (arg0.getKeyCode() == 39 && p1.getX() < 560) {
				p1Right = true;
				p1.setSpeed(5);
			}
			if (arg0.getKeyCode() == 37 && p1.getX() > 0) {
				p1Left = true;
				p1.setSpeed(-5);
			}
			if (arg0.getKeyCode() == 87) {
				p2.jump();
			}
			if (arg0.getKeyCode() == 68 && p2.getX() < 560) {
				p2Right = true;
				p2.setSpeed(5);                                                                                                             
			}
			if (arg0.getKeyCode() == 65 && p2.getX() > 0) {
				p2Left = true;
				p2.setSpeed(-5);
			}
					
			if (p2.getCharacter().equals("Mega") && arg0.getKeyCode() == 71 && p2Attack == false
					&& p2Cooldown == false) {
				if (left) {
					p2.changePicture("/imgs/MegaKnight Jump charge and landing left.png");
					if (p2.getX() + 20 > p1.getX() && p2.getX() < p1.getX() + 150 && p2.getY() + 60 < p1.getY() + 180
							&& p2.getY() + 80 > p1.getY()) {
						p1.setHealth(p1.getHealth() - 20);
						p1AttackCounter.setY(50);
						System.out.println("yes");
					}
				}
			}
				if (p2.getCharacter().equals("Boom") && arg0.getKeyCode() == 71 && p2Attack == false
						&& p2Cooldown == false) {
					if (left) {
						p2.changePicture("/imgs/MegaKnight Jump charge and landing left.png");
						if (p2.getX() + 20 > p1.getX() && p2.getX() < p1.getX() + 150
								&& p2.getY() + 60 < p1.getY() + 180 && p2.getY() + 80 > p1.getY()) {
							p1.setHealth(p1.getHealth() - 20);
							p1AttackCounter.setY(50);
							System.out.println("yes");
						}
					}
					p1AttackCounter.setY(20);
					p1CooldownCounter.setY(30);
					p1Cooldown = true;
					p1Attack = true;
				}

				if ((arg0.getKeyCode() == 97 && p1Attack == false && p1Cooldown == false)
						|| (arg0.getKeyCode() == 222 && p1Attack == false && p1Cooldown == false)) {
					if (p1.getCharacter().equals("Zangief")) {
						if (left) {
							if (p2Right == false) {
								p1.changePicture("/imgs/zangeifPunchRight.png");
								if (p1.getX() + 120 < p2.getX() + 150 && p1.getX() + 160 > p2.getX()
										&& p1.getY() + 60 < p2.getY() + 180 && p1.getY() + 80 > p2.getY()) {
									p2.setHealth(p2.getHealth() - 20);
								}
							} else {
								p2LeftBlock = true;
								blockLeftCounter.setY(10);
							}

						} else {
							p1.changePicture("/imgs/zangeifLeftPunch.png");
							if (p2Left == false) {
								if (p1.getX() + 20 > p2.getX() && p1.getX() < p2.getX() + 150
										&& p1.getY() + 60 < p2.getY() + 180 && p1.getY() + 80 > p2.getY()) {
									p2.setHealth(p2.getHealth() - 20);
								}
							} else {
								p2RightBlock = true;
								blockRightCounter.setY(10);
							}
						}
						p1AttackCounter.setY(20);
						p1CooldownCounter.setY(30);
						p1Cooldown = true;
						p1Attack = true;
					}
				}
				if ((arg0.getKeyCode() == 71 && p2Attack == false && p2Cooldown == false)) {
					if (p2.getCharacter().equals("Zangief")) {
						if (left) {
							p2.changePicture("/imgs/zangeifLeftPunch.png");
							if (p1Left == false) {
								
								if (p2.getX() + 20 > p1.getX() && p2.getX() < p1.getX() + 150
										&& p2.getY() + 60 < p1.getY() + 180 && p2.getY() + 80 > p1.getY()) {
									p1.setHealth(p1.getHealth() - 20);
								}
							} else {
								p1RightBlock = true;
								blockRightCounter.setY(10);
							}

						} else {
							p2.changePicture("/imgs/zangeifPunchRight.png");
							if (p1Right == false) {
								if (p2.getX() + 120 < p1.getX() + 150 && p2.getX() + 160 > p1.getX()
										&& p2.getY() + 60 < p1.getY() + 180 && p2.getY() + 80 > p1.getY()) {
									p1.setHealth(p1.getHealth() - 20);
								}
							} else {
								p1LeftBlock = true;
								blockLeftCounter.setY(10);
							}
						}
						p2AttackCounter.setY(20);
						p2CooldownCounter.setY(30);
						p2Cooldown = true;
						p2Attack = true;
					}
				}
				if (p1.getCharacter().equals("Mega") && arg0.getKeyCode() == 97
						&& p1Attack == false
						&& p1Cooldown == false) {
					if (left) {
						p1.changePicture("/imgs/MegaKnight Jump charge and landing left.png");
						if (p1.getX() + 120 < p2.getX() + 150 && p1.getX() + 160 > p2.getX()
								&& p1.getY() + 60 < p2.getY() + 180 && p1.getY() + 80 > p2.getY()) {
							p2.setHealth(p2.getHealth() - 20);
						}

					}
				}
				if (p2.getCharacter().equals("Mega") && arg0.getKeyCode() == 222 && p2Attack == false
						&& p2Cooldown == false) {
					if (left) {
						p1.changePicture("/imgs/MegaKnight Jump charge and landing left.png");
						if (p1.getX() + 120 < p2.getX() + 150 && p1.getX() + 160 > p2.getX()
								&& p1.getY() + 60 < p2.getY() + 180 && p1.getY() + 80 > p2.getY()) {
							p2.setHealth(p2.getHealth() - 20);
						}

					}
				}
				if (p1.getCharacter().equals("Boom") && arg0.getKeyCode() == 222 && p1Attack == false && p1Cooldown == false) {
					if (left) {
						p1.changePicture("/imgs/Boomright.png");
						if (p1.getX() + 120 < p2.getX() + 150 && p1.getX() + 160 > p2.getX()
								&& p1.getY() + 60 < p2.getY() + 180 && p1.getY() + 80 > p2.getY()) {
							p2.setHealth(p2.getHealth() - 20);
						}
					}
				}
					if (p1.getCharacter().equals("Purple") && arg0.getKeyCode() == 222 && p1Attack == false
							&& p1Cooldown == false) {
						if (left) {
							p1.changePicture("/imgs/purpleRight.png");
							if (p1.getX() + 120 < p2.getX() + 150 && p1.getX() + 160 > p2.getX()
									&& p1.getY() + 60 < p2.getY() + 180 && p1.getY() + 80 > p2.getY()) {
								p2.setHealth(p2.getHealth() - 20);
							}

						} 
					} 
					if (p1.getCharacter().equals("Gru")  && arg0.getKeyCode() == 222 && p1Attack == false
							&& p1Cooldown == false) {
						if (left) {
							p1.changePicture("/imgs/Gru Attack.png");
							if (p1.getX() + 120 < p2.getX() + 150 && p1.getX() + 160 > p2.getX()
									&& p1.getY() + 60 < p2.getY() + 180 && p1.getY() + 80 > p2.getY()) {
								p2.setHealth(p2.getHealth() - 20);
								p1AttackCounter.setY(50);
								p1Attack = true;
							}
						}
					}

					if (arg0.getKeyCode() == 105) {
						System.out.println(p1.getX() + " " + p1.getY());
					}
					if (arg0.getKeyCode() == 85) {
						System.out.println(p2.getX() + " " + p2.getY());
					}
				}
			}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	if(gameplay == true) {
		if(arg0.getKeyCode()==39) {
			p1.setSpeed(0);
			p1Right = false; 
		}
		if(arg0.getKeyCode()==37) {
			p1.setSpeed(0);
			p1Left = false; 
		}
		if(arg0.getKeyCode()==68) {
			p2.setSpeed(0);
			p2Right = false; 
		}
		if(arg0.getKeyCode()==65) {
			p2.setSpeed(0);
			p2Left = false; 
		}
	}
			}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//	
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		System.out.println("Y" + arg0.get);
//		System.out.println("X" + arg0.getX);
//	}
//	
	Timer t;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + " " + e.getY());
		if(endScreen) {
			if(e.getX()>160 && e.getX()<634 && e.getY()>212 && e.getY()<461 && e.getButton()==1) {
				endScreen = false;
				p1Lost= false;
				p2Lost = false;
				endTime =false;
				time = 99; 
				startScreen = true;
				p1.setHealth(200);
				p2.setHealth(200);
			}
		}
		if(startScreen) {
			if(e.getX()>167 && e.getX()<635 && e.getY() > 515 && e.getY() < 589) {
				startScreen = false; 
				characterSelect = true; 
			}
		} else if(characterSelect) {
			if(e.getX()>486 && e.getX()<636 && e.getY()>468 && e.getY()<586 && e.getButton()==1) {
				p1.setCharacter("Jett");
				p1Selection.changePicture(("/imgs/JettRight.png"));
				p1.changePicture("/imgs/JettRight.png");
				paintp1=true; 
				p1Selected = "Jett";
			}
			if(e.getX()>486 && e.getX()<636 && e.getY()>468 && e.getY()<586 && e.getButton()==3) {
				p2.setCharacter("Jett");
				p2Selection.changePicture(("/imgs/JettLeft.png"));
				p2.changePicture(("/imgs/JettLeft.png"));
				paintp2=true; 
				p2Selected = "Jett";
			}
			if(e.getX()>328 && e.getX()<481 && e.getY()>331 && e.getY()<463 && e.getButton()==1) {
				p1.setCharacter("Zangief");
				p1Selection.changePicture(("/imgs/Zangeif.png"));
				p1.changePicture(("/imgs/Zangeif.png"));
				paintp1=true; 
				p1Selected = "Zangief";
			}
			if(e.getX()>328 && e.getX()<481 && e.getY()>331 && e.getY()<463 && e.getButton()==3) {
				p2.setCharacter("Zangief");
				p2Selection.changePicture(("/imgs/ZangeifLeft.png"));
				p2.changePicture(("/imgs/ZangeifLeft.png"));
				paintp2=true; 
				p2Selected = "Zangief";
			}
			if(e.getX()>173 && e.getX()<325 && e.getY()>332 && e.getY()<465 && e.getButton()==3) {
				p2.setCharacter("Boom");
				p2Selection.changePicture(("/imgs/boomLeft.png"));
				p2.changePicture(("/imgs/boomLeft.png"));
				paintp2=true; 
				p2Selected = "Boom Beach Guy";
			}
			if(e.getX()>173 && e.getX()<325 && e.getY()>332 && e.getY()<465 && e.getButton()==1) {
				p1.setCharacter("Boom");
				p1Selection.changePicture(("/imgs/boomRight.png"));
				p1.changePicture(("/imgs/boomRight.png"));
				paintp1=true; 
				p1Selected = "Boom Beach Guy";
			}
			if(e.getX()>325 && e.getX()<468 && e.getY()>468 && e.getY()<589 && e.getButton()==1) {
				p1.setCharacter("Purple");
				p1Selection.changePicture(("/imgs/purpleRight.png"));
				p1.changePicture(("/imgs/purpleRight.png"));
				paintp1=true; 
				p1Selected = "Purple Guy";
			}
			if(e.getX()>325 && e.getX()<468 && e.getY()>468 && e.getY()<589 && e.getButton()==3) {
				p2.setCharacter("Purple");
				p2Selection.changePicture(("/imgs/purpleLeft.png"));
				p2.changePicture(("/imgs/purpleLeft.png"));
				paintp2=true; 
				p2Selected = "Purple Guy";
			}
			if(e.getX()>178 && e.getX()<318 && e.getY()>470 && e.getY()<586 && e.getButton()==3) {
				p2.setCharacter("Gru");
				p2Selection.changePicture(("/imgs/gruLeft.png"));
				p2.changePicture(("/imgs/gruLeft.png"));
				paintp2=true; 
				p2Selected = "Gru";
			}
			if(e.getX()>178 && e.getX()<318 && e.getY()>470 && e.getY()<586 && e.getButton()==1) {
				p1.setCharacter("Gru");
				p1Selection.changePicture(("/imgs/gruRight.png"));
				p1.changePicture(("/imgs/gruRight.png"));
				paintp1=true; 
				p1Selected = "Gru";
			}
			if(e.getX()>485 && e.getX()<631 && e.getY()>332 && e.getY()<465 && e.getButton()==1) {
				p1.setCharacter("Mega");
				p1Selection.changePicture(("/imgs/megaRight.png"));
				p1.changePicture(("/imgs/megaRight.png"));
				paintp1=true; 
				p1Selected = "Mega Knight";
			}
			if(e.getX()>485 && e.getX()<631 && e.getY()>332 && e.getY()<465 && e.getButton()==3) {
				p2.setCharacter("Mega");
				p2Selection.changePicture(("/imgs/megaLeft.png"));
				p2.changePicture(("/imgs/megaLeft.png"));
				paintp2=true; 
				p2Selected = "Mega Knight";
			}
			if(e.getX()>355 && e.getX()<462 && e.getY()>286 && e.getY()<310) {
				characterSelect=false; 
				gameplay = true; 
			}
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
