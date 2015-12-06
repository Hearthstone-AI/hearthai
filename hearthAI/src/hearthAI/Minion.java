package hearthAI;

public class Minion implements Card {

	int cost = 0;
	String name = "";
	int attack = 0;
	int health = 0;
	
	
	public Minion(String[] string) {
		name = string[0];
		cost = Integer.parseInt(string[1]);
		attack = Integer.parseInt(string[2]);
		health = Integer.parseInt(string[3]);
	}



}
