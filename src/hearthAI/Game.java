package hearthAI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {

	final static boolean DEBUGPRINT = false;
	final static String cardCSVFile = "res/hunter.csv";

	public static void main(String[] args) {

		//Full Card List
		//Stored as array for each class of cards
		@SuppressWarnings("unchecked")
		ArrayList<Card>[] cardList = (ArrayList<Card>[]) new ArrayList[10];
		for(int i = 0; i < 10; i++) cardList[i] = new ArrayList<Card>();
		
		Player p1 = new Player();
		Player p2 = new Player();

		Game.parseCSV(cardList);
		
		if(DEBUGPRINT)
			for(ArrayList<Card> list : cardList)
				for(Card c : list) c.print();
		
		/*
		 * for(int i = 0; i < 30; i++){ int idx = new
		 * Random().nextInt(cardList.size()); Card add = cardList.get(idx);
		 * System.out.println("Adding card: "); add.print(); p1.addToDeck(add);
		 * } for(int i = 0; i < 30; i++){ int idx = new
		 * Random().nextInt(cardList.size()); Card add = cardList.get(idx);
		 * p2.addToDeck(add); } System.out.println(
		 * "Deck of player 1: ------------------"); p1.printDeck();
		 * System.out.println("Deck of player 2: ------------------");
		 * p2.printDeck();
		 * 
		 * while(p1.getHealth()!= 0 && p2.getHealth()!= 0){ //Alternate turns
		 * until game over }
		 */

	}
	
	//Csv parse helper method
	private static void parseCSV(ArrayList<Card>[] cardList) {

		try {
			// Read file and get data
			FileReader fr = new FileReader(cardCSVFile);
			BufferedReader br = new BufferedReader(fr);

			String line;
			
			//Iterate through file
			while ((line = br.readLine()) != null) {
				String[] splitLine = line.split(",");
				if (DEBUGPRINT) System.out.println(splitLine[CSV.Name.val()] + " " + splitLine[CSV.Type.val()]);
				
				//Process card class
				int cardClass = 0;
				if(splitLine.length > CSV.Class.val()){
					switch(splitLine[CSV.Class.val()]){
					case "Warrior" : cardClass = Class.Warrior.val();break;
					case "Shaman" : cardClass = Class.Shaman.val();break;
					case "Rouge" : cardClass = Class.Rouge.val();break;
					case "Paladin" : cardClass = Class.Paladin.val();break;
					case "Druid" : cardClass = Class.Druid.val();break;
					case "Hunter" : cardClass = Class.Hunter.val();break;
					case "Warlock" : cardClass = Class.Warlock.val();break;
					case "Mage" : cardClass = Class.Mage.val();break;
					case "Priest" : cardClass = Class.Priest.val();break;
					}
				}
				
				//Process Card Type and add to appropriate card list by class
				if (splitLine[CSV.Type.val()].equals("Minion")) {
					Minion m = new Minion(splitLine);
					cardList[cardClass].add(m);
				} else if (splitLine[CSV.Type.val()] == "Spell") {
					Spell s = new Spell(splitLine);
					cardList[cardClass].add(s);
				}
			}
			
			br.close();
			fr.close();
			
		}
		catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
}
