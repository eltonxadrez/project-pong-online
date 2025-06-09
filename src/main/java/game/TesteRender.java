package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TesteRender implements Concreto {
	
	public Game game;
	private Font fonte;
	private EstadoJogoAtual estadoJogoAtual;
	
	public TesteRender(Game game) {
		this.game = game;
		this.estadoJogoAtual = game.estadoJogoAtual;
		fonte = new Font("Arial", Font.BOLD, 32);
//		this.game.iniciarRegra();
	}

	@Override
	public void render(Graphics2D graphics2d, int janelaWidth, int janelaHeight) {
		DadosJogoPong dadosJogoPong = estadoJogoAtual.snapshot();
		
		//jogador 1
		graphics2d.setColor(Color.BLUE);
		graphics2d.fillRect(20, dadosJogoPong.eixoYJ1, 20, 200);
		
		//jogador 2
		graphics2d.setColor(Color.GREEN);
		graphics2d.fillRect(970, dadosJogoPong.eixoYJ2, 20, 200);
		
		//bola
		graphics2d.setColor(Color.RED);
		graphics2d.fillRect(dadosJogoPong.eixoXB, dadosJogoPong.eixoYB, 20, 20);
		
		//area do campo
		graphics2d.setColor(Color.YELLOW);
		graphics2d.drawRect(10, 10, 990, 590);
//		graphics2d.fillRect(10, 10, 10, 10);
		
		//placar
		graphics2d.setFont(fonte);
		
		graphics2d.setColor(Color.BLUE);
		graphics2d.drawString("JOGADOR 1", 15, 640);
		
		graphics2d.setColor(Color.WHITE);
		graphics2d.drawString(dadosJogoPong.placarJogador1.toString(), 100, 680);
		
		graphics2d.setColor(Color.GREEN);
		graphics2d.drawString("JOGADOR 2", 805, 640);
		
		graphics2d.setColor(Color.WHITE);
		graphics2d.drawString(dadosJogoPong.placarJogador2.toString(), 890, 680);
		
//		graphics2d.setColor(Color.pink);
//		graphics2d.draw3DRect(60, 60, 60, 60, false);
//		graphics2d.fill3DRect(180, 180, 120, 120, true);
		
	}
}
