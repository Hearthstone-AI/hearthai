package hearthAI;

public class Spell implements Card{

	int cost = 0;
	String name = "";
	
	public Spell(String[] string) {
		name = string[0];
		cost = Integer.parseInt(string[2]);
	}
	
	public void print(){
		System.out.println("Name: " + name + " Cost: " + cost);
	}
	
	@Override public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Name: " + name + " Cost: " + cost);
		return result.toString();
	}

}
