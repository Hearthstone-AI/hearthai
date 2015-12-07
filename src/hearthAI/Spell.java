package hearthAI;

public class Spell implements Card{

	int cost;
	String name;
	
	public Spell(){
		cost = 0;
		name = "";
	}
	
	public Spell(String[] string) {
		name = string[CSV.Name.val()];
		if ((string.length > CSV.Cost.val()) && (string[CSV.Cost.val()] != "")) cost = Integer.parseInt(string[CSV.Cost.val()]);
		else cost = 0;
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
