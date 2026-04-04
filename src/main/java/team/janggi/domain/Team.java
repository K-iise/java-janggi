package team.janggi.domain;

public enum Team {
    NONE(0.0),
    CHO(0.0),
    HAN(1.5);

    private final double handicap;

    Team(double handicap) {
        this.handicap = handicap;
    }

    public double getHandicap() {
        return handicap;
    }

}
