package hearthAI;

import java.util.ArrayList;

public class AI{

	private State determineMove(State s, int d, int min, int max, boolean maximize,int player){
		boolean first = false;
		if(player == 1) first = true;
		if ((d == 0) || (s.gameOver() !=0)) return s;
		if (maximize){
			State v = new State();
			v.setStateValue(-1000); 
			ArrayList<State> maxarray = s.enumerateMoves(first, true);
			for(State child : maxarray){
				State maxtemp = determineMove(child, d - 1, min, max, false, player);
				if(v.getStateValue(first) < maxtemp.getStateValue(first)) v = maxtemp;
				if(min < v.getStateValue(first)) min = v.getStateValue(first);
				if (max >= min)
					break;
			}
			return v;
		}
		else{
			State v = new State();
			v.setStateValue(1000); 
			ArrayList<State> minarray = s.enumerateMoves(!first, false);
			for(State child : minarray){ 
				State mintemp = determineMove(child, d - 1, min, max, false, player);
				if(v.getStateValue(!first) > mintemp.getStateValue(!first)) v = mintemp;
				if(max > v.getStateValue(!first)) min = v.getStateValue(!first);
				if (max >= min)
					break;
			}
			return v;
		}
	}

	//alphabeta(originstate, maxdepth, -∞, +∞, TRUE)
}