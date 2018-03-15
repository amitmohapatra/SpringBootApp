/**
 * CREATE Script for init of DB
 */

-- Create 3 articles

insert into article (id, date_created, deleted, header, short_description, text) values (1, now(), false, 'mobile',
'samsung phone', 'samsung mobile phone');

insert into article (id, date_created, deleted, header, short_description, text) values (2, now(), false, 'nokia',
'nokia phone', 'nokia mobile phone');

insert into article (id, date_created, deleted, header, short_description, text) values (3, now(), false, 'apple',
'apple phone', 'apple mobile phone');

