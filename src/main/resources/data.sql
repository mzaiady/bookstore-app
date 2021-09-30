-- Insert Reference Data --
-- Inserting Book Classification/Type 
INSERT INTO BS_CLASSIFICATION_TYPE (ID, NAME) VALUES (1,'General Works');
INSERT INTO BS_CLASSIFICATION_TYPE (ID, NAME) VALUES (2,'Philosophy');
INSERT INTO BS_CLASSIFICATION_TYPE (ID, NAME) VALUES (3,'Fiction');
INSERT INTO BS_CLASSIFICATION_TYPE (ID, NAME) VALUES (4,'Science');
INSERT INTO BS_CLASSIFICATION_TYPE (ID, NAME) VALUES (5,'Medicine');
INSERT INTO BS_CLASSIFICATION_TYPE (ID, NAME) VALUES (6,'Agriculture');
INSERT INTO BS_CLASSIFICATION_TYPE (ID, NAME) VALUES (7,'Technology');
INSERT INTO BS_CLASSIFICATION_TYPE (ID, NAME) VALUES (8,'Comics');
INSERT INTO BS_CLASSIFICATION_TYPE (ID, NAME) VALUES (9,'Computer Science');

-- Inserting Predefine Set Of Promotions type (e.g. 10%, 20%)
INSERT INTO BS_PROMOTION_TYPE (ID, CODE, DESCRIPTION, DISCOUNT_PERCENTAGE) VALUES (0,'000-000-000' ,'NO PROMOTION', 0.0);
INSERT INTO BS_PROMOTION_TYPE (ID, CODE, DESCRIPTION, DISCOUNT_PERCENTAGE) VALUES (1,'PRO-105-000' ,'5% PROMOTION', 5.0);
INSERT INTO BS_PROMOTION_TYPE (ID, CODE, DESCRIPTION, DISCOUNT_PERCENTAGE) VALUES (2,'PRO-210-000' ,'10% PROMOTION', 10.0);
INSERT INTO BS_PROMOTION_TYPE (ID, CODE, DESCRIPTION, DISCOUNT_PERCENTAGE) VALUES (3,'PRO-315-000' ,'15% PROMOTION', 15.0);
INSERT INTO BS_PROMOTION_TYPE (ID, CODE, DESCRIPTION, DISCOUNT_PERCENTAGE) VALUES (4,'PRO-420-000' ,'20% PROMOTION', 20.0);
INSERT INTO BS_PROMOTION_TYPE (ID, CODE, DESCRIPTION, DISCOUNT_PERCENTAGE) VALUES (5,'PRO-525-000' ,'25% PROMOTION', 25.0);
INSERT INTO BS_PROMOTION_TYPE (ID, CODE, DESCRIPTION, DISCOUNT_PERCENTAGE) VALUES (6,'PRO-630-000' ,'30% PROMOTION', 30.0);


-- insert promotoion
INSERT INTO BS_PROMOTION (ID, CLASSIFICATION_TYPE_ID, PROMOTION_TYPE_ID) VALUES (1, 3, 2);
INSERT INTO BS_PROMOTION (ID, CLASSIFICATION_TYPE_ID, PROMOTION_TYPE_ID) VALUES (2, 8, 0);
INSERT INTO BS_PROMOTION (ID, CLASSIFICATION_TYPE_ID, PROMOTION_TYPE_ID) VALUES (3, 4, 4);


--- Insert Books Samples

INSERT INTO BS_BOOK (ID,NAME, DESCRIPTION, AUTHOR, CLASSIFICATION_TYPE_ID, PRICE, ISBN, OTPLOCK_VERSION) VALUES (1, 
'The Illustrated Brief History Of Time', 
'Professor Hawking explains his complex theories through a fresh visual dimension. Over one hundred and fifty stunning colour illustrations have been specially commissioned for this purpose to help the reader understand what have become popular mythic images of our century, but which nonetheless remain difficult, abstract ideas to grasp.', 
'Stephen Hawking' , 4, 94.17, '978-0593077184', 0);

INSERT INTO BS_BOOK (ID,NAME, DESCRIPTION, AUTHOR, CLASSIFICATION_TYPE_ID, PRICE, ISBN, OTPLOCK_VERSION) VALUES (2, 
'Clean Architecture: A Craftsman''s Guide to Software Structure and Design', 
'Robert C. "Uncle Bob" Martin shows how to bring greater professionalism and discipline to application architecture and design.', 
'Robert C. Martin' , 7, 121.0, '978-0134494166', 0);

INSERT INTO BS_BOOK (ID,NAME, DESCRIPTION, AUTHOR, CLASSIFICATION_TYPE_ID, PRICE, ISBN, OTPLOCK_VERSION) VALUES (3, 
'The Algorithm Design Manual', 
'The designing algorithms, and analyzing of Algorithm efficacy and efficiency', 
'Steven Skiena' , 7, 428.91, '978-1849967204', 0);

alter sequence BS_SEQ_BOOK_ID restart with 4



                                                                                        
                                                                                        
                                                                                