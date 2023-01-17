--drop table stat_basketball;
--drop table stat_softball;
--drop table referee_lookup;
--drop table team_requests;
--drop table game;
--drop table team;
--drop table im_user;
--drop table venue;
--drop table season;
--
--
--select * from im_user;

create type im_role as enum ('referee','player','admin');
create type sport as enum ('softball','basketball');
create type team_request_status as enum ('pending','denied','accepted');
create type team_status as enum ('active','inactive','suspended');
create type game_outcome as enum ('played','away_forfeit','home_forfeit','cancelled','scheduled');


-- parents of game
create table venue(
    title varchar primary key
);

create table season(
    title varchar primary key
);

create table im_user(
    user_id serial primary key,
    username varchar unique,
    password varchar,
    role im_role,
    height int, -- inches
    weight int, -- lbs
    profile_pic varchar,
    display_biometrics bool
);

create table team(
    name varchar primary key,
    captain int references im_user(user_id),
    team_status team_status,
    sport sport
);

create table game(
    game_id serial primary key,
    venue varchar references venue(title),
    season varchar references season(title),
    home_team varchar references team(name),
    away_team varchar references team(name),
    home_score int,
    away_score int,
    game_start int,
    game_outcome varchar
);



create table stat_basketball(
    s_basketball_id serial primary key,
    user_id int references im_user(user_id),
    game_id int references game(game_id),
    team_name varchar references team(name),
    points int,
    rebounds int,
    assists int,
    fouls int
);

create table stat_softball(
    s_softball_id serial primary key,
    user_id int references im_user(user_id),
    game_id int references game(game_id),
    team_name varchar references team(name),
    plate_appearances int,
    singles int,
    doubles int,
    triples int,
    homeruns int,
    walks int,
    strikeouts int
);

create table referee_lookup(
    referee_lookup_id serial primary key,
    user_id int references im_user(user_id),
    game_id int references game(game_id)
);


create table team_requests(
	request_id serial primary key,
	team varchar references team(name),
	user_id int references im_user(user_id),
	status team_request_status
);

-- Users

-- Admins
insert into im_user values (default,'gatorFan99','chomp!!','admin',0,0,'none',false);
insert into im_user values (default,'TopDawg','sicem','admin',0,0,'none',true);

-- Referees
insert into im_user values (default,'cindy101','pass123','referee',0,0,'none',true);
insert into im_user values (default,'mandy101','pass123','referee',0,0,'none',true);


-- Captains
insert into im_user values (default,'Bobby202','pass123','player',72,195,'none',true);
insert into im_user values (default,'Candice202','pass123','player',61,125,'none',true);
insert into im_user values (default,'Jessika202','pass123','player',64,130,'none',true);

-- Players
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'eegdell0', 'DyAU3y5hLA', 'player', 57, 61, 'https://robohash.org/sitnoneos.png?size=150x150&set=set1', true);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'slafoy1', '3hOS1nh', 'player', 54, 67, 'https://robohash.org/utvitaeofficia.png?size=150x150&set=set1', true);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'mgoodhay2', 'FHsI2MXUb', 'player', 80, 50, 'https://robohash.org/quiaatquevero.png?size=150x150&set=set1', true);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'phanrott3', 'rVufrSxG', 'player', 49, 73, 'https://robohash.org/nemoetdignissimos.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'kskottle4', 'fY8zGjrTbczw', 'player', 78, 52, 'https://robohash.org/vitaereiciendisqui.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'margo23', 'eCItXyih', 'player', 40, 64, 'https://robohash.org/occaecatiautlaborum.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'cmeekings6', 'Ft2RahrB', 'player', 49, 46, 'https://robohash.org/uttemporibuspariatur.png?size=150x150&set=set1', true);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'dmcwilliam7', 'XrcsKgdCUtRv', 'player', 76, 50, 'https://robohash.org/etconsequatursit.png?size=150x150&set=set1', true);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'rscreas8', 'gMhQ8W5ZObv', 'player', 73, 47, 'https://robohash.org/earumnamrepellat.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'cwippermann9', 'AFUp4lI0lOMS', 'player', 63, 83, 'https://robohash.org/etaliquidet.png?size=150x150&set=set1', true);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'mreddicka', 'EOG8Mb', 'player', 69, 48, 'https://robohash.org/illoerrorharum.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'backermannb', 'dDk0hto', 'player', 62, 86, 'https://robohash.org/autipsamcumque.png?size=150x150&set=set1', true);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'chutablec', 'swB1brkS2', 'player', 71, 79, 'https://robohash.org/suntdeseruntcum.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'rlegerwoodd', 'uAs0Kf', 'player', 61, 43, 'https://robohash.org/consequunturvoluptatemfugit.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'bbettensone', 'b2fAvPuM', 'player', 43, 59, 'https://robohash.org/nameteos.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'odowellf', 'MOXvFNcR', 'player', 46, 80, 'https://robohash.org/temporamolestiaesed.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'acolcloughg', 'jxvh2PvoO5y', 'player', 59, 63, 'https://robohash.org/asperioresipsadebitis.png?size=150x150&set=set1', false);
insert into im_user (user_id, username, password, role, height, weight, profile_pic, display_biometrics) values (default, 'wpaumierh', 'a9myRNBCGR', 'player', 77, 75, 'https://robohash.org/nequeestaccusantium.png?size=150x150&set=set1', false);



-- Teams
insert into team values('Grand Dunk Railroad',5, 'active','basketball');
insert into team values('The Ballers',6, 'active','basketball');
insert into team values('The Splash',7, 'active','basketball');

-- venue
insert into venue values ('Main Campus Gym: Court 1');
insert into venue values ('Main Campus Gym: Court 2');
insert into venue values ('Main Campus Gym: Court 3');
insert into venue values ('Satellite Campus Gym: Smith Field');
insert into venue values ('Satellite Campus Gym: Lee Field');


-- season
insert into season values ('Fall 2022 Regular Season Basketball');
insert into season values ('Fall 2022 Regular Season Softball');
insert into season values ('Hopping for a Cure Charity Tournament');



-- Games
insert into game values (default, 'Main Campus Gym: Court 1', 'Fall 2022 Regular Season Basketball', 'Grand Dunk Railroad', 'The Ballers',0,0,1669122000,'scheduled'); -- Tue Nov 22 2022 08:00:00 GMT-0500 (Eastern Standard Time)
insert into game values (default, 'Main Campus Gym: Court 2', 'Fall 2022 Regular Season Basketball', 'The Ballers', 'Grand Dunk Railroad',0,0,1669122000,'scheduled'); -- Tue Nov 22 2022 08:00:00 GMT-0500 (Eastern Standard Time)
insert into game values (default, 'Main Campus Gym: Court 1', 'Fall 2022 Regular Season Basketball', 'The Splash', 'Grand Dunk Railroad',0,0,1669122000,'scheduled'); -- Tue Nov 22 2022 08:00:00 GMT-0500 (Eastern Standard Time)
insert into game values (default, 'Satellite Campus Gym: Smith Field', 'Fall 2022 Regular Season Basketball', 'The Splash', 'The Ballers',0,0,1669215600,'scheduled'); -- Wed Nov 23 2022 10:00:00 GMT-0500 (Eastern Standard Time)





