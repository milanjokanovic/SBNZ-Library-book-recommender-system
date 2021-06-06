INSERT INTO author (id, name, system_grade) VALUES
    (1101, 'J. K. Rowling1', 0),
    (1102, 'C.S. Lewis1', 0),
    (1103, 'Henry H. Neff1', 0);

INSERT INTO genre (id, name, system_grade) VALUES
        (101, 'Fantasy1', 0),
        (102, 'Adventure1', 0),
        (103, 'Contemporary1 ', 0),
        (104, 'Dystopian1', 0),
        (105, 'Mystery1', 0),
        (106, 'Romance1', 0),
        (107, 'Horror1', 0),
        (108, 'Thriller1', 0),
        (109, 'Paranormal1', 0),
        (110, 'Historical fiction1', 0),
        (111, 'science fiction1', 0),
        (112, 'Memoir1', 0),
        (113, 'Cooking1', 0),
        (114, 'Art1', 0),
        (115, 'self help1', 0),
        (116, 'Development1', 0),
        (117, 'Motivational1', 0),
        (118, 'Health1', 0),
        (119, 'History1', 0),
        (120, 'Travel1', 0),
        (121, 'How to1', 0),
        (122, 'Families and relationships1', 0),
        (123, 'Comedy1', 0),
        (124, 'Crime1', 0);

INSERT INTO book (id, based_on_real_event, br_pregleda, nobel_prize, page_num, series, series_number,
    system_grade, target_audience, title, year_of_publishing, author_id, user_recommended_score) VALUES
    (1101, FALSE, 240, TRUE, 223, 'Harry Potter', 1, 0, '1', 'Harry Potter and the philosophers stone', 1997, 1101, 0),
    (1102, TRUE, 240, FALSE, 251, 'Harry Potter', 2, 0, '1', 'Harry Potter and the chamber of secrets', 1998, 1101, 0),
    (1108, FALSE, 240, TRUE, 223, 'The chronicles of Narnia', 1, 0, '0', 'The Lion, the witch and the wardrobe', 1950, 1102, 0),
    (1119, FALSE, 240, TRUE, 480, 'The tapestry', 5, 0, '1', 'The Red Winter', 2014, 1103, 0);

INSERT INTO user (id, email, last_active, password,  age, favorite_book_id, favorite_author_id, blocked_scoring_function ) VALUES
    (100, 'pera11@gmail.com', '2021-12-08 17:40:50', 12345, '1', 1119, 1103, 0);

INSERT INTO book_genres (book_id, genre_id) values
    (1101, 101),
    (1101, 105),
    (1108, 101),
    (1108, 102),
    (1119, 101);

INSERT into user_read_books (user_id, book_id) values
    (100, 1101),
    (100, 1108),
    (100, 1102);

INSERT INTO score (id, value, book_id, user_id) values
    (1, 7, 1101, 100),
    (2, 5, 1102, 100),
    (3, 8, 1108, 100);