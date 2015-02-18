DROP TABLE IF EXISTS "GetArmors";
CREATE VIEW "GetArmors" AS 
SELECT Equipment._id, Equipment.name, Equipment.proficiencyGroup, Armors.bonus, Armors.maxDexBonus, Armors.requiredStr, Armors.impairsStealth, Equipment.weight, Equipment.cost
FROM Armors, Equipment
WHERE Equipment._id = Armors._id;
INSERT INTO "GetArmors" VALUES(1,'Padded',1,1,NULL,NULL,1,8,50000);
INSERT INTO "GetArmors" VALUES(2,'Leather',1,1,NULL,NULL,0,10,100000);
INSERT INTO "GetArmors" VALUES(3,'Studded leather',1,2,NULL,NULL,0,13,450000);
INSERT INTO "GetArmors" VALUES(4,'Hide',2,2,2,NULL,0,12,100000);
INSERT INTO "GetArmors" VALUES(5,'Chain shirt',2,3,2,NULL,0,20,500000);
INSERT INTO "GetArmors" VALUES(6,'Scale mail',2,4,2,NULL,1,45,500000);
INSERT INTO "GetArmors" VALUES(7,'Breastplate',2,4,2,NULL,0,20,4000000);
INSERT INTO "GetArmors" VALUES(8,'Half plate',2,5,2,NULL,1,40,7500000);
INSERT INTO "GetArmors" VALUES(9,'Ring mail',3,4,0,NULL,1,40,300000);
INSERT INTO "GetArmors" VALUES(10,'Chain mail',3,6,0,13,1,55,750000);
INSERT INTO "GetArmors" VALUES(11,'Splint',3,7,0,15,1,60,2000000);
INSERT INTO "GetArmors" VALUES(12,'Plate',3,8,0,15,1,65,10000000);
INSERT INTO "GetArmors" VALUES(13,'Shield',4,2,NULL,NULL,0,6,100000);
