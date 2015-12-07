package hearthAI;

public enum Class {
	Neutral(0),
	Warrior(1),
	Shaman(2),
	Rouge(3),
	Paladin(4),
	Druid(5),
	Hunter(6),
	Warlock(7),
	Mage(8),
	Priest(9);
	
	int val;
	
	private Class(int i){ val = i;}
	public int val(){ return val;}
}
