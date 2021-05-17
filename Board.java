package othello;

public class Board {
	
	private String[][] board;
	private String[][] copyBoard;
	final int MAS_NUMBER;
	
	final String BLACK = "●";
	final String WHITE = "〇";
	final String EMPTY = "　"; 
	
	/**
	 * コンストラクタ
	 * @param マスの大きさ
	 */
	public Board(int masNum) {
		this.MAS_NUMBER = masNum;
		this.board = new String[MAS_NUMBER][MAS_NUMBER];
		
		for(int i = 0; i < MAS_NUMBER; i++) {
			for(int j = 0; j < MAS_NUMBER; j++) {
				this.board[i][j]=  EMPTY; 
			}
		}
		
		/**
		 * 真ん中に黒を２つと白を２つ
		 */
		this.board[(MAS_NUMBER / 2) - 1][(MAS_NUMBER / 2) - 1] = WHITE;
		this.board[(MAS_NUMBER / 2)][(MAS_NUMBER / 2)] = WHITE;
		
		this.board[(MAS_NUMBER / 2) - 1][(MAS_NUMBER / 2)] = BLACK;
		this.board[(MAS_NUMBER / 2)][(MAS_NUMBER / 2) - 1] = BLACK;
		
		this.copyBoard = new String[MAS_NUMBER][MAS_NUMBER];
		
	}
	
	
	/**
	 * ボードを表示する。
	 * @return void
	 */
	public void displayBoard() {
		String headString = "   ";
		for(int i = 0; i < MAS_NUMBER; i++) {
			headString += i + "　| ";
		}
		
		System.out.println(headString);
		if(MAS_NUMBER == 8) {
			System.out.println("  -----------------------------------------");
		}else if(MAS_NUMBER == 4) {
			System.out.println("  ------------------");
		}
		
		for(int iy = 0; iy < MAS_NUMBER; iy++) {
			String rowString = iy + "  ";
			for(int jx = 0; jx < MAS_NUMBER; jx++) {
				rowString += this.board[iy][jx] + " | ";			
			}
			System.out.println(rowString);
			if(MAS_NUMBER == 8) {
				System.out.println("  -----------------------------------------");
			}else if(MAS_NUMBER == 4) {
				System.out.println("  ------------------");
			}
		}
	}
	
	/**
	 * コマの数を数え、勝者を表示する関数
	 * @param player1 : 黒
	 * @param player2 ; 白
	 */
	public void checkWinner(Player player1, Player player2) {
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
		
		System.out.println(player1.getName() + "さん：" + numOfBlack);
		System.out.println(player2.getName() + "さん：" + numOfWhite);
		
		if(numOfBlack < numOfWhite) {
			System.out.println(player2.getName() + "さんの勝ち");
		} else if(numOfBlack > numOfWhite) {
			System.out.println(player1.getName() + "さんの勝ち");
		} else {
			System.out.println("引き分け");
		}
	}
	
	/**
	 * コマを置けるかどうかをチェックする ＋ ひっくり返す。
	 * @param turnPlayer : ターン中のプレイヤー
	 * @return 
	 * true :  コマを置けた。
	 * false : パスを使った。
	 */
	public boolean checkIfAddKoma(Player turnPlayer){
		while(true) {
			int[] place = turnPlayer.choosePlace();
			if(place[0] == -1 && place[1] == -1) {
				System.out.println("パスですね。");
				return false;
			}
			if(this.board[place[0]][place[1]] != EMPTY) {
				System.out.println("もうすでに埋められている場所なのでやり直してください。");
				continue;
			} else if(checkAround(place[0], place[1], turnPlayer.getColor())){
				this.board[place[0]][place[1]] = turnPlayer.getColor();
				return true;
			}
		}
	}
	
	/**
	 * コマをひっくり返す関数
	 * @param x : 横軸(右が＋)
	 * @param y : 縦軸(下が＋)
	 * @param color : Player.color
	 */
	public void chengeColor(int x, int y, String color) {
		if(color.equals(WHITE)){ 
			this.board[y][x] = WHITE;
		} else {
			this.board[y][x] = BLACK;
		}
		
	}
	
