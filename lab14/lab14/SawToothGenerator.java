package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        state = 0;
        this.period = period;
    }

    private double normalize(double state) {
        return 2 * state / (period * 1 - 1) - 1;
    }

    public double next() {
        state = (state + 1);
        return normalize(state % period);
    }
}
