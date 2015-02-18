DROP TABLE IF EXISTS "Equipment";
CREATE TABLE Equipment
(
  _id INTEGER NOT NULL
        CONSTRAINT Key9 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  weight REAL NOT NULL,
  cost INTEGER NOT NULL,
  proficiencyGroup INTEGER,
  CONSTRAINT Relationship18 FOREIGN KEY (proficiencyGroup) REFERENCES ProficiencyGroups (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "Equipment" VALUES(1,'Padded',8,50000,1);
INSERT INTO "Equipment" VALUES(2,'Leather',10,100000,1);
INSERT INTO "Equipment" VALUES(3,'Studded leather',13,450000,1);
INSERT INTO "Equipment" VALUES(4,'Hide',12,100000,2);
INSERT INTO "Equipment" VALUES(5,'Chain shirt',20,500000,2);
INSERT INTO "Equipment" VALUES(6,'Scale mail',45,500000,2);
INSERT INTO "Equipment" VALUES(7,'Breastplate',20,4000000,2);
INSERT INTO "Equipment" VALUES(8,'Half plate',40,7500000,2);
INSERT INTO "Equipment" VALUES(9,'Ring mail',40,300000,3);
INSERT INTO "Equipment" VALUES(10,'Chain mail',55,750000,3);
INSERT INTO "Equipment" VALUES(11,'Splint',60,2000000,3);
INSERT INTO "Equipment" VALUES(12,'Plate',65,10000000,3);
INSERT INTO "Equipment" VALUES(13,'Shield',6,100000,4);
INSERT INTO "Equipment" VALUES(27,'Club',2,100,5);
INSERT INTO "Equipment" VALUES(28,'Dagger',1,20000,5);
INSERT INTO "Equipment" VALUES(29,'Greatclub',10,200,5);
INSERT INTO "Equipment" VALUES(30,'Handaxe',2,50000,5);
INSERT INTO "Equipment" VALUES(31,'Javelin',2,500,5);
INSERT INTO "Equipment" VALUES(32,'Light hammer',2,20000,5);
INSERT INTO "Equipment" VALUES(33,'Mace',4,50000,5);
INSERT INTO "Equipment" VALUES(34,'Quarterstaff',4,200,5);
INSERT INTO "Equipment" VALUES(35,'Sickle',2,10000,5);
INSERT INTO "Equipment" VALUES(36,'Spear',3,10000,5);
INSERT INTO "Equipment" VALUES(37,'Crossbow, light',5,250000,5);
INSERT INTO "Equipment" VALUES(38,'Dart',0.25,5,5);
INSERT INTO "Equipment" VALUES(39,'Shortbow',2,250000,5);
INSERT INTO "Equipment" VALUES(40,'Sling',0.25,100,5);
INSERT INTO "Equipment" VALUES(41,'Battleaxe',4,100000,6);
INSERT INTO "Equipment" VALUES(42,'Flail',2,100000,6);
INSERT INTO "Equipment" VALUES(43,'Glaive',6,200000,6);
INSERT INTO "Equipment" VALUES(44,'Greataxe',7,300000,6);
INSERT INTO "Equipment" VALUES(45,'Greatsword',6,500000,6);
INSERT INTO "Equipment" VALUES(46,'Halberd',6,200000,6);
INSERT INTO "Equipment" VALUES(47,'Lance',6,100000,6);
INSERT INTO "Equipment" VALUES(48,'Longsword',3,150000,6);
INSERT INTO "Equipment" VALUES(49,'Maul',10,100000,6);
INSERT INTO "Equipment" VALUES(50,'Morningstar',4,150000,6);
INSERT INTO "Equipment" VALUES(51,'Pike',18,50000,6);
INSERT INTO "Equipment" VALUES(52,'Rapier',2,250000,6);
INSERT INTO "Equipment" VALUES(53,'Scimitar',3,250000,6);
INSERT INTO "Equipment" VALUES(54,'Shortsword',2,100000,6);
INSERT INTO "Equipment" VALUES(55,'Trident',4,50000,6);
INSERT INTO "Equipment" VALUES(56,'War pick',2,50000,6);
INSERT INTO "Equipment" VALUES(57,'Warhammer',2,150000,6);
INSERT INTO "Equipment" VALUES(58,'Whip',3,20000,6);
INSERT INTO "Equipment" VALUES(59,'Blowgun',1,100000,6);
INSERT INTO "Equipment" VALUES(60,'Crossbow, hand',3,750000,6);
INSERT INTO "Equipment" VALUES(61,'Crossbow, heavy',18,500000,6);
INSERT INTO "Equipment" VALUES(62,'Longbow',2,500000,6);
INSERT INTO "Equipment" VALUES(63,'Net',3,10000,6);
