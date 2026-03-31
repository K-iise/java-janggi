package team.janggi.view;

import java.util.Scanner;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.NormalSetup;

public class ConsoleInputView {
    private static final int Y_COUNT = 10; // 행(Row)
    private static final int X_COUNT = 9;  // 열(Col)
    private static final int SETUP_CHOICE_MIN = 1;

    private static final String TITLE_CHO_NORMAL_SETUP = "초나라 상차림을 선택하세요.";
    private static final String TITLE_HAN_NORMAL_SETUP = "한나라 상차림을 선택하세요.";

    private static final String PROMPT_MOVE_SOURCE_SUFFIX = "움직일 기물 좌표 (x y): ";
    private static final String PROMPT_MOVE_DESTINATION_SUFFIX = "도착 좌표 (x y): ";

    private static final String INVALID_COORDINATE_MESSAGE =
            String.format("가로(0~%d), 세로(0~%d) 형식으로 공백을 넣어 입력하세요. (예: 0 6)", X_COUNT - 1, Y_COUNT - 1);

    private static final String INVALID_SETUP_CHOICE_MESSAGE =
            SETUP_CHOICE_MIN + "부터 " + NormalSetup.values().length + "까지의 숫자를 입력하세요.";

    private final Scanner scanner = new Scanner(System.in);

    public NormalSetup readChoNormalSetup() {
        return readNormalSetup(TITLE_CHO_NORMAL_SETUP);
    }

    public NormalSetup readHanNormalSetup() {
        return readNormalSetup(TITLE_HAN_NORMAL_SETUP);
    }

    public Position readMoveSource(Team currentTurn) {
        return readCoordinate(turnPrefix(currentTurn) + PROMPT_MOVE_SOURCE_SUFFIX);
    }

    public Position readMoveDestination(Team currentTurn) {
        return readCoordinate(turnPrefix(currentTurn) + PROMPT_MOVE_DESTINATION_SUFFIX);
    }

    private String turnPrefix(Team team) {
        return switch (team) {
            case CHO -> "초 차례 — ";
            case HAN -> "한 차례 — ";
            case NONE -> "";
        };
    }

    private Position readCoordinate(String prompt) {
        while (true) {
            printText(prompt);
            String line = scanner.nextLine().trim();
            Position parsed = tryParsePosition(line);
            if (parsed != null) {
                return parsed;
            }
            printLine(INVALID_COORDINATE_MESSAGE);
        }
    }

    private NormalSetup readNormalSetup(String titleLine) {
        final NormalSetup[] setups = NormalSetup.values();
        NormalSetup readNormalSetup = null;
        do {
            printLine(titleLine);
            printSetup(setups);
            readNormalSetup = parseNormalSetup(setups);
        } while (readNormalSetup == null);

        return readNormalSetup;
    }

    private NormalSetup parseNormalSetup(NormalSetup[] setups) {
        printText("선택 (" + SETUP_CHOICE_MIN + "-" + setups.length + "): ");
        try {
            int setupChoice = Integer.parseInt(scanner.nextLine().trim());
            if (setupChoice >= SETUP_CHOICE_MIN && setupChoice <= setups.length) {
                return setups[setupChoice - SETUP_CHOICE_MIN];
            }
        } catch (NumberFormatException ignored) {
        }
        printLine(INVALID_SETUP_CHOICE_MESSAGE);
        return null;
    }

    private void printSetup(NormalSetup[] setups) {
        for (int i = 0; i < setups.length; i++) {
            printLine((i + SETUP_CHOICE_MIN) + ". " + setups[i].name());
        }
    }

    private Position tryParsePosition(String line) {
        final String[] parts = line.split("\\s+");
        if (parts.length < 2) {
            return null;
        }

        try {
            // 입력을 (열 행) 즉 (x y) 순서로 받습니다.
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            boolean xOk = x >= 0 && x < X_COUNT;
            boolean yOk = y >= 0 && y < Y_COUNT;

            // 도메인의 Position(x, y) 생성자에 그대로 매핑합니다.
            if (xOk && yOk) {
                return new Position(x, y);
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void printText(String text) {
        System.out.print(text);
    }

    private void printLine(String text) {
        System.out.println(text);
    }
}