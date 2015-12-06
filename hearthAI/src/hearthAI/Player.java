package hearthAI;

public class Player {

	Card deck[];
	int total = 0;
	Card field[];
	Card graveyard[];
	Card hand[];
	
	public Player(Card d[]){
		deck = d;
	}
	
	public Player() {
		// TODO Auto-generated constructor stub
	}

	public void draw(){
		total--;
		
	}
	
	public void printDeck(){
		for(Card c: deck){
			c.print();
		}
	}
	
	public void addToDeck(Card c){
		deck[total] = c;
		total++;
		
	}
	
	
	
	
	
	
}
