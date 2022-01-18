create table Art
(
    ArtName text not null,
    ArtUrl  text not null,
    ComUrl  text not null
);
insert into Art ('ArtName', 'ArtUrl', 'ComUrl')
values ('介绍', 'https://cdn.jsdelivr.net/gh/mingzhixian/BlogMd@master/Art/介绍.md',
        'https://cdn.jsdelivr.net/gh/mingzhixian/BlogMd@master/Com/介绍.md');