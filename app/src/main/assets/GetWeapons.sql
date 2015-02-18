DROP TABLE IF EXISTS "GetWeapons";
CREATE VIEW "GetWeapons" AS SELECT DISTINCT Equipment._id, Equipment.name, Equipment.proficiencyGroup, Weapons.dice, Weapons.dieSize, Weapons.id_damageType, RangedWeapons.shortRange, RangedWeapons.longRange, Equipment.weight, Equipment.cost
FROM Equipment, Weapons
LEFT JOIN RangedWeapons
ON RangedWeapons._id = Weapons._id
WHERE Equipment._id = Weapons._id;
INSERT INTO "GetWeapons" VALUES(27,'Club',5,1,4,1,NULL,NULL,2,100);
INSERT INTO "GetWeapons" VALUES(28,'Dagger',5,1,4,2,20,60,1,20000);
INSERT INTO "GetWeapons" VALUES(29,'Greatclub',5,1,8,1,NULL,NULL,10,200);
INSERT INTO "GetWeapons" VALUES(30,'Handaxe',5,1,6,3,20,60,2,50000);
INSERT INTO "GetWeapons" VALUES(31,'Javelin',5,1,6,2,30,120,2,500);
INSERT INTO "GetWeapons" VALUES(32,'Light hammer',5,1,4,1,20,60,2,20000);
INSERT INTO "GetWeapons" VALUES(33,'Mace',5,1,6,1,NULL,NULL,4,50000);
INSERT INTO "GetWeapons" VALUES(34,'Quarterstaff',5,1,6,1,NULL,NULL,4,200);
INSERT INTO "GetWeapons" VALUES(35,'Sickle',5,1,4,3,NULL,NULL,2,10000);
INSERT INTO "GetWeapons" VALUES(36,'Spear',5,1,6,2,20,60,3,10000);
INSERT INTO "GetWeapons" VALUES(37,'Crossbow, light',5,1,8,2,80,320,5,250000);
INSERT INTO "GetWeapons" VALUES(38,'Dart',5,1,4,2,20,60,0.25,5);
INSERT INTO "GetWeapons" VALUES(39,'Shortbow',5,1,6,2,80,320,2,250000);
INSERT INTO "GetWeapons" VALUES(40,'Sling',5,1,4,1,30,120,0.25,100);
INSERT INTO "GetWeapons" VALUES(41,'Battleaxe',6,1,8,3,NULL,NULL,4,100000);
INSERT INTO "GetWeapons" VALUES(42,'Flail',6,1,8,2,NULL,NULL,2,100000);
INSERT INTO "GetWeapons" VALUES(43,'Glaive',6,1,10,3,NULL,NULL,6,200000);
INSERT INTO "GetWeapons" VALUES(44,'Greataxe',6,1,12,3,NULL,NULL,7,300000);
INSERT INTO "GetWeapons" VALUES(45,'Greatsword',6,2,6,3,NULL,NULL,6,500000);
INSERT INTO "GetWeapons" VALUES(46,'Halberd',6,1,10,3,NULL,NULL,6,200000);
INSERT INTO "GetWeapons" VALUES(47,'Lance',6,1,12,2,NULL,NULL,6,100000);
INSERT INTO "GetWeapons" VALUES(48,'Longsword',6,1,8,3,NULL,NULL,3,150000);
INSERT INTO "GetWeapons" VALUES(49,'Maul',6,2,6,1,NULL,NULL,10,100000);
INSERT INTO "GetWeapons" VALUES(50,'Morningstar',6,1,8,2,NULL,NULL,4,150000);
INSERT INTO "GetWeapons" VALUES(51,'Pike',6,1,10,2,NULL,NULL,18,50000);
INSERT INTO "GetWeapons" VALUES(52,'Rapier',6,1,8,2,NULL,NULL,2,250000);
INSERT INTO "GetWeapons" VALUES(53,'Scimitar',6,1,6,3,NULL,NULL,3,250000);
INSERT INTO "GetWeapons" VALUES(54,'Shortsword',6,1,6,2,NULL,NULL,2,100000);
INSERT INTO "GetWeapons" VALUES(55,'Trident',6,1,6,2,20,60,4,50000);
INSERT INTO "GetWeapons" VALUES(56,'War pick',6,1,8,2,NULL,NULL,2,50000);
INSERT INTO "GetWeapons" VALUES(57,'Warhammer',6,1,8,1,NULL,NULL,2,150000);
INSERT INTO "GetWeapons" VALUES(58,'Whip',6,1,4,3,NULL,NULL,3,20000);
INSERT INTO "GetWeapons" VALUES(59,'Blowgun',6,1,1,2,25,100,1,100000);
INSERT INTO "GetWeapons" VALUES(60,'Crossbow, hand',6,1,6,2,30,120,3,750000);
INSERT INTO "GetWeapons" VALUES(61,'Crossbow, heavy',6,1,10,2,100,400,18,500000);
INSERT INTO "GetWeapons" VALUES(62,'Longbow',6,1,8,2,150,600,2,500000);
INSERT INTO "GetWeapons" VALUES(63,'Net',6,0,0,1,5,15,3,10000);
