/* count the number of movies that have been rated*/
SELECT count(*) from (SELECT distinct movie_id FROM movies4us.RATINGS as t1) t1;
