/* count the number of movies that have been rated*/
SELECT count(*) from (SELECT distinct movie_id FROM movies4us.RATINGS as t1) t1;

/* find the user(s) with the hgighest number of group membership */
select query1.* from
       (SELECT USER_ID, Count(*) AS group_count
       FROM movies4us.FRIENDS
       GROUP BY movies4us.FRIENDS.USER_ID) query1,

       (select max(query2.group_count) as highest_count
       from (SELECT USER_ID, Count(*) AS group_count
       FROM movies4us.FRIENDS
       GROUP BY movies4us.FRIENDS.USER_ID) query2) query3
    where query1.group_count = query3.highest_count;
