package team.janggi.domain;

import team.janggi.domain.strategy.layout.normal.NormalLayoutStrategy;
import team.janggi.domain.strategy.layout.normal.NormalSetup;

public class EmptyLayoutStrategy extends NormalLayoutStrategy {
    public static final EmptyLayoutStrategy instance = new EmptyLayoutStrategy();

    public EmptyLayoutStrategy() {
        super(NormalSetup.바깥상차림, NormalSetup.바깥상차림);
    }

    @Override
    public void init(BoardStatus boardStatus) {
        // do nothing
    }
}
