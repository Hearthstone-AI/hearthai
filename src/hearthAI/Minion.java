package hearthAI;

public class Minion implements Card {

	String name;
	int cost;
	int attack;
	int health;
	String special;
	
	public Minion(){
		name = "";
		cost = 0;
		attack = 0;
		health = 0;
		special = "";
	}
	
	public Minion(String[] string) {
		name = string[CSV.Name.val()];
		if((string.length > CSV.Cost.val()) && (string[CSV.Cost.val()] != "")) cost = Integer.parseInt(string[CSV.Cost.val()]);
		else cost = 0;
		if((string.length > CSV.Attack.val()) && (string[CSV.Attack.val()] != "")) attack = Integer.parseInt(string[CSV.Attack.val()]);
		else attack = 0;
		if((string.length > CSV.Health.val()) && (string[CSV.Health.val()] != "")) health = Integer.parseInt(string[CSV.Health.val()]);
		else health = 0;
		if(string.length > CSV.Text.val()) special = string[CSV.Text.val()];
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
