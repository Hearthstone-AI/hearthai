package hearthAI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Game {

	final static boolean DEBUGPRINT = false;
	final static boolean GAMECONSOLE = true;
	final static boolean CONSOLEIN = false;
	final static String cardCSVFile = "res/cards.csv";
	final static String specialCSVFile = "res/special.csv";
	final static String p1Deck = "res/decklist.csv";
	final static String p2Deck = "res/decklist.csv";
	final static boolean USECUSTOM1 = true;
	final static boolean USECUSTOM2 = true;
	final static ArrayList<String[]> allCards = new ArrayList<String[]>(); 
	final static ArrayList<String[]> specialCards = new ArrayList<String[]>(); 

	public static void main(String[] args) {

		//Full Card List
		//Stored as Decks for each class of cards and a special class
		
		Deck[] cardList = new Deck[11];
		for(int i = 0; i < 11; i++) cardList[i] = new Deck();
		
		Game.parseCSV(specialCards, specialCSVFile);
		Game.parseCSV(allCards, cardCSVFile);
		Game.parseCSV(cardList, cardCSVFile);
		
		if(GAMECONSOLE)
			System.out.println("All Parsed");
		
		if(DEBUGPRINT)
			for(int i = 0; i < 11; i++) cardList[i].print();
		
		State gameState = new State();
		
		if(USECUSTOM1){
			gameState.setDeck(makeCustomDeck(p1Deck), 1);
			if(DEBUGPRINT)
				gameState.getPlayer(1).printDeck();
		}
		if(USECUSTOM2){
			gameState.setDeck(makeCustomDeck(p2Deck), 2);
			if(DEBUGPRINT)
				gameState.getPlayer(2).printDeck();
		}
		
		if(GAMECONSOLE)
			System.out.println("Custom Decks Loaded");
		
		int first = (int) (Math.random() * 2) + 1;
		gameState.startGame(first);
		
		if(GAMECONSOLE){
			System.out.println("Game Started");
			System.out.println("Going First: " + first);
			System.out.print("Player 1 Hand: ");
			gameState.getPlayer(1).printHand();
			System.out.print("Player 2 Hand: ");
			gameState.getPlayer(2).printHand();
		}
		//Alternate turns until game over
		while(gameState.gameOver() > 0){
			
		} 
	}
	
	//CSV parse helper method writing to deck
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
		catch (Exception e) { System.out.println("An error occurred: " + e.getMessage());}
	}
	
	//CSV parse helper method writing to CSV array
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
		catch (Exception e) { System.out.println("An error occurred: " + e.getMessage());}
	}

	//Creating a custom deck from a file
	private static Deck makeCustomDeck(String file){
		Deck deck = new Deck();
		try {
			// Read file and get data
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
	
			String line;

			//Iterate through file
			while ((line = br.readLine()) != null) {
				String[] splitLine = line.split(",");
				String card = splitLine[0];
				deck.addCard(Card.makeCardFromName(card, allCards));
			}
			br.close();
			fr.close();
			return deck;
		}
		catch (Exception e) { System.out.println("An error occurred: " + e.getMessage());}
		return deck;
	}
}
