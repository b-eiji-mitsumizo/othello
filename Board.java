package othello;

public class Board {
	
	private String[][] board;
	final int MAS_NUMBER = 8;
		
	public Board() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.board = new String[MAS_NUMBER][MAS_NUMBER];
		
		for(int i = 0; i < MAS_NUMBER; i++) {
			for(int j = 0; j < MAS_NUMBER; j++) {
				this.board[i][j]=  " "; 
			}
		}
	}
	
	
	public void displayBoard() {
		System.out.println("   0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 ");
		System.out.println("  --------------------------------");
		
		for(int row = 0; row < MAS_NUMBER; row++) {
			System.out.println(row + "  " + board[row][0] + " | " + board[row][1] + " | " + board[row][2]+ " | " + board[row][3] + " | " + board[row][4] + " | " + board[row][5] + " | " + board[row][6] + " | " + board[row][7] );
			System.out.println("  --------------------------------");
		}
	}
}