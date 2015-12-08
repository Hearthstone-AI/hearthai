package hearthAI;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck;
	private int size = 0;
	
	public Deck(){
		deck = new ArrayList<Card>();
	}
	
	public void addCard(Card c){
		deck.add(c);
		size++;
	}
	
	public void appendDeck(Deck d){
		while(!d.isEmpty()){
			addCard(d.draw());
			size++;
		}
	}
	
	public void shuffle(){
		deck.trimToSize();
		Collections.shuffle(deck);
	}
	
	public void trim(){
		deck.trimToSize();
	}
	
	public boolean isEmpty(){
		return !(size > 0);
	}
	
	public Card draw(){
		if(size > 0){ return deck.remove(--size);}
		else return null;
	}
	
	public void print(){
		for(int i = 0; i < size; i++){
			deck.get(i).print();
		}
	}
}
