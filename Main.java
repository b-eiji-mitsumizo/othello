package othello;

import java.util.Scanner;

public class Main {
	
	final static String BLACK = "●";
	final static String WHITE = "〇";
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("何マスのオセロをしたいですか？ > ");
		int masNum = scan.nextInt();
		
		
		Board board = new Board(masNum);
		
		System.out.print("Player1(" + BLACK + ")の名前を教えてください。 > ");
		String player1Name = scan.next();
		
		Player player1 = new Player(player1Name, BLACK, masNum);
		
		System.out.print("Player2(" + WHITE + ")の名前を教えてください。 > ");
		String player2Name = scan.next();
		
		Player player2 = new Player(player2Name, WHITE, masNum);
		
		

		int count = 0;
		final int playNum = (masNum * masNum) - 4;
		boolean didPutKoma = false;
		
		Player playingPlayer = player1;
		
		while(count < playNum) {
//			board.displayBoard();
			board.displayPossiblePut(playingPlayer);
			didPutKoma = board.checkIfAddKoma(playingPlayer);
			
			
			if (playingPlayer == player1){
				playingPlayer = player2;
			} else {
				playingPlayer = player1;
			}
			
			if(didPutKoma) {
				count++;	
			}
		}
		board.checkWinner(player1, player2);
		
		System.out.println("最終結果");
		board.displayBoard();
		
		scan.close();
	}

}
