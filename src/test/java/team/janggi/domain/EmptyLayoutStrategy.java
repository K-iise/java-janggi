package team.janggi.domain;

import team.janggi.domain.strategy.layout.normal.NormalLayoutStrategy;
import team.janggi.domain.strategy.layout.normal.NormalSetup;

public class EmptyLayoutStrategy extends NormalLayoutStrategy {
    public static final EmptyLayoutStrategy instance = new EmptyLayoutStrategy();

    public EmptyLayoutStrategy() {
        super(NormalSetup.HEEH_LAYOUT, NormalSetup.HEEH_LAYOUT);
    }

    @Override
    public void init(BoardStatus boardStatus) {
        // do nothing
    }
}
