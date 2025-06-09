package game;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Regra implements Entity, Runnable {
	
	public Game game;
	public Teclado teclado;
	public Client client;
	public Set<Integer> teclasPressionadas;
	private boolean isMoving = false;
	
	
	public int velocidadeTick = 30;
//	private final int frameDuration = 10;
	
	
//	private long lastFrameTime = System.currentTimeMillis();
	
	public Regra(Game game) {
		this.game = game;
		this.teclado = game.teclado;
		this.client = game.client;
		this.teclasPressionadas = teclado.teclasPressionadas;
	}

	@Override
	public void run() {
		Thread.currentThread().setName("TRD-REGRA");
		try {
//			Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(true) {
			tick();
			try {
				Thread.sleep(1000/velocidadeTick);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void tick() {
//		double speed = 1.0;
		
		isMoving = false;
		
		if (teclasPressionadas.contains(KeyEvent.VK_UP) || teclasPressionadas.contains(KeyEvent.VK_W)) {
			System.out.println("up");
			DadosComandosPong dadosComandosPong = new DadosComandosPong();
			dadosComandosPong.up = true;
			client.sendingCommand(dadosComandosPong);
//			playerY -= speed;
			isMoving = true;
		}
		if (teclasPressionadas.contains(KeyEvent.VK_DOWN) || teclasPressionadas.contains(KeyEvent.VK_S)) {
			System.out.println("down");
			DadosComandosPong dadosComandosPong = new DadosComandosPong();
			dadosComandosPong.down = true;
			client.sendingCommand(dadosComandosPong);
//			playerY -= speed;
			isMoving = true;
		}
//        if (isMoving && System.currentTimeMillis() - lastFrameTime > frameDuration) {
//            frameIndex = (frameIndex + 1) % playerFrames.length;
//            lastFrameTime = System.currentTimeMillis();
//        }
	}

}
