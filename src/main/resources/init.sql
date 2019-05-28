CREATE TABLE users
(
  id       SERIAL  NOT NULL PRIMARY KEY,
  email    TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  role     TEXT NOT NULL
);