use expense_management;

INSERT INTO `role_mst` VALUES (1,'USER','USER','2023-04-11 14:06:49','2023-04-11 14:06:49'),(2,'ADMIN','ADMIN','2023-04-11 14:06:49','2023-04-11 14:06:49');
INSERT INTO `user_mst` VALUES (1,1,'admin@gmail.com','$2a$10$WGZPJKhRINR/6E3/DUfAhuoreLBT5iAw..T2EtNc2WZl2siT3zPtK','admin','2023-04-11 14:06:49','2023-04-11 14:06:49');
INSERT INTO `user_role_trn` VALUES (1,1,1),(2,1,2);