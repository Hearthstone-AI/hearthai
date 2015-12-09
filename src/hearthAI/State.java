package hearthAI;

import java.util.ArrayList;

public class State {
	
	private Player player1;
	private Player player2;
	
	public State(){
		player1 = new Player();
		player2 = new Player();
	}

	public State(Player p1, Player p2){
		player1 = p1;
		player2 = p2;
	}
	
	public void startGame(int first){
		player1.shuffleDeck();
		player2.shuffleDeck();
		player1.draw();
		player1.draw();
		player1.draw();
		player2.draw();
		player2.draw();
		player2.draw();
		if (first == 1){
			player2.draw();
			player2.addCardToHand("The Coin");
		}
		else if (first == 2){
			player1.draw();
			player1.addCardToHand("The Coin");
		}
	}
	
	public int gameOver(){
		if (!(player1.getEffectiveHealth() < 0)) return 2;
		else if (!(player2.getEffectiveHealth() < 0)) return 1;
		else return 0;
	}
	
	public int getStateValue(boolean p1){
		if (p1) return (player1.getEffectiveHealth() - player2.getEffectiveHealth());
		else return (player2.getEffectiveHealth() - player1.getEffectiveHealth());
	}
	
	public void setStateValue(int i){
		player1.healthbuff = i;
	}
	
	public ArrayList<State> enumerateMoves(boolean first, boolean enemy){
		return null;
	}
	
	public void setDeck(Deck d, int p){
		if (p == 1) player1.setDeck(d);
		else if (p == 2) player2.setDeck(d);
	}
	
	public Player getPlayer(int p){
		if (p == 1) return player1;
		else if (p == 2) return player2;
		return null;
	}
}