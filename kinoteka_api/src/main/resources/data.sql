INSERT INTO ROLE (ID, NAME) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

INSERT INTO USER (ID, USERNAME, PASSWORD, FK_ROLE) VALUES
(1, 'admin', '$2a$10$Vg0DX4wSeu0n89k1wJxy.OE38IlUSSj.3cbaebyJdtPjxIncdOmii', 1),
(2, 'test1', '$2a$10$Vg0DX4wSeu0n89k1wJxy.OE38IlUSSj.3cbaebyJdtPjxIncdOmii', 2),
(3, 'test2', '$2a$10$Vg0DX4wSeu0n89k1wJxy.OE38IlUSSj.3cbaebyJdtPjxIncdOmii', 2);

INSERT INTO MOVIE (ID, NAME) VALUES
(1,'Film 1'),
(3,'Pulp Ficition'),
(2,'Film 2');

INSERT INTO ROOM (ID, NAME) VALUES
(1, '1'),
(2, '2'),
(3, '3');

INSERT INTO SEAT (ID, ROW, SEAT_NUMBER, FK_ROOM) VALUES
(1, 'A', 1, 1),
(2, 'A', 2, 1),
(3, 'A', 3, 1),
(4, 'A', 4, 1),
(5, 'A', 5, 1),
(6, 'A', 6, 1),
(7, 'A', 7, 1),
(8, 'A', 8, 1),
(9, 'A', 9, 1),
(10, 'B', 10, 1),
(11, 'B', 1, 1),
(12, 'B', 2, 1),
(13, 'B', 3, 1),
(14, 'B', 4, 1),
(15, 'B', 5, 1),
(16, 'B', 6, 1),
(17, 'B', 7, 1),
(18, 'B', 8, 1),
(19, 'B', 9, 1),
(20, 'C', 10, 1),
(21, 'C', 1, 1),
(22, 'C', 2, 1),
(23, 'C', 3, 1),
(24, 'C', 4, 1),
(25, 'C', 5, 1),
(26, 'C', 6, 1),
(27, 'C', 7, 1),
(28, 'C', 8, 1),
(29, 'C', 9, 1),
(30, 'D', 10, 1),
(31, 'D', 1, 1),
(32, 'D', 2, 1),
(33, 'D', 3, 1),
(34, 'D', 4, 1),
(35, 'D', 5, 1),
(36, 'D', 6, 1),
(37, 'D', 7, 1),
(38, 'D', 8, 1),
(39, 'D', 9, 1),
(40, 'E', 10, 1),
(41, 'E', 1, 1),
(42, 'E', 2, 1),
(43, 'E', 3, 1),
(44, 'E', 4, 1),
(45, 'E', 5, 1),
(46, 'E', 6, 1),
(47, 'E', 7, 1),
(48, 'E', 8, 1),
(49, 'E', 9, 1),
(50, 'A', 10, 1),
(51, 'A', 1, 2),
(52, 'A', 2, 2),
(53, 'A', 3, 2),
(54, 'A', 4, 2),
(55, 'A', 5, 2),
(56, 'A', 6, 2),
(57, 'A', 7, 2),
(58, 'A', 8, 2),
(59, 'A', 9, 2),
(60, 'B', 10, 2),
(61, 'B', 1, 2),
(62, 'B', 2, 2),
(63, 'B', 3, 2),
(64, 'B', 4, 2),
(65, 'B', 5, 2),
(66, 'B', 6, 2),
(67, 'B', 7, 2),
(68, 'B', 8, 2),
(69, 'B', 9, 2),
(70, 'C', 10, 2),
(71, 'C', 1, 2),
(72, 'C', 2, 2),
(73, 'C', 3, 2),
(74, 'C', 4, 2),
(75, 'C', 5, 2),
(76, 'C', 6, 2),
(77, 'C', 7, 2),
(78, 'C', 8, 2),
(79, 'C', 9, 2),
(80, 'D', 10, 2),
(81, 'D', 1, 2),
(82, 'D', 2, 2),
(83, 'D', 3, 2),
(84, 'D', 4, 2),
(85, 'D', 5, 2),
(86, 'D', 6, 2),
(87, 'D', 7, 2),
(88, 'D', 8, 2),
(89, 'D', 9, 2),
(90, 'E', 10, 2),
(91, 'E', 1, 2),
(92, 'E', 2, 2),
(93, 'E', 3, 2),
(94, 'E', 4, 2),
(95, 'E', 5, 2),
(96, 'E', 6, 2),
(97, 'E', 7, 2),
(98, 'E', 8, 2),
(99, 'E', 9, 2),
(100, 'A', 10, 2),
(101, 'A', 1, 3),
(102, 'A', 2, 3),
(103, 'A', 3, 3),
(104, 'A', 4, 3),
(105, 'A', 5, 3),
(106, 'A', 6, 3),
(107, 'A', 7, 3),
(108, 'A', 8, 3),
(109, 'A', 9, 3),
(110, 'B', 10, 3),
(111, 'B', 1, 3),
(112, 'B', 2, 3),
(113, 'B', 3, 3),
(114, 'B', 4, 3),
(115, 'B', 5, 3),
(116, 'B', 6, 3),
(117, 'B', 7, 3),
(118, 'B', 8, 3),
(119, 'B', 9, 3),
(120, 'C', 10, 3),
(121, 'C', 1, 3),
(122, 'C', 2, 3),
(123, 'C', 3, 3),
(124, 'C', 4, 3),
(125, 'C', 5, 3),
(126, 'C', 6, 3),
(127, 'C', 7, 3),
(128, 'C', 8, 3),
(129, 'C', 9, 3),
(130, 'D', 10, 3),
(131, 'D', 1, 3),
(132, 'D', 2, 3),
(133, 'D', 3, 3),
(134, 'D', 4, 3),
(135, 'D', 5, 3),
(136, 'D', 6, 3),
(137, 'D', 7, 3),
(138, 'D', 8, 3),
(139, 'D', 9, 3),
(140, 'E', 10, 3),
(141, 'E', 1, 3),
(142, 'E', 2, 3),
(143, 'E', 3, 3),
(144, 'E', 4, 3),
(145, 'E', 5, 3),
(146, 'E', 6, 3),
(147, 'E', 7, 3),
(148, 'E', 8, 3),
(149, 'E', 9, 3),
(150, 'A', 10, 1),
(151, 'A', 10, 3);
