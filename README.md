# java-janggi

장기 미션 저장소

## 기능 요구 사항
- 게임 시작 시 보드 초기화를 진행한다.
  - 초나라(플레이어1) 플레이어는 번호로 제시된 마와 상의 4가지 유형(ex, 원앙상, 귀마, 양귀마, 양귀상)의 선택지를 출력한다.
  - 초나라 사용자로부터 제시된 선택지 번호를 입력 받는다.
  - 한나라(플레이어2) 플레이어는 번호로 제시된 마와 상의 4가지 유형(ex, 원앙상, 귀마, 양귀마, 양귀상)의 선택지를 출력한다.
  - 한나라 사용자로부터 제시된 선택지 번호를 입력 받는다.
- 게임이 시작되면, 플레이어는 턴을 번갈아 진행한다.(턴의 내용은 아래와 같다)
  - 매 턴 시작시 보드 상태를 갱신하여 출력한다.
  - 플레이어로부터 이동할 말의 이름을 입력받는다.
    - 입력받은 말의 이름이 유효한지 검증한다.
    - 입력받은 말이 살아있는지 검증한다.
  - 플레이어로부터 이동할 위치의 좌표를 입력받는다.
    - 입력받은 좌표가 장기 판의 크기를 벗어나지 않는지 검증한다.
    - 입력받은 좌표가 이동하려는 말의 이동 규칙에 맞는지 검증한다.
  - 입력 받은 위치로 기물을 이동 시킨다.
    - 이동한 위치에 상대방의 기물이 있다면, 상대방의 기물을 잡는다.
- 궁이 잡히면 게임이 종료된다.
  - 궁을 잡은 플레이어의 승리 메시지를 출력한다.(ex,"초나라 승리!", "한나라 승리!")

### 고급 기능 요구 사항
- 플레이어가 이동 가능한 말의 이동 경로를 출력한다.

---

## 도메인 구성

- Board: 장기판을 나타내는 클래스
  - Map으로 기물의 위치를 관리한다.
  - 보드 상태를 출력하는 메서드를 포함한다.
- Piece
  - pieceType:
  - PieceMovementStrategy:
  - 
- Position
  - row_index
  - colum_index
- PieceMovementStrategy : 기물의 이동 전략
- PieceType: 기물 종류를 나타내는 Enum
  - KING, GUARD, HORSE, ELEPHANT, CHARIOT, CANNON, SOLDIER, EMPTY
- PieceLayoutStrategy : 기물 배치 전략
- BoardStructureStrategy : 보드판 크기 생성 전략

- 아래 일급 객체로 래핑하기
Map<Position, Piece> map = 

interface MapStatus {
  move();

  remove();
  .
  .
  .
}

InMemoryMapStatus implements MapStatus {
  Map<Position, Piece> map = new HashMap<>();

hashsize(90)

// (new Postion(1000, 2000), piece) map
public void move(position) {
map.get(postion)
}
// MapStatus 인터페이스 구현
}

PlacementStrategy.init(MapStatus mapStatus) {
    // 기물 생성 및 배치 로직
}

- 피스 타입 enum에 전략을 주입받는다.
- 피스 타입을 초나라/한나라 둘다 구분해서 만든다.(그럼 team 필요없긴함)
  - team enum
  - 피스타입에 필드로 주입.
- team이랑 피스타입 래핑한다.