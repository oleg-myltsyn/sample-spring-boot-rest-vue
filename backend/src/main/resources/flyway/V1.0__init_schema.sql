

CREATE TABLE users (
	id BIGSERIAL PRIMARY KEY,
	username text UNIQUE,
	password text,
	email text
);
