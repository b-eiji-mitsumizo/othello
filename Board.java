package othello;

public class Board {
	
	private String[][] board;
	final int MAS_NUMBER = 8;
	
	final String BLACK = "●";
	final String WHITE = "〇";
	final String EMPTY = "　"; 
	
	
	public Board() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.board = new String[MAS_NUMBER][MAS_NUMBER];
		
		for(int i = 0; i < MAS_NUMBER; i++) {
			for(int j = 0; j < MAS_NUMBER; j++) {
				this.board[i][j]=  EMPTY; 
			}
		}
		
		
		this.board[3][3] = WHITE;
		this.board[4][4] = WHITE;
		
		this.board[3][4] = BLACK;
		this.board[4][3] = BLACK;
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
				if(this.board[i][j] == BLACK) {
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
			if(this.board[place[0]][place[1]] != EMPTY) {
				System.out.println("もうすでに埋められている場所なのでやり直してください。");
				continue;
			} else if(checkAround(place[0], place[1], turnPlayer.getColor())){
				this.board[place[0]][place[1]] = turnPlayer.getColor();;
				break;
			}
		}
	}
	// TODO : ひっくり返す関数
	public void chengeColor(int x, int y, String color) {
		if(color.equals(WHITE)){ 
			this.board[y][x] = WHITE;
		} else {
			this.board[y][x] = BLACK;
		}
		
	}
	
	public boolean checkAround(int y, int x, String color) {
		boolean[] check = new boolean[8];
		// TODO : 左上
//		upperLeft();
		check[0] = upperLeft(x, y, color, false);

//		// TODO : 上
		check[1] = upper(x, y, color, false);
//		// TODO : 右上
//		upperRight();
		check[2] = upperRight(x, y, color, false);
//		// TODO : 右
		check[3] = right(x, y, color, false);
//		right();
//		// TODO : 右下
		check[4] = downRight(x, y, color, false);
//		downRight();
//		// TODO : 下
		check[5] = down(x, y, color, false);
//		down();
//		// TODO : 左下
		check[6] = downleft(x, y, color, false);
//		downleft();
		// TODO : 左
		check[7] = left(x, y, color, false);	
		
		boolean possible = false;
		for(boolean eachCheck : check) {
			if(eachCheck) {
				possible = true;
			}
		}
		
		if(! possible) {
			System.out.println("ひっくり返すところがないので、そこは置けません。");
			System.out.println("もう一度");
			System.out.println();
			displayBoard();
		}
		
		return possible;
	}
	
	public boolean upperLeft(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		int moving_x = x;
		
		if(y < 1 || x < 1) {
			return false;
		} else {
			moving_y -= 1;
			moving_x -= 1;
			
			if(this.board[moving_y][moving_x].equals(EMPTY)) {
				return false;
			} else {
				if(this.board[moving_y][moving_x].equals(color)) {
					if(!possible_reverse) {
						return false;
					} else {
							return true;						
					}
				} else {
					possible_reverse = true;
					if(upperLeft(moving_x, moving_y, color, possible_reverse)) {
						chengeColor(moving_x, moving_y, color);
					}else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean upper(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		if(y < 1) {
			return false;
		} else {
			moving_y -= 1;
			
			if(this.board[moving_y][x].equals(EMPTY)) {
				return false;
			} else {
				if(this.board[moving_y][x].equals(color)) {
					if(!possible_reverse) {
						return false;
					} else {
							return true;						
					}
				} else {
					possible_reverse = true;
					if(upper(x, moving_y, color, possible_reverse)) {
						chengeColor(x, moving_y, color);
					}else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean upperRight(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		int moving_x = x;
		
		if(y < 1 || x > 6) {
			return false;
		} else {
			moving_y -= 1;
			moving_x += 1;
			
			if(this.board[moving_y][moving_x].equals(EMPTY)) {
				return false;
			} else {
				if(this.board[moving_y][moving_x].equals(color)) {
					if(!possible_reverse) {
						return false;
					} else {
							return true;						
					}
				} else {
					possible_reverse = true;
					if(upperRight(moving_x, moving_y, color, possible_reverse)) {
						chengeColor(moving_x, moving_y, color);
					}else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean right(int x, int y, String color, boolean possible_reverse) {
		int moving_x = x;
		if(x > 6) {
			return false;
		} else {
			moving_x += 1;
			
			if(this.board[y][moving_x].equals(EMPTY)) {
				return false;
			} else {
				if(this.board[y][moving_x].equals(color)) {
					if(!possible_reverse) {
						return false;
					} else {
							return true;						
					}
				} else {
					possible_reverse = true;
					if(right(moving_x, y, color, possible_reverse)) {
						chengeColor(moving_x, y, color);
					}else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean downRight(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		int moving_x = x;
		
		if(y > 6 || x > 6) {
			return false;
		} else {
			moving_y += 1;
			moving_x += 1;
			
			if(this.board[moving_y][moving_x].equals(EMPTY)) {
				return false;
			} else {
				if(this.board[moving_y][moving_x].equals(color)) {
					if(!possible_reverse) {
						return false;
					} else {
							return true;						
					}
				} else {
					possible_reverse = true;
					if(downRight(moving_x, moving_y, color, possible_reverse)) {
						chengeColor(moving_x, moving_y, color);
					}else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean down(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		if(y > 6) {
			return false;
		} else {
			moving_y += 1;
			
			if(this.board[moving_y][x].equals(EMPTY)) {
				return false;
			} else {
				if(this.board[moving_y][x].equals(color)) {
					if(!possible_reverse) {
						return false;
					} else {
							return true;						
					}
				} else {
					possible_reverse = true;
					if(down(x, moving_y, color, possible_reverse)) {
						chengeColor(x, moving_y, color);
					}else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean downleft(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		int moving_x = x;
		
		if(y > 6 || x < 1) {
			return false;
		} else {
			moving_y += 1;
			moving_x -= 1;
			
			if(this.board[moving_y][moving_x].equals(EMPTY)) {
				return false;
			} else {
				if(this.board[moving_y][moving_x].equals(color)) {
					if(!possible_reverse) {
						return false;
					} else {
							return true;						
					}
				} else {
					possible_reverse = true;
					if(downleft(moving_x, moving_y, color, possible_reverse)) {
						chengeColor(moving_x, moving_y, color);
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean left(int x, int y, String color, boolean possible_reverse) {
		int moving_x = x;
		if(x < 1) {
			return false;
		} else {
			moving_x -= 1;
			
			if(this.board[y][moving_x].equals(EMPTY)) {
				return false;
			} else {
				if(this.board[y][moving_x].equals(color)) {
					if(!possible_reverse) {
						return false;
					} else {
							return true;						
					}
				} else {
					possible_reverse = true;
					if(left(moving_x, y, color, possible_reverse)) {
						chengeColor(moving_x, y, color);
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	// TODO : 置ける場所に☆を表示
}