DROP TABLE USER;
DROP TABLE CONTACT;
DROP TABLE CITY;
DROP TABLE REVIEW;
DROP TABLE RESTAURANT;
DROP TABLE HOURS;
DROP TABLE RCONTACT;
DROP TABLE MENU;
DROP TABLE FOOD_ENTRIES;
CREATE TABLE USER
(
    UserID int UNIQUE NOT NULL,
    NAME VARCHAR(30) NOT NULL,
    FAVORITE_RESTAURANT INT,
    PRIMARY KEY (UserID),
    FOREIGN KEY (FAVORITE_RESTAURANT) REFERENCES RESTAURANT (RID)
);

CREATE TABLE CONTACT
(
    EMAIL VARCHAR(30) NOT NULL,
    PhoneNumber INT NOT NULL,
    UID INT UNIQUE NOT NULL,
    FOREIGN KEY (UID) REFERENCES USER (UserID),
    PRIMARY KEY (UID)
);

-- When creating data we only need 5 cities and their corresponding states
CREATE TABLE CITY
(
    NAME VARCHAR(30) NOT NULL,
    STATE VARCHAR(30) NOT NULL,
    PRIMARY KEY (NAME, STATE)
);

CREATE TABLE REVIEW
(
    RATING INT NOT NULL,
    UID INT UNIQUE NOT NULL,
    RID INT UNIQUE NOT NULL,
    FOREIGN KEY(UID) REFERENCES USER(UserID),
    FOREIGN KEY(RID) REFERENCES RESTUARANT(RID)
);

CREATE TABLE RESTAURANT
(
    RID INT UNIQUE NOT NULL,
    ADDRESS VARCHAR(50) NOT NULL,
    CITY VARCHAR(30) NOT NULL,
    STATE VARCHAR(30) NOT NULL,
    CUISINE VARCHAR(20) NOT NULL,
    NAME VARCHAR(50) NOT NULL,
    PRIMARY KEY (RID),
    FOREIGN KEY (CITY) REFERENCES CITY(NAME),
    FOREIGN KEY (STATE) REFERENCES CITY(STATE)
);

CREATE TABLE HOURS
(
    START_TIME VARCHAR (15),
    END_TIME VARCHAR (15),
    TIME_NAME VARCHAR(20),
    REST_ID INT UNIQUE NOT NULL,
    PRIMARY KEY (TIME_NAME),
    FOREIGN KEY (REST_ID) REFERENCES RESTAURANT(RID)
);

CREATE TABLE RCONTACT
(
    EMAIL VARCHAR (50) UNIQUE NOT NULL,
    PHONE_NUMBER CHAR(10) UNIQUE NOT NULL,
    REST_ID INT UNIQUE NOT NULL,
    PRIMARY KEY (EMAIL, PHONE_NUMBER),
    FOREIGN KEY (REST_ID) REFERENCES RESTAURANT(RID)
);

CREATE TABLE MENU
(
    LANGUAGES VARCHAR(30),
    R_ID INT UNIQuE NOT NULL,
    PRIMARY KEY (LANGUAGES),
    FOREIGN KEY (R_ID) REFERENCES RESTAURANT(RID)
);

CREATE TABLE FOOD_ENTRIES
(
    PRICES VARCHAR(15),
    NAME VARCHAR(60),
    R_MENUID INT NOT NULL,
    PRIMARY KEY (NAME),
    FOREIGN KEY (R_MENUID) REFERENCES MENU(R_ID)
);


INSERT INTO USER
values(
        1 , "Will", 1
);

INSERT INTO CONTACT
values(
        "wot@uw.edu" , 425311777, 1
);
INSERT INTO CONTACT 
values(
    "email@email.email", 43231177, 1
);

INSERT INTO CITY
values(
        "Lynnwood" , "Washington"
);

INSERT INTO REVIEW
values(
        5, 1, 1
);

INSERT INTO RESTAURANT
values(
        1, "1232 A Food Place", "Lynwood", "Washington", "Mexican", "El Diablo"
);

INSERT INTO HOURS
values(
        "8:00 AM", "4:00 PM", "Operating Hours", 1
);

INSERT INTO RCONTACT
values(
        "ElDiabblo@food.yum", 4254322345, 1
);

INSERT INTO MENU
values(
"English", 1
);

INSERT INTO FOOD_ENTRIES
values(
    "$10.00", "Tamales", 1
);
INSERT INTO FOOD_ENTRIES
values(
    "$12.00", "Tacos", 1
);
INSERT INTO FOOD_ENTRIES
values(
    "$3.00", "Pork", 1
);
INSERT INTO FOOD_ENTRIES
values(
    "$6.00", "Tortillas", 1
);
INSERT INTO FOOD_ENTRIES
values(
    "$30.00", "Steak", 1
);

-- Display's all food entires for a certian rest named "BLANK"
SELECT *
FROM FOOD_ENTRIES
WHERE(R_MENUID = (SELECT RID FROM RESTAURANT WHERE NAME = "El Diablo"));

-- TODO Display cheapest food

-- TODO Display most expensive food

-- TODO Display the most expensive food in each enthic catagory

-- TODO Display the least expensive food in each ethnic catagory

-- TODO Display the cheapest food in each town

-- TODO Display the most expensive food in each town 

-- TODO Display highest rated restaurant in each city 

-- TODO Display highest rated restaurant in each state

-- TODO Display highest rated restaurant in each ethnic group and price 

-- TODO add more functionality 