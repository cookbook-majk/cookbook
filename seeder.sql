USE cookbook_db;

INSERT INTO users (email, first_name, last_name, password, profile_picture, user_bio, user_created, username)

VALUES ('spoonacular@email.com', 'Spoonacular', 'Spoonacular', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc','https://spoonacular.com/images/spoonacular-logo-b.svg', 'Fantastic Recipe API', now(), 'Spoonacular'),
       ('dingobat@email.com', 'Johnathan', 'Smallhorn', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc', 'https://blog.lehmans.com/wp-content/uploads/2019/07/kentrollins.jpg', 'fastest grill in the west', now(), 'one-and-only-smallhorn'),
       ('bigcat@email.com', 'Cathy', 'Pigeon', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc', 'https://scontent-hou1-1.xx.fbcdn.net/v/t1.6435-9/184562842_2989037661423479_2797047519422511758_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=SYYSSCit9LkAX-M4PYJ&_nc_ht=scontent-hou1-1.xx&oh=00_AfDBHYs6KC6RfGppWKeU4r8p2ul_qA-Job1LyvSRTF129A&oe=6428DDD1', 'im a realtor', now(), 'cathypigeonrealty'),
       ('jimothy@email.com', 'Jimothy', 'Jimsworth', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc', 'http://static.demilked.com/wp-content/uploads/2019/04/5cad97a853183-father-and-sons-tight-shirt-male-model-ad-reactions-1-5cac436e6bd41__700.jpg', 'I am the great Jimothy.', now(), 'jimothy'),
       ('joshypoo@email.com', 'Josh', 'Joshsworth', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc', 'https://food.fnr.sndimg.com/content/dam/images/food/fullset/2016/8/30/1/FN_Eddie-Jackson-Kitchen.s4x3.jpg.rend.hgtvcom.616.462.suffix/1472623392171.jpeg', 'Lord Josh of Joshington, England', now(), 'lordjosh'),
       ('gordanramsay@email.com', 'Gordon', 'Ramsay', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc', 'https://parade.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTkwNTc4MTE4NTMyMDE1OTk2/gordonramsay_kitchenmaster-ftr.jpg', 'Get out of my kitchen', now(), 'ramsay'),
       ('burgerking@email.com', 'Burger', 'King', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc', 'https://i.imgflip.com/6j1l38.jpg', 'Have it your way', now(), 'burgerking'),
       ('daughternatasha@email.com', 'Natasha', 'Winklenov', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc', 'https://i0.wp.com/prescriptiveoptimization.com/wp-content/uploads/2019/08/diet-4364283_1920.jpg?fit=1920%2C1375&ssl=1', 'Moms Vegan Prodigy', now(), 'natasha'),
       ('sonviktor@email.com', 'Viktor', 'Winklenov', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc', 'https://nypost.com/wp-content/uploads/sites/2/2021/07/men-eat-meat-01.jpg?quality=75&strip=all', 'Moms Carnivore Prodigy', now(), 'viktor'),
       ('ronaldmcdonald@email.com', 'Ronald', 'McDonald', '$2a$10$VfAsJ18TI0pSyEcH6rMkZeDgdZ5Eyc', 'https://api.time.com/wp-content/uploads/2014/04/74432_wavingronald.jpg?quality=85&w=2596', 'Im Lovin It', now(), 'mcdonalds');

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
       ('cathypigeonrealty saved recipes', false, 'cathypigeonrealtySaved', 3),
       ('jimothy saved recipes', false, 'jimothySaved', 4),
       ('lordjosh saved recipes', false, 'lordjoshSaved', 5),
       ('ramsay saved recipes', false, 'ramsaySaved', 6),
       ('burgerking saved recipes', false, 'burgerkingSaved', 7),
       ('natasha saved recipes', false, 'natashaSaved', 8),
       ('viktor saved recipes', false, 'viktorSaved', 9),
       ('mcdonalds saved recipes', false, 'mcdonaldsSaved', 10);


INSERT INTO custom_recipes (ready_in_minutes, servings, summary, user_id)
VALUES   (30, 2,'Vaquero Eggs and Bacon, best breakfast in the west',2),
         (15, 1,'Ranchero Steak, best lunch in the west',2),
         (10, 1,'Cowboy Sandwich, best snack in the west',2),
         (25, 4, 'Roasted Chicken with Lemon and Rosemary, perfect for dinner', 3),
         (40, 8, 'Classic Lasagna, perfect for a family dinner', 3),
         (20, 2, 'Shrimp Scampi, quick and easy seafood dish', 3),
         (35, 4, 'Beef Stroganoff, a classic comfort food', 4),
         (20, 2, 'Caprese Salad, a simple and refreshing dish', 4),
         (30, 6, 'Spaghetti and Meatballs, a crowd-pleasing pasta dish', 4),
         (25, 2, 'Garlic Shrimp Pasta, a quick and flavorful meal', 5),
         (45, 8, 'Beef and Vegetable Stew, a hearty and comforting dish', 5),
         (15, 4, 'Caprese Skewers, an easy and elegant appetizer', 5),
         (30, 4, 'Crispy Baked Chicken, a healthier alternative to fried chicken', 6),
         (20, 2, 'Greek Salad, a fresh and healthy dish', 6),
         (40, 6, 'Beef Enchiladas, a delicious and filling Mexican dish', 6),
         (25, 4, 'Lemon Garlic Shrimp, a flavorful seafood dish', 7),
         (35, 6, 'Beef and Broccoli Stir Fry, a quick and easy Asian-inspired meal', 7),
         (15, 2, 'Caprese Toast, a delicious and simple breakfast or snack', 7),
         (30, 4, 'Baked Salmon with Lemon and Dill, a healthy and flavorful seafood dish', 8),
         (25, 2, 'Tomato and Mozzarella Panini, a delicious and easy sandwich', 8),
         (40, 8, 'Beef Pot Roast, a classic slow-cooked comfort food', 8),
         (20, 2, 'Caprese Pasta Salad, a fresh and summery dish', 9),
         (30, 4, 'Lemon Herb Roasted Chicken, a simple and flavorful dinner', 9),
         (15, 2, 'Avocado Toast with Egg, a delicious and healthy breakfast or snack', 9),
         (25, 4,'Homemade Meatball Subs',10),
         (30, 2,'Pesto Chicken with Roasted Vegetables',10),
         (15, 1,'Quick and Easy Tuna Salad',10);

INSERT INTO recipes (created_at, image, image_type, spoonacular_id, title, custom_recipe_id)
VALUES (now(), 'https://www.femalefirst.co.uk/image-library/square/500/c/cowboy-breakfast.jpg', 'jpg', 0, 'Vaquero Eggs and Bacon', 1),
       (now(), 'https://leitesculinaria.com/wp-content/uploads/2020/08/cowboy-steak-coffee-rub-fp.jpg', 'jpg', 0, 'Ranchero Steak', 2),
       (now(), 'https://images.ctfassets.net/uw7yiu2kuigc/f3705cd2a281e55bbce2b504be3b7aaca78165b7a3fa564724543102adc5b470/ab3c81538c6899a09e5e007f78e9e016/cowboy.jpg', 'jpg', 0, 'Cowboy Sandwich', 3),
       (now(), 'example.jpg', 'jpg', 0, 'Roasted Chicken with Lemon and Rosemary', 4),
       (now(), 'example.jpg', 'jpg', 0, 'Classic Lasagna', 5),
       (now(), 'example.jpg', 'jpg', 0, 'Shrimp Scampi', 6),
       (now(), 'example.jpg', 'jpg', 0, 'Beef Stroganoff', 7),
       (now(), 'example.jpg', 'jpg', 0, 'Caprese Salad', 8),
       (now(), 'example.jpg', 'jpg', 0, 'Spaghetti and Meatballs', 9),
       (now(), 'example.jpg', 'jpg', 0, 'Garlic Shrimp Pasta', 10),
       (now(), 'example.jpg', 'jpg', 0, 'Beef and Vegetable Stew', 11),
       (now(), 'example.jpg', 'jpg', 0, 'Caprese Skewers', 12),
       (now(), 'example.jpg', 'jpg', 0, 'Crispy Baked Chicken', 13),
       (now(), 'example.jpg', 'jpg', 0, 'Greek Salad', 14),
       (now(), 'example.jpg', 'jpg', 0, 'Beef Enchiladas', 15),
       (now(), 'example.jpg', 'jpg', 0, 'Lemon Garlic Shrimp', 16),
       (now(), 'example.jpg', 'jpg', 0, 'Beef and Broccoli Stir Fry', 17),
       (now(), 'example.jpg', 'jpg', 0, 'Caprese Toast', 18),
       (now(), 'example.jpg', 'jpg', 0, 'Baked Salmon with Lemon and Dill', 19),
       (now(), 'example.jpg', 'jpg', 0, 'Tomato and Mozzarella Panini', 20),
       (now(), 'example.jpg', 'jpg', 0, 'Beef Pot Roast', 21),
       (now(), 'example.jpg', 'jpg', 0, 'Caprese Pasta Salad', 22),
       (now(), 'example.jpg', 'jpg', 0, 'Lemon Herb Roasted Chicken', 23),
       (now(), 'example.jpg', 'jpg', 0, 'Avocado Toast with Egg', 24),
       (now(), 'example.jpg', 'jpg', 0, 'Homemade Meatball Subs', 25),
       (now(), 'example.jpg', 'jpg', 0, 'Pesto Chicken with Roasted Vegetables', 26),
       (now(), 'example.jpg', 'jpg', 0, 'Quick and Easy Tuna Salad', 27);



INSERT INTO ingredients (amount, name, unit, custom_recipe_id)
VALUES (4, 'slices of bacon', '', 1),
       (4, 'large eggs', '', 1),
       (2, 'tbsp butter', '', 1),
       (1, 'lb flank steak', '', 2),
       (.5, 'diced red bell pepper', '', 2),
       (1, 'tsp chili powder', '', 2),
       (2, 'slices sourdough bread', '', 3),
       (2, 'slices cheddar cheese', '', 3),
       (1/2, 'lb roast beef', '', 3),
       (1, 'lb ground beef', '', 4),
       (1, 'tbsp chili powder', '', 4),
       (1/2, 'cup onion', 'diced', 4),
       (2, 'lbs beef stew meat', '', 5),
       (2, 'carrots', 'sliced', 5),
       (2, 'potatoes', 'cubed', 5),
       (4, 'boneless chicken breasts', '', 6),
       (2, 'zucchinis', 'sliced', 6),
       (1, 'red bell pepper', '', 6),
       (1, 'lb ground beef', '', 7),
       (1, 'can corn', '', 7),
       (2, 'cups cheddar cheese', '', 7),
       (1/2, 'lb linguine', '', 8),
       (1/2, 'lb shrimp', '', 8),
       (2, 'cloves garlic', '', 8),
       (1, 'red bell pepper', '', 9),
       (1, 'yellow bell pepper', '', 9),
       (1/2, 'lb broccoli', '', 9),
       (1, 'lb salmon fillets', '', 10),
       (2, 'tbsp olive oil', '', 10),
       (2, 'tsp lemon juice', '', 10),
       (2, 'Boneless chicken breasts', 'lb', 11),
       (1, 'Olive oil', 'tbsp', 11),
       (2, 'Garlic cloves', 'cloves', 11),
       (1, 'Ground beef', 'lb', 12),
       (1, 'Yellow onion', '', 12),
       (2, 'Garlic cloves', 'cloves', 12),
       (1, 'Chicken thighs', 'lb', 13),
       (1, 'Red onion', '', 13),
       (2, 'Garlic cloves', 'cloves', 13),
       (1, 'Pork shoulder', 'lb', 14),
       (2, 'Limes', '', 14),
       (1, 'Orange', '', 14),
       (1, 'Salmon fillets', 'lb', 15),
       (2, 'Lemons', '', 15),
       (2, 'Garlic cloves', 'cloves', 15),
       (1, 'Pork tenderloin', 'lb', 16),
       (1, 'Soy sauce', 'tbsp', 16),
       (1, 'Honey', 'tbsp', 16),
       (1, 'Ground beef', 'lb', 17),
       (1, 'Green bell pepper', '', 17),
       (1, 'Yellow onion', '', 17),
       (1, 'Boneless chicken thighs', 'lb', 18),
       (1, 'Lemon', '', 18),
       (1, 'Garlic cloves', 'cloves', 18),
       (1, 'Salmon fillets', 'lb', 19),
       (2, 'Limes', '', 19),
       (2, 'Garlic cloves', 'cloves', 19),
       (1, 'Pork chops', 'lb', 20),
       (1, 'Soy sauce', 'tbsp', 20),
       (1, 'Honey', 'tbsp', 20),
       (1, 'Ground beef', 'lb', 21),
       (1, 'Red bell pepper', '', 21),
       (1, 'Yellow onion', '', 21),
       (2, 'Chicken Breasts', 'lbs', 22),
       (1, 'Soy Sauce', 'cup', 22),
       (2, 'Garlic Cloves', 'pcs', 22),
       (2, 'Chicken Thighs', 'lbs', 23),
       (1, 'Lemon Juice', 'tbsp', 23),
       (1, 'Garlic Clove', 'pcs', 23),
       (1, 'Eggplant', 'pcs', 24),
       (1, 'Tomato', 'pcs', 24),
       (0.5, 'Red Onion', 'pcs', 24),
       (1, 'Flour', 'cup', 25),
       (0.5, 'Sugar', 'cup', 25),
       (2, 'Eggs', 'pcs', 25),
       (1, 'Ground Beef', 'lbs', 26),
       (1, 'Onion', 'pcs', 26),
       (1, 'Bell Pepper', 'pcs', 26),
       (1, 'Butter', 'tbsp', 27),
       (1, 'tuna', 'lb', 27),
       (1, 'Garlic Clove', 'pcs', 27);


INSERT INTO instructions (content, order_num, custom_recipe_id)
VALUES
    ('add bacon to cast iron pan.', 1, 1),
    ('add eggs once bacon grease has covered pan.', 2, 1),
    ('cook eggs to your style.', 3, 1),

    ('heat a grill or grill pan over high heat.', 1, 2),
    ('season the steak with chili powder and grill for 4-5 minutes per side.', 2, 2),
    ('let the steak rest for 5 minutes before slicing thinly against the grain.', 3, 2),

    ('heat a pan over medium heat.', 1, 3),
    ('build the sandwich by layering the cheese, roast beef, and bread.', 2, 3),
    ('spread butter on the outside of the bread slices and grill the sandwich for 2-3 minutes on each side.', 3, 3),

    ('preheat the oven to 375°F.', 1, 4),
    ('place the chicken in a roasting pan.', 2, 4),
    ('mix together the lemon juice, olive oil, rosemary, salt, and pepper in a small bowl, then pour the mixture over the chicken.', 3, 4),
    ('bake the chicken for 45-60 minutes, or until it reaches an internal temperature of 165°F.', 4, 4),

    ('cook the lasagna noodles according to package instructions.', 1, 5),
    ('brown the ground beef in a large skillet over medium heat.', 2, 5),
    ('add the pasta sauce and chili powder to the skillet and stir to combine.', 3, 5),
    ('layer the cooked noodles, beef mixture, and cheese in a 9x13 inch baking dish.', 4, 5),
    ('bake the lasagna at 375°F for 25-30 minutes, or until bubbly and browned on top.', 5, 5),

    ('bring a large pot of salted water to a boil.', 1, 6),
    ('cook the linguine according to package instructions.', 2, 6),
    ('heat the olive oil in a large skillet over medium heat.', 3, 6),
    ('add the shrimp and garlic to the skillet and cook for 2-3 minutes per side.', 4, 6),
    ('add the red bell pepper and cook for an additional 2-3 minutes.', 5, 6),
    ('toss the cooked linguine with the shrimp and vegetables.', 6, 6),

    ('brown the ground beef in a large skillet over medium heat.', 1, 7),
    ('add the corn and cook until heated through.', 2, 7),
    ('top the beef mixture with the cheddar cheese and let it melt.', 3, 7),
    ('serve the beef stroganoff hot with egg noodles or rice.', 4, 7),

    ('cook the linguine according to package instructions.', 1, 8),
    ('heat the olive oil in a large skillet over medium heat.', 2, 8),
    ('add the shrimp and garlic to the skillet and cook for 2-3 minutes per side.', 3, 8),
    ('add the diced tomatoes and cook for an additional 2-3 minutes.', 4, 8),
    ('toss the cooked linguine with the shrimp and tomato mixture.', 5, 8),

    ('dice red and yellow bell peppers and broccoli.', 1, 9),
    ('brown the meatballs in a skillet with oil over medium-high heat.', 2, 9),
    ('add the diced vegetables to the skillet and cook until tender.', 3, 9),
    ('add cooked spaghetti and meatballs to the skillet and mix together.', 4, 9),

    ('bake salmon fillets at 400 degrees F for 12-15 minutes.', 1, 10),
    ('heat olive oil in a pan over medium heat.', 2, 10),
    ('add garlic to the pan and cook until fragrant.', 3, 10),
    ('add cooked salmon fillets to the pan.', 4, 10),

    ('season chicken breasts with salt and pepper.', 1, 11),
    ('heat olive oil in a dutch oven over medium heat.', 2, 11),
    ('brown the chicken on both sides.', 3, 11),
    ('add diced vegetables to the dutch oven.', 4, 11),
    ('cover the dutch oven and let it simmer for 1-2 hours.', 5, 11),

    ('skewer cherry tomatoes, fresh mozzarella, and basil leaves onto wooden skewers.', 1, 12),
    ('brush the skewers with olive oil.', 2, 12),
    ('season the skewers with salt and pepper.', 3, 12),
    ('bake the skewers at 400 degrees F for 10-12 minutes.', 4, 12),

    ('dredge chicken thighs in flour.', 1, 13),
    ('heat oil in a skillet over medium-high heat.', 2, 13),
    ('add chicken thighs to the skillet and cook until browned.', 3, 13),
    ('add sliced red onion and garlic to the skillet and cook until softened.', 4, 13),

    ('add diced pork shoulder to a large pot and cook until browned.', 1, 14),
    ('add lime juice and orange juice to the pot.', 2, 14),
    ('simmer the pork shoulder for 2-3 hours.', 3, 14),

    ('bake the beef enchiladas at 350 degrees F for 20-25 minutes.', 1, 15),
    ('heat olive oil in a pan over medium heat.', 2, 15),
    ('add garlic to the pan and cook until fragrant.', 3, 15),
    ('add diced tomatoes to the pan and cook until softened.', 4, 15),

    ('marinate pork tenderloin in soy sauce and honey for 30 minutes.', 1, 16),
    ('heat oil in a pan over medium-high heat.', 2, 16),
    ('add pork tenderloin to the pan and cook until browned on all sides.', 3, 16),
    ('add sliced bell pepper and onion to the pan and cook until softened.', 4, 16),

    ('cook beef and green bell pepper in a wok over high heat.', 1, 17),
    ('add sliced onion and garlic to the wok and cook until softened.', 2, 17),

    ('Toast bread in toaster or oven', 1, 18),
    ('Chop tomatoes and mozzarella cheese', 2, 18),
    ('Layer mozzarella and tomatoes on toast', 3, 18),
    ('Sprinkle with salt, pepper, and basil leaves', 4, 18),
    ('Drizzle with balsamic glaze', 5, 18),

    ('Preheat oven to 375°F', 1, 19),
    ('Place salmon fillets skin-side down in a baking dish', 2, 19),
    ('Squeeze the juice of two limes over the salmon', 3, 19),
    ('Sprinkle minced garlic over the salmon', 4, 19),
    ('Bake for 15-20 minutes, or until the salmon is cooked through', 5, 19),

    ('Preheat panini press', 1, 20),
    ('Slice the bread, tomato, and mozzarella', 2, 20),
    ('Assemble the sandwich with tomato, mozzarella, and basil leaves', 3, 20),
    ('Brush the outside of the sandwich with olive oil', 4, 20),
    ('Grill the sandwich in the panini press until cheese is melted and bread is toasted', 5, 20),

    ('Preheat oven to 350°F', 1, 21),
    ('Season the beef with salt and pepper', 2, 21),
    ('Brown the beef in a dutch oven on medium-high heat', 3, 21),
    ('Add chopped onions and bell pepper to the dutch oven', 4, 21),
    ('Bake for 3 hours or until the beef is tender', 5, 21),

    ('Cook pasta according to package directions', 1, 22),
    ('Drain pasta and rinse with cold water', 2, 22),
    ('Combine pasta, chopped tomatoes, and mozzarella in a bowl', 3, 22),
    ('Whisk together olive oil, balsamic vinegar, and soy sauce in a small bowl', 4, 22),
    ('Pour the dressing over the pasta mixture and stir to combine', 5, 22),

    ('Preheat oven to 400°F.', 1, 23),
    ('Combine lemon juice, minced garlic, and melted butter.', 2, 23),
    ('Season chicken with salt and pepper and place in a baking dish.', 3, 23),
    ('Pour the butter mixture over the chicken.', 4, 23),
    ('Bake for 40-45 minutes or until the chicken is fully cooked.', 5, 23),

    ('Toast bread to your preference.', 1, 24),
    ('Mash avocado and spread it on the toast.', 2, 24),
    ('Slice tomato and red onion and add them on top of the avocado.', 3, 24),
    ('Cook egg to your style and place it on top of the tomato and red onion.', 4, 24),
    ('Season with salt and pepper to taste.', 5, 24),

    ('Preheat oven to 375°F.', 1, 25),
    ('Mix ground beef, eggs, bread crumbs, and spices in a bowl.', 2, 25),
    ('Form meatballs and place them on a baking sheet.', 3, 25),
    ('Bake for 25-30 minutes or until fully cooked.', 4, 25),
    ('Toast bread, add meatballs and sauce, and serve.', 5, 25),

    ('Preheat oven to 400°F.', 1, 26),
    ('Cut vegetables into bite-sized pieces and place on a baking sheet.', 2, 26),
    ('Season vegetables with salt, pepper, and olive oil.', 3, 26),
    ('Season chicken with salt and pepper and place on top of the vegetables.', 4, 26),
    ('Bake for 20-25 minutes or until the chicken is fully cooked.', 5, 26),

    ('Drain and flake tuna in a bowl.', 1, 27),
    ('Add chopped garlic and butter to the bowl.', 2, 27),
    ('Mix all ingredients together.', 3, 27),
    ('Serve with bread or crackers.', 4, 27),
    ('Enjoy!', 5, 27);

# INSERT INTO recipe_chapter (recipes, chapters) VALUES (2, 4);

# INSERT INTO recipe_types (re)

INSERT INTO followers (follower_id, user_id)
VALUES (4, 2),
       (4, 3),
       (4, 5),
       (3, 5),
       (3, 7),
       (3, 6),
       (4, 7),
       (4, 9),
       (4, 8),
       (6, 3),
       (7, 3),
       (7, 4),
       (7, 8),
       (9, 4),
       (9, 2),
       (9, 3),
       (9, 8),
       (6, 9);

INSERT INTO recent_activity (activity_type, created_at, recipe_id, user_id)
VALUES (1, now(), 1, 4);

INSERT INTO reviews (comment, created_at, edited_at, rating, recipe_id, user_id, title)
VALUES
    ('so simple, yet so satisfying! thanks for sharing.', now(), null, 5, 1, 2,'My new go-to recipe'),
    ('Delicious! I added some extra garlic and it turned out amazing!', now(),null, 4, 1, 1,'Great recipe!'),
    ('This was a hit with my family! Will definitely be making this again.', now(),null, 5, 2, 1,'Amazing recipe!'),
    ('Loved this recipe! The flavors were perfect.', now(),null, 5, 3, 1,'Delicious recipe!'),
    ('This recipe was easy to follow and turned out great! Will be making this again soon.', now(),null, 4, 4, 2,'My new favorite recipe!'),
    ('Fantastic recipe! Ive already made this twice this week.', now(),null, 5, 5, 2,'Easy and delicious!'),
    ('This recipe was a hit with my friends! Will definitely be making this again.', now(),null, 5, 6, 2,'Great recipe!'),
    ('Loved this recipe! Will be adding this to my regular rotation.', now(),null, 5, 7, 3,'Delicious and easy!'),
    ('This recipe was a hit with my family! The flavors were perfect.', now(),null, 5, 8, 3,'Amazing recipe!'),
    ('This recipe turned out great! I added some extra spices and it was perfect.', now(),null, 4, 9, 3,'Easy and delicious!'),
    ('This recipe was so easy to follow and turned out amazing!', now(),null, 5, 10, 4,'Great recipe!'),
    ('This was a hit with my family! The flavors were perfect.', now(),null, 5, 11, 4,'Amazing recipe!'),
    ('Loved this recipe! Will be making this again soon.', now(),null, 5, 12, 4,'Delicious recipe!'),
    ('This recipe was easy to follow and turned out great! Will be making this again.', now(),null, 4, 13, 5,'My new go-to recipe'),
    ('Fantastic recipe! The flavors were perfect.', now(),null, 5, 14, 5,'Easy and delicious!'),
    ('Loved this recipe! Will be adding this to my regular rotation.', now(),null, 5, 15, 5,'Great recipe!'),
    ('This recipe turned out great! I added some extra spices and it was perfect.', now(),null, 4, 16, 6,'Delicious and easy!'),
    ('This recipe was so easy to follow and turned out amazing!', now(),null, 5, 17, 6,'Amazing recipe!'),
    ('This was a hit with my family! The flavors were perfect.', now(),null, 5, 18, 6,'Delicious recipe!'),
    ('Fantastic recipe! Will definitely be making this again.', now(),null, 5, 19, 7,'My new favorite recipe!'),
    ('Loved this recipe! The flavors were perfect.', now(),null, 5, 20, 7,'Easy and delicious!'),

    ('This recipe turned out great! Will be making this again soon.', now(),null, 4, 21, 7,'Great recipe!');
