package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Renderizador extends Canvas{
	
	public boolean renderizar = true;
	
	public Graphics graphics;
	public Graphics2D graphics2d;
	
	public BufferStrategy bs;
	public int width, height;
	public JFrame jFrame;
	
	//apenas para teste background
	private BufferedImage tileImage;
	
	public ArrayList<Concreto> elementosRenderizadosList;

	private static final long serialVersionUID = 1L;
	
	public Renderizador(int width, int height) {
		this.setBackground(Color.BLACK);
		this.elementosRenderizadosList = new ArrayList<Concreto>();
		this.setPreferredSize(new Dimension(width, height));
		this.width = width;
		this.height = height;
		try {
			tileImage = ImageIO.read(new File("resources/sprites/background/background_java_version.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render() {
		if(renderizar) {
			this.bs = this.getBufferStrategy();
			
			if(this.bs == null) {
				this.createBufferStrategy(3);
				return;
			}
			this.graphics = bs.getDrawGraphics();
			this.graphics2d = (Graphics2D) this.graphics;
			
			this.background();
			
			for (Concreto concreto : this.elementosRenderizadosList) {
				concreto.render(this.graphics2d, this.jFrame.getBounds().width, this.jFrame.getBounds().height);
			}
			this.bs.show();
		}
	}
	
	private void background() {
		this.graphics2d.setColor(Color.BLACK);
		this.graphics2d.fillRect(0, 0, this.height, this.width);
		this.graphics2d.setColor(Color.BLACK);
		
		//linhas vermelhas marcando o centro
//		this.graphics2d.setColor(Color.RED);
//		this.graphics2d.fillRect(0, this.width/2, this.height, 1);
//		this.graphics2d.setColor(Color.RED);
//		this.graphics2d.fillRect(this.height/2, 0, 1, this.width);
		
		//background
		this.graphics2d.drawImage(tileImage, 0, 0, 2255, 1673, null);
		
		graphics2d.setColor(Color.BLACK);
		graphics2d.fillRect(10, 610, 990, 110);
	}

}
