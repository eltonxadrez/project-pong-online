package game;

import javax.swing.JFrame;

public class Janela extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Renderizador renderizador;
	
	public Janela(Renderizador renderizador, int width, int height) {
		this.renderizador = renderizador;
		this.setBounds(0, 0, width, height);
		this.add(renderizador);
		this.setTitle("PONG CLIENTE");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponentListener(new ListenerJanela(renderizador, this));
		this.setVisible(true);
	}

}
