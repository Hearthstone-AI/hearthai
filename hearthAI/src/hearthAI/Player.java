package hearthAI;

import java.util.Stack;

public class Player {

	Stack<Card> deck = new Stack();
	//Card deck[];
	int total = 0;
	Card field[];
	Card graveyard[];
	Card hand[];
	int handSize;
	
	
	
	public Player() {
		
	}

	public void draw(){
		total--;
		handSize++;
		
	}
	
	public void printDeck(){
		
	}
	
	public void addToDeck(Card c){
		deck.push(c);
		total++;
		
	}
	
	
	
	
	
	
}