	/**
	 * 8方向にひっくり返せる部分があるかをチェック
	 * @param x : 横軸(右が＋)
	 * @param y : 縦軸(下が＋)
	 * @param color : Player.color
	 * @return
	 * 			true  :  ひっくり返すコマがあった。
	 * 			false :  ひっくり返すコマがなかった。
	 */			
	public boolean checkAround(int y, int x, String color) {
		
		boolean[] check = new boolean[8];
		
		
		check[0] = upperLeft(x, y, color, false);
		check[1] = upper(x, y, color, false);
		check[2] = upperRight(x, y, color, false);
		check[3] = right(x, y, color, false);
		check[4] = downRight(x, y, color, false);
		check[5] = down(x, y, color, false);
		check[6] = downleft(x, y, color, false);
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
		
		if(y < 1 || x > MAS_NUMBER - 2){
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
		if(x > MAS_NUMBER - 2) {
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
		
		if(y > MAS_NUMBER - 2 || x > MAS_NUMBER - 2) {
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
		if(y > MAS_NUMBER - 2) {
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
		
		if(y > MAS_NUMBER - 2 || x < 1) {
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
	
	public boolean checkPutKoma(int x, int y, Player turningPlayer){
		if(this.board[y][x] != EMPTY) {
				return false;
		} else if(justCheckAround(y,x, turningPlayer.getColor())){
				return true;
		} else {
			return false;
		}
	}
	
	// TODO : 置ける場所に☆を表示
	public void displayPossiblePut(Player playingPlayer) {
		for(int i = 0; i < MAS_NUMBER; i++) {
			for(int j = 0; j < MAS_NUMBER; j++) {
				this.copyBoard[i][j] = this.board[i][j];
			}
		}
		
		String putStar = "☆";
		boolean possibleToPut = false;
		
		for(int iy = 0; iy < MAS_NUMBER; iy++ ) {
			for(int jx = 0; jx < MAS_NUMBER; jx++) {
				possibleToPut = checkPutKoma(jx, iy, playingPlayer);
				if(possibleToPut) {
					this.copyBoard[iy][jx]= putStar;
				}
				possibleToPut = false;
			}
		}
		copydisplayBoard();
	}
	
	public void copydisplayBoard() {
		String headString = "   ";
		for(int i = 0; i < MAS_NUMBER; i++) {
			headString += i + "　| ";
		}
		
		System.out.println(headString);
		if(MAS_NUMBER == 8) {
			System.out.println("  -----------------------------------------");
		}else if(MAS_NUMBER == 4) {
			System.out.println("  ------------------");
		}
		
		for(int iy = 0; iy < MAS_NUMBER; iy++) {
			String rowString = iy + "  ";
			for(int jx = 0; jx < MAS_NUMBER; jx++) {
				rowString += this.copyBoard[iy][jx] + " | ";			
			}
			System.out.println(rowString);
			if(MAS_NUMBER == 8) {
				System.out.println("  -----------------------------------------");
			}else if(MAS_NUMBER == 4) {
				System.out.println("  ------------------");
			}
		}
	}
	public boolean justCheckAround(int y, int x, String color) {
		boolean[] check = new boolean[8];
		// TODO : 左上
//		upperLeft();
		check[0] = checkupperLeft(x, y, color, false);

//		// TODO : 上
		check[1] = checkupper(x, y, color, false);
//		// TODO : 右上
//		upperRight();
		check[2] = checkupperRight(x, y, color, false);
//		// TODO : 右
		check[3] = checkright(x, y, color, false);
//		right();
//		// TODO : 右下
		check[4] = checkdownRight(x, y, color, false);
//		downRight();
//		// TODO : 下
		check[5] = checkdown(x, y, color, false);
//		down();
//		// TODO : 左下
		check[6] = checkdownleft(x, y, color, false);
//		downleft();
		// TODO : 左
		check[7] = checkleft(x, y, color, false);	
		
		boolean possible = false;
		for(boolean eachCheck : check) {
			if(eachCheck) {
				possible = true;
				break;
			}
		}
		return possible;
	}
	
	public boolean checkupperLeft(int x, int y, String color, boolean possible_reverse) {
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
					if(checkupperLeft(moving_x, moving_y, color, possible_reverse)) {
						return true;
					}else {
						return false;
					}
				}
			}
		}
	}
	
	public boolean checkupper(int x, int y, String color, boolean possible_reverse) {
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
					if(checkupper(x, moving_y, color, possible_reverse)) {
						return true;
					}else {
						return false;
					}
				}
			}
		}
	}
	
	public boolean checkupperRight(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		int moving_x = x;
		
		if(y < 1 || x > MAS_NUMBER - 2){
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
					if(checkupperRight(moving_x, moving_y, color, possible_reverse)) {
						return true;
					}else {
						return false;
					}
				}
			}
		}
	}
	
	public boolean checkright(int x, int y, String color, boolean possible_reverse) {
		int moving_x = x;
		if(x > MAS_NUMBER - 2) {
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
					if(checkright(moving_x, y, color, possible_reverse)) {
						return true;
					}else {
						return false;
					}
				}
			}
		}
	}
	
	public boolean checkdownRight(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		int moving_x = x;
		
		if(y > MAS_NUMBER - 2 || x > MAS_NUMBER - 2) {
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
					if(checkdownRight(moving_x, moving_y, color, possible_reverse)) {
						return true;
					}else {
						return false;
					}
				}
			}
		}
	}
	
	public boolean checkdown(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		if(y > MAS_NUMBER - 2) {
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
					if(checkdown(x, moving_y, color, possible_reverse)) {
						return true;
					}else {
						return false;
					}
				}
			}
		}
	}
	
	public boolean checkdownleft(int x, int y, String color, boolean possible_reverse) {
		int moving_y = y;
		int moving_x = x;
		
		if(y > MAS_NUMBER - 2 || x < 1) {
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
					if(checkdownleft(moving_x, moving_y, color, possible_reverse)) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}
	
	public boolean checkleft(int x, int y, String color, boolean possible_reverse) {
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
					if(checkleft(moving_x, y, color, possible_reverse)) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}
}