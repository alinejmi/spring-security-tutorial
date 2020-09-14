INSERT into users(username,password,email,enabled) values ('admin','{noop}secret1','ali@g.com', true);

INSERT into users(username,password,email,enabled) values ('user','{noop}secret1','ali@g.com', true);

INSERT into authorities values ('admin','ADMIN');
INSERT into authorities values ('admin','USER');
INSERT into authorities values ('user','USER');

