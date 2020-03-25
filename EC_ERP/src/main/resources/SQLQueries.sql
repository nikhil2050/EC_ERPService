CREATE TABLE `employee_master` (
   `employee_id` bigint(20) NOT NULL,
   `created_date` datetime NOT NULL DEFAULT NOW(),
   `employee_name` varchar(128) DEFAULT NULL,
   `mobile_no` varchar(32) DEFAULT NULL,
   `emergency_contact_no` varchar(32) DEFAULT NULL,
   `address` varchar(256) DEFAULT NULL,
   `birth_date` date DEFAULT NULL,
   `joining_date` date DEFAULT NULL,
   `department` varchar(128) DEFAULT NULL,
   PRIMARY KEY (`employee_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 