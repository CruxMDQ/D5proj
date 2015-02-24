DROP TABLE IF EXISTS "AmmoPerWeapon";
CREATE TABLE IF NOT EXISTS AmmoPerWeapon
(
  id_weapon INTEGER NOT NULL,
  id_ammo INTEGER NOT NULL,
  CONSTRAINT Key28 PRIMARY KEY (id_weapon,id_ammo),
  CONSTRAINT Relationship38 FOREIGN KEY (id_weapon) REFERENCES RangedWeapons (_id),
  CONSTRAINT Relationship39 FOREIGN KEY (id_ammo) REFERENCES Equipment (_id)
);
DROP TABLE IF EXISTS "ArmorProficiencyGroups";
CREATE TABLE IF NOT EXISTS ArmorProficiencyGroups
(
  _id INTEGER NOT NULL
        CONSTRAINT Key11 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);
DROP TABLE IF EXISTS "ArmorProfsPerClass";
CREATE TABLE IF NOT EXISTS ArmorProfsPerClass
(
  id_class INTEGER NOT NULL,
  id_armor INTEGER NOT NULL,
  CONSTRAINT Key17 PRIMARY KEY (id_class,id_armor),
  CONSTRAINT Relationship21 FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship22 FOREIGN KEY (id_armor) REFERENCES Armors (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "Armors";
CREATE TABLE IF NOT EXISTS Armors
(
  _id INTEGER NOT NULL,
  bonus INTEGER NOT NULL,
  impairsStealth INTEGER NOT NULL DEFAULT 0
        CONSTRAINT CheckConstraintA1 CHECK (impairsStealth IN (0, 1)),
  requiredStr INTEGER,
  maxDexBonus INTEGER,
  CONSTRAINT Key10 PRIMARY KEY (_id),
  CONSTRAINT Relationship10 FOREIGN KEY (_id) REFERENCES Equipment (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "Armors" VALUES(1,1,1,NULL,NULL);
INSERT INTO "Armors" VALUES(2,1,0,NULL,NULL);
INSERT INTO "Armors" VALUES(3,2,0,NULL,NULL);
INSERT INTO "Armors" VALUES(4,2,0,NULL,2);
INSERT INTO "Armors" VALUES(5,3,0,NULL,2);
INSERT INTO "Armors" VALUES(6,4,1,NULL,2);
INSERT INTO "Armors" VALUES(7,4,0,NULL,2);
INSERT INTO "Armors" VALUES(8,5,1,NULL,2);
INSERT INTO "Armors" VALUES(9,4,1,NULL,0);
INSERT INTO "Armors" VALUES(10,6,1,13,0);
INSERT INTO "Armors" VALUES(11,7,1,15,0);
INSERT INTO "Armors" VALUES(12,8,1,15,0);
INSERT INTO "Armors" VALUES(13,2,0,NULL,NULL);
DROP TABLE IF EXISTS "CharacterClasses";
CREATE TABLE IF NOT EXISTS "CharacterClasses"
(
  _id INTEGER NOT NULL
        CONSTRAINT Key6 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  dieSize INTEGER NOT NULL,
  skills INTEGER NOT NULL,
  CONSTRAINT name UNIQUE (name)
);
INSERT INTO "CharacterClasses" VALUES(1,'Barbarian',12,3);
INSERT INTO "CharacterClasses" VALUES(2,'Bard',8,3);
INSERT INTO "CharacterClasses" VALUES(3,'Cleric',8,2);
INSERT INTO "CharacterClasses" VALUES(4,'Druid',8,2);
INSERT INTO "CharacterClasses" VALUES(5,'Fighter',10,2);
INSERT INTO "CharacterClasses" VALUES(6,'Monk',8,2);
INSERT INTO "CharacterClasses" VALUES(7,'Paladin',10,2);
INSERT INTO "CharacterClasses" VALUES(8,'Ranger',10,3);
INSERT INTO "CharacterClasses" VALUES(9,'Rogue',8,4);
INSERT INTO "CharacterClasses" VALUES(10,'Sorcerer',6,2);
INSERT INTO "CharacterClasses" VALUES(11,'Warlock',8,2);
INSERT INTO "CharacterClasses" VALUES(12,'Wizard',6,2);
DROP TABLE IF EXISTS "CharacterInventory";
CREATE TABLE IF NOT EXISTS CharacterInventory
(
  id_character INTEGER NOT NULL,
  id_item INTEGER NOT NULL,
  CONSTRAINT Key24 PRIMARY KEY (id_character,id_item),
  CONSTRAINT Relationship29 FOREIGN KEY (id_character) REFERENCES Characters (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship30 FOREIGN KEY (id_item) REFERENCES Equipment (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "CharacterLevels";
CREATE TABLE IF NOT EXISTS CharacterLevels
(
  id_character INTEGER NOT NULL,
  id_class INTEGER NOT NULL,
  hpRoll INTEGER NOT NULL,
  CONSTRAINT Key23 PRIMARY KEY (id_character,id_class),
  CONSTRAINT Relationship27 FOREIGN KEY (id_character) REFERENCES Characters (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship28 FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "Characters";
CREATE TABLE IF NOT EXISTS Characters
(
  _id INTEGER NOT NULL
        CONSTRAINT Key22 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  strength INTEGER NOT NULL,
  dexterity INTEGER NOT NULL,
  constitution INTEGER NOT NULL,
  intelligence INTEGER NOT NULL,
  wisdom INTEGER NOT NULL,
  charisma INTEGER NOT NULL,
  hitPoints INTEGER NOT NULL,
  experience INTEGER NOT NULL
);
DROP TABLE IF EXISTS "ClassFeatures";
CREATE TABLE IF NOT EXISTS ClassFeatures
(
  id_class INTEGER NOT NULL,
  id_feature INTEGER NOT NULL,
  CONSTRAINT pk_class_features PRIMARY KEY (id_class,id_feature),
  CONSTRAINT fk_class FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Features (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "ClassProficiencyGroups";
CREATE TABLE IF NOT EXISTS "ClassProficiencyGroups"
(
  id_class INTEGER NOT NULL,
  id_group INTEGER NOT NULL,
  CONSTRAINT Key16 PRIMARY KEY (id_class,id_group),
  CONSTRAINT Relationship19 FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship20 FOREIGN KEY (id_group) REFERENCES ProficiencyGroups (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "DamageTypes";
CREATE TABLE IF NOT EXISTS DamageTypes
(
  _id INTEGER NOT NULL
        CONSTRAINT Key29 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);
INSERT INTO "DamageTypes" VALUES(1,'Bludgeoning');
INSERT INTO "DamageTypes" VALUES(2,'Piercing');
INSERT INTO "DamageTypes" VALUES(3,'Slashing');
DROP TABLE IF EXISTS "Equipment";
CREATE TABLE IF NOT EXISTS Equipment
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
INSERT INTO "Equipment" VALUES(64,'Abacus',2,20000,NULL);
INSERT INTO "Equipment" VALUES(65,'Acid (vial)',1,250000,NULL);
INSERT INTO "Equipment" VALUES(66,'Alchemist''s fire (flask)',1,500000,NULL);
INSERT INTO "Equipment" VALUES(67,'Arrow',0.05,500,NULL);
INSERT INTO "Equipment" VALUES(68,'Blowgun needle',0.02,200,NULL);
INSERT INTO "Equipment" VALUES(69,'Crossbow bolt',0.075,500,NULL);
INSERT INTO "Equipment" VALUES(70,'Sling bullets (20)',1.5,4,NULL);
INSERT INTO "Equipment" VALUES(71,'Antitoxin (vial)',0.5,500000,NULL);
INSERT INTO "Equipment" VALUES(72,'Arcane focus: Crystal',1,100000,NULL);
INSERT INTO "Equipment" VALUES(73,'Arcane focus: Orb',3,200000,NULL);
INSERT INTO "Equipment" VALUES(74,'Arcane focus: Rod',2,100000,NULL);
INSERT INTO "Equipment" VALUES(75,'Arcane focus: Staff',4,50000,NULL);
INSERT INTO "Equipment" VALUES(76,'Arcane focus: Wand',1,100000,NULL);
INSERT INTO "Equipment" VALUES(77,'Backpack',5,20000,NULL);
INSERT INTO "Equipment" VALUES(78,'Ball bearings (bag of 1,000)',2,10000,NULL);
INSERT INTO "Equipment" VALUES(79,'Barrel',70,20000,NULL);
INSERT INTO "Equipment" VALUES(80,'Basket',2,400,NULL);
INSERT INTO "Equipment" VALUES(81,'Bedroll',7,10000,NULL);
INSERT INTO "Equipment" VALUES(82,'Bell',0.2,10000,NULL);
INSERT INTO "Equipment" VALUES(83,'Blanket',3,500,NULL);
INSERT INTO "Equipment" VALUES(84,'Block and tackle',5,10000,NULL);
INSERT INTO "Equipment" VALUES(85,'Book',5,250000,NULL);
INSERT INTO "Equipment" VALUES(86,'Bottle, glass',2,20000,NULL);
INSERT INTO "Equipment" VALUES(87,'Bucket',2,5,NULL);
INSERT INTO "Equipment" VALUES(88,'Caltrops (bag of 20)',2,10000,NULL);
INSERT INTO "Equipment" VALUES(89,'Candle',0,1,NULL);
INSERT INTO "Equipment" VALUES(90,'Case, crossbow bolt',1,10000,NULL);
INSERT INTO "Equipment" VALUES(91,'Case, map or scroll',1,10000,NULL);
INSERT INTO "Equipment" VALUES(92,'Chain (10 ft.)',10,50000,NULL);
INSERT INTO "Equipment" VALUES(93,'Chalk (1 piece)',0,1,NULL);
INSERT INTO "Equipment" VALUES(94,'Chest',25,50000,NULL);
INSERT INTO "Equipment" VALUES(95,'Climber''s kit',12,250000,NULL);
INSERT INTO "Equipment" VALUES(96,'Clothes, common',3,500,NULL);
INSERT INTO "Equipment" VALUES(97,'Clothes, costume',4,50000,NULL);
INSERT INTO "Equipment" VALUES(98,'Clothes, fine',6,150000,NULL);
INSERT INTO "Equipment" VALUES(99,'Clothes, traveler''s',4,20000,NULL);
INSERT INTO "Equipment" VALUES(100,'Component pouch',2,250000,NULL);
INSERT INTO "Equipment" VALUES(101,'Druidic focus: Sprig of mistletoe',0,10000,NULL);
INSERT INTO "Equipment" VALUES(102,'Druidic focus: Totem',0,10000,NULL);
INSERT INTO "Equipment" VALUES(103,'Druidic focus: Wooden staff',4,50000,NULL);
INSERT INTO "Equipment" VALUES(104,'Druidic focus: Yew wand',1,100000,NULL);
INSERT INTO "Equipment" VALUES(105,'Fishing tackle',4,10000,NULL);
INSERT INTO "Equipment" VALUES(106,'Flask or tankard',1,2,NULL);
INSERT INTO "Equipment" VALUES(107,'Grappling hook',4,20000,NULL);
INSERT INTO "Equipment" VALUES(108,'Hammer',3,10000,NULL);
INSERT INTO "Equipment" VALUES(109,'Hammer, sledge',10,20000,NULL);
INSERT INTO "Equipment" VALUES(110,'Healer''s kit',3,50000,NULL);
INSERT INTO "Equipment" VALUES(111,'Holy symbol: Amulet',1,50000,NULL);
INSERT INTO "Equipment" VALUES(112,'Holy symbol: Emblem',0,50000,NULL);
INSERT INTO "Equipment" VALUES(113,'Holy symbol: Reliquary',2,50000,NULL);
INSERT INTO "Equipment" VALUES(114,'Holy water (flask)',1,250000,NULL);
INSERT INTO "Equipment" VALUES(115,'Hourglass',1,250000,NULL);
INSERT INTO "Equipment" VALUES(116,'Hunting trap',25,50000,NULL);
INSERT INTO "Equipment" VALUES(117,'Ink (1 ounce bottle)',0,100000,NULL);
INSERT INTO "Equipment" VALUES(118,'Ink pen',0,2,NULL);
INSERT INTO "Equipment" VALUES(119,'Jug or pitcher',4,2,NULL);
INSERT INTO "Equipment" VALUES(120,'Ladder (10-foot)',25,100,NULL);
INSERT INTO "Equipment" VALUES(121,'Lamp',1,500,NULL);
INSERT INTO "Equipment" VALUES(122,'Lantern, bullseye',2,100000,NULL);
INSERT INTO "Equipment" VALUES(123,'Lantern, hooded',2,50000,NULL);
INSERT INTO "Equipment" VALUES(124,'Lock',1,100000,NULL);
INSERT INTO "Equipment" VALUES(125,'Magnifying glass',0,1000000,NULL);
INSERT INTO "Equipment" VALUES(126,'Manacles',6,20000,NULL);
INSERT INTO "Equipment" VALUES(127,'Mess kit',1,200,NULL);
INSERT INTO "Equipment" VALUES(128,'Mirror, steel',0.5,50000,NULL);
INSERT INTO "Equipment" VALUES(129,'Oil (flask)',1,100,NULL);
INSERT INTO "Equipment" VALUES(130,'Paper (one sheet)',0,200,NULL);
INSERT INTO "Equipment" VALUES(131,'Parchment (one sheet)',0,100,NULL);
INSERT INTO "Equipment" VALUES(132,'Perfume (vial)',0,50000,NULL);
INSERT INTO "Equipment" VALUES(133,'Pick, miner''s',10,20000,NULL);
INSERT INTO "Equipment" VALUES(134,'Piton',0.25,5,NULL);
INSERT INTO "Equipment" VALUES(135,'Poison, basic (vial)',0,1000000,NULL);
INSERT INTO "Equipment" VALUES(136,'Pole (10-foot)',7,5,NULL);
INSERT INTO "Equipment" VALUES(137,'Pot, iron',10,20000,NULL);
INSERT INTO "Equipment" VALUES(138,'Potion of healing',0.5,500000,NULL);
INSERT INTO "Equipment" VALUES(139,'Pouch',1,500,NULL);
INSERT INTO "Equipment" VALUES(140,'Quiver',1,10000,NULL);
INSERT INTO "Equipment" VALUES(141,'Ram, portable',35,40000,NULL);
INSERT INTO "Equipment" VALUES(142,'Rations (1 day)',2,500,NULL);
INSERT INTO "Equipment" VALUES(143,'Robes',4,10000,NULL);
INSERT INTO "Equipment" VALUES(144,'Rope, hempen (50 feet)',10,10000,NULL);
INSERT INTO "Equipment" VALUES(145,'Rope, silk (50 feet)',5,100000,NULL);
INSERT INTO "Equipment" VALUES(146,'Sack',0.5,1,NULL);
INSERT INTO "Equipment" VALUES(147,'Scale, merchant''s',3,50000,NULL);
INSERT INTO "Equipment" VALUES(148,'Sealing wax',0,500,NULL);
INSERT INTO "Equipment" VALUES(149,'Shovel',5,20000,NULL);
INSERT INTO "Equipment" VALUES(150,'Signal whistle',0,5,NULL);
INSERT INTO "Equipment" VALUES(151,'Signet ring',0,50000,NULL);
INSERT INTO "Equipment" VALUES(152,'Soap',0,2,NULL);
INSERT INTO "Equipment" VALUES(153,'Spellbook',3,500000,NULL);
INSERT INTO "Equipment" VALUES(154,'Spike, iron',0.5,1000,NULL);
INSERT INTO "Equipment" VALUES(155,'Spyglass',1,10000000,NULL);
INSERT INTO "Equipment" VALUES(156,'Tent, two-person',20,20000,NULL);
INSERT INTO "Equipment" VALUES(157,'Tinderbox',1,500,NULL);
INSERT INTO "Equipment" VALUES(158,'Torch',1,1,NULL);
INSERT INTO "Equipment" VALUES(159,'Vial',0,10000,NULL);
INSERT INTO "Equipment" VALUES(160,'Waterskin',5,200,NULL);
INSERT INTO "Equipment" VALUES(161,'Whetstone',1,1,NULL);
INSERT INTO "Equipment" VALUES(162,'Alchemist''s supplies',8,500000,NULL);
INSERT INTO "Equipment" VALUES(163,'Brewer''s supplies',9,200000,NULL);
INSERT INTO "Equipment" VALUES(164,'Calligrapher''s supplies',5,100000,NULL);
INSERT INTO "Equipment" VALUES(165,'Carpenter''s tools',6,80000,NULL);
INSERT INTO "Equipment" VALUES(166,'Cartographer''s tools',6,150000,NULL);
INSERT INTO "Equipment" VALUES(167,'Cobbler''s tools',5,50000,NULL);
INSERT INTO "Equipment" VALUES(168,'Cook''s utensils',8,10000,NULL);
INSERT INTO "Equipment" VALUES(169,'Glassblower''s tools',5,50000,NULL);
INSERT INTO "Equipment" VALUES(170,'Jeweler''s tools',2,250000,NULL);
INSERT INTO "Equipment" VALUES(171,'Leatherworker''s tools',5,50000,NULL);
INSERT INTO "Equipment" VALUES(172,'Mason''s tools',8,100000,NULL);
INSERT INTO "Equipment" VALUES(173,'Painter''s supplies',5,100000,NULL);
INSERT INTO "Equipment" VALUES(174,'Potter''s tools',3,100000,NULL);
INSERT INTO "Equipment" VALUES(175,'Smith''s tools',8,200000,NULL);
INSERT INTO "Equipment" VALUES(176,'Tinker''s tools',10,500000,NULL);
INSERT INTO "Equipment" VALUES(177,'Weaver''s tools',5,10000,NULL);
INSERT INTO "Equipment" VALUES(178,'Woodcarver''s tools',5,10000,NULL);
INSERT INTO "Equipment" VALUES(179,'Disguise kit',3,250000,NULL);
INSERT INTO "Equipment" VALUES(180,'Forgery kit',5,150000,NULL);
INSERT INTO "Equipment" VALUES(181,'Dice set',0,100,NULL);
INSERT INTO "Equipment" VALUES(182,'Dragonchess set',0.5,10000,NULL);
INSERT INTO "Equipment" VALUES(183,'Playing card set',0,500,NULL);
INSERT INTO "Equipment" VALUES(184,'Three-Dragon Ante set',0,10000,NULL);
INSERT INTO "Equipment" VALUES(185,'Herbalism kit',3,50000,NULL);
INSERT INTO "Equipment" VALUES(186,'Bagpipes',6,300000,NULL);
INSERT INTO "Equipment" VALUES(187,'Drum',3,60000,NULL);
INSERT INTO "Equipment" VALUES(188,'Dulcimer',10,250000,NULL);
INSERT INTO "Equipment" VALUES(189,'Flute',1,20000,NULL);
INSERT INTO "Equipment" VALUES(190,'Lute',2,350000,NULL);
INSERT INTO "Equipment" VALUES(191,'Lyre',2,300000,NULL);
INSERT INTO "Equipment" VALUES(192,'Horn',2,30000,NULL);
INSERT INTO "Equipment" VALUES(193,'Pan flute',2,120000,NULL);
INSERT INTO "Equipment" VALUES(194,'Shawm',1,20000,NULL);
INSERT INTO "Equipment" VALUES(195,'Viol',1,300000,NULL);
INSERT INTO "Equipment" VALUES(196,'Navigator''s tools',2,250000,NULL);
INSERT INTO "Equipment" VALUES(197,'Poisoner''s kit',2,500000,NULL);
INSERT INTO "Equipment" VALUES(198,'Thieves'' tools',1,250000,NULL);
DROP TABLE IF EXISTS "ExperienceLevels";
CREATE TABLE IF NOT EXISTS ExperienceLevels
(
  _id INTEGER NOT NULL
        CONSTRAINT Key21 PRIMARY KEY AUTOINCREMENT,
  experience INTEGER NOT NULL,
  proficiencyBonus INTEGER NOT NULL
);
INSERT INTO "ExperienceLevels" VALUES(1,0,2);
INSERT INTO "ExperienceLevels" VALUES(2,300,2);
INSERT INTO "ExperienceLevels" VALUES(3,900,2);
INSERT INTO "ExperienceLevels" VALUES(4,2700,2);
INSERT INTO "ExperienceLevels" VALUES(5,6500,3);
INSERT INTO "ExperienceLevels" VALUES(6,14000,3);
INSERT INTO "ExperienceLevels" VALUES(7,23000,3);
INSERT INTO "ExperienceLevels" VALUES(8,34000,3);
INSERT INTO "ExperienceLevels" VALUES(9,48000,4);
INSERT INTO "ExperienceLevels" VALUES(10,64000,4);
INSERT INTO "ExperienceLevels" VALUES(11,85000,4);
INSERT INTO "ExperienceLevels" VALUES(12,100000,4);
INSERT INTO "ExperienceLevels" VALUES(13,120000,5);
INSERT INTO "ExperienceLevels" VALUES(14,140000,5);
INSERT INTO "ExperienceLevels" VALUES(15,165000,5);
INSERT INTO "ExperienceLevels" VALUES(16,195000,5);
INSERT INTO "ExperienceLevels" VALUES(17,225000,6);
INSERT INTO "ExperienceLevels" VALUES(18,265000,6);
INSERT INTO "ExperienceLevels" VALUES(19,305000,6);
INSERT INTO "ExperienceLevels" VALUES(20,355000,6);
DROP TABLE IF EXISTS "FeatureChoices";
CREATE TABLE IF NOT EXISTS "FeatureChoices"
(
id_feature INTEGER NOT NULL,
id_choice INTEGER NOT NULL CHECK (id_choice IS NOT id_feature),
CONSTRAINT pk_choices_features PRIMARY KEY (id_feature, id_choice),
CONSTRAINT fk_feat_parent FOREIGN KEY (id_feature) REFERENCES FeaturesWithOptions (id_feature) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_feat_option FOREIGN KEY (id_choice) REFERENCES Features (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO "FeatureChoices" VALUES(3,4);
INSERT INTO "FeatureChoices" VALUES(3,5);
INSERT INTO "FeatureChoices" VALUES(3,6);
DROP TABLE IF EXISTS "FeatureLanguages";
CREATE TABLE IF NOT EXISTS FeatureLanguages
(
  id_feature INTEGER NOT NULL,
  id_language INTEGER NOT NULL,
  CONSTRAINT pk_feature_profs PRIMARY KEY (id_feature ,id_language),
  CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Feature (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_language FOREIGN KEY (id_language) REFERENCES Languages(_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "FeatureLanguages" VALUES(8,1);
INSERT INTO "FeatureLanguages" VALUES(9,2);
INSERT INTO "FeatureLanguages" VALUES(20,3);
INSERT INTO "FeatureLanguages" VALUES(26,4);
INSERT INTO "FeatureLanguages" VALUES(27,5);
INSERT INTO "FeatureLanguages" VALUES(28,6);
INSERT INTO "FeatureLanguages" VALUES(29,7);
INSERT INTO "FeatureLanguages" VALUES(30,8);
INSERT INTO "FeatureLanguages" VALUES(31,9);
INSERT INTO "FeatureLanguages" VALUES(32,10);
INSERT INTO "FeatureLanguages" VALUES(33,11);
INSERT INTO "FeatureLanguages" VALUES(34,12);
INSERT INTO "FeatureLanguages" VALUES(35,13);
INSERT INTO "FeatureLanguages" VALUES(36,14);
INSERT INTO "FeatureLanguages" VALUES(37,15);
INSERT INTO "FeatureLanguages" VALUES(38,16);
DROP TABLE IF EXISTS "FeatureProficiencies";
CREATE TABLE IF NOT EXISTS FeatureProficiencies
(
  id_feature INTEGER NOT NULL,
  id_equipment INTEGER NOT NULL,
  CONSTRAINT pk_feature_profs PRIMARY KEY (id_feature ,id_equipment),
  CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Feature (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_equipment FOREIGN KEY (id_equipment) REFERENCES Equipment(_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "FeatureProficiencies" VALUES(4,175);
INSERT INTO "FeatureProficiencies" VALUES(5,163);
INSERT INTO "FeatureProficiencies" VALUES(6,172);
INSERT INTO "FeatureProficiencies" VALUES(10,41);
INSERT INTO "FeatureProficiencies" VALUES(11,30);
INSERT INTO "FeatureProficiencies" VALUES(12,32);
INSERT INTO "FeatureProficiencies" VALUES(13,57);
INSERT INTO "FeatureProficiencies" VALUES(21,48);
INSERT INTO "FeatureProficiencies" VALUES(22,54);
INSERT INTO "FeatureProficiencies" VALUES(23,39);
INSERT INTO "FeatureProficiencies" VALUES(24,62);
DROP TABLE IF EXISTS "FeatureProficiencyGroups";
CREATE TABLE IF NOT EXISTS FeatureProficiencyGroups
(
  id_feature INTEGER NOT NULL,
  id_profgroup INTEGER NOT NULL,
  CONSTRAINT pk_feature_profgroupss PRIMARY KEY (id_feature ,id_profgroup),
  CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Feature (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_profgroup FOREIGN KEY (id_profgroup) REFERENCES ProficiencyGroups(_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "FeatureProficiencyGroups" VALUES(15,1);
INSERT INTO "FeatureProficiencyGroups" VALUES(16,2);
DROP TABLE IF EXISTS "FeatureSkills";
CREATE TABLE IF NOT EXISTS FeatureSkills
(
id_feature INTEGER NOT NULL,
id_skill INTEGER NOT NULL,
CONSTRAINT pk_comp_feature_skills PRIMARY KEY (id_feature, id_skill),
CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Features (_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_skill FOREIGN KEY (id_skill) REFERENCES Skills (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO "FeatureSkills" VALUES(17,13);
DROP TABLE IF EXISTS "Features";
CREATE TABLE IF NOT EXISTS Features
(
_id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL,
hasOptions INTEGER NOT NULL DEFAULT 0
        CONSTRAINT chk_feat_bool CHECK (hasOptions IN (0, 1))
);
INSERT INTO "Features" VALUES(1,'Darkvision',0);
INSERT INTO "Features" VALUES(2,'Dwarven Resilience',0);
INSERT INTO "Features" VALUES(3,'Dwarven tool proficiency',1);
INSERT INTO "Features" VALUES(4,'Proficiency: smith''s tools',0);
INSERT INTO "Features" VALUES(5,'Proficiency: brewer''s supplies',0);
INSERT INTO "Features" VALUES(6,'Proficiency: mason''s tools',0);
INSERT INTO "Features" VALUES(7,'Stonecunning',0);
INSERT INTO "Features" VALUES(8,'Language: common',0);
INSERT INTO "Features" VALUES(9,'Language: dwarven',0);
INSERT INTO "Features" VALUES(10,'Proficiency: battleaxe',0);
INSERT INTO "Features" VALUES(11,'Proficiency: handaxe',0);
INSERT INTO "Features" VALUES(12,'Proficiency: light hammer',0);
INSERT INTO "Features" VALUES(13,'Proficiency: warhammer',0);
INSERT INTO "Features" VALUES(14,'Dwarven toughness',0);
INSERT INTO "Features" VALUES(15,'Proficiency: light armor',0);
INSERT INTO "Features" VALUES(16,'Proficiency: medium armor',0);
INSERT INTO "Features" VALUES(17,'Proficiency: skill (Perception)',0);
INSERT INTO "Features" VALUES(18,'Fey Ancestry',0);
INSERT INTO "Features" VALUES(19,'Trance',0);
INSERT INTO "Features" VALUES(20,'Language: elvish',0);
INSERT INTO "Features" VALUES(21,'Proficiency: longsword',0);
INSERT INTO "Features" VALUES(22,'Proficiency: shortsword',0);
INSERT INTO "Features" VALUES(23,'Proficiency: shortbow',0);
INSERT INTO "Features" VALUES(24,'Proficiency: longbow',0);
INSERT INTO "Features" VALUES(25,'Extra language',1);
INSERT INTO "Features" VALUES(26,'Language: giant',0);
INSERT INTO "Features" VALUES(27,'Language: gnomish',0);
INSERT INTO "Features" VALUES(28,'Language: goblin',0);
INSERT INTO "Features" VALUES(29,'Language: halfling',0);
INSERT INTO "Features" VALUES(30,'Language: orcish',0);
INSERT INTO "Features" VALUES(31,'Language: abyssal',0);
INSERT INTO "Features" VALUES(32,'Language: celestial',0);
INSERT INTO "Features" VALUES(33,'Language: draconic',0);
INSERT INTO "Features" VALUES(34,'Language: deep speech',0);
INSERT INTO "Features" VALUES(35,'Language: infernal',0);
INSERT INTO "Features" VALUES(36,'Language: primordial',0);
INSERT INTO "Features" VALUES(37,'Language: sylvan',0);
INSERT INTO "Features" VALUES(38,'Language: undercommon',0);
DROP TABLE IF EXISTS "FeaturesWithOptions";
CREATE TABLE IF NOT EXISTS FeaturesWithOptions 
(
id_feature INTEGER NOT NULL,
choices INTEGER DEFAULT 2,
CONSTRAINT fk_feat_fopts FOREIGN KEY (id_feature) REFERENCES Features (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO "FeaturesWithOptions" VALUES(3,1);
INSERT INTO "FeaturesWithOptions" VALUES(18,1);
DROP TABLE IF EXISTS "KeyStatsPerClass";
CREATE TABLE IF NOT EXISTS KeyStatsPerClass
(
  _id INTEGER NOT NULL,
  id_class INTEGER NOT NULL,
  stat TEXT NOT NULL CHECK (stat IN('STR', 'DEX', 'CON', 'INT', 'WIS', 'CHA')),
  type TEXT NOT NULL CHECK (type IN('Primary', 'Save')),
  CONSTRAINT pk_stats_class PRIMARY KEY (_id),
  CONSTRAINT fk_stats_class FOREIGN KEY (id_class) REFERENCES CharacterClasses (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO "KeyStatsPerClass" VALUES(1,1,'STR','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(2,1,'STR','Save');
INSERT INTO "KeyStatsPerClass" VALUES(3,1,'CON','Save');
INSERT INTO "KeyStatsPerClass" VALUES(4,2,'CHA','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(5,2,'CHA','Save');
INSERT INTO "KeyStatsPerClass" VALUES(6,2,'DEX','Save');
INSERT INTO "KeyStatsPerClass" VALUES(7,3,'WIS','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(8,3,'WIS','Save');
INSERT INTO "KeyStatsPerClass" VALUES(9,3,'CHA','Save');
INSERT INTO "KeyStatsPerClass" VALUES(10,4,'WIS','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(11,4,'WIS','Save');
INSERT INTO "KeyStatsPerClass" VALUES(12,4,'INT','Save');
INSERT INTO "KeyStatsPerClass" VALUES(13,5,'STR','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(14,5,'DEX','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(15,5,'STR','Save');
INSERT INTO "KeyStatsPerClass" VALUES(16,5,'CON','Save');
INSERT INTO "KeyStatsPerClass" VALUES(17,6,'DEX','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(18,6,'WIS','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(19,6,'DEX','Save');
INSERT INTO "KeyStatsPerClass" VALUES(20,6,'STR','Save');
INSERT INTO "KeyStatsPerClass" VALUES(21,7,'STR','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(22,7,'CHA','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(23,7,'WIS','Save');
INSERT INTO "KeyStatsPerClass" VALUES(24,7,'CHA','Save');
INSERT INTO "KeyStatsPerClass" VALUES(25,8,'DEX','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(26,8,'WIS','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(27,8,'DEX','Save');
INSERT INTO "KeyStatsPerClass" VALUES(28,8,'STR','Save');
INSERT INTO "KeyStatsPerClass" VALUES(29,9,'DEX','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(30,9,'DEX','Save');
INSERT INTO "KeyStatsPerClass" VALUES(31,9,'INT','Save');
INSERT INTO "KeyStatsPerClass" VALUES(32,10,'CHA','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(33,10,'CHA','Save');
INSERT INTO "KeyStatsPerClass" VALUES(34,10,'CON','Save');
INSERT INTO "KeyStatsPerClass" VALUES(35,11,'CHA','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(36,11,'CHA','Save');
INSERT INTO "KeyStatsPerClass" VALUES(37,11,'WIS','Save');
INSERT INTO "KeyStatsPerClass" VALUES(38,12,'INT','Primary');
INSERT INTO "KeyStatsPerClass" VALUES(39,12,'INT','Save');
INSERT INTO "KeyStatsPerClass" VALUES(40,12,'WIS','Save');
DROP TABLE IF EXISTS "Languages";
CREATE TABLE IF NOT EXISTS Languages
(
_id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL,
id_script INTEGER,
CONSTRAINT fk_lang_script FOREIGN KEY (id_script) REFERENCES WrittenAlphabets (_id)
);
INSERT INTO "Languages" VALUES(1,'Common',1);
INSERT INTO "Languages" VALUES(2,'Dwarvish',2);
INSERT INTO "Languages" VALUES(3,'Elvish',3);
INSERT INTO "Languages" VALUES(4,'Giant',2);
INSERT INTO "Languages" VALUES(5,'Gnomish',2);
INSERT INTO "Languages" VALUES(6,'Goblin',2);
INSERT INTO "Languages" VALUES(7,'Halfling',1);
INSERT INTO "Languages" VALUES(8,'Orcish',2);
INSERT INTO "Languages" VALUES(9,'Abyssal',4);
INSERT INTO "Languages" VALUES(10,'Celestial',5);
INSERT INTO "Languages" VALUES(11,'Draconic',6);
INSERT INTO "Languages" VALUES(12,'Deep Speech',NULL);
INSERT INTO "Languages" VALUES(13,'Infernal',4);
INSERT INTO "Languages" VALUES(14,'Primordial',2);
INSERT INTO "Languages" VALUES(15,'Sylvan',3);
INSERT INTO "Languages" VALUES(16,'Undercommon',3);
DROP TABLE IF EXISTS "LanguagesPerRace";
CREATE TABLE IF NOT EXISTS LanguagesPerRace
(
id_race INTEGER NOT NULL,
id_language INTEGER NOT NULL,
CONSTRAINT pk_composite_lang_race PRIMARY KEY (id_race, id_language),
CONSTRAINT fk_composite_lr_race FOREIGN KEY (id_race) REFERENCES Races (_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_composite_lr_language FOREIGN KEY (id_language) REFERENCES Languages (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "ProficiencyGroups";
CREATE TABLE IF NOT EXISTS ProficiencyGroups
(
  _id INTEGER NOT NULL
        CONSTRAINT Key15 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);
INSERT INTO "ProficiencyGroups" VALUES(1,'LightArmor');
INSERT INTO "ProficiencyGroups" VALUES(2,'MediumArmor');
INSERT INTO "ProficiencyGroups" VALUES(3,'HeavyArmor');
INSERT INTO "ProficiencyGroups" VALUES(4,'Shield');
INSERT INTO "ProficiencyGroups" VALUES(5,'SimpleWeapon');
INSERT INTO "ProficiencyGroups" VALUES(6,'MartialWeapon');
DROP TABLE IF EXISTS "Races";
CREATE TABLE IF NOT EXISTS "Races"
(
_id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL,
speed INTEGER NOT NULL,
id_size INTEGER NOT NULL,
id_parent INTEGER CHECK (id_parent IS NOT _id),
isArchetype INTEGER CHECK (isArchetype IN (0, 1)),
CONSTRAINT fk_race_size FOREIGN KEY (id_size) REFERENCES Sizes (_id) 
CONSTRAINT fk_parent FOREIGN KEY (id_parent) REFERENCES Races (_id)
);
INSERT INTO "Races" VALUES(1,'Dwarf',25,2,NULL,1);
INSERT INTO "Races" VALUES(2,'Hill Dwarf',25,2,1,0);
INSERT INTO "Races" VALUES(3,'Mountain Dwarf',25,2,1,0);
INSERT INTO "Races" VALUES(4,'Elf',30,2,NULL,1);
INSERT INTO "Races" VALUES(5,'High Elf',30,2,4,0);
INSERT INTO "Races" VALUES(6,'Wood Elf',35,2,4,0);
INSERT INTO "Races" VALUES(7,'Dark Elf (Drow)',30,2,4,0);
DROP TABLE IF EXISTS "RacialFeatureValues";
CREATE TABLE IF NOT EXISTS RacialFeatureValues
(
  id_value INTEGER PRIMARY KEY,
  value INTEGER NOT NULL,
  CONSTRAINT fk_featureVALUE FOREIGN KEY (id_value) REFERENCES RacialFeatures(_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "RacialFeatureValues" VALUES(1,60);
INSERT INTO "RacialFeatureValues" VALUES(18,60);
INSERT INTO "RacialFeatureValues" VALUES(19,120);
DROP TABLE IF EXISTS "RacialFeatures";
CREATE TABLE IF NOT EXISTS RacialFeatures
(
  _id INTEGER PRIMARY KEY AUTOINCREMENT,
  id_race INTEGER NOT NULL,
  id_feature INTEGER NOT NULL,
  CONSTRAINT fk_race FOREIGN KEY (id_race) REFERENCES Races (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Features (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "RacialFeatures" VALUES(1,1,1);
INSERT INTO "RacialFeatures" VALUES(2,1,2);
INSERT INTO "RacialFeatures" VALUES(3,1,3);
INSERT INTO "RacialFeatures" VALUES(4,1,7);
INSERT INTO "RacialFeatures" VALUES(5,1,8);
INSERT INTO "RacialFeatures" VALUES(6,1,9);
INSERT INTO "RacialFeatures" VALUES(7,1,10);
INSERT INTO "RacialFeatures" VALUES(8,1,11);
INSERT INTO "RacialFeatures" VALUES(9,1,12);
INSERT INTO "RacialFeatures" VALUES(10,1,13);
INSERT INTO "RacialFeatures" VALUES(11,2,14);
INSERT INTO "RacialFeatures" VALUES(12,3,15);
INSERT INTO "RacialFeatures" VALUES(13,3,16);
INSERT INTO "RacialFeatures" VALUES(14,4,17);
INSERT INTO "RacialFeatures" VALUES(15,4,18);
INSERT INTO "RacialFeatures" VALUES(16,4,19);
INSERT INTO "RacialFeatures" VALUES(17,4,20);
INSERT INTO "RacialFeatures" VALUES(18,4,1);
INSERT INTO "RacialFeatures" VALUES(19,7,1);
INSERT INTO "RacialFeatures" VALUES(20,5,21);
INSERT INTO "RacialFeatures" VALUES(21,5,22);
INSERT INTO "RacialFeatures" VALUES(22,5,23);
INSERT INTO "RacialFeatures" VALUES(23,5,24);
INSERT INTO "RacialFeatures" VALUES(24,6,21);
INSERT INTO "RacialFeatures" VALUES(25,6,22);
INSERT INTO "RacialFeatures" VALUES(26,6,23);
INSERT INTO "RacialFeatures" VALUES(27,6,24);
DROP TABLE IF EXISTS "RacialStats";
CREATE TABLE IF NOT EXISTS RacialStats
(
  _id INTEGER NOT NULL,
  id_race INTEGER NOT NULL,
  stat TEXT NOT NULL CHECK (stat IN('STR', 'DEX', 'CON', 'INT', 'WIS', 'CHA')),
  bonus INTEGER NOT NULL,
  CONSTRAINT pk_race_stats PRIMARY KEY (_id),
  CONSTRAINT fk_race_stats FOREIGN KEY (id_race) REFERENCES Races (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO "RacialStats" VALUES(1,1,'CON',2);
INSERT INTO "RacialStats" VALUES(2,2,'WIS',1);
INSERT INTO "RacialStats" VALUES(3,3,'STR',2);
INSERT INTO "RacialStats" VALUES(4,4,'DEX',2);
INSERT INTO "RacialStats" VALUES(5,6,'WIS',1);
INSERT INTO "RacialStats" VALUES(6,7,'CHA',1);
INSERT INTO "RacialStats" VALUES(7,5,'INT',1);
DROP TABLE IF EXISTS "RangedWeapons";
CREATE TABLE IF NOT EXISTS RangedWeapons
(
  _id INTEGER NOT NULL,
  shortRange INTEGER,
  longRange INTEGER,
  CONSTRAINT Key27 PRIMARY KEY (_id),
  CONSTRAINT Relationship36 FOREIGN KEY (_id) REFERENCES Weapons (_id)
);
INSERT INTO "RangedWeapons" VALUES(28,20,60);
INSERT INTO "RangedWeapons" VALUES(30,20,60);
INSERT INTO "RangedWeapons" VALUES(31,30,120);
INSERT INTO "RangedWeapons" VALUES(32,20,60);
INSERT INTO "RangedWeapons" VALUES(36,20,60);
INSERT INTO "RangedWeapons" VALUES(37,80,320);
INSERT INTO "RangedWeapons" VALUES(38,20,60);
INSERT INTO "RangedWeapons" VALUES(39,80,320);
INSERT INTO "RangedWeapons" VALUES(40,30,120);
INSERT INTO "RangedWeapons" VALUES(55,20,60);
INSERT INTO "RangedWeapons" VALUES(59,25,100);
INSERT INTO "RangedWeapons" VALUES(60,30,120);
INSERT INTO "RangedWeapons" VALUES(61,100,400);
INSERT INTO "RangedWeapons" VALUES(62,150,600);
INSERT INTO "RangedWeapons" VALUES(63,5,15);
DROP TABLE IF EXISTS "Sizes";
CREATE TABLE IF NOT EXISTS Sizes
(
_id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL
);
INSERT INTO "Sizes" VALUES(1,'Small');
INSERT INTO "Sizes" VALUES(2,'Medium');
DROP TABLE IF EXISTS "Skills";
CREATE TABLE IF NOT EXISTS Skills
(
  _id INTEGER NOT NULL
        CONSTRAINT Key13 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  keyStat TEXT NOT NULL
        CONSTRAINT CheckConstraintA1 CHECK (keyStat IN ('STR', 'DEX', 'CON', 'INT', 'WIS', 'CHA'))
);
INSERT INTO "Skills" VALUES(1,'Athletics','STR');
INSERT INTO "Skills" VALUES(2,'Acrobatics','DEX');
INSERT INTO "Skills" VALUES(3,'Sleight of Hand','DEX');
INSERT INTO "Skills" VALUES(4,'Stealth','DEX');
INSERT INTO "Skills" VALUES(5,'Arcana','INT');
INSERT INTO "Skills" VALUES(6,'History','INT');
INSERT INTO "Skills" VALUES(7,'Investigation','INT');
INSERT INTO "Skills" VALUES(8,'Nature','INT');
INSERT INTO "Skills" VALUES(9,'Religion','INT');
INSERT INTO "Skills" VALUES(10,'Animal Handling','WIS');
INSERT INTO "Skills" VALUES(11,'Insight','WIS');
INSERT INTO "Skills" VALUES(12,'Medicine','WIS');
INSERT INTO "Skills" VALUES(13,'Perception','WIS');
INSERT INTO "Skills" VALUES(14,'Survival','WIS');
INSERT INTO "Skills" VALUES(15,'Deception','CHA');
INSERT INTO "Skills" VALUES(16,'Intimidation','CHA');
INSERT INTO "Skills" VALUES(17,'Performance','CHA');
INSERT INTO "Skills" VALUES(18,'Persuasion','CHA');
DROP TABLE IF EXISTS "SkillsPerClass";
CREATE TABLE IF NOT EXISTS SkillsPerClass
(
  id_class INTEGER NOT NULL,
  id_skill INTEGER NOT NULL,
  CONSTRAINT Key14 PRIMARY KEY (id_class,id_skill),
  CONSTRAINT Relationship16 FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship17 FOREIGN KEY (id_skill) REFERENCES Skills (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "SkillsPerClass" VALUES(1,10);
INSERT INTO "SkillsPerClass" VALUES(1,1);
INSERT INTO "SkillsPerClass" VALUES(1,16);
INSERT INTO "SkillsPerClass" VALUES(1,8);
INSERT INTO "SkillsPerClass" VALUES(1,13);
INSERT INTO "SkillsPerClass" VALUES(1,14);
INSERT INTO "SkillsPerClass" VALUES(2,1);
INSERT INTO "SkillsPerClass" VALUES(2,2);
INSERT INTO "SkillsPerClass" VALUES(2,3);
INSERT INTO "SkillsPerClass" VALUES(2,4);
INSERT INTO "SkillsPerClass" VALUES(2,5);
INSERT INTO "SkillsPerClass" VALUES(2,6);
INSERT INTO "SkillsPerClass" VALUES(2,7);
INSERT INTO "SkillsPerClass" VALUES(2,8);
INSERT INTO "SkillsPerClass" VALUES(2,9);
INSERT INTO "SkillsPerClass" VALUES(2,10);
INSERT INTO "SkillsPerClass" VALUES(2,11);
INSERT INTO "SkillsPerClass" VALUES(2,12);
INSERT INTO "SkillsPerClass" VALUES(2,13);
INSERT INTO "SkillsPerClass" VALUES(2,14);
INSERT INTO "SkillsPerClass" VALUES(2,15);
INSERT INTO "SkillsPerClass" VALUES(2,16);
INSERT INTO "SkillsPerClass" VALUES(2,17);
INSERT INTO "SkillsPerClass" VALUES(2,18);
INSERT INTO "SkillsPerClass" VALUES(3,6);
INSERT INTO "SkillsPerClass" VALUES(3,11);
INSERT INTO "SkillsPerClass" VALUES(3,12);
INSERT INTO "SkillsPerClass" VALUES(3,18);
INSERT INTO "SkillsPerClass" VALUES(3,9);
INSERT INTO "SkillsPerClass" VALUES(4,5);
INSERT INTO "SkillsPerClass" VALUES(4,10);
INSERT INTO "SkillsPerClass" VALUES(4,11);
INSERT INTO "SkillsPerClass" VALUES(4,12);
INSERT INTO "SkillsPerClass" VALUES(4,8);
INSERT INTO "SkillsPerClass" VALUES(4,13);
INSERT INTO "SkillsPerClass" VALUES(4,9);
INSERT INTO "SkillsPerClass" VALUES(4,14);
INSERT INTO "SkillsPerClass" VALUES(5,2);
INSERT INTO "SkillsPerClass" VALUES(5,10);
INSERT INTO "SkillsPerClass" VALUES(5,1);
INSERT INTO "SkillsPerClass" VALUES(5,6);
INSERT INTO "SkillsPerClass" VALUES(5,11);
INSERT INTO "SkillsPerClass" VALUES(5,16);
INSERT INTO "SkillsPerClass" VALUES(5,13);
INSERT INTO "SkillsPerClass" VALUES(5,14);
INSERT INTO "SkillsPerClass" VALUES(6,1);
INSERT INTO "SkillsPerClass" VALUES(6,2);
INSERT INTO "SkillsPerClass" VALUES(6,6);
INSERT INTO "SkillsPerClass" VALUES(6,11);
INSERT INTO "SkillsPerClass" VALUES(6,9);
INSERT INTO "SkillsPerClass" VALUES(6,4);
INSERT INTO "SkillsPerClass" VALUES(7,1);
INSERT INTO "SkillsPerClass" VALUES(7,11);
INSERT INTO "SkillsPerClass" VALUES(7,16);
INSERT INTO "SkillsPerClass" VALUES(7,12);
INSERT INTO "SkillsPerClass" VALUES(7,18);
INSERT INTO "SkillsPerClass" VALUES(7,9);
INSERT INTO "SkillsPerClass" VALUES(8,10);
INSERT INTO "SkillsPerClass" VALUES(8,1);
INSERT INTO "SkillsPerClass" VALUES(8,11);
INSERT INTO "SkillsPerClass" VALUES(8,7);
INSERT INTO "SkillsPerClass" VALUES(8,8);
INSERT INTO "SkillsPerClass" VALUES(8,13);
INSERT INTO "SkillsPerClass" VALUES(8,4);
INSERT INTO "SkillsPerClass" VALUES(8,14);
INSERT INTO "SkillsPerClass" VALUES(9,2);
INSERT INTO "SkillsPerClass" VALUES(9,1);
INSERT INTO "SkillsPerClass" VALUES(9,15);
INSERT INTO "SkillsPerClass" VALUES(9,11);
INSERT INTO "SkillsPerClass" VALUES(9,16);
INSERT INTO "SkillsPerClass" VALUES(9,7);
INSERT INTO "SkillsPerClass" VALUES(9,13);
INSERT INTO "SkillsPerClass" VALUES(9,17);
INSERT INTO "SkillsPerClass" VALUES(9,18);
INSERT INTO "SkillsPerClass" VALUES(9,3);
INSERT INTO "SkillsPerClass" VALUES(9,4);
INSERT INTO "SkillsPerClass" VALUES(10,5);
INSERT INTO "SkillsPerClass" VALUES(10,15);
INSERT INTO "SkillsPerClass" VALUES(10,11);
INSERT INTO "SkillsPerClass" VALUES(10,16);
INSERT INTO "SkillsPerClass" VALUES(10,18);
INSERT INTO "SkillsPerClass" VALUES(10,9);
INSERT INTO "SkillsPerClass" VALUES(11,5);
INSERT INTO "SkillsPerClass" VALUES(11,15);
INSERT INTO "SkillsPerClass" VALUES(11,6);
INSERT INTO "SkillsPerClass" VALUES(11,16);
INSERT INTO "SkillsPerClass" VALUES(11,7);
INSERT INTO "SkillsPerClass" VALUES(11,8);
INSERT INTO "SkillsPerClass" VALUES(11,9);
INSERT INTO "SkillsPerClass" VALUES(12,5);
INSERT INTO "SkillsPerClass" VALUES(12,6);
INSERT INTO "SkillsPerClass" VALUES(12,11);
INSERT INTO "SkillsPerClass" VALUES(12,7);
INSERT INTO "SkillsPerClass" VALUES(12,12);
INSERT INTO "SkillsPerClass" VALUES(12,9);
DROP TABLE IF EXISTS "ToolProfsPerClass";
CREATE TABLE IF NOT EXISTS ToolProfsPerClass
(
  id_tool INTEGER NOT NULL,
  id_class INTEGER NOT NULL,
  CONSTRAINT pk_composite_tools_profs PRIMARY KEY (id_tool,id_class),
  CONSTRAINT fk_composite_tools FOREIGN KEY (id_tool) REFERENCES Tools (_id),
  CONSTRAINT fk_composite_classes FOREIGN KEY (id_class) REFERENCES CharacterClasses (_id)
);
DROP TABLE IF EXISTS "Tools";
CREATE TABLE IF NOT EXISTS Tools
(
  _id INTEGER NOT NULL PRIMARY KEY,
  CONSTRAINT fk_tools_eq FOREIGN KEY (_id) REFERENCES Equipment (_id)
);
INSERT INTO "Tools" VALUES(162);
INSERT INTO "Tools" VALUES(163);
INSERT INTO "Tools" VALUES(164);
INSERT INTO "Tools" VALUES(165);
INSERT INTO "Tools" VALUES(166);
INSERT INTO "Tools" VALUES(167);
INSERT INTO "Tools" VALUES(168);
INSERT INTO "Tools" VALUES(169);
INSERT INTO "Tools" VALUES(170);
INSERT INTO "Tools" VALUES(171);
INSERT INTO "Tools" VALUES(172);
INSERT INTO "Tools" VALUES(173);
INSERT INTO "Tools" VALUES(174);
INSERT INTO "Tools" VALUES(175);
INSERT INTO "Tools" VALUES(176);
INSERT INTO "Tools" VALUES(177);
INSERT INTO "Tools" VALUES(178);
INSERT INTO "Tools" VALUES(179);
INSERT INTO "Tools" VALUES(180);
INSERT INTO "Tools" VALUES(181);
INSERT INTO "Tools" VALUES(182);
INSERT INTO "Tools" VALUES(183);
INSERT INTO "Tools" VALUES(184);
INSERT INTO "Tools" VALUES(185);
INSERT INTO "Tools" VALUES(186);
INSERT INTO "Tools" VALUES(187);
INSERT INTO "Tools" VALUES(188);
INSERT INTO "Tools" VALUES(189);
INSERT INTO "Tools" VALUES(190);
INSERT INTO "Tools" VALUES(191);
INSERT INTO "Tools" VALUES(192);
INSERT INTO "Tools" VALUES(193);
INSERT INTO "Tools" VALUES(194);
INSERT INTO "Tools" VALUES(195);
INSERT INTO "Tools" VALUES(196);
INSERT INTO "Tools" VALUES(197);
INSERT INTO "Tools" VALUES(198);
DROP TABLE IF EXISTS "WeaponFeatureTypes";
CREATE TABLE IF NOT EXISTS WeaponFeatureTypes
(
  _id INTEGER NOT NULL
        CONSTRAINT Key2 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);
DROP TABLE IF EXISTS "WeaponFeatureValues";
CREATE TABLE IF NOT EXISTS WeaponFeatureValues
(
  _id INTEGER NOT NULL
        CONSTRAINT Key4 PRIMARY KEY AUTOINCREMENT,
  id_feature INTEGER NOT NULL,
  value TEXT NOT NULL,
  CONSTRAINT Relationship3 FOREIGN KEY (id_feature) REFERENCES WeaponFeatures (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "WeaponFeatures";
CREATE TABLE IF NOT EXISTS WeaponFeatures
(
  _id INTEGER NOT NULL
        CONSTRAINT Key3 PRIMARY KEY AUTOINCREMENT,
  id_weapon INTEGER NOT NULL,
  id_wp_feat_type INTEGER NOT NULL,
  CONSTRAINT Relationship1 FOREIGN KEY (id_weapon) REFERENCES Weapons (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship2 FOREIGN KEY (id_wp_feat_type) REFERENCES WeaponFeatureTypes (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "WeaponProfsPerClass";
CREATE TABLE IF NOT EXISTS WeaponProfsPerClass
(
  id_class INTEGER NOT NULL,
  id_weapon INTEGER NOT NULL,
  CONSTRAINT Key7 PRIMARY KEY (id_class,id_weapon),
  CONSTRAINT Relationship5 FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship6 FOREIGN KEY (id_weapon) REFERENCES Weapons (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "Weapons";
CREATE TABLE IF NOT EXISTS Weapons
(
  _id INTEGER NOT NULL,
  dice INTEGER NOT NULL,
  dieSize INTEGER NOT NULL,
  id_damageType INTEGER NOT NULL,
  CONSTRAINT Key1 PRIMARY KEY (_id),
  CONSTRAINT Relationship9 FOREIGN KEY (_id) REFERENCES Equipment (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship41 FOREIGN KEY (id_damageType) REFERENCES DamageTypes (_id)
);
INSERT INTO "Weapons" VALUES(27,1,4,1);
INSERT INTO "Weapons" VALUES(28,1,4,2);
INSERT INTO "Weapons" VALUES(29,1,8,1);
INSERT INTO "Weapons" VALUES(30,1,6,3);
INSERT INTO "Weapons" VALUES(31,1,6,2);
INSERT INTO "Weapons" VALUES(32,1,4,1);
INSERT INTO "Weapons" VALUES(33,1,6,1);
INSERT INTO "Weapons" VALUES(34,1,6,1);
INSERT INTO "Weapons" VALUES(35,1,4,3);
INSERT INTO "Weapons" VALUES(36,1,6,2);
INSERT INTO "Weapons" VALUES(37,1,8,2);
INSERT INTO "Weapons" VALUES(38,1,4,2);
INSERT INTO "Weapons" VALUES(39,1,6,2);
INSERT INTO "Weapons" VALUES(40,1,4,1);
INSERT INTO "Weapons" VALUES(41,1,8,3);
INSERT INTO "Weapons" VALUES(42,1,8,2);
INSERT INTO "Weapons" VALUES(43,1,10,3);
INSERT INTO "Weapons" VALUES(44,1,12,3);
INSERT INTO "Weapons" VALUES(45,2,6,3);
INSERT INTO "Weapons" VALUES(46,1,10,3);
INSERT INTO "Weapons" VALUES(47,1,12,2);
INSERT INTO "Weapons" VALUES(48,1,8,3);
INSERT INTO "Weapons" VALUES(49,2,6,1);
INSERT INTO "Weapons" VALUES(50,1,8,2);
INSERT INTO "Weapons" VALUES(51,1,10,2);
INSERT INTO "Weapons" VALUES(52,1,8,2);
INSERT INTO "Weapons" VALUES(53,1,6,3);
INSERT INTO "Weapons" VALUES(54,1,6,2);
INSERT INTO "Weapons" VALUES(55,1,6,2);
INSERT INTO "Weapons" VALUES(56,1,8,2);
INSERT INTO "Weapons" VALUES(57,1,8,1);
INSERT INTO "Weapons" VALUES(58,1,4,3);
INSERT INTO "Weapons" VALUES(59,1,1,2);
INSERT INTO "Weapons" VALUES(60,1,6,2);
INSERT INTO "Weapons" VALUES(61,1,10,2);
INSERT INTO "Weapons" VALUES(62,1,8,2);
INSERT INTO "Weapons" VALUES(63,0,0,1);
DROP TABLE IF EXISTS "WrittenAlphabets";
CREATE TABLE IF NOT EXISTS WrittenAlphabets
(
_id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL
);
INSERT INTO "WrittenAlphabets" VALUES(1,'Common');
INSERT INTO "WrittenAlphabets" VALUES(2,'Dwarvish');
INSERT INTO "WrittenAlphabets" VALUES(3,'Elvish');
INSERT INTO "WrittenAlphabets" VALUES(4,'Infernal');
INSERT INTO "WrittenAlphabets" VALUES(5,'Celestial');
INSERT INTO "WrittenAlphabets" VALUES(6,'Draconic');

DROP VIEW IF EXISTS CheckFeatureLanguages;
CREATE VIEW CheckFeatureLanguages AS SELECT DISTINCT Languages.name AS Language, Features.name AS Feature
FROM Languages, Features
JOIN FeatureLanguages
WHERE Languages._id = FeatureLanguages.id_language
AND Features._id = FeatureLanguages.id_feature;

Drop View If EXISTS CheckFeatureProficiencies;
CREATE VIEW CheckFeatureProficiencies AS SELECT DISTINCT Equipment.name AS Equipment, Features.name AS Feature
FROM Equipment, Features
JOIN FeatureProficiencies
WHERE Equipment._id = FeatureProficiencies.id_equipment
AND Features._id = FeatureProficiencies.id_feature;

DROP VIEW IF EXISTS CheckRacialFeatures;
CREATE VIEW CheckRacialFeatures AS SELECT DISTINCT Races.name AS Race, Features.name AS Feature
FROM Races, Features
JOIN RacialFeatures
WHERE Races._id = RacialFeatures.id_race
AND Features._id = RacialFeatures.id_feature;

DROP VIEW IF EXISTS CheckRacialStats;
CREATE VIEW CheckRacialStats AS SELECT DISTINCT Races.name AS Race, RacialStats.stat AS Statistic, RacialStats.bonus AS Bonus
FROM Races, RacialStats
WHERE Races._id = RacialStats.id_race;

DROP VIEW IF EXISTS GetArmors;
CREATE VIEW "GetArmors" AS
SELECT Equipment._id, Equipment.name, Equipment.proficiencyGroup, Armors.bonus, Armors.maxDexBonus, Armors.requiredStr, Armors.impairsStealth, Equipment.weight, Equipment.cost
FROM Armors, Equipment
WHERE Equipment._id = Armors._id;

DROP VIEW IF EXISTS GetClassSkills;
CREATE VIEW GetClassSkills AS
SELECT CharacterClasses.name AS className, Skills.name AS skill, keyStat AS defaultStatistic
FROM CharacterClasses, Skills, SkillsPerClass
WHERE Skills._id = SkillsPerClass.id_skill 
AND CharacterClasses._id = SkillsPerClass.id_class;

DROP VIEW IF EXISTS GetTools;
CREATE VIEW "GetTools" AS SELECT Tools._id, Equipment.name, Equipment.weight, Equipment.cost FROM Tools
JOIN Equipment
WHERE Tools._id = Equipment._id;

DROP VIEW IF EXISTS GetWeapons;
CREATE VIEW "GetWeapons" AS SELECT DISTINCT Equipment._id, Equipment.name, Equipment.proficiencyGroup, Weapons.dice, Weapons.dieSize, Weapons.id_damageType, RangedWeapons.shortRange, RangedWeapons.longRange, Equipment.weight, Equipment.cost
FROM Equipment, Weapons
LEFT JOIN RangedWeapons
ON RangedWeapons._id = Weapons._id
WHERE Equipment._id = Weapons._id;

DROP TRIGGER IF EXISTS insert_equipment_armor;
CREATE TRIGGER IF NOT EXISTS insert_equipment_armor
INSTEAD OF INSERT 
ON GetArmors

BEGIN

INSERT OR IGNORE INTO     Equipment (name, weight, cost, proficiencyGroup)
SELECT new.name, new.weight, new.cost, new.proficiencyGroup
WHERE NOT EXISTS
(
SELECT 1 FROM Equipment
WHERE name = new.name
);

INSERT OR IGNORE INTO     Armors (_id, bonus, impairsStealth, requiredStr, maxDexBonus)
SELECT Equipment._id, new.bonus, new.impairsStealth, new.requiredStr, new.maxDexBonus
FROM Equipment
WHERE Equipment.name = new.name;

END;

DROP TRIGGER IF EXISTS insert_equipment_tools;
CREATE TRIGGER IF NOT EXISTS insert_equipment_tools
INSTEAD OF INSERT 
ON GetTools

BEGIN

INSERT OR IGNORE INTO     Equipment (name, weight, cost)
SELECT new.name, new.weight, new.cost
WHERE NOT EXISTS
(
SELECT 1 FROM Equipment
WHERE name = new.name
);

INSERT OR IGNORE INTO     Tools (_id)
SELECT Equipment._id
FROM Equipment
WHERE Equipment.name = new.name;

END;

DROP TRIGGER IF EXISTS insert_equipment_weapon;
CREATE TRIGGER IF NOT EXISTS insert_equipment_weapon
INSTEAD OF INSERT 
ON GetWeapons

BEGIN

INSERT OR IGNORE INTO     Equipment (name, weight, cost, proficiencyGroup)
SELECT new.name, new.weight, new.cost, new.proficiencyGroup
WHERE NOT EXISTS
(
SELECT 1 FROM Equipment
WHERE name = new.name
);

INSERT OR IGNORE INTO     Weapons (_id, dice, dieSize, id_damageType)
SELECT Equipment._id, new.dice, new.dieSize, new.id_damageType
FROM Equipment
WHERE Equipment.name = new.name;

INSERT OR IGNORE INTO     RangedWeapons (_id, shortRange, longRange)
SELECT Equipment._id, new.shortRange, new.longRange
FROM Equipment
WHERE new.shortRange NOTNULL 
AND new.name = Equipment.name;

END;
