
CREATE TABLE RUSER
(
    userID int UNIQUE NOT NULL,
    name VARCHAR(30) NOT NULL,
    favorite_restaurant INT,
    PRIMARY KEY (userID),
    FOREIGN KEY (favorite_restaurant) REFERENCES RESTAURANT (rID)
);

CREATE TABLE CONTACT
(
    email VARCHAR(30) UNIQUE NOT NULL,
    phone_number VARCHAR(10) UNIQUE NOT NULL,
    uID INT NOT NULL,
    counter INT UNIQUE NOT NULL,
    FOREIGN KEY (uID) REFERENCES rUser

 (userID),
    PRIMARY KEY (counter)
);

-- When creating data we only need 5 cities and their corresponding states
CREATE TABLE city
(
    name VARCHAR(30) NOT NULL,
    state VARCHAR(30) NOT NULL,
    PRIMARY KEY (name, state)
);

CREATE TABLE REVIEW
(
    rating INT NOT NULL,
    uID INT UNIQUE NOT NULL,
    rID INT UNIQUE NOT NULL,
    review_day DATE NOT NULL,
    message TEXT,
    FOREIGN KEY(uID) REFERENCES rUser

(userID),
    FOREIGN KEY(rID) REFERENCES RESTUARANT(rID)
);

CREATE TABLE RESTAURANT
(
    rID INT UNIQUE NOT NULL,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(30) NOT NULL,
    cuisine VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (rID),
    FOREIGN KEY (city) REFERENCES city(name)
);

CREATE TABLE HOURS
(
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    time_name VARCHAR(20),
    rest_id INT UNIQUE NOT NULL,
    PRIMARY KEY (time_name),
    FOREIGN KEY (rest_id) REFERENCES RESTAURANT(rID)
);

CREATE TABLE RCONTACT
(
    email VARCHAR (50) UNIQUE NOT NULL,
    phone_number VARCHAR(10) UNIQUE NOT NULL,
    rest_id INT UNIQUE NOT NULL,
    PRIMARY KEY (email, phone_number),
    FOREIGN KEY (rest_id) REFERENCES RESTAURANT(rID)
);

CREATE TABLE MENU
(
    m_name VARCHAR(30) NOT NULL,
    r_id INT UNIQUE NOT NULL,
    LANGUAGE VARCHAR(30) DEFAULT 'English',
    PRIMARY KEY (m_name),
    FOREIGN KEY (r_id) REFERENCES RESTAURANT(rID)
);

CREATE TABLE FOOD_ENTRIES
(
    price DECIMAL(13,2) NOT NULL,
    name VARCHAR(60) NOT NULL,
    r_menu_id INT NOT NULL,
    m_name VARCHAR(30) NOT NULL,
    PRIMARY KEY (name),
    FOREIGN KEY (r_menu_id) REFERENCES MENU(r_id),
    FOREIGN KEY (m_name) REFERENCES MENU(m_name)
);


INSERT INTO RUSER
values(
        1 , "Will", 1
);

INSERT INTO RUSER
values(
        2 , "Willie Eato", 1
);

INSERT INTO RUSER
values(
        3 , "Dan Burger", 3
);

INSERT INTO CONTACT
values(
        "wot@uw.edu" , 425311777, 1, 1
);
INSERT INTO CONTACT 
values(
    "email@email.email", 43231177, 1, 2
);

INSERT INTO city
values(
        "Lynnwood" , "Washington"
);

INSERT INTO city
values(
        "BagelsHaven" , "Washington"
);

INSERT INTO city
values(
        "FallingLeaves" , "Washington"
);

--Dates follow 'YYYY-MM-DD' format

INSERT INTO REVIEW
values(
        4, 3, 1, date('2019-03-07'), 'Good restaurant'
);

INSERT INTO REVIEW
values(
        5, 2, 2, date('2019-03-01'), 'Great restaurant'
);

INSERT INTO REVIEW
values(
        3, 1, 3, date('2019-03-06'), 'Terrible restaurant'
);


INSERT INTO RESTAURANT
values(
        1, "1232 A Food Place", "Lynnwood", "Mexican", "El Diablo"
);

INSERT INTO RESTAURANT
values(
        2, "1581 Better Foods", "BagelsHaven", "Japanese", "Sushi"
);


INSERT INTO RESTAURANT
values(
        3, "2569 Fast Foods", "FallingLeaves", "Grease", "BurgerGreasy"
);


INSERT INTO HOURS
values(
        "8:00 AM", "4:00 PM", "Operating Hours", 1
);

INSERT INTO RCONTACT
values(
        "ElDiabblo@food.yum", 4254322345, 1
);

INSERT INTO RCONTACT
values(
        "sushi@yum.rice", 4251234444, 2
);

INSERT INTO RCONTACT
values(
        "burgresa@grease.yum", 4251235555, 3
);

INSERT INTO MENU
values(
"Lunch", 1, "English"
);

INSERT INTO FOOD_ENTRIES
values(
    "$10.00", "Tamales", 1, "Lunch"
);
INSERT INTO FOOD_ENTRIES
values(
    "$12.00", "Tacos", 1, "Lunch"
);
INSERT INTO FOOD_ENTRIES
values(
    "$3.00", "Pork", 1, "Lunch"
);
INSERT INTO FOOD_ENTRIES
values(
    "$6.00", "Tortillas", 1, "Lunch"
);
INSERT INTO FOOD_ENTRIES
values(
    "$30.00", "Steak", 1, "Lunch"
);

-- Display's all food entires for a certian rest named "El Diablo"
SELECT *
FROM FOOD_ENTRIES
WHERE(r_menu_id = (SELECT rID FROM RESTAURANT WHERE name = "El Diablo"));

SELECT *
FROM REVIEW
WHERE rating >= 1;
-- Joins
SELECT *
FROM   RCONTACT
LEFT JOIN REVIEW ON
RCONTACT.rest_id = REVIEW.rid;


-- Convoluted join example
SELECT *
FROM  RCONTACT,city
LEFT JOIN REVIEW ON
RCONTACT.rest_id = REVIEW.rid
INNER JOIN RESTAURANT ON
city.name = RESTAURANT.city
ORDER BY REVIEW.rating DESC;

-- Nested
SELECT *
FROM  RCONTACT
WHERE RCONTACT.rest_id =
    (SELECT RESTAURANT.rid
    FROM RESTAURANT
    WHERE RESTAURANT.rid = 
        (SELECT MENU.r_id
        FROM MENU));

-- Simple
SELECT *
FROM  city
LEFT JOIN RESTAURANT ON
city.name = RESTAURANT.city;

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

DROP TABLE RUSER;
DROP TABLE CONTACT;
DROP TABLE city;
DROP TABLE REVIEW;
DROP TABLE RESTAURANT;
DROP TABLE HOURS;
DROP TABLE RCONTACT;
DROP TABLE MENU;
DROP TABLE FOOD_ENTRIES;

