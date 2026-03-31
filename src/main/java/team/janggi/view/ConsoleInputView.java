package team.janggi.view;

import java.util.Arrays;
import java.util.Scanner;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.JanggiFormation;
import team.janggi.util.Parser;

public class ConsoleInputView {
    private static final int Y_COUNT = 10; // 행(Row)
    private static final int X_COUNT = 9;  // 열(Col)
    private static final int SETUP_CHOICE_MIN = 1;

    private static final String TITLE_CHO_FORMATION_SETUP = "초나라 상차림을 선택하세요.";
    private static final String TITLE_HAN_FORMATION_SETUP = "한나라 상차림을 선택하세요.";

    private static final String INVALID_SETUP_CHOICE_MESSAGE =
            SETUP_CHOICE_MIN + "부터 " + JanggiFormation.values().length + "까지의 숫자를 입력하세요.";
    private static final String SELECT_SETUP_CHOICE_MESSAGE = "선택 (" + SETUP_CHOICE_MIN + "-" + JanggiFormation.values().length + "): ";

    private static final String PROMPT_MOVE_SOURCE_SUFFIX = "움직일 기물 좌표 (x y): ";
    private static final String PROMPT_MOVE_DESTINATION_SUFFIX = "도착 좌표 (x y): ";

    private static final String INVALID_COORDINATE_MESSAGE =
            String.format("가로(0~%d), 세로(0~%d) 형식으로 공백을 넣어 입력하세요. (예: 0 6)", X_COUNT - 1, Y_COUNT - 1);


    private final Scanner scanner = new Scanner(System.in);

    public int readChoFormation() {
        printLine(TITLE_CHO_FORMATION_SETUP);
        printLine(INVALID_SETUP_CHOICE_MESSAGE);
        printSetup();
        printText(SELECT_SETUP_CHOICE_MESSAGE);
        return Parser.parseByInteger(scanner.nextLine(), "[ERROR] 상차림 번호는 숫자만 입력해주세요.");
    }

    public int readHanFormation() {
        printLine(TITLE_HAN_FORMATION_SETUP);
        printLine(INVALID_SETUP_CHOICE_MESSAGE);
        printSetup();
        printText(SELECT_SETUP_CHOICE_MESSAGE);
        return Parser.parseByInteger(scanner.nextLine(), "[ERROR] 상차림 번호는 숫자만 입력해주세요.");
    }

    private void printSetup() {
        Arrays.stream(JanggiFormation.values()).forEach(
                setup -> printLine(setup.getNumber() + ". " + setup.getName())
        );
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