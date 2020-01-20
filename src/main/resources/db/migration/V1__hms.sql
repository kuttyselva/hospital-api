CREATE DATABASE `mydb`;
CREATE TABLE `t_branch`
(
    `pk_branch_id`    int(11)     NOT NULL AUTO_INCREMENT,
    `branch_name`     varchar(45) NOT NULL,
    `branch_location` varchar(45) NOT NULL,
    `fk_hospital_id`  int(11)     NOT NULL,
    `is_active`       tinyint(4)  NOT NULL DEFAULT '1',
    `created_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_branch_id`),
    UNIQUE KEY `branchID_UNIQUE` (`pk_branch_id`),
    UNIQUE KEY `branch_name_UNIQUE` (`branch_name`),
    KEY `fk_t_branch_t_hospital1_idx` (`fk_hospital_id`),
    CONSTRAINT `fk_t_branch_t_hospital1` FOREIGN KEY (`fk_hospital_id`) REFERENCES `t_hospital` (`pk_hospital_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_branch_admin_map`
(
    `pk_branch_admin_id` int(11)    NOT NULL AUTO_INCREMENT,
    `fk_user_id`         int(11)    NOT NULL,
    `fk_branch_id`       int(11)    NOT NULL,
    `is_active`          tinyint(4) NOT NULL DEFAULT '1',
    `created_at`         timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`         timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_branch_admin_id`),
    UNIQUE KEY `pk_branch_admin_id_UNIQUE` (`pk_branch_admin_id`),
    KEY `fk_branch_id_idx` (`fk_branch_id`),
    KEY `fk_user_id_idx` (`fk_user_id`),
    CONSTRAINT `fk_branchs_id` FOREIGN KEY (`fk_branch_id`) REFERENCES `t_branch` (`pk_branch_id`),
    CONSTRAINT `fk_users_id` FOREIGN KEY (`fk_user_id`) REFERENCES `t_user` (`pk_user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_branch_user_map`
(
    `pk_branch_user_map` int(11)    NOT NULL AUTO_INCREMENT,
    `fk_branch_id`       int(11)    NOT NULL,
    `fk_user_id`         int(11)    NOT NULL,
    `is_active`          tinyint(4) NOT NULL DEFAULT '1',
    `created_at`         timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`         timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_branch_user_map`),
    UNIQUE KEY `idt_branch_patient_map_UNIQUE` (`pk_branch_user_map`),
    KEY `fk_branch_id_idx` (`fk_branch_id`),
    KEY `fk_user_id_idx` (`fk_user_id`),
    CONSTRAINT `fk_branch_id` FOREIGN KEY (`fk_branch_id`) REFERENCES `t_branch` (`pk_branch_id`),
    CONSTRAINT `fk_user_id` FOREIGN KEY (`fk_user_id`) REFERENCES `t_user` (`pk_user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_doctor`
(
    `pk_doctor_id`      int(11)     NOT NULL AUTO_INCREMENT,
    `doctor_speciality` varchar(45) NOT NULL,
    `fk_user_id`        int(11)     NOT NULL,
    `is_active`         tinyint(4)  NOT NULL DEFAULT '1',
    `created_at`        timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`        timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_doctor_id`),
    UNIQUE KEY `pk_doctor_id_UNIQUE` (`pk_doctor_id`),
    KEY `fk_Doctor_user1_idx` (`fk_user_id`),
    CONSTRAINT `fk_Doctor_user1` FOREIGN KEY (`fk_user_id`) REFERENCES `t_user` (`pk_user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_hospital`
(
    `pk_hospital_id` int(11)     NOT NULL AUTO_INCREMENT,
    `hospital_name`  varchar(45) NOT NULL,
    `is_active`      tinyint(4)  NOT NULL DEFAULT '1',
    `created_at`     timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`     timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_hospital_id`),
    UNIQUE KEY `pk_hospital_id_UNIQUE` (`pk_hospital_id`),
    UNIQUE KEY `hospital_name_UNIQUE` (`hospital_name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_patient`
(
    `pk_patient_id`   int(11)     NOT NULL AUTO_INCREMENT,
    `patient_disease` varchar(45) NOT NULL,
    `fk_user_id`      int(11)     NOT NULL,
    `is_active`       tinyint(4)  NOT NULL DEFAULT '1',
    `created_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_patient_id`),
    UNIQUE KEY `pk_patient_id_UNIQUE` (`pk_patient_id`),
    KEY `fk_Patient_user1_idx` (`fk_user_id`),
    CONSTRAINT `fk_Patient_user1` FOREIGN KEY (`fk_user_id`) REFERENCES `t_user` (`pk_user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_patient_doctor_map`
(
    `pk_patient_doctor_id` int(11)    NOT NULL AUTO_INCREMENT,
    `fk_patient_id`        int(11)    NOT NULL,
    `fk_doctor_id`         int(11)    NOT NULL,
    `is_active`            tinyint(4) NOT NULL DEFAULT '1',
    `created_at`           timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_patient_doctor_id`),
    UNIQUE KEY `pk_patient_doctor_id_UNIQUE` (`pk_patient_doctor_id`),
    KEY `fk_patient_id_idx` (`fk_patient_id`),
    KEY `fk_doctor_id_idx` (`fk_doctor_id`),
    CONSTRAINT `fk_doctor_id` FOREIGN KEY (`fk_doctor_id`) REFERENCES `t_doctor` (`pk_doctor_id`),
    CONSTRAINT `fk_patient_id` FOREIGN KEY (`fk_patient_id`) REFERENCES `t_patient` (`pk_patient_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_role`
(
    `pk_role_id` int(11)     NOT NULL AUTO_INCREMENT,
    `role_desc`  varchar(45) NOT NULL,
    `is_active`  tinyint(4)  NOT NULL DEFAULT '1',
    `created_at` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_role_id`),
    UNIQUE KEY `pk_role_id_UNIQUE` (`pk_role_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

CREATE TABLE `t_user`
(
    `pk_user_id`    int(11)     NOT NULL AUTO_INCREMENT,
    `user_name`     varchar(45) NOT NULL,
    `user_password` varchar(45) NOT NULL,
    `user_location` varchar(45) NOT NULL,
    `user_age`      int(11)     NOT NULL,
    `user_phone`    varchar(45) NOT NULL,
    `fk_role_id`    int(11)     NOT NULL,
    `is_active`     tinyint(4)  NOT NULL DEFAULT '1',
    `created_at`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_user_id`),
    UNIQUE KEY `roleID_UNIQUE` (`pk_user_id`),
    UNIQUE KEY `user_name_UNIQUE` (`user_name`),
    KEY `fk_t_user_t_role1_idx` (`fk_role_id`),
    CONSTRAINT `fk_t_user_t_role1` FOREIGN KEY (`fk_role_id`) REFERENCES `t_role` (`pk_role_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8;
