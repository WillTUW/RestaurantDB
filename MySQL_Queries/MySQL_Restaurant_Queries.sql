-- ----------------- QUERIES ----------------------------------------------
-- Displays the names of all the RESTAURANTs
SELECT RESTAURANT.name
 FROM RESTAURANT;
 
-- Display's all food entires for a certian RESTAURANT named "Corwin Inc"
 SELECT *
 FROM FOOD_ENTRIES
 WHERE(FOOD_ENTRIES.r_menu_id = 
	(SELECT RESTAURANT.rI
    FROM RESTAURANT
    WHERE RESTAURANT.name = "Corwin Inc"));
-- Displays all review attributes that have a rating between 3 and 5 inclusive
 SELECT *
 FROM REVIEW
 WHERE REVIEW.rating >= 3 
 AND REVIEW.rating <= 5;
--
 -- FROM THIS LINE BELOW I HAVE TESTED
  -- Gives all reviews
  SELECT *
 FROM   RCONTACT
 INNER JOIN REVIEW ON
 RCONTACT.rest_id = REVIEW.rid;
 
 
 -- All reviews including null
 SELECT *
 FROM   RCONTACT
 LEFT JOIN REVIEW ON
 RCONTACT.rest_id = REVIEW.rid;
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
 -- Lists all rest
 SELECT *
 FROM  CITY
 LEFT JOIN RESTAURANT ON
 CITY.name = RESTAURANT.rCity;

-- Display cheapest food
 SELECT *
 FROM FOOD_ENTRIES
 WHERE FOOD_ENTRIES.price = (SELECT MIN(price) FROM FOOD_ENTRIES);
--
-- Display most expensive food
 SELECT FOOD_ENTRIES.name, FOOD_ENTRIES.price
 FROM FOOD_ENTRIES
 WHERE FOOD_ENTRIES.price = (SELECT MAX(price) FROM FOOD_ENTRIES);
--
 -- Display the most expensive food in each enthic catagory
 SELECT FOOD_ENTRIES.price , FOOD_ENTRIES.name, RESTAURANT.cuisine
 FROM FOOD_ENTRIES, RESTAURANT
 WHERE FOOD_ENTRIES.r_menu_id = RESTAURANT.rID AND FOOD_ENTRIES.price IN(SELECT MAX(price)
                            FROM FOOD_ENTRIES, MENU, RESTAURANT
                            WHERE r_menu_id = r_id AND r_id = rID
                            GROUP BY cuisine)
 ORDER BY price ASC;
--
 -- Display the least expensive food in each ethnic catagory
 SELECT FOOD_ENTRIES.price , FOOD_ENTRIES.name, RESTAURANT.name
 FROM FOOD_ENTRIES, RESTAURANT
 WHERE FOOD_ENTRIES.r_menu_id = RESTAURANT.rID AND FOOD_ENTRIES.price IN(SELECT MIN(price)
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

-- Lists all rest above a 3 star rating
SELECT RESTAURANT.name
FROM RESTAURANT, REVIEW
WHERE 3 < (SELECT AVG(REVIEW.rating)
FROM REVIEW as r, RESTAURANT as rst
WHERE r.rID = rst.rID)
GROUP BY RESTAURANT.name;
