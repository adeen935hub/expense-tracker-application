drop database if exists expense_management;

create database expense_management;

use expense_management;
--
-- Table structure for table role
--
CREATE TABLE role_mst (
  id bigint NOT NULL PRIMARY KEY  AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  name varchar(255) NOT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--
-- Table structure for table user
--

CREATE TABLE user_mst (
  id bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
  active tinyint(1) NOT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  user_name varchar(255) DEFAULT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);
--
-- Table structure for table user_role
--

CREATE TABLE user_role_trn (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user_mst(id),
  FOREIGN KEY (role_id) REFERENCES role_mst(id)
);

CREATE TABLE category_mst (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  category_name varchar(255) NOT NULL,
  category_description varchar(1000),
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
  
);

CREATE TABLE sub_category_mst (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  sub_category_name varchar(255) NOT NULL,
  sub_category_description varchar(1000),
  category_id BIGINT NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category_mst(id),
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
  
);

CREATE TABLE expense_detail_trn (
  id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  expense_amount decimal(10,2) NOT NULL,
  expense_date timestamp NOT NULL,
  category_id BIGINT NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category_mst(id),
  sub_category_id BIGINT NOT NULL,
  FOREIGN KEY (sub_category_id) REFERENCES sub_category_mst(id),
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
  
);



source insert_script.sql;