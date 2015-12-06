package hearthAI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

	public static void main(String[] args){
		
		ArrayList<Card> cards = parseCSV();
		Player p1 = new Player();
		Player p2 = new Player();
		
		
		for(int i = 0; i < 30; i++){
			int idx = new Random().nextInt(cards.size());
			Card add = cards.get(idx);
			System.out.println("Adding card: ");
			add.print();
			p1.addToDeck(add);
		}
		for(int i = 0; i < 30; i++){
			int idx = new Random().nextInt(cards.size());
			Card add = cards.get(idx);
			p2.addToDeck(add);
		}
		System.out.println("Deck of player 1: ------------------");
		p1.printDeck();
		System.out.println("Deck of player 2: ------------------");
		p2.printDeck();
		
	}
	
<<<<<<< Updated upstream
	private static ArrayList<Card> parseCSV(){
		final String fileLoc = "C:/Users/Owner/Desktop/hunter.csv";
=======
	private static String[] parseCSV(){
		final String fileLoc = "../../hunter.csv";
>>>>>>> Stashed changes
		String fileData = null;
        String cards[] = null;
        String record = null;
        ArrayList<Card> allcards = new ArrayList();
 
        try{
            //Read file and get data
            fileData = getData(fileLoc);
            
            //If file does not have data, exit program
            if(fileData.length() == 0){
                System.out.println("File does not have data");
                System.exit(0);
            }
            
            //Split over newline to get records
            cards = fileData.split(System.getProperty("line.separator"));
            
            //For all records in file
            for(int i=0; i<cards.length; i++){
                //call function to remove comma in data
                record = remCommaFmData(cards[i]);
                
                //Get data from record
                String[] recordData = record.split(",");
                
                //Write to console after changing !@ back to ,
                /*System.out.println(recordData[0].replaceAll("!@", ",") + " -- " +
                        recordData[1].replaceAll("!@", ",") + " -- " +
                        recordData[2].replaceAll("!@", ",") + " -- " +
                        recordData[3].replaceAll("!@", ",") + " -- " +
                        recordData[4].replaceAll("!@", ",") + " -- " +
                        recordData[5].replaceAll("!@", ",") + " -- " +
                        recordData[4].replaceAll("!@", ",") + " -- " +
                        recordData[4].replaceAll("!@", ","));*/
                if(recordData[1] == "Minion"){
                	
                	Minion m = new Minion(recordData);
                	allcards.add(m);
                }
                else{
                	
                	Spell s = new Spell(recordData);
                	allcards.add(s);
                }
                
                        
            }
        }catch(Exception e){
            System.out.println("An error occurred: " + e.getMessage());
        }
        return allcards;
	}
	
	/**
     * 
     * This function does the magic of replacing commas in data with !@ 
     * 
     */
    private static String remCommaFmData(String record) {
         String output = record;
        
         //Create a pattern which detects comma in data
        Pattern p = Pattern.compile("\"[a-zA-Z0-9 ]+[,][ a-zA-Z0-9]+\"");
        
        //Match the pattern on the record
        Matcher matcher = p.matcher(output);
        
        //For each pattern match replace , with !@
        while(matcher.find()){
            String replacement = matcher.group().replaceAll(",", "!@");
            output = output.replaceAll( matcher.group(), replacement);
        }
        
        //return record with , replaced with !@ in data
        return output;
    }
 
    /**
     * 
     * Function to read file.
     * 
     */
    private static String getData(String fileLoc) throws IOException {
        FileReader fr = new FileReader(fileLoc);
        BufferedReader br = new BufferedReader(fr); 
 
        String data = br.readLine();
        StringBuffer buff = new StringBuffer();
 
        while(data != null){
            buff.append(data + System.getProperty("line.separator"));
            data=br.readLine();
        }
 
        br.close();
        fr.close();
        
        return buff.toString();
		
	}
	
}
