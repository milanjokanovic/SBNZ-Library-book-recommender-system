INSERT INTO author (id, name, system_grade) VALUES
    (1001, 'J. K. Rowling', 0),
    (1002, 'C.S. Lewis', 0),
    (1003, 'Henry H. Neff', 0);

INSERT INTO genre (id, name, system_grade) VALUES
        (1, 'Fantasy', 0),
        (2, 'Adventure', 0),
        (3, 'Contemporary ', 0),
        (4, 'Dystopian', 0),
        (5, 'Mystery', 0),
        (6, 'Romance', 0),
        (7, 'Horror', 0),
        (8, 'Thriller', 0),
        (9, 'Paranormal', 0),
        (10, 'Historical fiction', 0),
        (11, 'science fiction', 0),
        (12, 'Memoir', 0),
        (13, 'Cooking', 0),
        (14, 'Art', 0),
        (15, 'self help', 0),
        (16, 'Development', 0),
        (17, 'Motivational', 0),
        (18, 'Health', 0),
        (19, 'History', 0),
        (20, 'Travel', 0),
        (21, 'How to', 0),
        (22, 'Families and relationships', 0),
        (23, 'Comedy', 0),
        (24, 'Crime', 0);

INSERT INTO book (id, based_on_real_event, br_pregleda, nobel_prize, page_num, series, series_number,
    system_grade, target_audience, title, year_of_publishing, author_id, user_recommended_score) VALUES
    (1001, FALSE, 240, TRUE, 223, 'Harry Potter', 1, 0, '1', 'Harry Potter and the philosophers stone', 1997, 1001, 0),
    (1002, TRUE, 240, FALSE, 251, 'Harry Potter', 2, 0, '1', 'Harry Potter and the chamber of secrets', 1998, 1001, 0),
    (1008, FALSE, 240, TRUE, 223, 'The chronicles of Narnia', 1, 0, '0', 'The Lion, the witch and the wardrobe', 1950, 1002, 0),
    (1019, FALSE, 240, TRUE, 480, 'The tapestry', 5, 0, '1', 'The Red Winter', 2014, 1003, 0);

INSERT INTO user (id, email, last_active, password,  age, blocked_scoring_function) VALUES
    (1, 'pera@gmail.com', '2020-12-08 17:40:50', 12345, '1', 0);

INSERT INTO book_genres (book_id, genre_id) values
    (1001, 1),
    (1001, 5),
    (1008, 1),
    (1008, 2),
    (1019, 1);

INSERT into user_read_books (user_id, book_id) values
    (1, 1001),
    (1, 1008),
    (1, 1002);

INSERT INTO score (id, value, book_id, user_id) values
    (1, 7, 1001, 1),
    (2, 5, 1002, 1),
    (3, 8, 1008, 1);