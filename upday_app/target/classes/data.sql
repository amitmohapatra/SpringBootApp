/**
 * Script for init of DB
 */
insert into role(id, name) values (1, 'ADMIN');

insert into user (id, date_created, deleted, name, password, username) values (1, now(), false, 'admin',
'admin', 'admin');

insert into user_roles(APPLICATION_USER_ID, ROLES_ID) values (1, 1);

-- Create 3 articles

insert into article (id, date_created, deleted, header, short_description, text, created_by, date_updated) values (1, now(), false, 'mobile',
'samsung phone', 'samsung mobile phone', 'admin', now());

insert into article (id, date_created, deleted, header, short_description, text, created_by, date_updated) values (2, now(), false, 'nokia',
'nokia phone', 'nokia mobile phone', 'admin', now());

insert into article (id, date_created, deleted, header, short_description, text, created_by, date_updated) values (3, now(), false, 'apple',
'apple phone', 'apple mobile phone', 'admin', now());

