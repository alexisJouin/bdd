#
# Structure for the `vol` table :
#

CREATE TABLE vol (
    NumVol VARCHAR(8) NOT NULL,
    Heure_depart TIME,
    Heure_arrive TIME,
    Ville_depart VARCHAR(20),
    Ville_arrivee VARCHAR(20),
    PRIMARY KEY (NumVol)
);

INSERT INTO vol VALUES ('AF118', '08:30', '10:57','Paris','Athens');
INSERT INTO vol VALUES ('AF212','09:21','14:10','Paris','Moscow');
INSERT INTO vol VALUES ('AF178','12:56','14:15','Paris','London');
INSERT INTO vol VALUES ('TA215','08:00','10:10','Tunis','Paris') ;
INSERT INTO vol VALUES ('OA005','14:20','17:00','Athens','Paris');
INSERT INTO vol VALUES ('SA854','22:00','10:14','Singapore','Athens');
INSERT INTO vol VALUES ('AA111','15:45','21:10','Beijing','Singapore');
INSERT INTO vol VALUES ('AF218','21:12','09:16','Beijing','Paris');
INSERT INTO vol VALUES ('SA012','07:57','11:26','Sydney','Singapore');
INSERT INTO vol VALUES ('AF109','07:39','14:10','Tahiti','Sydney');
INSERT INTO vol VALUES ('AA517','23:57','07:12','Honolulu','Tokyo');
INSERT INTO vol VALUES ('JA014','15:35','19:00','Tokyo','Beijing');
INSERT INTO vol VALUES ('AF002','15:52','00:12','Tokyo','Paris');
INSERT INTO vol VALUES ('JA115','21:26','10:10','Los Angeles','Tokyo');
INSERT INTO vol VALUES ('AA015','20:50','07:00','New York','Lima');
INSERT INTO vol VALUES ('AA515','07:20','12:38','New York','Los Angeles');
INSERT INTO vol VALUES ('AF010','07:53','14:19','Paris New','York');
INSERT INTO vol VALUES ('AF012','07:58','20:10','Paris Los','Angeles');
INSERT INTO vol VALUES ('AA118','07:15','13:10','New York','Paris');
INSERT INTO vol VALUES ('AF001','22:10','12:00','Paris','Tahiti') ;
INSERT INTO vol VALUES ('PA022','10:12','23:55','Lima','Paris');
INSERT INTO vol (`NumVol`, `Heure_depart`, `Heure_arrive`, `Ville_depart`, `Ville_arrivee`) VALUES ('AF666', '15:00', '12:00', 'Moscow', 'Paris');
INSERT INTO vol (`NumVol`, `Heure_depart`, `Heure_arrive`, `Ville_depart`, `Ville_arrivee`) VALUES ('AF667', '09:00', '11:00', 'London', 'Paris');

#
# Structure for the `Escales` table :
#

CREATE TABLE Escales (
    Numescale INT PRIMARY KEY AUTO_INCREMENT,
    Ville_escale VARCHAR(30),
    Duree_escale INT
)

INSERT INTO Escales VALUES (1, 'Moscou', 5);
INSERT INTO Escales VALUES (2, 'Singapour', 5);
INSERT INTO Escales VALUES (3, 'Sydney', 4);
INSERT INTO Escales VALUES (4, 'Tahiti', 4);
INSERT INTO Escales VALUES (5, 'Honolulu', 4);
INSERT INTO Escales VALUES (6, 'Los Angeles', 5);
INSERT INTO Escales VALUES (7, 'New York', 4);
INSERT INTO Escales VALUES (8, 'Londres', 3);