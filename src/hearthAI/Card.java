package hearthAI;

import java.util.ArrayList;

public class Card{

	protected int cost = 0;
	protected int costbuff = 0;
	protected int attack = 0;
	protected int attackbuff = 0;
	protected int health = 0;
	protected int healthbuff = 0;
	protected int damage = 0;
	protected int spell = 0;
	protected boolean taunt = false;
	protected boolean ready = false;
	protected boolean guru = false;
	protected Card battlecry = null;
	protected Card aura = null;
	protected String name = "";
	protected String race = "";
	protected String type = "";
	protected String target = "";
	protected String spelltype = "";
	
	public Card(){
	}
	
	public Card(String nm, int cst, int atk, int hp, String tp, String rc, String trg){
		name = nm;
		cost = cst;
		attack = atk;
		health = hp;
		type = tp;
		race = rc;
		target = trg;
	}
	
	public Card(String nm, int hp, int atk, String rc, String trg){
		name = nm;
		health = hp;
		attack = atk;
		race = rc;
		target = trg;
		type = "Aura";
	}
	
	public Card(String[] string) {
		name = string[CSV.Name.val()];
		type = string[CSV.Type.val()];
		if((string.length > CSV.Cost.val()) && !(string[CSV.Cost.val()].equals(""))) cost = Integer.parseInt(string[CSV.Cost.val()]);
		if((string.length > CSV.Attack.val()) && !(string[CSV.Attack.val()].equals(""))) attack = Integer.parseInt(string[CSV.Attack.val()]);
		if((string.length > CSV.Health.val()) && !(string[CSV.Health.val()].equals(""))) health = Integer.parseInt(string[CSV.Health.val()]);
		if((string.length > CSV.Race.val()) && !(string[CSV.Race.val()].equals(""))) race = string[CSV.Race.val()];
		if((string.length > CSV.Mech.val()) && !(string[CSV.Mech.val()].equals(""))){
			switch (string[CSV.Mech.val()]){
			case "Taunt" : taunt = true; break;
			case "Spellpower" : spell = Integer.parseInt(string[CSV.Special1.val()]); break;
			case "Charge" : ready = true; break;
			case "Battlecry" : 
			case "Aura" : specialHandler(); break;
			}
		}
		if(name.equals("Gurubashi Berserker")) guru = true;
		if(type.equals("Spell") && (string.length > CSV.Special1.val())) target = string[CSV.Special1.val()];
		if(type.equals("Spell") && (string.length > CSV.Special2.val())) spelltype = string[CSV.Special2.val()];
	}
	
	public void buff(Card c, boolean buff){
		if(buff){
			attackbuff += c.attack;
			healthbuff += c.health;
		}
		else{
			attackbuff -= c.attack;
			healthbuff -= c.health;
		}
		if(c.taunt) taunt = true;
	}
	
	public void buff(int cst, int atk, int hp){
		costbuff += cst;
		attackbuff += atk;
		healthbuff += hp;
	}
	
	public void buff(int cst, int atk, int hp, boolean tn){
		costbuff += cst;
		attackbuff += atk;
		healthbuff += hp;
		taunt = tn;
	}
	
	public int getBaseHealth(){ return health;}
	public int getBuffHealth(){ return healthbuff;}
	public int getTotalHealth(){ return health + healthbuff;}
	public int getEffectiveHealth(){ return health + healthbuff - damage;}
	
	public int getBaseAttack(){ return attack;}
	public int getBuffAttack(){ return attackbuff;}
	public int getTotalAttack(){ return attack + attackbuff;}
	
	public boolean getReady(){ return ready;}
	
	public Card getArua(){ return aura;}
	
	public String getType(){ return type;}
	
	public void setReady(){ ready = true;}
	
	public Card attacked(int hp){
		damage += hp;
		if (damage < 0) damage = 0;
		if (guru && (hp > 0)) attackbuff +=3;
		if(damage >= (health+healthbuff)) return this;
		else return null;
	}
	
	public Card attacked(Card m){
		int incoming = (m.attack + m.attackbuff);
		damage += incoming;
		if (damage < 0) damage = 0;
		if (guru && (incoming > 0)) attackbuff +=3;
		if(damage >= (health+healthbuff)) return this;
		else return null;
	}
	
	private void specialHandler(){
		switch(name){
		case "Darkscale Healer" : battlecry = makeCardFromName("Heal2All", Game.specialCards); break;
		case "Dragonling Mechanic" : battlecry = makeCardFromName("Mechanical Dragonling", Game.specialCards); break;
		case "Ironforge Rifleman" :
		case "Elven Archer" : battlecry = makeCardFromName("Damage1", Game.specialCards); break;
		case "Frostwolf Warlord" : battlecry = makeCardFromName("FrostWolf", Game.specialCards); break;
		case "Novice Engineer" : 
		case "Gnomish Inventor" : battlecry = makeCardFromName("Draw1", Game.specialCards); break;
		case "Grimscale Oracle" : aura = new Card("Aura", 0, 1, "Murloc", "All"); break;
		case "Murloc Tidehunter" : battlecry = makeCardFromName("Murloc Scout", Game.specialCards); break;
		case "Nightblade" : battlecry = makeCardFromName("DamageHero3", Game.specialCards); break;
		case "Radi Leader" : aura = new Card("Aura", 0, 1, "", "Friendly"); break;
		case "Razorfen Hunter" : battlecry = makeCardFromName("Boar", Game.specialCards); break;
		case "Shattered Sun Cleric" : battlecry = makeCardFromName("Buff1/1", Game.specialCards); break;
		case "Stormpike Commando"  : battlecry = makeCardFromName("Damage2", Game.specialCards); break;
		case "Stormwind Champion" : aura = new Card("Aura", 1, 1, "", "Friendly"); break;
		case "Voodoo Doctor" : battlecry = makeCardFromName("Heal2", Game.specialCards); break;
		case "Houndmaster" : battlecry = makeCardFromName("Hound", Game.specialCards); break;
		case "Starving Buzzard" : aura = new Card("Buzzard", 0, 0, "", ""); break;
		case "Timber Wolf" : aura = new Card("Aura", 0, 1, "Beast", "All"); break;
		}
	}
	
	public static Card makeCardFromName(String name, ArrayList<String[]> source){
		for(int i = 0; i < source.size(); i++){
			if(source.get(i)[0].equals(name)) return (new Card(source.get(i)));
		}
		System.out.println("Card not found: " + name);
		return null;
	}
	
	public void print(){
		System.out.println("Name: " + name + " Cost: " + cost + " Attack: " + attack + " Health: " + health);
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Name: " + name + " Cost: " + cost + " Attack: " + attack + " Health: " + health);
		return result.toString();
	}



}
