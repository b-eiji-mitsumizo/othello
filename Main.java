package othello;

import java.util.Scanner;

public class Main {
	
	final static String BLACK = "●";
	final static String WHITE = "〇";
	
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		
		Scanner scan = new Scanner(System.in);
		
		/*
		 * 何マスにするかを設定する。
		 * 偶数のみ許す。
		 */
		int masNum = 0;
		
		while(true) {
			System.out.print("何マスのオセロをしたいですか？ > ");
			masNum = scan.nextInt();
			if(masNum % 2 == 0 && masNum >= 4) {
				break;
			} else {
				System.out.println("4以上の偶数を入力してください。");
			}
		}
		
		/*
		 * ボードの作成（マス目の大きさを引数として渡す。）
		 */
		Board board = new Board(masNum);
		
		/*
		 * 黒のプレイヤーを作成
		 */
		System.out.print("Player1(" + BLACK + ")の名前を教えてください。 > ");
		String player1Name = scan.next();
		Player player1 = new Player(player1Name, BLACK, masNum);
		
		
		/*
		 * 白のプレイヤーを作成
		 */
		System.out.print("Player2(" + WHITE + ")の名前を教えてください。 > ");
		String player2Name = scan.next();
		Player player2 = new Player(player2Name, WHITE, masNum);
		

		int count = 0;
		
		// 置けるマス目の数（最初に４つ置いてあるので、引いている。）
		final int playNum = (masNum * masNum) - 4;
		
		// パスした場合はfalseとなり、countを増やさない。
		boolean didPutKoma = false;
		
		/*
		 * プレイするプレイヤー
		 */
		Player playingPlayer = player1;
		
		while(count < playNum) {
			// ボードを表示する。（置ける場所は星印になっている。）
			board.displayPossiblePut(playingPlayer);
			
			/*
			 * コマを置く。（置く場所がない場合はFalseが返ってくる。）
			 */
			didPutKoma = board.checkIfAddKoma(playingPlayer);
			
			
			/*
			 * プレイヤーを変える。
			 */
			if (playingPlayer == player1){
				playingPlayer = player2;
			} else {
				playingPlayer = player1;
			}
			
			// パスをしてない場合は、countをプラスする。
			if(didPutKoma) {
				count++;	
			}
		}
		
		// 勝者をチェックする。
		board.checkWinner(player1, player2);
		
		System.out.println("===最終結果===");
		// 最終結果のボードを表示する。		
		board.displayBoard();
		
		scan.close();
	}
}
