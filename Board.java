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
	
	
	// TODO : マスを表示する.(変数でできるように。)
	public void displayBoard() {
		System.out.println("   0　| 1　| 2　| 3　| 4　| 5　| 6　| 7　");
		System.out.println("  -----------------------------------------");
		
		for(int row = 0; row < MAS_NUMBER; row++) {
			System.out.println(row + "  " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]+ " | " + board[row][3] + " | " + board[row][4] + " | " + board[row][5] + " | " + board[row][6] + " | " + board[row][7] );
			System.out.println("  -----------------------------------------");
		}
	}
	
	// TODO : 勝者を判定する関数。
	public void checkWinner() {
		int numOfBlack = 0;
		int numOfWhite = 0;
		
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board[i].length; j++) {
				if(this.board[i][j] == "●") {
					numOfBlack++;
				} else {
					numOfWhite++;
				}
			}
		}
		
		System.out.println("白：" + numOfWhite);
		System.out.println("黒：" + numOfBlack);
		
		if(numOfBlack < numOfWhite) {
			System.out.println("白の勝ち");
		} else {
			System.out.println("黒の勝ち");
		}
	}
	
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
	public void chengeColor() {
		
	}
	
	public void checkAround(int row, int col, int color) {
		
		// TODO : 左上
		upperLeft();
		// TODO : 上
		upper();
		// TODO : 右上
		upperRight();
		// TODO : 右
		right();
		// TODO : 右下
		downRight();
		// TODO : 下
		down();
		// TODO : 左下
		downleft();
		// TODO : 左
		left();		
		
	}
	
	public void upperLeft() {}
	
	public void upper() {}
	
	public void upperRight() {}
	
	public void right() {}
	
	public void downRight() {}
	
	public void down() {}
	
	public void downleft() {}
	
	public void left() {}
	
	
	// TODO : 置ける場所に☆を表示
}