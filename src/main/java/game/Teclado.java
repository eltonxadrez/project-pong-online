package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Teclado implements KeyListener {
	
	public Game game;
	public Janela janela;
//	public boolean tecladoLivre;
	
	//adicao para evitar o delay do pressionamento da tecla inicialmente
	public Set<Integer> teclasPressionadas = new HashSet<>();
	
	public Teclado(Game game) {
		this.game = game;
//		this.tecladoLivre = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		teclasPressionadas.add(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclasPressionadas.remove(e.getKeyCode());
	}
}
