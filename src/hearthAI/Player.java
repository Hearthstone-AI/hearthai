package hearthAI;

import java.util.ArrayList;
import java.util.Stack;

public class Player extends Card{

	private int handSize = 0;
	private int availmana = 0;
	private int totalmana = 0;
	private int fieldSize = 0;
	private int heroclass = 0;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Card> field = new ArrayList<Card>();
	private Deck deck = new Deck();
	private Deck played = new Deck();
	
	public Player() {
		health = 30;
		heroclass = Class.Hunter.val();
		type = "Player";
	}
	
	public Player(int hp, int hs, int am, int tm,int fs,int hc, ArrayList<Card> hd, ArrayList<Card> fd, Deck dk, Deck pl) {
		health = hp;
		handSize = hs;
		availmana = am;
		totalmana = tm;
		fieldSize = fs;
		heroclass = hc;
		hand = hd;
		field = fd;
		deck = dk;
		played = pl;
		type = "Player";
	}
	
	public void setDeck(Deck d){ deck = d;}
	public void shuffleDeck(){ deck.shuffle();}
	
	public void draw(){
		Card c = deck.draw();
		if(c != null){
			handSize++;
			hand.add(c);
		}
		else System.out.println("Attempted Card Draw Faied: No more cards in deck");
	}
	
	public void addCardToHand(String name){
		hand.add(Card.makeCardFromName(name, Game.allCards));
	}
	
	public void playCard(Card c){
		
	}
	
	public void newTurn(){
		if(totalmana < 10) totalmana++;
		availmana = totalmana;
		draw();
	}
	
	public ArrayList<Card> getMinionTargets(){
		ArrayList<Card> re = new ArrayList<Card>();
		ArrayList<Card> temp = new ArrayList<Card>();
		boolean hasTaunt = false;
		for(Card c : field){
			if(c.taunt){
				re.add(c);
				hasTaunt = true;
			}
			else{
				temp.add(c);
			}
		}
		if(!hasTaunt){
			re.addAll(temp);
			re.add(this);
		}
		return re;
	}
	
	public ArrayList<Card> getSpellTargets(){
		ArrayList<Card> re = new ArrayList<Card>();
		re.addAll(field);
		re.add(this);
		return re;
	}
	
	public void printHand(){
		String print = "";
		for(Card c : hand) print += (c.getName() + " | ");
		System.out.println(print);
	}
	
	public void printField(){
		String print = "";
		for(Card c : field) print += (c.getName() + " | ");
		System.out.println(print);
	}
	
	public void printDeck(){ deck.print();}
}