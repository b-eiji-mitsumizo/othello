package othello;

import java.util.Scanner;

public class Player {
	
	private String name;
	private String color;
	Scanner scanner = new Scanner(System.in);
	
	public Player(String name, String color){
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = name;
		this.color = color;		
	}
	
	// TODO : 置く場所を選ぶ関数
	public int[] choosePlace() {
		/*
		 * 0 : 縦(y軸)
		 * 1 : 横(x軸)
		 */
		int[] place = new int[2];
		
		while(true) {
			
			System.out.print("縦を0 ～ 7で選んでください。 > ");
			place[0] = scanner.nextInt();
			
			if(place[0] >= 0 && place[0] <= 7) {
				break;
			} else {
				System.out.println("0から7で選択してください。");
				System.out.println("もう一度。");
			}	
		}
		while(true) {
			System.out.print("横を0 ～ 7で選んでください。 > ");
			place[1] = scanner.nextInt();
			
			if(place[1] >= 0 && place[1] <= 7) {
				break;
			} else {
				System.out.println("0から7で選択してください。");
				System.out.println("もう一度。");
			}	
		}
		return place;
	}
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
