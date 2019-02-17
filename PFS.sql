create database PFS;
use PFS;
create table user (
	id INT NOT NULL auto_increment,
    username varchar(1000) NOT NULL,
    token varchar(1000) NOT NULL,
    primary key (id)
    );

# commented out since the messages are never stored in db
#create table message (
#	id int not null auto_increment,
#    sender varchar(1000) not null,
#    recipient varchar(1000) not null,
#    message varchar(1000) not null,
#    time_in_millis bigint unsigned not null,
#    sender_key varchar(255) not null,
#    recipient_key varchar(255) not null,
#    token varchar(1000) NOT NULL,
#    primary key (id)
#    );

create table spare_key (
	id int not null auto_increment,
    userid int not null,
    spare_key varchar(255) not null,
    primary key (id)
    );
    
create table key_hash_count (
	userid_1 int not null,
    userid_2 int not null,
    key_count int not null,
    primary key (userid_1, userid_2),
    foreign key (userid_1) references account (id),
    foreign key (userid_2) references account (id)
    );
    
# used to add foreign key to spare_keys
ALTER TABLE `PFS`.`spare_key` 
ADD INDEX `fk_spare_key_1_idx` (`userid` ASC);
ALTER TABLE `PFS`.`spare_key` 
ADD CONSTRAINT `fk_spare_key_1`
  FOREIGN KEY (`userid`)
  REFERENCES `PFS`.`user` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



# test update queries from Spring
use PFS;
insert into user(username, token) values("Test", "token1");
select * from user where username="Test";
insert into spare_key(userid, spare_key) values(1, "abc");
insert into spare_key(userid, spare_key) values(1, "0");
