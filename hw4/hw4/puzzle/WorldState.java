package hw4.puzzle;

public interface WorldState {
    /** Provides an estimate of the number of moves to reach the goal.
     * Must be less than or equal to the correct distance.
     * 提供一个到达终点距离的估计值，必须小于等于正确的距离 */
    int estimatedDistanceToGoal();

    /** Provides an iterable of all the neighbors of this WorldState.
     * 提供一个可迭代的邻居*/
    Iterable<WorldState> neighbors();

    default boolean isGoal() {
        return estimatedDistanceToGoal() == 0;
    }
}
