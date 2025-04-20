package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayDeque;

public class Solver {
    public class SearchNode implements Comparable<SearchNode> {
        private WorldState ws;
        private int move;
        private SearchNode prev;
        private int estimatedDistanceToGoalSN;

        public SearchNode(WorldState ws, int move, SearchNode prev) {
            this.ws = ws;
            this.move = move;
            this.prev = prev;
            this.estimatedDistanceToGoalSN = ws.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.move + this.estimatedDistanceToGoalSN - (o.move + o.estimatedDistanceToGoalSN);
        }
    }

    private int move;
    private SearchNode x;
    private MinPQ<SearchNode> mpq = new MinPQ<>();

    public Solver(WorldState initial) {
        SearchNode initSN = new SearchNode(initial, 0, null);
        mpq.insert(initSN);

        SearchNode x = mpq.delMin();
        while (!x.ws.isGoal()) {
            for (WorldState neighbor : x.ws.neighbors()) {
                if (x.prev == null || neighbor.equals(x.prev.ws)) {
                    SearchNode node = new SearchNode(neighbor, x.move + 1, x);
                    mpq.insert(node);
                }
            }
            x = mpq.delMin();
        }
        move = x.move;
        }

    public int moves() {
        return move;
    }

    public Iterable<WorldState> solution() {
        Stack<WorldState> stack = new Stack<>();
        SearchNode thisSearchNode = x;
        while (thisSearchNode != null) {
            stack.push(thisSearchNode.ws);
            thisSearchNode = thisSearchNode.prev;
        }
        return stack;
    }
}
