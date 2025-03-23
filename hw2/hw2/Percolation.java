package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int N;
    private WeightedQuickUnionUF wquuf;
    private int virtualTopSite;
    private int virtualBottomSite;
    private int openSiteSum;
    private WeightedQuickUnionUF wquufWithBackWash;
    /**
     * 创建一个N * N的二维网格结构
     * @param N 边长
     */
    public Percolation(int N) {
        this.N = N;
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.grid = new boolean[N][N];
        this.wquuf = new WeightedQuickUnionUF(N * N + 2);
        this.virtualTopSite = N * N;
        this.virtualBottomSite = N * N + 1;
        this.openSiteSum = 0;
        this.wquufWithBackWash = new WeightedQuickUnionUF(N * N + 1);
    }

    /**
     * 将特定行列转换为一个数字index
     * @param row 行
     * @param col 列
     * @return index
     */
    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    /**
     * 如果特定行列的site没有被打开，则打开
     * @param row 行
     * @param col 列
     */
    public void open(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        } else {
            if (!grid[row][col]) {
                grid[row][col] = true;
                openSiteSum++;
            } else {
                return;
            }
        }

        int index = xyTo1D(row, col);
        // 将上方的虚拟节点连接到第一行
        if (row == 0) {
            wquuf.union(virtualTopSite, index);
            wquufWithBackWash.union(virtualTopSite, index);
        }
        // 将下方的虚拟节点连接到最后一行
        if (row == N - 1) {
            wquuf.union(virtualBottomSite, index);
        }
        // 现开始考虑四周如果是打开的情况，与之相连
        // 上边，原节点至少是第二行
        if (row > 0 && isOpen(row - 1, col)) {
            wquuf.union(xyTo1D(row - 1, col), index);
            wquufWithBackWash.union(xyTo1D(row - 1, col), index);
        }
        // 下边，原节点至多是倒数第二行
        if (row < N - 1 && isOpen(row + 1, col)) {
            wquuf.union(xyTo1D(row + 1, col), index);
            wquufWithBackWash.union(xyTo1D(row + 1, col), index);
        }
        // 左边，至少是第二列
        if (col > 0 && isOpen(row, col - 1)) {
            wquuf.union(xyTo1D(row, col - 1), index);
            wquufWithBackWash.union(xyTo1D(row, col - 1), index);
        }
        // 右边，至多是倒数第二列
        if (col < N - 1 && isOpen(row, col + 1)) {
            wquuf.union(xyTo1D(row, col + 1), index);
            wquufWithBackWash.union(xyTo1D(row, col + 1), index);
        }
    }

    /**
     * 判断特定行列的site是否已经打开
     * @param row 行
     * @param col 列
     * @return 若时打开的则返回true，反之false
     */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    /**
     * 判断特定行列的site是否已经关闭
     * @param row 行
     * @param col 列
     * @return 若已关闭返回true，反之false
     */
    public boolean isFull(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
        int index = xyTo1D(row, col);
        return wquufWithBackWash.connected(index, virtualTopSite);
    }

    /**
     * @return 已经打开了多少个site
     */
    public int numberOfOpenSites() {
        return openSiteSum;
    }

    /**
     * 判断此系统是否可以被渗透
     * @return 若可以返回true，反之false
     */
    public boolean percolates() {
        return wquuf.connected(virtualTopSite, virtualBottomSite);
    }

    public static void main(String[] args) {

    }
}
