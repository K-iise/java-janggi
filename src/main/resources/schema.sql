CREATE TABLE IF NOT EXISTS game
(
    id           int auto_increment primary key,
    game_name    varchar(20) not null,
    current_turn varchar(5)  not null
);

CREATE TABLE IF NOT EXISTS board_piece
(
    id         int auto_increment primary key,
    game_id    int references game (id),
    type       varchar(10) not null,
    team       varchar(5)  not null,
    x_position int         not null,
    y_position int         not null
);