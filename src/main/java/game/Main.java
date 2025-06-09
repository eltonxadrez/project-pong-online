package game;

public class Main {

	public static void main(String[] args) {
//		new Client();
		Game game = new Game();
		game.gameThread = new Thread(game);
		game.gameThread.start();
	}
}
