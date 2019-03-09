-- ----------------- QUERIES ----------------------------------------------
-- Displays the names of all the RESTAURANTs
SELECT RESTAURANT.name
 FROM RESTAURANT;
 
-- Display's all food entires for a certian RESTAURANT named "Corwin Inc"
 SELECT *
 FROM FOOD_ENTRIES
 WHERE(FOOD_ENTRIES.r_menu_id = 
	(SELECT RESTAURANT.rID
    FROM RESTAURANT
    WHERE RESTAURANT.name = "Corwin Inc"));
-- Displays all review attributes that have a rating between 3 and 5 inclusive
 SELECT *
 FROM REVIEW
 WHERE REVIEW.rating >= 3 
 AND REVIEW.rating <= 5;
 -- Joins
 SELECT *
 FROM   RCONTACT
 LEFT JOIN REVIEW ON
 RCONTACT.rest_id = REVIEW.rid;
--
--
 -- Convoluted join example
 SELECT *
 FROM  RCONTACT,CITY
 LEFT JOIN REVIEW ON
 RCONTACT.rest_id = REVIEW.rid
 INNER JOIN RESTAURANT ON
 CITY.name = RESTAURANT.rCity
 ORDER BY REVIEW.rating DESC;
--
 -- Nested
 SELECT *
 FROM  RCONTACT
 WHERE RCONTACT.rest_id =
    (SELECT RESTAURANT.rid
    FROM RESTAURANT
    WHERE RESTAURANT.rid =
        (SELECT MENU.r_id
        FROM MENU));
--
 -- Simple
 SELECT *
 FROM  CITY
 LEFT JOIN RESTAURANT ON
 CITY.name = RESTAURANT.rCity;

-- -- TODO Display cheapest food
 SELECT *
 FROM FOOD_ENTRIES
 WHERE FOOD_ENTRIES.price = (SELECT MIN(price) FROM FOOD_ENTRIES);
--
-- TODO Display most expensive food
 SELECT *
 FROM FOOD_ENTRIES
 WHERE FOOD_ENTRIES.price = (SELECT MAX(price) FROM FOOD_ENTRIES);
--
 -- TODO Display the most expensive food in each enthic catagory
 SELECT *
 FROM FOOD_ENTRIES
 WHERE FOOD_ENTRIES.price IN(SELECT MAX(price)
                            FROM FOOD_ENTRIES, MENU, RESTAURANT
                            WHERE r_menu_id = r_id AND r_id = rID
                            GROUP BY cuisine)
 ORDER BY price ASC;
--
 -- TODO Display the least expensive food in each ethnic catagory
 SELECT *
 FROM FOOD_ENTRIES
 WHERE FOOD_ENTRIES.price IN(SELECT MIN(price)
                            FROM FOOD_ENTRIES, MENU, RESTAURANT
                            WHERE r_menu_id = r_id AND r_id = rID
                            GROUP BY cuisine)
 ORDER BY price ASC;
--
 -- TODO Display the cheapest food in each town
 SELECT *
 FROM FOOD_ENTRIES
 WHERE FOOD_ENTRIES.price IN(SELECT MIN(price)
                            FROM FOOD_ENTRIES, MENU, RESTAURANT, CITY
                            WHERE r_menu_id = r_id AND r_id = rID AND rCity = CITY.name
                            GROUP BY CITY.name)
 ORDER BY price ASC;
--
 -- TODO Display the most expensive food in each town
 SELECT *
 FROM FOOD_ENTRIES
 WHERE FOOD_ENTRIES.price IN(SELECT MAX(price)
                            FROM FOOD_ENTRIES, MENU, RESTAURANT, CITY
                            WHERE r_menu_id = r_id AND r_id = rID AND rCity = CITY.name
                            GROUP BY CITY.name)
 ORDER BY price ASC;
--
-- -- TODO Display highest rated restaurant in each CITY
--
-- -- TODO Display highest rated restaurant in each state
--
-- -- TODO Display highest rated restaurant in each ethnic group and price
--
-- -- TODO add more functionality