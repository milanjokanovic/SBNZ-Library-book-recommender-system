INSERT INTO author (id, name, system_grade) VALUES
    (1001, 'J. K. Rowling', 0),
    (1002, 'C.S. Lewis', 0),
    (1003, 'Henry H. Neff', 0);

INSERT INTO genre (id, name, system_grade) VALUES
        (100, 'Fantasy', 0),
        (200, 'Adventure', 0),
        (300, 'Contemporary ', 0),
        (400, 'Dystopian', 0),
        (500, 'Mystery', 0),
        (600, 'Romance', 0),
        (700, 'Horror', 0),
        (800, 'Thriller', 0),
        (900, 'Paranormal', 0),
        (1000, 'Historical fiction', 0),
        (1100, 'science fiction', 0),
        (1200, 'Memoir', 0),
        (1300, 'Cooking', 0),
        (1400, 'Art', 0),
        (1500, 'self help', 0),
        (1600, 'Development', 0),
        (1700, 'Motivational', 0),
        (1800, 'Health', 0),
        (1900, 'History', 0),
        (2000, 'Travel', 0),
        (2100, 'How to', 0),
        (2200, 'Families and relationships', 0),
        (2300, 'Comedy', 0),
        (2400, 'Crime', 0);

INSERT INTO book (id, based_on_real_event, br_pregleda, nobel_prize, page_num, series, series_number,
    system_grade, target_audience, title, year_of_publishing, author_id, user_recommended_score) VALUES
    (1001, FALSE, 240, TRUE, 223, 'Harry Potter', 1, 0, '1', 'Harry Potter and the philosophers stone', 1997, 1001, 0),
    (1002, TRUE, 240, FALSE, 251, 'Harry Potter', 2, 0, '1', 'Harry Potter and the chamber of secrets', 1998, 1001, 0),
    (1008, FALSE, 240, TRUE, 223, 'The chronicles of Narnia', 1, 0, '0', 'The Lion, the witch and the wardrobe', 1950, 1002, 0),
    (1019, FALSE, 240, TRUE, 480, 'The tapestry', 5, 0, '1', 'The Red Winter', 2014, 1003, 0);

INSERT INTO user (id, email, last_active, password,  age, blocked_scoring_function) VALUES
    (101, 'pera@gmail.com', '2020-12-08 17:40:50', 12345, '1', 0);

INSERT INTO book_genres (book_id, genre_id) values
    (1001, 100),
    (1001, 500),
    (1008, 100),
    (1008, 200),
    (1019, 100);

INSERT into user_read_books (user_id, book_id) values
    (101, 1001),
    (101, 1008),
    (101, 1002);

INSERT INTO score (id, value, book_id, user_id) values
    (1001, 7, 1001, 101),
    (1002, 5, 1002, 101),
    (1003, 8, 1008, 101);