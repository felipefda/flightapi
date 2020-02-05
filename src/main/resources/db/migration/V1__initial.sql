create table REQUEST (
    ID SERIAL not null PRIMARY KEY,
    CREATED_ON TIMESTAMP NOT NULL,
    IP_ADDRESS varchar(50) not null,
    FROM_CODE varchar(3) not null,
    DESTINY varchar(100) not null,
    DATE_FROM varchar(10),
    DATE_TO varchar(10),
    CURRENCY varchar(3) not null
);

create table CURRENCY (
    NAME varchar(3) not null PRIMARY KEY
);

INSERT INTO CURRENCY VALUES ('BRL');
INSERT INTO CURRENCY VALUES ('GBP');
INSERT INTO CURRENCY VALUES ('EUR');