package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private double[] threshold;
    private Percolation p;
    /**
     * 在 N * N的网格上进行T独立实验
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.N = N;
        this.T = T;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.threshold = new double[T];

        for (int i = 0; i < T; i++) {
            // 初始化site，全为blocked
            this.p = pf.make(N);
            while (!p.percolates()) {
                // 随机打开一个site
                int randomRow = StdRandom.uniform(N);
                int randomCol = StdRandom.uniform(N);
                p.open(randomRow, randomCol);
            }
            threshold[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    /**
     * 渗流阈值取样平均值
     */
    public double mean() {
        return StdStats.mean(threshold);
    }

    /**
     * 渗流阈值的样本标准差
     */
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    /**
     * 置信区间左边界
     */
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }

    /**
     * 置信区间右边界
     */
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }
}
