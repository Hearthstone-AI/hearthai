package hearthAI;

public class AI{

	public static void determineMove(State s, int d, int min, int max, boolean max, int player){
		if ((d == 0) || (s.gameOver() !=0)) return s;
		if (max){
			int v = -1;
			ArrayList<State>
			for each child of node
				v := max(v, alphabeta(child, d - 1, α, β, FALSE))
				α := max(α, v)
				if β ≤ α
					break (* β cut-off *)
			return v
		}
		else
		v := ∞
		for each child of node
			v := min(v, alphabeta(child, d - 1, α, β, TRUE))
			β := min(β, v)
			if β ≤ α
				break (* α cut-off *)
		return v
	}
	//alphabeta(originstate, maxdepth, -∞, +∞, TRUE)
}