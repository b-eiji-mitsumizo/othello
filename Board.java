package othello;

public class Board {
	
	private String[][] board;
	final int MAS_NUMBER = 8;
		
	public Board() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.board = new String[MAS_NUMBER][MAS_NUMBER];
		
		for(int i = 0; i < MAS_NUMBER; i++) {
			for(int j = 0; j < MAS_NUMBER; j++) {
				this.board[i][j]=  "　"; 
			}
		}
		
		
		this.board[3][3] = "〇";
		this.board[4][4] = "〇";
		
		this.board[3][4] = "●";
		this.board[4][3] = "●";
	}
	
	
	// TODO : マスを表示する
	public void displayBoard() {
		System.out.println("   0　| 1　| 2　| 3　| 4　| 5　| 6　| 7　");
		System.out.println("  -----------------------------------------");
		
		for(int row = 0; row < MAS_NUMBER; row++) {
			System.out.println(row + "  " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]+ " | " + board[row][3] + " | " + board[row][4] + " | " + board[row][5] + " | " + board[row][6] + " | " + board[row][7] );
			System.out.println("  -----------------------------------------");
		}
	}
	
	// TODO : 勝者を判定する関数。
	// TODO : 置けるかチェックする関数
	public void checkIfAddKoma(Player turnPlayer){
		
		while(true) {
			int[] place = turnPlayer.choosePlace();		
			if(this.board[place[0]][place[1]] != "　") {
				System.out.println("もうすでに埋められている場所なのでやり直してください。");
				continue;
			} else {
				this.board[place[0]][place[1]] = turnPlayer.getColor();
				break;
			}
		}
	}
	// TODO : ひっくり返す関数
	// TODO : 置ける場所に☆を表示
}