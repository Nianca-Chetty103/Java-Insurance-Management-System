Create Schema java;
use java;

create table customers(
customer_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) not null,
age INT
);

Create table policies(
id INT PRIMARY KEY AUTO_INCREMENT,
policy_type VARCHAR(100) not null,
coverage_amount DECIMAL not null,
premium_amount DECIMAL not null,
customer_id  int references customers(customers_id)
);

