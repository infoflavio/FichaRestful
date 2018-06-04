
CREATE DATABASE IF NOT EXISTS `ficha` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ficha`;


DROP TABLE IF EXISTS `animal`;
CREATE TABLE IF NOT EXISTS `animal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `ficha_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFICHA` (`ficha_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;


INSERT INTO `animal` (`id`, `nome`, `ficha_id`) VALUES
(10, 'Cachorro', NULL),
(11, 'Cavalo', NULL),
(12, 'Coelho', NULL),
(13, 'EscorpiÃƒÂ£o', NULL),
(14, 'Abelha', 10),
(15, 'Rinoceronte', NULL),
(16, 'TubarÃƒÂ£o', NULL),
(17, 'Sapo', NULL);



DROP TABLE IF EXISTS `ficha`;
CREATE TABLE IF NOT EXISTS `ficha` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dt_cadastro` datetime NOT NULL,
  `status` varchar(10) NOT NULL,
  `observacao` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;



INSERT INTO `ficha` (`id`, `dt_cadastro`, `status`, `observacao`) VALUES
(1, '2018-05-17 21:00:00', 'ativa', 'Animal coletado em RS'),
(2, '2018-06-10 00:00:00', 'desativa', 'Animal coletado em SC'),
(7, '2018-06-04 00:00:00', 'desativa', 'Animal coletado em BA'),
(10, '2018-06-06 00:00:00', 'desativa', 'Animal coletado em BH'),
(11, '2018-06-11 00:00:00', 'desativa', 'Animal coletado em SC'),
(12, '2018-06-08 00:00:00', 'ativa', 'Animal coletado em PR'),
(13, '2018-06-06 00:00:00', 'ativa', 'Animal coletado em SC'),
(23, '2018-06-08 00:00:00', 'desativa', 'Animal coletado em PE');


ALTER TABLE `animal`
  ADD CONSTRAINT `FKFICHA` FOREIGN KEY (`ficha_id`) REFERENCES `ficha` (`id`);

