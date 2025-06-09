package game;

public class EstadoJogoAtual {
	
	//placar
	public Integer placarJogador1;
	public Integer placarJogador2;
	//bola
	private Integer eixoXB, eixoYB;
	//raquetes
	private Integer eixoYJ1, eixoYJ2;
	
    public synchronized void updateBall(Integer eixoXB, Integer eixoYB) {
        this.eixoXB = eixoXB;
        this.eixoYB = eixoYB;
    }
    
    public synchronized void updatePaddle(Integer eixoYJ1, Integer eixoYJ2) {
    	this.eixoYJ1 = eixoYJ1;
    	this.eixoYJ2 = eixoYJ2;
    }
    
    public synchronized void eixoTeste() {
    	this.eixoYJ1 += 1;
    }
	
    public synchronized DadosJogoPong snapshot() {
        DadosJogoPong snapshot = new DadosJogoPong();
        snapshot.eixoXB = this.eixoXB;
        snapshot.eixoYB = this.eixoYB;
        snapshot.eixoYJ1 = this.eixoYJ1;
        snapshot.eixoYJ2 = this.eixoYJ2;
        snapshot.placarJogador1 = this.placarJogador1;
        snapshot.placarJogador2 = this.placarJogador2;
        return snapshot;
    }
    
    public synchronized void updateAll(DadosJogoPong dadosJogoPong) {
    	this.eixoXB = dadosJogoPong.eixoXB;
    	this.eixoYB = dadosJogoPong.eixoYB;
    	this.eixoYJ1 = dadosJogoPong.eixoYJ1;
    	this.eixoYJ2 = dadosJogoPong.eixoYJ2;
    	this.placarJogador1 = dadosJogoPong.placarJogador1;
    	this.placarJogador2 = dadosJogoPong.placarJogador2;
    }

}
