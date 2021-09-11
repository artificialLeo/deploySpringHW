insert into employer (name, address, version) values ('test1', 'test1', 1);
insert into employer (name, address, version) values ('test2', 'test2', 2);
insert into employer (name, address, version) values ('test3', 'test3', 3);

insert into tbl_customer (name, email, age, version) values ('test1', 'test1', 1, 0);
insert into tbl_customer (name, email, age, version) values ('test2', 'test2', 2, 0);
insert into tbl_customer (name, email, age, version) values ('test3', 'test3', 3, 0);
insert into tbl_customer (name, email, age, version, password) values ('test4', 'test4', 3, 0, '$2a$10$uqFe65SYUW4UfnlMFv60zu.fUcpSIQUtkD/j6SHBgV6NPMsDQx0lS');

insert into account (number, currency, balance, CUSTOMER_ID, version) values ('test1', 1, 100, 1, 0);
insert into account (number, currency, balance, CUSTOMER_ID, version) values ('test2', 2, 1000, 2, 0);
insert into account (number, currency, balance, CUSTOMER_ID, version) values ('test3', 3, 20000, 3, 0);
insert into account (number, currency, balance, CUSTOMER_ID, version) values ('test3', 3, 30000, 3, 0);
insert into account (number, currency, balance, CUSTOMER_ID, version) values ('test3', 3, 50000, 3, 0);
