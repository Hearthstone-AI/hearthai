package hearthAI;

public enum CSV {
	Name(0),
	Type(1),
	Cost(2),
	Attack(3),
	Health(4),
	Text(5),
	Mech(6),
	Class(7),
	Race(8);
	
	int val;
	
	private CSV(int i){ val = i;}
	public int val(){ return val;}
}
