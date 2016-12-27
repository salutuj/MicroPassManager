CREATE TABLE users (
  username varchar(32) NOT NULL PRIMARY KEY,
  password varchar(32) NOT NULL 
) IF NOT EXISTS;