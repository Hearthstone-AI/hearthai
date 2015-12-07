package hearthAI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Game {

	final static boolean DEBUGPRINT = false;
	final static boolean CONSOLEIN = false;
	final static String cardCSVFile = "res/cards.csv";
	final static String specialCSVFile = "res/special.csv";
	final static ArrayList<String[]> specialCards = new ArrayList<String[]>(); 

	public static void main(String[] args) {

		//Full Card List
		//Stored as Decks for each class of cards and a special class
		
		Deck[] cardList = new Deck[11];
		for(int i = 0; i < 11; i++) cardList[i] = new Deck();
		
		Game.parseCSV(specialCards, specialCSVFile);
		Game.parseCSV(cardList, cardCSVFile);
		
		if(DEBUGPRINT)
			for(int i = 0; i < 11; i++) cardList[i].print();
		
		Player p1 = new Player();
		Player p2 = new Player();
		
		 
		while(p1.getHealth()!= 0 && p2.getHealth()!= 0){
			//Alternate turns until game over
		} 
	}
	
	//CSV parse helper method
	private static void parseCSV(Deck[] cardList, String file) {

		try {
			// Read file and get data
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line;
			
			//Iterate through file
			while ((line = br.readLine()) != null) {
				String[] splitLine = line.split(",");
				if (DEBUGPRINT)
					System.out.println(splitLine[CSV.Name.val()] + " " + splitLine[CSV.Type.val()]);
				
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
					Card m = new Card(splitLine);
					cardList[cardClass].addCard(m);
				} else if (splitLine[CSV.Type.val()].equals("Spell")) {
					Card s = new Card(splitLine);
					cardList[cardClass].addCard(s);
				}
			}
			
			br.close();
			fr.close();
			
		}
		catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
	
	//CSV parse helper method
		private static void parseCSV(ArrayList<String[]> cardList, String file) {

			try {
				// Read file and get data
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);

				String line;
				
				//Iterate through file
				while ((line = br.readLine()) != null) {
					String[] splitLine = line.split(",");
					if (DEBUGPRINT)
						System.out.println(splitLine[CSV.Name.val()] + " " + splitLine[CSV.Type.val()]);
					cardList.add(splitLine);
				}
				br.close();
				fr.close();
			}
			catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
		}
}
