create table Art
(
    ArtName text not null,
    ArtUrl  text not null,
    ComUrl  text not null
);
insert into Art ('ArtName', 'ArtUrl', 'ComUrl')
values ('介绍', '/home/kic/Ablog/Art/介绍.md', '/home/kic/Ablog/Com/介绍.md');