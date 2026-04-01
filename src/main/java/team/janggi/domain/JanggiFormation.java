package team.janggi.domain;

import java.util.Arrays;

public enum JanggiFormation {
    EHEH(1, "상마상마"),
    HEHE(2, "마상마상"),
    EHHE(3, "상마마상"),
    HEEH(4, "마상상마");

    private final int number;
    private final String name;

    JanggiFormation(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static JanggiFormation from(int selectNumber) {
        return Arrays.stream(JanggiFormation.values())
                .filter(setup -> setup.number == selectNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 배치 선택 번호입니다: " + selectNumber));
    }

    public int getNumber(){
        return number;
    }

    public String getName(){
        return name;
    }
}
