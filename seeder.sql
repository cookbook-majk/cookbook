DROP DATABASE cookbook_db;


use cookbook_db;


INSERT INTO user (email, first_name, last_name, password, profile_picture, user_bio, user_created, username)
VALUES ('spoonacular@email.com', 'Spoonacular', 'Spoonacular', 'password',
        'https://spoonacular.com/images/spoonacular-logo-b.svg',
        'Fantastic Recipe API', now(), 'Spoonacular'),
       ('dingobat@email.com', 'Johnathan', 'Smallhorn', 'password',
        'https://yt3.googleusercontent.com/ytc/AL5GRJWTD5bssX80iVmVskHgLi7yksF0fJ9uJkkhixnWKA=s900-c-k-c0x00ffffff-no-rj',
        'big guy, small horn', now(), 'one-and-only-smallhorn'),
       ('bigcat@email.com', 'Cathy', 'Pigeon', 'password',
        'https://scontent-hou1-1.xx.fbcdn.net/v/t1.6435-9/184562842_2989037661423479_2797047519422511758_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=SYYSSCit9LkAX-M4PYJ&_nc_ht=scontent-hou1-1.xx&oh=00_AfDBHYs6KC6RfGppWKeU4r8p2ul_qA-Job1LyvSRTF129A&oe=6428DDD1',
        'im a realtor', now(), 'cathypigeonrealty'),
       ('joshua@email.com', 'Joshua', 'Mulldawg', 'password',
        'https://ih1.redbubble.net/image.4255137034.0373/poster,504x498,f8f8f8-pad,600x600,f8f8f8.u2.jpg',
        'dont mess with the big dag', now(), 'mulldawgydawg');


INSERT INTO chapters (description, featured, name, user_id)
VALUES ('Spoonacular saved recipes', false, 'SpoonacularSaved', 1),
       ('one-and-only-smallhorn saved recipes', false, 'one-and-only-smallhornSaved', 2),
       ('cathypigeonrealty saved recipes', false, 'cathypigeonrealtySaved', 3),
       ('mulldawgydawg saved recipes', false, 'mulldawgydawgSaved', 4);

