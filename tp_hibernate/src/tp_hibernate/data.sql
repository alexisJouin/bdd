#
# Structure for the `groupes` table :
#

CREATE TABLE `groupes` (
`idgroupe` int(4) NOT NULL auto_increment,
`nomgroupe` varchar(50) default NULL,
`commentairegroupe` varchar(150) default NULL,
PRIMARY KEY (`idgroupe`),
UNIQUE KEY `idgroupe` (`idgroupe`)
)
#
# Structure for the `personnes` table :
#

CREATE TABLE `personnes` (
`idpersonne` int(11) NOT NULL auto_increment,
`nompersonne` varchar(50) default NULL,
`prenompersonne` varchar(50) default NULL,
`datenaisspersonne` datetime default NULL,
`coeffpersonne` int(11) default NULL,
PRIMARY KEY (`idpersonne`),
UNIQUE KEY `idpersonne` (`idpersonne`)
)

#
# Structure for the `grppers` table :
#

CREATE TABLE `grppers` (
`idgrppers` int(11) NOT NULL auto_increment,
`idgroupe` int(11) default NULL,
`idpersonne` int(11) default NULL,
PRIMARY KEY (`idgrppers`),
UNIQUE KEY `idgrppers` (`idgrppers`),
KEY `idgroupe` (`idgroupe`),
KEY `idpersonne` (`idpersonne`),
CONSTRAINT `0_48` FOREIGN KEY (`idpersonne`) REFERENCES `personnes`
(`idpersonne`),
CONSTRAINT `0_45` FOREIGN KEY (`idgroupe`) REFERENCES `groupes` (`idgroupe`)
)
INSERT INTO `groupes` (`idgroupe`, `nomgroupe`, `commentairegroupe`) VALUES
(1,'groupe 1',NULL),
(2,'groupe 2',NULL);

INSERT INTO `personnes` (`idpersonne`, `nompersonne`, `prenompersonne`,
`datenaisspersonne`, `coeffpersonne`) VALUES
(1,'nom1','prenom1','1967-01-06',123),
(2,'nom2','prenom2','1973-08-11',34),
(3,'nom3','prenom3','1956-04-28',145),
(4,'nom4','prenom4','1980-12-02',23),
(5,'nom5','prenom5','1966-10-13',119);

INSERT INTO `grppers` (`idgrppers`, `idgroupe`, `idpersonne`) VALUES
(1,1,1),
(2,2,2),
(3,2,3),
(4,1,4),
(5,1,5);
