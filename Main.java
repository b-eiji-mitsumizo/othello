package othello;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		Board board = new Board();
		
		Player player1 = new Player("EIJI", "●");
		Player player2 = new Player("MIKI", "○");
		
		

		int count = 0;
		boolean finish = false;
		
		while(count < 9) {
			board.displayBoard();
			board.checkIfAddKoma(count % 2 == 0 ? player1 : player2);
			if(finish) {
				break;
			}
			count++;
		}
		}

}
