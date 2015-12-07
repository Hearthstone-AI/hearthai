package hearthAI;

import java.util.Collections;
import java.util.Stack;

public class Deck {
	private Stack<Card> deck;
	private int size;
	
	public Deck(){
		deck = new Stack<Card>();
		size = 0;
	}
	
	public void addCard(Card c){
		deck.push(c);
		size++;
	}
	
	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	public Card draw(){
		size--;
		return deck.pop();
	}
	
	public void print(){
		for(int i = 0; i < size; i++){
			deck.elementAt(i).print();
		}
	}
}
