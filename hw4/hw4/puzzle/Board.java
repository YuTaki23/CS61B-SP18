package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;


public class Board implements WorldState{
    private static final int BLANK = 0;
    private int[][] tiles;

    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, tiles.length);
        }
    }

    public int tileAt(int i, int j) {
        if (i > size() || i < 0 || j > size() || j < 0) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return tiles.length;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int haming() {
        int res = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tiles[i][j] == BLANK || tiles[i][j] != i * size() + j + 1) {
                    res++;
                }
            }
        }
        return res;
    }

    public int manhatten() {
        int res = 0;
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tiles[i][j] != BLANK) {
                    res += Math.abs((tiles[i][j] - 1) / size() - j) + Math.abs((tiles[i][j] - 1) % size() - j);
                }
            }
        }
        return res;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhatten();
    }

    @Override
    public boolean equals(Object y) {
        return Arrays.deepEquals(this.tiles, ((Board) y).tiles);
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
