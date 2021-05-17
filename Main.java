package othello;

public class Main {
	
	final static String BLACK = "●";
	final static String WHITE = "〇";
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		Board board = new Board();
		
		Player player1 = new Player("EIJI", BLACK);
		Player player2 = new Player("MIKI", WHITE);
		
		

		int count = 0;
		boolean finish = false;
		
		while(count < 60) {
			board.displayBoard();
			board.checkIfAddKoma(count % 2 == 0 ? player1 : player2);
			if(finish) {
				break;
			}
			count++;
		}
		board.checkWinner();
		
		System.out.println("最終結果");
		board.displayBoard();
	}

}
