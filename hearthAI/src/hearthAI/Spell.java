package hearthAI;

public class Spell implements Card{

	int cost = 0;
	String name = "";
	
	public Spell(String[] string) {
		name = string[0];
		cost = Integer.parseInt(string[1]);
	}

}
