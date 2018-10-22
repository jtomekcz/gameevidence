- vytvoreni DB
create table PLAYER (
    id_player integer primary key auto_increment,
    alias varchar(255),
    winners_count number,
    losts_count number,
    valid varchar(1));

create table TEAM (
    id_team integer primary key auto_increment,
    id_player number,
    alias varchar(255),
    winners_count number,
    losts_count number,
    valid varchar(1),
    constraint FK_PlayerTeam foreign key (id_player) references Player(id_player));

create table GAME (
    id_game integer primary key auto_increment,
    id_winner_team number,
    alias varchar(255),
    valid varchar(1),
    constraint FK_PlayerTeam foreign key (id_winner_team) references Team(id_team));
