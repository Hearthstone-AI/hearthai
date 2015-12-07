package hearthAI;

import java.util.ArrayList;
import java.util.Stack;

public class Player extends Card{

	private int handSize = 0;
	private int availmana = 0;
	private int totalmana = 0;
	private int fieldSize;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Card> field = new ArrayList<Card>();
	private Deck deck = new Deck();
	private Deck played = new Deck();
	private Card hero = new Card();
	
	public Player() {
		health = 30;
		type = "Player";
	}
	
	public Player(int hp, int hs, int am, int tm,int fs, ArrayList<Card> hd, ArrayList<Card> fd, Deck dk, Deck pl) {
		health = hp;
		handSize = hs;
		availmana = am;
		totalmana = tm;
		fieldSize = fs;
		hand = hd;
		field = fd;
		deck = dk;
		played = pl;
		type = "Player";
	}

	public void draw(){
		handSize++;
		hand.add(deck.draw());
	}
	
	public void playCard(Card c){
		
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
	
	public int getHealth(){
		return health;
	}
	
	public void printDeck(){
		deck.print();
	}
	
	public void setDeck(Deck d){
		deck = d;
	}
}
