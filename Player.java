package othello;

import java.util.Scanner;

public class Player {
	
	private String name;
	private String color;
	Scanner scanner = new Scanner(System.in);
	final static String BLACK = "●";
	final static String WHITE = "〇";
	final int MAS_NUM;
	
	public Player(String name, String color, int masNum){
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = name;
		this.color = color;
		this.MAS_NUM = masNum;
	}
	
	// TODO : 置く場所を選ぶ関数
	public int[] choosePlace() {
		/*
		 * 0 : 縦(y軸)
		 * 1 : 横(x軸)
		 */
		int[] place = new int[2];
		
		while(true) {
			System.out.println(this.name + "さん(" + this.color + ")のターンです");
			System.out.println("置く場所がない時は、(縦 : -1, 横 : -1)に設定してください。");
			System.out.print("縦(y軸)を0～" + (MAS_NUM - 1) +"選んでください。 > ");
			place[0] = scanner.nextInt();
			
			if(place[0] >= -1 && place[0] <= MAS_NUM - 1) {
				break;
			} else {
				System.out.println("範囲内で選択してください。");
				System.out.println("もう一度。");
			}	
		}
		while(true) {
			System.out.print("横(x軸)を0 ～" +(MAS_NUM - 1) + "で選んでください。 > ");
			place[1] = scanner.nextInt();
			
			if(place[1] >= -1 && place[1] <= MAS_NUM - 1) {
				break;
			} else {
				System.out.println("範囲内で選択してください。");
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
