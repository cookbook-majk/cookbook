drop database cookbook_db;

use cookbook_db;

INSERT INTO users (email, first_name, last_name, password, profile_picture, user_bio, user_created, username)
VALUES ('spoonacular@email.com', 'Spoonacular', 'Spoonacular', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc',
        'https://spoonacular.com/images/spoonacular-logo-b.svg',
        'Fantastic Recipe API', now(), 'Spoonacular');

       ('dingobat@email.com', 'Johnathan', 'Smallhorn', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc',
        'https://yt3.googleusercontent.com/ytc/AL5GRJWTD5bssX80iVmVskHgLi7yksF0fJ9uJkkhixnWKA=s900-c-k-c0x00ffffff-no-rj',
        'big guy, small horn', now(), 'one-and-only-smallhorn'),
       ('bigcat@email.com', 'Cathy', 'Pigeon', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc',
        'https://scontent-hou1-1.xx.fbcdn.net/v/t1.6435-9/184562842_2989037661423479_2797047519422511758_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=SYYSSCit9LkAX-M4PYJ&_nc_ht=scontent-hou1-1.xx&oh=00_AfDBHYs6KC6RfGppWKeU4r8p2ul_qA-Job1LyvSRTF129A&oe=6428DDD1',
        'im a realtor', now(), 'cathypigeonrealty');

#        ('joshua@email.com', 'Joshua', 'Mulldawg', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc',
#         'https://ih1.redbubble.net/image.4255137034.0373/poster,504x498,f8f8f8-pad,600x600,f8f8f8.u2.jpg',
#         'dont mess with the big dag', now(), 'mulldawgydawg');

INSERT INTO dish_types (type)
VALUES ('main course'),
       ('side dish'),
       ('dessert'),
       ('appetizer'),
       ('salad'),
       ('bread'),
       ('breakfast'),
       ('soup'),
       ('beverage'),
       ('sauce');

INSERT INTO chapters (description, featured, name, user_id)
VALUES ('Spoonacular saved recipes', false, 'SpoonacularSaved', 1),
       ('one-and-only-smallhorn saved recipes', false, 'one-and-only-smallhornSaved', 2),
       ('cathypigeonrealty saved recipes', false, 'cathypigeonrealtySaved', 3);

#        ('mulldawgydawg saved recipes', false, 'mulldawgydawgSaved', 4);

INSERT INTO custom_recipes (ready_in_minutes, servings, summary, user_id)
VALUES (2, 1,'delicious cereal recipe! try now',4);

INSERT INTO recipes (created_at, image, image_type, spoonacular_id, title, custom_recipe_id)
VALUES (now(), 'example.jpg', 'jpg', null, 'joshs delicious cereal', 1);

INSERT INTO ingredients (amount, name, unit, custom_recipe_id)
VALUES (1, 'milk', 'cup', 1),
       (2.5, 'cereal', 'cups', 1);

INSERT INTO instructions (content, order_num, custom_recipe_id)
VALUES ('add cereal into bowl.', 1, 1),
       ('add milk to cereal bowl.', 2, 1);

# INSERT INTO recipe_chapter (recipes, chapters) VALUES (2, 4);

# INSERT INTO recipe_types (re)

INSERT INTO followers (follower_id, user_id) VALUES (1, 4);
INSERT INTO followers (follower_id, user_id) VALUES (4, 2);
INSERT INTO followers (follower_id, user_id) VALUES (4, 3);

INSERT INTO recent_activity (activity_type, created_at, recipe_id, user_id)
VALUES (1, now(), 1, 4);

INSERT INTO reviews (comment, created_at, edited_at, rating, recipe_id, user_id)
VALUES ('so simple, yet so satisfying! thanks for sharing.', now(), null, 5, 1, 2),
       ('why share this? we all know how to make cereal...', now(), null, 2, 1, 3),
       ('trash.', now(), now(), 1, 1, 2);
