create table REPORT (
    ID int not null,
    IP_ADDRESS varchar(50) not null,
    FROM_CODE varchar(3) not null,
    DESTINY varchar(100) not null,
    DATE_FROM varchar(10),
    DATE_TO varchar(10),
    CURRENCY varchar(3) not null,
    STATUS varchar(20) not null
);

create table CURRENCY (
    NAME varchar(3) not null
);
