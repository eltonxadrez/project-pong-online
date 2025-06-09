package game;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Game implements Runnable{
	
	public Client client;
	public EstadoJogoAtual estadoJogoAtual;
	
//	public Integer eixoYJ1 = 210;
//	public Integer eixoYJ2 = 210;
//	public Integer eixoXB = 500;
//	public Integer eixoYB = 300;
	
	public Thread gameThread;
	public Thread regraThread;
	public Regra regra;
	
	public Janela janela;
	public Renderizador renderizador;
	public Teclado teclado;
	
//	public int testeMovX = 10;
//	public int testeMovY = 10;
	
	//tamanho janela
	private int width, height;
//	public int speedGame = 5;
	public int fps = 75;
	public boolean renderizar = true;

	public Game() {
		init();
	}

	private void init() {
		this.estadoJogoAtual = new EstadoJogoAtual();
		
		this.dadosIniciais();
		
		this.client = new Client(this.estadoJogoAtual);
		
		this.setWindowSize();
		
		this.initMainComponents();
		
		this.initMainConections();
		
		this.initTeste();
		
		this.iniciarRegra();
		
	}
	
	private void dadosIniciais() {
		this.estadoJogoAtual.updateBall(-210, -210);
		this.estadoJogoAtual.updatePaddle(-500, -500);
		this.estadoJogoAtual.placarJogador1 = 0;
		this.estadoJogoAtual.placarJogador2 = 0;
	}

	private void setWindowSize() {
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		//1920 - 1080
		this.width = (int) dim.getWidth()/2;
		this.height = (int) dim.getHeight()/2;
		
		this.width = 1024;
		this.height = 768;
	}
	
	private void initMainComponents() {
//		this.entidades = new ArrayList<Entity>();
		this.teclado = new Teclado(this);
		this.renderizador = new Renderizador(this.width, this.height);
		this.janela = new Janela(this.renderizador, this.width, this.height);
	}
	
	public void initMainConections() {
		this.teclado.janela = this.janela;
//		this.teclado.menu = this.menu;
		this.renderizador.jFrame = this.janela;
		this.renderizador.addKeyListener(teclado);
		this.renderizador.requestFocusInWindow();
	}
	
	
	private void initTeste() {
		TesteRender testeRender = new TesteRender(this);
		this.renderizador.elementosRenderizadosList.add(testeRender);
		this.renderizador.renderizar = true;
		this.renderizar = true;
		
	}
	
	public void iniciarRegra() {
		this.regra = new Regra(this);
		this.regraThread = new Thread(regra);
		this.regraThread.start();
	}

	
	//APENAS TESTE DE V-SYNC
	final int targetFPS = 60;
	final long targetTime = 1_000_000_000 / targetFPS;
	
	public void run() {
		Thread.currentThread().setName("TRD-GAME");
		while(true) {
//			long startTime = System.nanoTime();
//			this.renderizador.render();
//			long elapsed = System.nanoTime() - startTime;
//		    long wait = targetTime - elapsed;
//
//		    if (wait > 0) {
//		        try {
//		            Thread.sleep(wait / 1_000_000, (int)(wait % 1_000_000));
//		        } catch (InterruptedException ignored) {}
//		    }
			try {
				if(renderizar) {
					this.renderizador.render();
				}
				Thread.sleep(1000/targetFPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
