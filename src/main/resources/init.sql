CREATE TABLE users
(
  id       SERIAL NOT NULL PRIMARY KEY,
  email    TEXT   NOT NULL UNIQUE,
  password TEXT   NOT NULL,
  role     TEXT   NOT NULL
);

CREATE TABLE exhibition_theme
(
  id   SERIAL NOT NULL PRIMARY KEY,
  name TEXT   NOT NULL
);
INSERT INTO exhibition_theme (name)
VALUES ('Tiny art');
INSERT INTO exhibition_theme (name)
VALUES ('Local inspiration');
INSERT INTO exhibition_theme (name)
VALUES ('Inspired by great artists');
INSERT INTO exhibition_theme (name)
VALUES ('Famous landmarks');
INSERT INTO exhibition_theme (name)
VALUES ('National identity');
INSERT INTO exhibition_theme (name)
VALUES ('Self-portraiture');
INSERT INTO exhibition_theme (name)
VALUES ('Interactive art');
INSERT INTO exhibition_theme (name)
VALUES ('3D art');
INSERT INTO exhibition_theme (name)
VALUES ('Unique medium');
INSERT INTO exhibition_theme (name)
VALUES ('Things that move and live');

CREATE TABLE exhibit
(
  id          SERIAL NOT NULL PRIMARY KEY,
  name        TEXT   NOT NULL,
  theme_id    INT    NOT NULL,
  showroom_id INT    NOT NULL
);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Cormopants', 1, 1);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Pelican rock', 1, 1);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Nature walk', 2, 1);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Royal owlness', 2, 2);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Ode to spring', 2, 2);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Wolf dog landscape', 3, 2);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('For safe keeping', 3, 3);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Snow hen', 4, 3);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Ambivalence', 4, 3);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Red-vented cockatoo and bakhaw lalaki mangrove', 5, 4);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Boobok owl', 5, 4);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Tawny owl', 6, 4);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Eurasion eagle owl', 6, 4);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Ouraboros', 6, 4);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Fox', 7, 5);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Prisms: mus musculus', 7, 5);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Prisms: vulpes vulpes', 8, 5);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Who holds the reigns of power', 8, 6);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('An ode', 9, 6);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Black wolf and pasque flowers', 9, 6);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Joie de vivre: banditry', 9, 7);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('Spirited away', 10, 7);
INSERT INTO exhibit (name, theme_id, showroom_id)
VALUES ('The ghost of you', 10, 7);

CREATE TABLE showroom
(
  id   SERIAL NOT NULL PRIMARY KEY,
  name TEXT   NOT NULL
);
INSERT INTO showroom (name)
VALUES ('Casala');
INSERT INTO showroom (name)
VALUES ('Connection');
INSERT INTO showroom (name)
VALUES ('Creatif');
INSERT INTO showroom (name)
VALUES ('Dauphin');
INSERT INTO showroom (name)
VALUES ('Clarus');
INSERT INTO showroom (name)
VALUES ('Domus');
INSERT INTO showroom (name)
VALUES ('Dieffebi');

CREATE TABLE payment
(
  id      SERIAL NOT NULL PRIMARY KEY,
  price   INT    NOT NULL,
  name    TEXT   NOT NULL,
  surname TEXT   NOT NULL
);

CREATE TABLE ticket
(
  id         SERIAL NOT NULL PRIMARY KEY,
  user_id    INT    NOT NULL,
  date       DATE   NOT NULL,
  theme_id   INT    NOT NULL,
  payment_id INT    NOT NULL
);