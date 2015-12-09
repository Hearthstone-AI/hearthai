package hearthAI;

import java.util.ArrayList;
import java.util.Stack;

public class Player extends Card{

	private int handSize = 0;
	private int availmana = 0;
	private int totalmana = 0;
	private int fieldsize = 0;
	private int heroclass = 0;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Card> field = new ArrayList<Card>();
	private Deck deck = new Deck();
	private Deck history = new Deck();
	
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
		fieldsize = fs;
		heroclass = hc;
		hand = hd;
		field = fd;
		deck = dk;
		history = pl;
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
	
	public Card playMinion(Card c, boolean fromhand){
		field.add(c);
		fieldsize++;
		if(fromhand) availmana -= c.cost;
		if(hand.remove(c)) handSize--;
		if(c.battlecry != null){
			return battlecry;
		}
		if(c.aura != null){
			return aura;
		}
		return null;
	}
	
	public ArrayList<Card> playSpell(Card c, Card target, Card source, boolean fromhand){
		ArrayList<Card> re = new ArrayList<Card>();
		if(fromhand) availmana -= c.cost;
		if(hand.remove(c)) handSize--;
		if((c.spelltype == "Damage") || (c.spelltype == "Heal")){
			re.add(target.attacked(c));
			return re;
		}
		if(c.spelltype == "Buff"){
			target.buff(c, true);
			return null;
		}
		if(c.spelltype == "Draw"){
			draw();
			return null;
		}
		switch(c.name){
		case "Heal2All" :
			for(Card fd : field) c.attacked(-2); this.attacked(-2); return null;
		case "FrostWolf" : source.buff(0, (fieldsize - 1), (fieldsize - 1)); return null;
		case "DamageHero3" : target.attacked(3);
		case "Animal Companion" :
			int tmp = (int) (Math.random() * 3);
			if(tmp == 0) playMinion(Card.makeCardFromName("Misha", Game.specialCards), false);
			if(tmp == 1) playMinion(Card.makeCardFromName("Huffer", Game.specialCards), false);
			if(tmp == 2) playMinion(Card.makeCardFromName("Leokk", Game.specialCards), false);
			return null;
		case "Hunter's Mark" : target.healthbuff = -target.health + 1;
		case "Kill Command" : 
			boolean hasbeast = false;
			for(Card fd : field) if(fd.race.equals("Beast")) hasbeast = true;
			if(hasbeast){ re.add(target.attacked(5)); return re;}
			else{ re.add(target.attacked(3)); return re;}
		case "Multi-Shot" :
			int tmp1 = (int) (Math.random() * ((Player) target).field.size());
			int tmp2;
			while ((tmp2 = (int) (Math.random() * ((Player) target).field.size())) == tmp1){;}
			re.add(((Player) target).field.get(tmp1).attacked(3));
			re.add(((Player) target).field.get(tmp2).attacked(3));
			return re;
		case "Tracking" : 
			draw();
			deck.draw();
			deck.draw();
			return null;
		case "The Coin" :
			availmana++;
			return null;
		default : 
			System.out.println("Error: Playing Card - Card Not found");
		}
		return null;
	}

	public void handleAura(Card c, boolean play, boolean enemy){
		if((c.target.equals("All") || c.target.equals("Friendly")) && !enemy){
			if(!c.race.equals("")) for(Card fd : field) if(fd.race.equals(c.race)) fd.buff(c, play);
			else for(Card fd1 : field) fd1.buff(c, play);
		}
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
	
	public ArrayList<Card> getSpellTargets(Card c, boolean enemy){
		ArrayList<Card> re = new ArrayList<Card>();
		if(c.race.equals("")){
			if((c.spelltype.equals("Damage") && enemy) || (c.spelltype.equals("Heal") && !enemy)){
				re.add(this);
			}
			if(!c.spelltype.equals("Special")){
				re.addAll(field);
			}
			else{
				switch(c.name){
				case "Heal2All" : 
				case "Animal Companion" :
				case "Multi-Shot" :
				case "Tracking" :
				case "The Coin" : return null;
				case "Kill Command" : 
					if(enemy){
						re.add(this);
						re.addAll(field);
					}
					break;
				case "Hunter's Mark" : if(enemy) re.addAll(field); break;
				case "DamageHero3" : if(enemy) re.add(this); break;
				}
			}
		}
		else{
			for(Card fd : field){
				if(c.race.equals(fd.race)) re.add(fd);
			}
		}
		return re;
	}
	
	/*
	public ArrayList<Card> getAvailMoves(){
		for(Card hn : hand)
	}
	*/
	
	public int getAvailMana(){
		return availmana;
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