DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;


create table users(
                      id int auto_increment primary key,
                      username varchar(50) not null,
                      password varchar(50) not null,
                      enabled boolean not null
);
create table authorities (
                             user_id int not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(user_id) references users(id)
);

CREATE TABLE notes (
                       id SERIAL NOT NULL PRIMARY KEY,
                       title VARCHAR(50) NOT NULL,
                       note VARCHAR(1000),
                       create_time TIMESTAMP DEFAULT NOW(),
                       last_update_time TIMESTAMP DEFAULT NOW(),
                       user_id INTEGER REFERENCES users(id)
);
create unique index ix_auth_username on authorities (user_id,authority);

-- Insert some data
INSERT INTO users (id, username, password, enabled)
values(1, 'user','password', true);
INSERT INTO users (id, username, password, enabled)
values(2, 'admin','password', true);
INSERT INTO authorities(user_id, authority)
values(1,'ROLE_USER');
INSERT INTO authorities(user_id, authority)
values(2,'ROLE_ADMIN');