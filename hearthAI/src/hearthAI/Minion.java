package hearthAI;

public class Minion implements Card {

	int cost = 0;
	String name = "";
	int attack = 0;
	int health = 0;
	String special = "";
	
	public Minion(String[] string) {
		name = string[0];
		cost = Integer.parseInt(string[2]);
		attack = Integer.parseInt(string[3]);
		health = Integer.parseInt(string[4]);
		special = string[5];
	}
	
	public void print(){
		System.out.println("Name: " + name + " Cost: " + cost + " Attack: " + attack + " Health: " + health + " Special: " + special);
	}
	
	@Override public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Name: " + name + " Cost: " + cost + " Attack: " + attack + " Health: " + health);
		return result.toString();
	}



}
