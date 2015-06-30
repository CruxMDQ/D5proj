DROP TABLE IF EXISTS "AmmoPerWeapon";
CREATE TABLE AmmoPerWeapon
(
  id_weapon INTEGER NOT NULL,
  id_ammo INTEGER NOT NULL,
  CONSTRAINT Key28 PRIMARY KEY (id_weapon,id_ammo),
  CONSTRAINT Relationship38 FOREIGN KEY (id_weapon) REFERENCES RangedWeapons (_id),
  CONSTRAINT Relationship39 FOREIGN KEY (id_ammo) REFERENCES "Items" (_id)
);
DROP TABLE IF EXISTS "AreaOfEffectSpells";
CREATE TABLE "AreaOfEffectSpells" (
"id_spell"  INTEGER NOT NULL,
"id_aoe_type"  INTEGER NOT NULL,
"dimension1"  INTEGER DEFAULT 0,
"dimension2"  INTEGER DEFAULT 0,
"heightenedFactor"  REAL DEFAULT 1,
PRIMARY KEY ("id_spell" ASC, "id_aoe_type" ASC),
CONSTRAINT "fk_spell" FOREIGN KEY ("id_spell") REFERENCES "Spells" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_aoe" FOREIGN KEY ("id_aoe_type") REFERENCES "AreasOfEffect" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "AreasOfEffect";
CREATE TABLE AreasOfEffect
(
  _id INTEGER NOT NULL
        CONSTRAINT Key5 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);
INSERT INTO "AreasOfEffect" VALUES(1,'Cone');
DROP TABLE IF EXISTS "ArmorProficiencyGroups";
CREATE TABLE ArmorProficiencyGroups
(
  _id INTEGER NOT NULL
        CONSTRAINT Key11 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);
DROP TABLE IF EXISTS "ArmorProfsPerClass";
CREATE TABLE ArmorProfsPerClass
(
  id_class INTEGER NOT NULL,
  id_armor INTEGER NOT NULL,
  CONSTRAINT Key17 PRIMARY KEY (id_class,id_armor),
  CONSTRAINT Relationship21 FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship22 FOREIGN KEY (id_armor) REFERENCES Armors (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "Armors";
CREATE TABLE "Armors" (
"_id"  INTEGER NOT NULL,
"bonus"  INTEGER NOT NULL,
"impairsStealth"  INTEGER NOT NULL DEFAULT 0,
"requiredStr"  INTEGER,
"maxDexBonus"  INTEGER,
PRIMARY KEY ("_id" ASC),
CONSTRAINT "fk_equipment" FOREIGN KEY ("_id") REFERENCES "Equipment" ("id_item") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "CheckConstraintA1" CHECK (impairsStealth IN (0, 1))
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
CREATE TABLE "CharacterClasses"
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
CREATE TABLE "CharacterInventory" (
"_id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"id_character"  INTEGER NOT NULL,
"id_item"  INTEGER NOT NULL,
CONSTRAINT "fk_character" FOREIGN KEY ("id_character") REFERENCES "Characters" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_equipment" FOREIGN KEY ("id_item") REFERENCES "Items" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "CharacterLevels";
CREATE TABLE "CharacterLevels" (
"id_character"  INTEGER NOT NULL,
"id_class"  INTEGER NOT NULL,
"hpRoll"  INTEGER NOT NULL,
PRIMARY KEY ("id_character" ASC, "id_class" ASC),
CONSTRAINT "Relationship27" FOREIGN KEY ("id_character") REFERENCES "Characters" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "Relationship28" FOREIGN KEY ("id_class") REFERENCES "CharacterClasses" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "Characters";
CREATE TABLE "Characters" (
"_id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"id_race"  INTEGER NOT NULL,
"name"  TEXT NOT NULL,
"strength"  INTEGER NOT NULL,
"dexterity"  INTEGER NOT NULL,
"constitution"  INTEGER NOT NULL,
"intelligence"  INTEGER NOT NULL,
"wisdom"  INTEGER NOT NULL,
"charisma"  INTEGER NOT NULL,
"hitPoints"  INTEGER NOT NULL,
"experience"  INTEGER NOT NULL,
CONSTRAINT "fk_race" FOREIGN KEY ("id_race") REFERENCES "Races" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "ClassFeatures";
CREATE TABLE ClassFeatures
(
  id_class INTEGER NOT NULL,
  id_feature INTEGER NOT NULL,
  CONSTRAINT pk_class_features PRIMARY KEY (id_class,id_feature),
  CONSTRAINT fk_class FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Features (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "ClassProficiencyGroups";
CREATE TABLE "ClassProficiencyGroups"
(
  id_class INTEGER NOT NULL,
  id_group INTEGER NOT NULL,
  CONSTRAINT Key16 PRIMARY KEY (id_class,id_group),
  CONSTRAINT Relationship19 FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship20 FOREIGN KEY (id_group) REFERENCES ProficiencyGroups (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "DamageDealingSpells";
CREATE TABLE "DamageDealingSpells" (
"id_spell"  INTEGER NOT NULL,
"id_type_damage"  INTEGER NOT NULL,
"dice"  INTEGER,
"dieSize"  INTEGER,
"avgDamage"  INTEGER DEFAULT 0,
"heightenedFactor"  INTEGER DEFAULT 1,
PRIMARY KEY ("id_spell" ASC, "id_type_damage" ASC),
CONSTRAINT "fk_spell" FOREIGN KEY ("id_spell") REFERENCES "Spells" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_type_damage" FOREIGN KEY ("id_type_damage") REFERENCES "DamageTypes" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "DamageTypes";
CREATE TABLE DamageTypes
(
  _id INTEGER NOT NULL
        CONSTRAINT Key29 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);
INSERT INTO "DamageTypes" VALUES(1,'Bludgeoning');
INSERT INTO "DamageTypes" VALUES(2,'Piercing');
INSERT INTO "DamageTypes" VALUES(3,'Slashing');
INSERT INTO "DamageTypes" VALUES(4,'Acid');
INSERT INTO "DamageTypes" VALUES(5,'Cold');
INSERT INTO "DamageTypes" VALUES(6,'Fire');
INSERT INTO "DamageTypes" VALUES(7,'Force');
INSERT INTO "DamageTypes" VALUES(8,'Lightning');
INSERT INTO "DamageTypes" VALUES(9,'Necrotic');
INSERT INTO "DamageTypes" VALUES(10,'Poison');
INSERT INTO "DamageTypes" VALUES(11,'Psychic');
INSERT INTO "DamageTypes" VALUES(12,'Radiant');
INSERT INTO "DamageTypes" VALUES(13,'Thunder');
DROP TABLE IF EXISTS "Equipment";
CREATE TABLE "Equipment" (
"id_item"  INTEGER NOT NULL,
"proficiencyGroup"  INTEGER NOT NULL,
PRIMARY KEY ("id_item"),
CONSTRAINT "fk_item" FOREIGN KEY ("id_item") REFERENCES "Items" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_profGroup" FOREIGN KEY ("proficiencyGroup") REFERENCES "ProficiencyGroups" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "Equipment" VALUES(1,1);
INSERT INTO "Equipment" VALUES(2,1);
INSERT INTO "Equipment" VALUES(3,1);
INSERT INTO "Equipment" VALUES(4,2);
INSERT INTO "Equipment" VALUES(5,2);
INSERT INTO "Equipment" VALUES(6,2);
INSERT INTO "Equipment" VALUES(7,2);
INSERT INTO "Equipment" VALUES(8,2);
INSERT INTO "Equipment" VALUES(9,3);
INSERT INTO "Equipment" VALUES(10,3);
INSERT INTO "Equipment" VALUES(11,3);
INSERT INTO "Equipment" VALUES(12,3);
INSERT INTO "Equipment" VALUES(13,4);
INSERT INTO "Equipment" VALUES(27,5);
INSERT INTO "Equipment" VALUES(28,5);
INSERT INTO "Equipment" VALUES(29,5);
INSERT INTO "Equipment" VALUES(30,5);
INSERT INTO "Equipment" VALUES(31,5);
INSERT INTO "Equipment" VALUES(32,5);
INSERT INTO "Equipment" VALUES(33,5);
INSERT INTO "Equipment" VALUES(34,5);
INSERT INTO "Equipment" VALUES(35,5);
INSERT INTO "Equipment" VALUES(36,5);
INSERT INTO "Equipment" VALUES(37,5);
INSERT INTO "Equipment" VALUES(38,5);
INSERT INTO "Equipment" VALUES(39,5);
INSERT INTO "Equipment" VALUES(40,5);
INSERT INTO "Equipment" VALUES(41,6);
INSERT INTO "Equipment" VALUES(42,6);
INSERT INTO "Equipment" VALUES(43,6);
INSERT INTO "Equipment" VALUES(44,6);
INSERT INTO "Equipment" VALUES(45,6);
INSERT INTO "Equipment" VALUES(46,6);
INSERT INTO "Equipment" VALUES(47,6);
INSERT INTO "Equipment" VALUES(48,6);
INSERT INTO "Equipment" VALUES(49,6);
INSERT INTO "Equipment" VALUES(50,6);
INSERT INTO "Equipment" VALUES(51,6);
INSERT INTO "Equipment" VALUES(52,6);
INSERT INTO "Equipment" VALUES(53,6);
INSERT INTO "Equipment" VALUES(54,6);
INSERT INTO "Equipment" VALUES(55,6);
INSERT INTO "Equipment" VALUES(56,6);
INSERT INTO "Equipment" VALUES(57,6);
INSERT INTO "Equipment" VALUES(58,6);
INSERT INTO "Equipment" VALUES(59,6);
INSERT INTO "Equipment" VALUES(60,6);
INSERT INTO "Equipment" VALUES(61,6);
INSERT INTO "Equipment" VALUES(62,6);
INSERT INTO "Equipment" VALUES(63,6);
DROP TABLE IF EXISTS "ExperienceLevels";
CREATE TABLE ExperienceLevels
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
CREATE TABLE "FeatureChoices"
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
INSERT INTO "FeatureChoices" VALUES(39,47);
INSERT INTO "FeatureChoices" VALUES(39,48);
INSERT INTO "FeatureChoices" VALUES(39,49);
INSERT INTO "FeatureChoices" VALUES(39,50);
INSERT INTO "FeatureChoices" VALUES(39,51);
INSERT INTO "FeatureChoices" VALUES(39,52);
INSERT INTO "FeatureChoices" VALUES(60,61);
INSERT INTO "FeatureChoices" VALUES(60,62);
INSERT INTO "FeatureChoices" VALUES(60,63);
INSERT INTO "FeatureChoices" VALUES(60,64);
INSERT INTO "FeatureChoices" VALUES(60,65);
INSERT INTO "FeatureChoices" VALUES(60,66);
INSERT INTO "FeatureChoices" VALUES(60,67);
INSERT INTO "FeatureChoices" VALUES(60,68);
INSERT INTO "FeatureChoices" VALUES(60,69);
INSERT INTO "FeatureChoices" VALUES(60,70);
DROP TABLE IF EXISTS "FeatureLanguages";
CREATE TABLE FeatureLanguages
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
CREATE TABLE "FeatureProficiencies" (
"id_feature"  INTEGER NOT NULL,
"id_equipment"  INTEGER NOT NULL,
PRIMARY KEY ("id_feature" ASC, "id_equipment" ASC),
CONSTRAINT "fk_feature" FOREIGN KEY ("id_feature") REFERENCES "Features" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_equipment" FOREIGN KEY ("id_equipment") REFERENCES "Equipment" ("id_item") ON DELETE CASCADE ON UPDATE CASCADE
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
INSERT INTO "FeatureProficiencies" VALUES(45,52);
INSERT INTO "FeatureProficiencies" VALUES(46,60);
DROP TABLE IF EXISTS "FeatureProficiencyGroups";
CREATE TABLE FeatureProficiencyGroups
(
  id_feature INTEGER NOT NULL,
  id_profgroup INTEGER NOT NULL,
  CONSTRAINT pk_feature_profgroupss PRIMARY KEY (id_feature ,id_profgroup),
  CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Feature (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_profgroup FOREIGN KEY (id_profgroup) REFERENCES ProficiencyGroups(_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "FeatureProficiencyGroups" VALUES(15,1);
INSERT INTO "FeatureProficiencyGroups" VALUES(16,2);
DROP TABLE IF EXISTS "FeatureResistances";
CREATE TABLE FeatureResistances
(
  id_feature INTEGER NOT NULL,
  id_damageType INTEGER NOT NULL,
  CONSTRAINT pk_feature_resistances PRIMARY KEY (id_feature ,id_damageType),
  CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Feature (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_resistance FOREIGN KEY (id_damageType) REFERENCES DamageTypes(_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "FeatureResistances" VALUES(1,101);
INSERT INTO "FeatureResistances" VALUES(2,102);
INSERT INTO "FeatureResistances" VALUES(3,103);
INSERT INTO "FeatureResistances" VALUES(4,104);
INSERT INTO "FeatureResistances" VALUES(5,105);
INSERT INTO "FeatureResistances" VALUES(6,106);
INSERT INTO "FeatureResistances" VALUES(7,107);
INSERT INTO "FeatureResistances" VALUES(8,108);
INSERT INTO "FeatureResistances" VALUES(9,109);
INSERT INTO "FeatureResistances" VALUES(10,110);
INSERT INTO "FeatureResistances" VALUES(11,111);
INSERT INTO "FeatureResistances" VALUES(12,112);
INSERT INTO "FeatureResistances" VALUES(13,113);
DROP TABLE IF EXISTS "FeatureSkills";
CREATE TABLE FeatureSkills
(
id_feature INTEGER NOT NULL,
id_skill INTEGER NOT NULL,
CONSTRAINT pk_comp_feature_skills PRIMARY KEY (id_feature, id_skill),
CONSTRAINT fk_feature FOREIGN KEY (id_feature) REFERENCES Features (_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_skill FOREIGN KEY (id_skill) REFERENCES Skills (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO "FeatureSkills" VALUES(17,13);
INSERT INTO "FeatureSkills" VALUES(78,16);
INSERT INTO "FeatureSkills" VALUES(83,1);
INSERT INTO "FeatureSkills" VALUES(84,2);
INSERT INTO "FeatureSkills" VALUES(85,3);
INSERT INTO "FeatureSkills" VALUES(86,5);
INSERT INTO "FeatureSkills" VALUES(87,4);
INSERT INTO "FeatureSkills" VALUES(88,6);
INSERT INTO "FeatureSkills" VALUES(89,7);
INSERT INTO "FeatureSkills" VALUES(90,8);
INSERT INTO "FeatureSkills" VALUES(91,9);
INSERT INTO "FeatureSkills" VALUES(92,10);
INSERT INTO "FeatureSkills" VALUES(93,11);
INSERT INTO "FeatureSkills" VALUES(94,12);
INSERT INTO "FeatureSkills" VALUES(95,14);
INSERT INTO "FeatureSkills" VALUES(96,15);
DROP TABLE IF EXISTS "Features";
CREATE TABLE Features
(
_id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL,
hasOptions INTEGER NOT NULL DEFAULT 0
        CONSTRAINT chk_feat_bool CHECK (hasOptions IN (0, 1))
, "description" TEXT);
INSERT INTO "Features" VALUES(1,'Darkvision',0,'You have superior vision in dark and dim conditions. You can see in dim light within the specified range as if it were bright light, and in darkness as if it were dim light.');
INSERT INTO "Features" VALUES(2,'Resilience',0,'You have advantage on saving throws against poison, and you have resistance against poison damage.');
INSERT INTO "Features" VALUES(3,'Dwarven tool proficiency',1,'You gain proficiency with the artisan''s tools of your choice: smith''s tools, brewer''s supplies, or mason''s tools.');
INSERT INTO "Features" VALUES(4,'Proficiency: smith''s tools',0,'You gain proficiency with smith''s tools.');
INSERT INTO "Features" VALUES(5,'Proficiency: brewer''s supplies',0,'You gain proficiency with brewer''s supplies.');
INSERT INTO "Features" VALUES(6,'Proficiency: mason''s tools',0,'You gain proficiency with mason''s tools.');
INSERT INTO "Features" VALUES(7,'Stonecunning',0,'Whenever you make an Intelligence (History) check related to the origin of stonework, you are considered proficient in the History skill and add double your proficiency bonus to the check, instead of your normal proficiency bonus.');
INSERT INTO "Features" VALUES(8,'Language: common',0,'You can speak, read and write Common.');
INSERT INTO "Features" VALUES(9,'Language: dwarven',0,'You can speak, read and write Dwarvish. The language is full of hard consonants and guttural sounds, and those characteristics spill over into whatever other language a dwarf might speak.');
INSERT INTO "Features" VALUES(10,'Proficiency: battleaxe',0,'You have proficiency with the battleaxe.');
INSERT INTO "Features" VALUES(11,'Proficiency: handaxe',0,'You have proficiency with the handaxe.');
INSERT INTO "Features" VALUES(12,'Proficiency: light hammer',0,'You have proficiency with the light hammer.');
INSERT INTO "Features" VALUES(13,'Proficiency: warhammer',0,'You have proficiency with the warhammer.');
INSERT INTO "Features" VALUES(14,'Dwarven toughness',0,'Your hit point maximum increases by 1, and it increases by 1 every time you gain a level.');
INSERT INTO "Features" VALUES(15,'Proficiency: light armor',0,'You have proficiency with light armor.');
INSERT INTO "Features" VALUES(16,'Proficiency: medium armor',0,'You have proficiency with medium armor.');
INSERT INTO "Features" VALUES(17,'Proficiency: skill (Perception)',0,'You have proficiency with the Perception skill.');
INSERT INTO "Features" VALUES(18,'Fey Ancestry',0,'You have advantage on saving throws against being charmed, and magic can''t put you to sleep.');
INSERT INTO "Features" VALUES(19,'Trance',0,'Elves don''t need to sleep. Instead, they meditate deeply, remaining semiconscious, for 4 hours a day. While meditating, elves dream in a fashion; such dreams are actually mental exercises that have become reflexive through years of practice. After resting in this way, elves gain the same benefit that a human does from 8 hours of sleep.');
INSERT INTO "Features" VALUES(20,'Language: elvish',0,'You can read, speak and write Elvish. It is fluid, with subtle intonations and intrincate grammar. Elven literature is rich and varied, and their songs and poems are famous among other races. Many bards learn their language so they can add Elvish ballads to their repertoires.');
INSERT INTO "Features" VALUES(21,'Proficiency: longsword',0,'You have proficiency with the longsword.');
INSERT INTO "Features" VALUES(22,'Proficiency: shortsword',0,'You have proficiency with the shortsword.');
INSERT INTO "Features" VALUES(23,'Proficiency: shortbow',0,'You have proficiency with the shortbow.');
INSERT INTO "Features" VALUES(24,'Proficiency: longbow',0,'You have proficiency with the longbow.');
INSERT INTO "Features" VALUES(25,'Extra language',1,'You can speak, read and write one extra language of your choice.');
INSERT INTO "Features" VALUES(26,'Language: giant',0,'You can speak, read and write Giant.');
INSERT INTO "Features" VALUES(27,'Language: gnomish',0,'You can speak, read and write Gnomish. It uses the Dwarvish script and is renowned for its technical treatises and its catalogs of knowledge about the natural world.');
INSERT INTO "Features" VALUES(28,'Language: goblin',0,'You can speak, read and write Goblin.');
INSERT INTO "Features" VALUES(29,'Language: halfling',0,'You can speak, read and write Halfling. It isn''t a secret language, but halflings are loath to share it with others. They write very little, so they don''t have a rich body of literature. Their oral tradition, however, is very strong.');
INSERT INTO "Features" VALUES(30,'Language: orcish',0,'You can speak, read and write Orc, a harsh, grating language with hard consonants. It has no script of its own but is written in the Dwarvish script.');
INSERT INTO "Features" VALUES(31,'Language: abyssal',0,'You can speak, read and write Abyssal.');
INSERT INTO "Features" VALUES(32,'Language: celestial',0,'You can speak, read and write Celestial.');
INSERT INTO "Features" VALUES(33,'Language: draconic',0,'You can speak, read and write Draconic.');
INSERT INTO "Features" VALUES(34,'Language: deep speech',0,'You can speak, read and write Deep Speech.');
INSERT INTO "Features" VALUES(35,'Language: infernal',0,'You can speak, read and write Infernal.');
INSERT INTO "Features" VALUES(36,'Language: primordial',0,'You can speak, read and write Primordial.');
INSERT INTO "Features" VALUES(37,'Language: sylvan',0,'You can speak, read and write Sylvan.');
INSERT INTO "Features" VALUES(38,'Language: undercommon',0,'You can speak, read and write Undercommon.');
INSERT INTO "Features" VALUES(39,'Increased ability score (2 choices)',1,'Two ability scores of your choice increase by 1.');
INSERT INTO "Features" VALUES(40,'Fleet of foot',0,'Your base walking speed increases by +5 feet.');
INSERT INTO "Features" VALUES(41,'Mask of the Wild',0,'You can attempt to hide even when you are only lightly obscored by foliage, heavy rain, falling snow, mist, and other natural phenomena.');
INSERT INTO "Features" VALUES(42,'Cantrip',1,'You know one cantrip of your choice from the wizard spell list. Intelligence is your spellcasting ability for it.');
INSERT INTO "Features" VALUES(43,'Sunlight Sensitivity',0,'You have disadvantage on attack rolls and on Wisdom (Perception) checks that rely on sight when you, the target of your attack, or whatever you are trying to perceive is in direct sunlight.');
INSERT INTO "Features" VALUES(44,'Drow magic',0,'You know the Dancing Lights cantrip. When you reach 3rd level, you can cast the Faerie Fire spell once per day. When you reach 5th level, you can also cast the Darkness spell once per day. Charisma is your spellcasting ability for these spells.');
INSERT INTO "Features" VALUES(45,'Proficiency: rapier',0,'You have proficiency with the rapier.');
INSERT INTO "Features" VALUES(46,'Proficiency: hand crossbow',0,'You have proficiency with the hand crossbow.');
INSERT INTO "Features" VALUES(47,'Increased ability score (Strength)',0,'Your Strength score increases by 1.');
INSERT INTO "Features" VALUES(48,'Increased ability score (Dexterity)',0,'Your Dexterity score increases by 1.');
INSERT INTO "Features" VALUES(49,'Increased ability score (Constitution)',0,'Your Constitution score increases by 1.');
INSERT INTO "Features" VALUES(50,'Increased ability score (Intelligence)',0,'Your Intelligence score increases by 1.');
INSERT INTO "Features" VALUES(51,'Increased ability score (Wisdom)',0,'Your Wisdom score increases by 1.');
INSERT INTO "Features" VALUES(52,'Increased ability score (Charisma)',0,'Your Charisma score increases by 1.');
INSERT INTO "Features" VALUES(53,'Lucky',0,'When you roll a 1 on an attack roll, ability check, or saving throw, you can reroll the die and must use the new roll.');
INSERT INTO "Features" VALUES(54,'Brave',0,'You have advantage on saving throws against being frightened.');
INSERT INTO "Features" VALUES(55,'Halfling Nimbleness',0,'You can move through the space of any creature that is of a size larger than yours.');
INSERT INTO "Features" VALUES(56,'Naturally Stealthy',0,'You can attempt to hide even when you are obscured only by a creature that is at least one size larger than you.');
INSERT INTO "Features" VALUES(57,'Stout Resilience',0,'You have advantage on saving throws against poison, and you have resistance against poison damage.');
INSERT INTO "Features" VALUES(58,'Extra feat',1,'You gain one feat of your choice.');
INSERT INTO "Features" VALUES(59,'Extra skill proficiency',1,'You gain proficiency in one skill of your choice.');
INSERT INTO "Features" VALUES(60,'Draconic Ancestry',1,'Choose one type of dragon. Your breath weapon and damage resistance are determined by the dragon type.');
INSERT INTO "Features" VALUES(61,'Draconic Ancestry (Black)',0,NULL);
INSERT INTO "Features" VALUES(62,'Draconic Ancestry (Blue)',0,NULL);
INSERT INTO "Features" VALUES(63,'Draconic Ancestry (Brass)',0,NULL);
INSERT INTO "Features" VALUES(64,'Draconic Ancestry (Bronze)',0,NULL);
INSERT INTO "Features" VALUES(65,'Draconic Ancestry (Copper)',0,NULL);
INSERT INTO "Features" VALUES(66,'Draconic Ancestry (Gold)',0,NULL);
INSERT INTO "Features" VALUES(67,'Draconic Ancestry (Green)',0,NULL);
INSERT INTO "Features" VALUES(68,'Draconic Ancestry (Red)',0,NULL);
INSERT INTO "Features" VALUES(69,'Draconic Ancestry (Silver)',0,NULL);
INSERT INTO "Features" VALUES(70,'Draconic Ancestry (White)',0,NULL);
INSERT INTO "Features" VALUES(71,'Gnome Cunning',0,'You have advantage on all Intelligence, Wisdom, and Charisma saving throws against magic.');
INSERT INTO "Features" VALUES(72,'Natural Illusionist',0,'You know the Minor Illusion cantrip. Intelligence is your spellcasting ability for it.');
INSERT INTO "Features" VALUES(73,'Speak with Small Beasts',0,'Through sounds and small gestures, you can communicate simple ideas with Small or smaller beasts.');
INSERT INTO "Features" VALUES(74,'Artificer''s Lore',0,'Whenever you make an Intelligence (History) check related to magic items, alchemical objects, or technological devices, you can add twice your proficiency bonus, instead of any proficiency bonus you normally apply.');
INSERT INTO "Features" VALUES(75,'Tinker',0,'You can use artisan''s tools (tinker tools) and spend 1 hour and 10 gp worth of materials to construct a Tiny clockwork device.');
INSERT INTO "Features" VALUES(76,'Proficiency: artisan''s tools (tinker''s tools)',0,'You gain proficiency with artisan''s tools (tinker''s tools).');
INSERT INTO "Features" VALUES(77,'Skill Versatility',1,'You gain proficiency in two skills of your choice.');
INSERT INTO "Features" VALUES(78,'Proficiency: skill (Intimidation)',0,'You gain proficiency in the Intimidation skill.');
INSERT INTO "Features" VALUES(79,'Relentless Endurance',0,'When you are reduced to 0 hit points but not killed outright, you can drop to 1 hit point instead. You can''t use this feature again until you finish a long rest.');
INSERT INTO "Features" VALUES(80,'Savage Attacks',0,'When you score a critical hit with a melee weapon attack, you can roll one of the weapon''s damage dice one additional time and add it to the extra damage of the critical hit.');
INSERT INTO "Features" VALUES(81,'Resistance: fire',0,NULL);
INSERT INTO "Features" VALUES(82,'Infernal Legacy',0,NULL);
INSERT INTO "Features" VALUES(83,'Proficiency: skill (Athletics)',0,NULL);
INSERT INTO "Features" VALUES(84,'Proficiency: skill (Acrobatics)',0,NULL);
INSERT INTO "Features" VALUES(85,'Proficiency: skill (Sleight of Hand)',0,NULL);
INSERT INTO "Features" VALUES(86,'Proficiency: skill (Arcana)',0,NULL);
INSERT INTO "Features" VALUES(87,'Proficiency: skill (Stealth)',0,NULL);
INSERT INTO "Features" VALUES(88,'Proficiency: skill (History)',0,NULL);
INSERT INTO "Features" VALUES(89,'Proficiency: skill (Investigation)',0,NULL);
INSERT INTO "Features" VALUES(90,'Proficiency: skill (Nature)',0,NULL);
INSERT INTO "Features" VALUES(91,'Proficiency: skill (Religion)',0,NULL);
INSERT INTO "Features" VALUES(92,'Proficiency: skill (Animal Handling)',0,NULL);
INSERT INTO "Features" VALUES(93,'Proficiency: skill (Insight)',0,NULL);
INSERT INTO "Features" VALUES(94,'Proficiency: skill (Medicine)',0,NULL);
INSERT INTO "Features" VALUES(95,'Proficiency: skill (Survival)',0,NULL);
INSERT INTO "Features" VALUES(96,'Proficiency: skill (Deception)',0,NULL);
INSERT INTO "Features" VALUES(97,'Proficiency: skill (Performance)',0,NULL);
INSERT INTO "Features" VALUES(98,'Proficiency: skill (Persuasion)',0,NULL);
INSERT INTO "Features" VALUES(99,'Breath weapon (dragonborn)',0,'You can use your action to exhale destructive energy. Each creature in the area of the exhalation must make a saving throw (DC 8 + Constitution modifier + proficiency bonus). You can''t use your breath weapon again until you complete a short or long rest.');
INSERT INTO "Features" VALUES(100,'Damage Resistance',1,'You have damage resistance to the chosen damage type.');
INSERT INTO "Features" VALUES(101,'Damage Resistance: Bludgeoning',0,'You are resistant to Bludgeoning damage.');
INSERT INTO "Features" VALUES(102,'Damage Resistance: Piercing',0,'You are resistant to Piercing damage.');
INSERT INTO "Features" VALUES(103,'Damage Resistance: Slashing',0,'You are resistant to Slashing damage.');
INSERT INTO "Features" VALUES(104,'Damage Resistance: Acid',0,'You are resistant to Acid damage.');
INSERT INTO "Features" VALUES(105,'Damage Resistance: Cold',0,'You are resistant to Cold damage.');
INSERT INTO "Features" VALUES(106,'Damage Resistance: Fire',0,'You are resistant to Fire damage.');
INSERT INTO "Features" VALUES(107,'Damage Resistance: Force',0,'You are resistant to Force damage.');
INSERT INTO "Features" VALUES(108,'Damage Resistance: Lightning',0,'You are resistant to Lightning damage.');
INSERT INTO "Features" VALUES(109,'Damage Resistance: Necrotic',0,'You are resistant to Necrotic damage.');
INSERT INTO "Features" VALUES(110,'Damage Resistance: Poison',0,'You are resistant to Poison damage.');
INSERT INTO "Features" VALUES(111,'Damage Resistance: Psychic',0,'You are resistant to Psychic damage.');
INSERT INTO "Features" VALUES(112,'Damage Resistance: Radiant',0,'You are resistant to Radiant damage.');
INSERT INTO "Features" VALUES(113,'Damage Resistance: Thunder',0,'You are resistant to Thunder damage.');
DROP TABLE IF EXISTS "FeaturesWithOptions";
CREATE TABLE FeaturesWithOptions 
(
id_feature INTEGER NOT NULL,
choices INTEGER DEFAULT 2,
CONSTRAINT fk_feat_fopts FOREIGN KEY (id_feature) REFERENCES Features (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO "FeaturesWithOptions" VALUES(3,1);
INSERT INTO "FeaturesWithOptions" VALUES(18,1);
INSERT INTO "FeaturesWithOptions" VALUES(39,2);
INSERT INTO "FeaturesWithOptions" VALUES(60,1);
INSERT INTO "FeaturesWithOptions" VALUES(77,2);
DROP TABLE IF EXISTS "Items";
CREATE TABLE "Items" (
"_id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT NOT NULL,
"weight"  REAL NOT NULL,
"cost"  INTEGER NOT NULL
);
INSERT INTO "Items" VALUES(1,'Padded',8,50000);
INSERT INTO "Items" VALUES(2,'Leather',10,100000);
INSERT INTO "Items" VALUES(3,'Studded leather',13,450000);
INSERT INTO "Items" VALUES(4,'Hide',12,100000);
INSERT INTO "Items" VALUES(5,'Chain shirt',20,500000);
INSERT INTO "Items" VALUES(6,'Scale mail',45,500000);
INSERT INTO "Items" VALUES(7,'Breastplate',20,4000000);
INSERT INTO "Items" VALUES(8,'Half plate',40,7500000);
INSERT INTO "Items" VALUES(9,'Ring mail',40,300000);
INSERT INTO "Items" VALUES(10,'Chain mail',55,750000);
INSERT INTO "Items" VALUES(11,'Splint',60,2000000);
INSERT INTO "Items" VALUES(12,'Plate',65,10000000);
INSERT INTO "Items" VALUES(13,'Shield',6,100000);
INSERT INTO "Items" VALUES(27,'Club',2,100);
INSERT INTO "Items" VALUES(28,'Dagger',1,20000);
INSERT INTO "Items" VALUES(29,'Greatclub',10,200);
INSERT INTO "Items" VALUES(30,'Handaxe',2,50000);
INSERT INTO "Items" VALUES(31,'Javelin',2,500);
INSERT INTO "Items" VALUES(32,'Light hammer',2,20000);
INSERT INTO "Items" VALUES(33,'Mace',4,50000);
INSERT INTO "Items" VALUES(34,'Quarterstaff',4,200);
INSERT INTO "Items" VALUES(35,'Sickle',2,10000);
INSERT INTO "Items" VALUES(36,'Spear',3,10000);
INSERT INTO "Items" VALUES(37,'Crossbow, light',5,250000);
INSERT INTO "Items" VALUES(38,'Dart',0.25,5);
INSERT INTO "Items" VALUES(39,'Shortbow',2,250000);
INSERT INTO "Items" VALUES(40,'Sling',0.25,100);
INSERT INTO "Items" VALUES(41,'Battleaxe',4,100000);
INSERT INTO "Items" VALUES(42,'Flail',2,100000);
INSERT INTO "Items" VALUES(43,'Glaive',6,200000);
INSERT INTO "Items" VALUES(44,'Greataxe',7,300000);
INSERT INTO "Items" VALUES(45,'Greatsword',6,500000);
INSERT INTO "Items" VALUES(46,'Halberd',6,200000);
INSERT INTO "Items" VALUES(47,'Lance',6,100000);
INSERT INTO "Items" VALUES(48,'Longsword',3,150000);
INSERT INTO "Items" VALUES(49,'Maul',10,100000);
INSERT INTO "Items" VALUES(50,'Morningstar',4,150000);
INSERT INTO "Items" VALUES(51,'Pike',18,50000);
INSERT INTO "Items" VALUES(52,'Rapier',2,250000);
INSERT INTO "Items" VALUES(53,'Scimitar',3,250000);
INSERT INTO "Items" VALUES(54,'Shortsword',2,100000);
INSERT INTO "Items" VALUES(55,'Trident',4,50000);
INSERT INTO "Items" VALUES(56,'War pick',2,50000);
INSERT INTO "Items" VALUES(57,'Warhammer',2,150000);
INSERT INTO "Items" VALUES(58,'Whip',3,20000);
INSERT INTO "Items" VALUES(59,'Blowgun',1,100000);
INSERT INTO "Items" VALUES(60,'Crossbow, hand',3,750000);
INSERT INTO "Items" VALUES(61,'Crossbow, heavy',18,500000);
INSERT INTO "Items" VALUES(62,'Longbow',2,500000);
INSERT INTO "Items" VALUES(63,'Net',3,10000);
INSERT INTO "Items" VALUES(64,'Abacus',2,20000);
INSERT INTO "Items" VALUES(65,'Acid (vial)',1,250000);
INSERT INTO "Items" VALUES(66,'Alchemist''s fire (flask)',1,500000);
INSERT INTO "Items" VALUES(67,'Arrow',0.05,500);
INSERT INTO "Items" VALUES(68,'Blowgun needle',0.02,200);
INSERT INTO "Items" VALUES(69,'Crossbow bolt',0.075,500);
INSERT INTO "Items" VALUES(70,'Sling bullets (20)',1.5,4);
INSERT INTO "Items" VALUES(71,'Antitoxin (vial)',0.5,500000);
INSERT INTO "Items" VALUES(72,'Arcane focus: Crystal',1,100000);
INSERT INTO "Items" VALUES(73,'Arcane focus: Orb',3,200000);
INSERT INTO "Items" VALUES(74,'Arcane focus: Rod',2,100000);
INSERT INTO "Items" VALUES(75,'Arcane focus: Staff',4,50000);
INSERT INTO "Items" VALUES(76,'Arcane focus: Wand',1,100000);
INSERT INTO "Items" VALUES(77,'Backpack',5,20000);
INSERT INTO "Items" VALUES(78,'Ball bearings (bag of 1,000)',2,10000);
INSERT INTO "Items" VALUES(79,'Barrel',70,20000);
INSERT INTO "Items" VALUES(80,'Basket',2,400);
INSERT INTO "Items" VALUES(81,'Bedroll',7,10000);
INSERT INTO "Items" VALUES(82,'Bell',0.2,10000);
INSERT INTO "Items" VALUES(83,'Blanket',3,500);
INSERT INTO "Items" VALUES(84,'Block and tackle',5,10000);
INSERT INTO "Items" VALUES(85,'Book',5,250000);
INSERT INTO "Items" VALUES(86,'Bottle, glass',2,20000);
INSERT INTO "Items" VALUES(87,'Bucket',2,5);
INSERT INTO "Items" VALUES(88,'Caltrops (bag of 20)',2,10000);
INSERT INTO "Items" VALUES(89,'Candle',0,1);
INSERT INTO "Items" VALUES(90,'Case, crossbow bolt',1,10000);
INSERT INTO "Items" VALUES(91,'Case, map or scroll',1,10000);
INSERT INTO "Items" VALUES(92,'Chain (10 ft.)',10,50000);
INSERT INTO "Items" VALUES(93,'Chalk (1 piece)',0,1);
INSERT INTO "Items" VALUES(94,'Chest',25,50000);
INSERT INTO "Items" VALUES(95,'Climber''s kit',12,250000);
INSERT INTO "Items" VALUES(96,'Clothes, common',3,500);
INSERT INTO "Items" VALUES(97,'Clothes, costume',4,50000);
INSERT INTO "Items" VALUES(98,'Clothes, fine',6,150000);
INSERT INTO "Items" VALUES(99,'Clothes, traveler''s',4,20000);
INSERT INTO "Items" VALUES(100,'Component pouch',2,250000);
INSERT INTO "Items" VALUES(101,'Druidic focus: Sprig of mistletoe',0,10000);
INSERT INTO "Items" VALUES(102,'Druidic focus: Totem',0,10000);
INSERT INTO "Items" VALUES(103,'Druidic focus: Wooden staff',4,50000);
INSERT INTO "Items" VALUES(104,'Druidic focus: Yew wand',1,100000);
INSERT INTO "Items" VALUES(105,'Fishing tackle',4,10000);
INSERT INTO "Items" VALUES(106,'Flask or tankard',1,2);
INSERT INTO "Items" VALUES(107,'Grappling hook',4,20000);
INSERT INTO "Items" VALUES(108,'Hammer',3,10000);
INSERT INTO "Items" VALUES(109,'Hammer, sledge',10,20000);
INSERT INTO "Items" VALUES(110,'Healer''s kit',3,50000);
INSERT INTO "Items" VALUES(111,'Holy symbol: Amulet',1,50000);
INSERT INTO "Items" VALUES(112,'Holy symbol: Emblem',0,50000);
INSERT INTO "Items" VALUES(113,'Holy symbol: Reliquary',2,50000);
INSERT INTO "Items" VALUES(114,'Holy water (flask)',1,250000);
INSERT INTO "Items" VALUES(115,'Hourglass',1,250000);
INSERT INTO "Items" VALUES(116,'Hunting trap',25,50000);
INSERT INTO "Items" VALUES(117,'Ink (1 ounce bottle)',0,100000);
INSERT INTO "Items" VALUES(118,'Ink pen',0,2);
INSERT INTO "Items" VALUES(119,'Jug or pitcher',4,2);
INSERT INTO "Items" VALUES(120,'Ladder (10-foot)',25,100);
INSERT INTO "Items" VALUES(121,'Lamp',1,500);
INSERT INTO "Items" VALUES(122,'Lantern, bullseye',2,100000);
INSERT INTO "Items" VALUES(123,'Lantern, hooded',2,50000);
INSERT INTO "Items" VALUES(124,'Lock',1,100000);
INSERT INTO "Items" VALUES(125,'Magnifying glass',0,1000000);
INSERT INTO "Items" VALUES(126,'Manacles',6,20000);
INSERT INTO "Items" VALUES(127,'Mess kit',1,200);
INSERT INTO "Items" VALUES(128,'Mirror, steel',0.5,50000);
INSERT INTO "Items" VALUES(129,'Oil (flask)',1,100);
INSERT INTO "Items" VALUES(130,'Paper (one sheet)',0,200);
INSERT INTO "Items" VALUES(131,'Parchment (one sheet)',0,100);
INSERT INTO "Items" VALUES(132,'Perfume (vial)',0,50000);
INSERT INTO "Items" VALUES(133,'Pick, miner''s',10,20000);
INSERT INTO "Items" VALUES(134,'Piton',0.25,5);
INSERT INTO "Items" VALUES(135,'Poison, basic (vial)',0,1000000);
INSERT INTO "Items" VALUES(136,'Pole (10-foot)',7,5);
INSERT INTO "Items" VALUES(137,'Pot, iron',10,20000);
INSERT INTO "Items" VALUES(138,'Potion of healing',0.5,500000);
INSERT INTO "Items" VALUES(139,'Pouch',1,500);
INSERT INTO "Items" VALUES(140,'Quiver',1,10000);
INSERT INTO "Items" VALUES(141,'Ram, portable',35,40000);
INSERT INTO "Items" VALUES(142,'Rations (1 day)',2,500);
INSERT INTO "Items" VALUES(143,'Robes',4,10000);
INSERT INTO "Items" VALUES(144,'Rope, hempen (50 feet)',10,10000);
INSERT INTO "Items" VALUES(145,'Rope, silk (50 feet)',5,100000);
INSERT INTO "Items" VALUES(146,'Sack',0.5,1);
INSERT INTO "Items" VALUES(147,'Scale, merchant''s',3,50000);
INSERT INTO "Items" VALUES(148,'Sealing wax',0,500);
INSERT INTO "Items" VALUES(149,'Shovel',5,20000);
INSERT INTO "Items" VALUES(150,'Signal whistle',0,5);
INSERT INTO "Items" VALUES(151,'Signet ring',0,50000);
INSERT INTO "Items" VALUES(152,'Soap',0,2);
INSERT INTO "Items" VALUES(153,'Spellbook',3,500000);
INSERT INTO "Items" VALUES(154,'Spike, iron',0.5,1000);
INSERT INTO "Items" VALUES(155,'Spyglass',1,10000000);
INSERT INTO "Items" VALUES(156,'Tent, two-person',20,20000);
INSERT INTO "Items" VALUES(157,'Tinderbox',1,500);
INSERT INTO "Items" VALUES(158,'Torch',1,1);
INSERT INTO "Items" VALUES(159,'Vial',0,10000);
INSERT INTO "Items" VALUES(160,'Waterskin',5,200);
INSERT INTO "Items" VALUES(161,'Whetstone',1,1);
INSERT INTO "Items" VALUES(162,'Alchemist''s supplies',8,500000);
INSERT INTO "Items" VALUES(163,'Brewer''s supplies',9,200000);
INSERT INTO "Items" VALUES(164,'Calligrapher''s supplies',5,100000);
INSERT INTO "Items" VALUES(165,'Carpenter''s tools',6,80000);
INSERT INTO "Items" VALUES(166,'Cartographer''s tools',6,150000);
INSERT INTO "Items" VALUES(167,'Cobbler''s tools',5,50000);
INSERT INTO "Items" VALUES(168,'Cook''s utensils',8,10000);
INSERT INTO "Items" VALUES(169,'Glassblower''s tools',5,50000);
INSERT INTO "Items" VALUES(170,'Jeweler''s tools',2,250000);
INSERT INTO "Items" VALUES(171,'Leatherworker''s tools',5,50000);
INSERT INTO "Items" VALUES(172,'Mason''s tools',8,100000);
INSERT INTO "Items" VALUES(173,'Painter''s supplies',5,100000);
INSERT INTO "Items" VALUES(174,'Potter''s tools',3,100000);
INSERT INTO "Items" VALUES(175,'Smith''s tools',8,200000);
INSERT INTO "Items" VALUES(176,'Tinker''s tools',10,500000);
INSERT INTO "Items" VALUES(177,'Weaver''s tools',5,10000);
INSERT INTO "Items" VALUES(178,'Woodcarver''s tools',5,10000);
INSERT INTO "Items" VALUES(179,'Disguise kit',3,250000);
INSERT INTO "Items" VALUES(180,'Forgery kit',5,150000);
INSERT INTO "Items" VALUES(181,'Dice set',0,100);
INSERT INTO "Items" VALUES(182,'Dragonchess set',0.5,10000);
INSERT INTO "Items" VALUES(183,'Playing card set',0,500);
INSERT INTO "Items" VALUES(184,'Three-Dragon Ante set',0,10000);
INSERT INTO "Items" VALUES(185,'Herbalism kit',3,50000);
INSERT INTO "Items" VALUES(186,'Bagpipes',6,300000);
INSERT INTO "Items" VALUES(187,'Drum',3,60000);
INSERT INTO "Items" VALUES(188,'Dulcimer',10,250000);
INSERT INTO "Items" VALUES(189,'Flute',1,20000);
INSERT INTO "Items" VALUES(190,'Lute',2,350000);
INSERT INTO "Items" VALUES(191,'Lyre',2,300000);
INSERT INTO "Items" VALUES(192,'Horn',2,30000);
INSERT INTO "Items" VALUES(193,'Pan flute',2,120000);
INSERT INTO "Items" VALUES(194,'Shawm',1,20000);
INSERT INTO "Items" VALUES(195,'Viol',1,300000);
INSERT INTO "Items" VALUES(196,'Navigator''s tools',2,250000);
INSERT INTO "Items" VALUES(197,'Poisoner''s kit',2,500000);
INSERT INTO "Items" VALUES(198,'Thieves'' tools',1,250000);
DROP TABLE IF EXISTS "KeyStatsPerClass";
CREATE TABLE KeyStatsPerClass
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
DROP TABLE IF EXISTS "KnownSpellsPerCharacter";
CREATE TABLE KnownSpellsPerCharacter
(
id_char INTEGER NOT NULL,
id_spell INTEGER NOT NULL,
requiresPreparation INTEGER NOT NULL DEFAULT 0 CHECK (requiresPreparation IN (0, 1)),
CONSTRAINT pk_spells_char PRIMARY KEY (id_char, id_spell),
CONSTRAINT fk_char FOREIGN KEY (id_char) REFERENCES Characters (_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_spell FOREIGN KEY (id_spell) REFERENCES Spells (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "Languages";
CREATE TABLE Languages
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
CREATE TABLE LanguagesPerRace
(
id_race INTEGER NOT NULL,
id_language INTEGER NOT NULL,
CONSTRAINT pk_composite_lang_race PRIMARY KEY (id_race, id_language),
CONSTRAINT fk_composite_lr_race FOREIGN KEY (id_race) REFERENCES Races (_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_composite_lr_language FOREIGN KEY (id_language) REFERENCES Languages (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "ProficiencyGroups";
CREATE TABLE ProficiencyGroups
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
CREATE TABLE "Races"
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
INSERT INTO "Races" VALUES(2,'Dwarf, Hill',25,2,1,0);
INSERT INTO "Races" VALUES(3,'Dwarf, Mountain',25,2,1,0);
INSERT INTO "Races" VALUES(4,'Elf',30,2,NULL,1);
INSERT INTO "Races" VALUES(5,'Elf, High',30,2,4,0);
INSERT INTO "Races" VALUES(6,'Elf, Wood',35,2,4,0);
INSERT INTO "Races" VALUES(7,'Elf, Drow',30,2,4,0);
INSERT INTO "Races" VALUES(8,'Halfling',25,1,NULL,1);
INSERT INTO "Races" VALUES(9,'Halfling, Lightfoot',25,1,8,0);
INSERT INTO "Races" VALUES(10,'Halfling, Stout',25,1,8,0);
INSERT INTO "Races" VALUES(11,'Human',30,2,NULL,1);
INSERT INTO "Races" VALUES(12,'Human, alternate',30,2,NULL,1);
INSERT INTO "Races" VALUES(13,'Dragonborn',30,2,NULL,1);
INSERT INTO "Races" VALUES(14,'Gnome',25,1,NULL,1);
INSERT INTO "Races" VALUES(15,'Gnome, Forest',25,1,14,0);
INSERT INTO "Races" VALUES(16,'Gnome, Rock',25,1,14,0);
INSERT INTO "Races" VALUES(17,'Half-Elf',30,2,NULL,1);
INSERT INTO "Races" VALUES(18,'Half-Orc',30,2,NULL,1);
INSERT INTO "Races" VALUES(19,'Tiefling',30,2,NULL,1);
DROP TABLE IF EXISTS "RacialFeatureValues";
CREATE TABLE RacialFeatureValues
(
  id_value INTEGER PRIMARY KEY,
  value INTEGER NOT NULL,
  CONSTRAINT fk_featureVALUE FOREIGN KEY (id_value) REFERENCES RacialFeatures(_id) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "RacialFeatureValues" VALUES(1,60);
INSERT INTO "RacialFeatureValues" VALUES(18,60);
INSERT INTO "RacialFeatureValues" VALUES(19,120);
INSERT INTO "RacialFeatureValues" VALUES(45,60);
DROP TABLE IF EXISTS "RacialFeatures";
CREATE TABLE RacialFeatures
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
INSERT INTO "RacialFeatures" VALUES(28,5,42);
INSERT INTO "RacialFeatures" VALUES(29,6,40);
INSERT INTO "RacialFeatures" VALUES(30,7,43);
INSERT INTO "RacialFeatures" VALUES(31,7,44);
INSERT INTO "RacialFeatures" VALUES(32,7,22);
INSERT INTO "RacialFeatures" VALUES(33,7,45);
INSERT INTO "RacialFeatures" VALUES(34,7,46);
INSERT INTO "RacialFeatures" VALUES(35,8,53);
INSERT INTO "RacialFeatures" VALUES(36,8,54);
INSERT INTO "RacialFeatures" VALUES(37,8,55);
INSERT INTO "RacialFeatures" VALUES(38,9,56);
INSERT INTO "RacialFeatures" VALUES(39,10,57);
INSERT INTO "RacialFeatures" VALUES(40,8,29);
INSERT INTO "RacialFeatures" VALUES(41,12,39);
INSERT INTO "RacialFeatures" VALUES(42,12,58);
INSERT INTO "RacialFeatures" VALUES(43,12,59);
INSERT INTO "RacialFeatures" VALUES(44,13,60);
INSERT INTO "RacialFeatures" VALUES(45,14,1);
INSERT INTO "RacialFeatures" VALUES(46,14,27);
INSERT INTO "RacialFeatures" VALUES(47,14,71);
INSERT INTO "RacialFeatures" VALUES(48,15,72);
INSERT INTO "RacialFeatures" VALUES(49,15,73);
INSERT INTO "RacialFeatures" VALUES(50,16,74);
INSERT INTO "RacialFeatures" VALUES(51,16,75);
INSERT INTO "RacialFeatures" VALUES(52,16,76);
INSERT INTO "RacialFeatures" VALUES(53,17,39);
INSERT INTO "RacialFeatures" VALUES(54,17,1);
INSERT INTO "RacialFeatures" VALUES(55,17,18);
INSERT INTO "RacialFeatures" VALUES(56,17,77);
INSERT INTO "RacialFeatures" VALUES(57,17,20);
INSERT INTO "RacialFeatures" VALUES(58,18,1);
INSERT INTO "RacialFeatures" VALUES(59,18,78);
INSERT INTO "RacialFeatures" VALUES(60,18,79);
INSERT INTO "RacialFeatures" VALUES(61,18,80);
INSERT INTO "RacialFeatures" VALUES(62,19,81);
INSERT INTO "RacialFeatures" VALUES(63,19,82);
INSERT INTO "RacialFeatures" VALUES(64,4,8);
INSERT INTO "RacialFeatures" VALUES(65,8,8);
INSERT INTO "RacialFeatures" VALUES(66,11,8);
INSERT INTO "RacialFeatures" VALUES(67,14,8);
INSERT INTO "RacialFeatures" VALUES(68,17,8);
INSERT INTO "RacialFeatures" VALUES(69,18,8);
INSERT INTO "RacialFeatures" VALUES(70,19,8);
INSERT INTO "RacialFeatures" VALUES(71,13,99);
DROP TABLE IF EXISTS "RacialStats";
CREATE TABLE RacialStats
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
INSERT INTO "RacialStats" VALUES(8,8,'DEX',2);
INSERT INTO "RacialStats" VALUES(9,9,'CHA',1);
INSERT INTO "RacialStats" VALUES(10,10,'CON',1);
INSERT INTO "RacialStats" VALUES(11,11,'STR',1);
INSERT INTO "RacialStats" VALUES(12,11,'DEX',1);
INSERT INTO "RacialStats" VALUES(13,11,'CON',1);
INSERT INTO "RacialStats" VALUES(14,11,'INT',1);
INSERT INTO "RacialStats" VALUES(15,11,'WIS',1);
INSERT INTO "RacialStats" VALUES(16,11,'CHA',1);
INSERT INTO "RacialStats" VALUES(17,13,'STR',2);
INSERT INTO "RacialStats" VALUES(18,13,'CHA',1);
INSERT INTO "RacialStats" VALUES(19,14,'INT',2);
INSERT INTO "RacialStats" VALUES(20,15,'DEX',1);
INSERT INTO "RacialStats" VALUES(21,16,'CON',1);
INSERT INTO "RacialStats" VALUES(22,17,'CHA',2);
INSERT INTO "RacialStats" VALUES(23,18,'STR',2);
INSERT INTO "RacialStats" VALUES(24,18,'CON',1);
INSERT INTO "RacialStats" VALUES(25,19,'INT',1);
INSERT INTO "RacialStats" VALUES(26,19,'CHA',2);
DROP TABLE IF EXISTS "RangedWeapons";
CREATE TABLE RangedWeapons
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
CREATE TABLE Sizes
(
_id INTEGER PRIMARY KEY AUTOINCREMENT,
name TEXT NOT NULL
);
INSERT INTO "Sizes" VALUES(1,'Small');
INSERT INTO "Sizes" VALUES(2,'Medium');
DROP TABLE IF EXISTS "Skills";
CREATE TABLE Skills
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
CREATE TABLE SkillsPerClass
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
DROP TABLE IF EXISTS "SpellCastingTimes";
CREATE TABLE "SpellCastingTimes" (
"id_spell"  INTEGER NOT NULL,
"id_timeUnit"  INTEGER NOT NULL,
"timeUnitAmount"  INTEGER,
PRIMARY KEY ("id_spell", "id_timeUnit"),
CONSTRAINT "fk_spell" FOREIGN KEY ("id_spell") REFERENCES "Spells" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_timeUnit" FOREIGN KEY ("id_timeUnit") REFERENCES "TimeUnits" ("_id")
);
DROP TABLE IF EXISTS "SpellDurations";
CREATE TABLE "SpellDurations" (
"id_spell"  INTEGER NOT NULL,
"id_time_unit"  INTEGER NOT NULL,
"time_units"  INTEGER NOT NULL,
"heightenedEffect"  TEXT,
"heightenedFactor"  REAL,
PRIMARY KEY ("id_spell" ASC, "id_time_unit" ASC),
CONSTRAINT "fk_spell" FOREIGN KEY ("id_spell") REFERENCES "Spells" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_time_unit" FOREIGN KEY ("id_time_unit") REFERENCES "TimeUnits" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "chk_heighten_effect" CHECK (heightenedEffect IN ('multiply', 'increase'))
);
DROP TABLE IF EXISTS "SpellMaterials";
CREATE TABLE "SpellMaterials" (
"id_spell"  INTEGER NOT NULL,
"id_item"  INTEGER NOT NULL,
PRIMARY KEY ("id_spell", "id_item"),
CONSTRAINT "fk_spell" FOREIGN KEY ("id_spell") REFERENCES "Spells" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_item" FOREIGN KEY ("id_item") REFERENCES "Items" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "SpellSchools";
CREATE TABLE "SpellSchools" (
"_id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"schoolName"  TEXT NOT NULL
);
INSERT INTO "SpellSchools" VALUES(1,'Abjuration');
INSERT INTO "SpellSchools" VALUES(2,'Conjuration');
INSERT INTO "SpellSchools" VALUES(3,'Divination');
INSERT INTO "SpellSchools" VALUES(4,'Enchantment');
INSERT INTO "SpellSchools" VALUES(5,'Evocation');
INSERT INTO "SpellSchools" VALUES(6,'Illusion');
INSERT INTO "SpellSchools" VALUES(7,'Necromancy');
INSERT INTO "SpellSchools" VALUES(8,'Transmutation');
DROP TABLE IF EXISTS "SpellTargets";
CREATE TABLE "SpellTargets" (
"id_spell"  INTEGER NOT NULL,
"targets"  INTEGER NOT NULL,
"heightenedFactor"  INTEGER,
PRIMARY KEY ("id_spell" ASC),
CONSTRAINT "fk_spell" FOREIGN KEY ("id_spell") REFERENCES "Spells" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "Spellbooks";
CREATE TABLE Spellbooks
(
id_item INTEGER NOT NULL,
id_spell INTEGER NOT NULL,
CONSTRAINT pk_item_spells PRIMARY KEY (id_item, id_spell),
CONSTRAINT fk_item FOREIGN KEY (id_item) REFERENCES CharacterInventory(_id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_spell FOREIGN KEY (id_spell) REFERENCES Spells (_id) ON UPDATE CASCADE ON DELETE CASCADE
);
DROP TABLE IF EXISTS "Spells";
CREATE TABLE "Spells" (
"_id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT NOT NULL,
"id_school"  INTEGER NOT NULL,
"level"  INTEGER DEFAULT 0,
"isCombatSpell"  INTEGER DEFAULT 0,
"isInstantaneous"  INTEGER DEFAULT 1,
"isRitual"  INTEGER DEFAULT 0,
"requiresConcentration"  INTEGER DEFAULT 0,
"hasVerbalComponent"  INTEGER DEFAULT 0,
"hasSomaticComponent"  INTEGER DEFAULT 0,
"hasMaterialComponent"  INTEGER DEFAULT 0,
"hasVariants"  INTEGER DEFAULT 0,
"canBeHeightened"  INTEGER DEFAULT 0,
"id_parent"  INTEGER,
"detail"  TEXT NOT NULL,
CONSTRAINT "fk_spell_school" FOREIGN KEY ("id_school") REFERENCES "SpellSchools" ("_id"),
CONSTRAINT "fk_parent" FOREIGN KEY ("id_parent") REFERENCES "Spells" ("_id")
);
INSERT INTO "Spells" VALUES(1,'Dancing Lights',5,0,1,1,0,0,1,1,1,0,0,NULL,'You create up to four torch-sized lights within range.');
INSERT INTO "Spells" VALUES(2,'Blade Ward',1,0,1,1,0,0,1,1,0,0,0,NULL,'Until the end of your next turn, you have resistance against bludgeoning, piercing, and slashing damage dealt by weapon attacks.');
INSERT INTO "Spells" VALUES(3,'Friends',4,0,0,0,0,1,0,1,1,0,0,NULL,'You have advantage on all Charisma checks directed at one creature of your choice that is not hostile toward you. When the spell ends, the creature realizes that you used magic to influence its mood and becomes hostile toward you.');
INSERT INTO "Spells" VALUES(4,'Light',5,0,1,0,0,0,1,0,1,0,0,NULL,'One object touched no larger than 10 feet in any dimension sheds bright light in a 20-foot radius and dim light for an additional 20 feet.');
DROP TABLE IF EXISTS "SpellsPerClass";
CREATE TABLE "SpellsPerClass" (
"id_class"  INTEGER NOT NULL,
"id_spell"  INTEGER NOT NULL,
PRIMARY KEY ("id_class" ASC, "id_spell" ASC),
CONSTRAINT "fk_class" FOREIGN KEY ("id_class") REFERENCES "CharacterClasses" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_spell" FOREIGN KEY ("id_spell") REFERENCES "Spells" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO "SpellsPerClass" VALUES(2,1);
INSERT INTO "SpellsPerClass" VALUES(2,2);
INSERT INTO "SpellsPerClass" VALUES(2,3);
INSERT INTO "SpellsPerClass" VALUES(2,4);
INSERT INTO "SpellsPerClass" VALUES(10,1);
INSERT INTO "SpellsPerClass" VALUES(10,2);
INSERT INTO "SpellsPerClass" VALUES(10,3);
INSERT INTO "SpellsPerClass" VALUES(10,4);
INSERT INTO "SpellsPerClass" VALUES(12,1);
INSERT INTO "SpellsPerClass" VALUES(12,2);
INSERT INTO "SpellsPerClass" VALUES(12,3);
INSERT INTO "SpellsPerClass" VALUES(12,4);
INSERT INTO "SpellsPerClass" VALUES(11,2);
INSERT INTO "SpellsPerClass" VALUES(11,3);
INSERT INTO "SpellsPerClass" VALUES(3,4);
DROP TABLE IF EXISTS "StatusEffectSpells";
CREATE TABLE "StatusEffectSpells" (
"id_spell"  INTEGER NOT NULL,
"id_status_effect"  INTEGER NOT NULL,
PRIMARY KEY ("id_spell" ASC, "id_status_effect"),
CONSTRAINT "fk_id_spell" FOREIGN KEY ("id_spell") REFERENCES "Spells" ("_id") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "fk_id_st_ef" FOREIGN KEY ("id_status_effect") REFERENCES "StatusEffects" ("_id") ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "StatusEffects";
CREATE TABLE "StatusEffects" (
"_id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT NOT NULL
);
DROP TABLE IF EXISTS "TimeUnits";
CREATE TABLE "TimeUnits"
(
  _id INTEGER NOT NULL
        CONSTRAINT Key1 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);
INSERT INTO "TimeUnits" VALUES(1,'Round');
DROP TABLE IF EXISTS "ToolProfsPerClass";
CREATE TABLE ToolProfsPerClass
(
  id_tool INTEGER NOT NULL,
  id_class INTEGER NOT NULL,
  CONSTRAINT pk_composite_tools_profs PRIMARY KEY (id_tool,id_class),
  CONSTRAINT fk_composite_tools FOREIGN KEY (id_tool) REFERENCES Tools (_id),
  CONSTRAINT fk_composite_classes FOREIGN KEY (id_class) REFERENCES CharacterClasses (_id)
);
DROP TABLE IF EXISTS "Tools";
CREATE TABLE Tools
(
  _id INTEGER NOT NULL PRIMARY KEY,
  CONSTRAINT fk_tools_eq FOREIGN KEY (_id) REFERENCES "Items" (_id)
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
CREATE TABLE WeaponFeatureTypes
(
  _id INTEGER NOT NULL
        CONSTRAINT Key2 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL
);
DROP TABLE IF EXISTS "WeaponFeatureValues";
CREATE TABLE WeaponFeatureValues
(
  _id INTEGER NOT NULL
        CONSTRAINT Key4 PRIMARY KEY AUTOINCREMENT,
  id_feature INTEGER NOT NULL,
  value TEXT NOT NULL,
  CONSTRAINT Relationship3 FOREIGN KEY (id_feature) REFERENCES WeaponFeatures (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "WeaponFeatures";
CREATE TABLE WeaponFeatures
(
  _id INTEGER NOT NULL
        CONSTRAINT Key3 PRIMARY KEY AUTOINCREMENT,
  id_weapon INTEGER NOT NULL,
  id_wp_feat_type INTEGER NOT NULL,
  CONSTRAINT Relationship1 FOREIGN KEY (id_weapon) REFERENCES Weapons (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship2 FOREIGN KEY (id_wp_feat_type) REFERENCES WeaponFeatureTypes (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "WeaponProfsPerClass";
CREATE TABLE WeaponProfsPerClass
(
  id_class INTEGER NOT NULL,
  id_weapon INTEGER NOT NULL,
  CONSTRAINT Key7 PRIMARY KEY (id_class,id_weapon),
  CONSTRAINT Relationship5 FOREIGN KEY (id_class) REFERENCES CharacterClass (_id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT Relationship6 FOREIGN KEY (id_weapon) REFERENCES Weapons (_id) ON DELETE CASCADE ON UPDATE CASCADE
);
DROP TABLE IF EXISTS "Weapons";
CREATE TABLE "Weapons" (
"_id"  INTEGER NOT NULL,
"dice"  INTEGER NOT NULL,
"dieSize"  INTEGER NOT NULL,
"id_damageType"  INTEGER NOT NULL,
PRIMARY KEY ("_id" ASC),
CONSTRAINT "Relationship9" FOREIGN KEY ("_id") REFERENCES "Equipment" ("id_item") ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT "Relationship41" FOREIGN KEY ("id_damageType") REFERENCES "DamageTypes" ("_id")
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
CREATE TABLE WrittenAlphabets
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
CREATE VIEW CheckFeatureLanguages AS SELECT DISTINCT Languages.name AS Language, Features.name AS Feature
FROM Languages, Features
JOIN FeatureLanguages
WHERE Languages._id = FeatureLanguages.id_language
AND Features._id = FeatureLanguages.id_feature;
CREATE VIEW "CheckFeatureProficiencies" AS SELECT DISTINCT Items.name AS Item, Features.name AS Feature
FROM Items, Features
JOIN FeatureProficiencies
WHERE Items._id = FeatureProficiencies.id_equipment
AND Features._id = FeatureProficiencies.id_feature;
CREATE VIEW CheckFeatureSkills AS SELECT DISTINCT Skills.name AS Skill, Features.name AS Feature
FROM Features, Skills
JOIN FeatureSkills
WHERE Skills._id = FeatureSkills.id_skill
AND Features._id = FeatureSkills.id_feature;
CREATE VIEW CheckRacialFeatures AS SELECT DISTINCT Races.name AS Race, Features.name AS Feature
FROM Races, Features
JOIN RacialFeatures
WHERE Races._id = RacialFeatures.id_race
AND Features._id = RacialFeatures.id_feature;
CREATE VIEW CheckRacialStats AS SELECT DISTINCT Races.name AS Race, RacialStats.stat AS Statistic, RacialStats.bonus AS Bonus
FROM Races, RacialStats
WHERE Races._id = RacialStats.id_race;
CREATE VIEW CheckSpellsPerClass AS SELECT DISTINCT Spells.name AS Spell, CharacterClasses.name AS Class
FROM Spells, CharacterClasses
JOIN SpellsPerClass
WHERE Spells._id = SpellsPerClass.id_spell
AND CharacterClasses._id = SpellsPerClass.id_class;
CREATE VIEW "GetArmors" AS 
SELECT Items._id, Items.name, Equipment.proficiencyGroup, Armors.bonus, Armors.maxDexBonus, Armors.requiredStr, Armors.impairsStealth, Items.weight, Items.cost
FROM Armors, Equipment, Items
WHERE Equipment.id_item = Armors._id
AND Items._id = Equipment.id_item;
CREATE VIEW GetClassSkills AS
SELECT CharacterClasses.name AS className, Skills.name AS skill, keyStat AS defaultStatistic
FROM CharacterClasses, Skills, SkillsPerClass
WHERE Skills._id = SkillsPerClass.id_skill 
AND CharacterClasses._id = SkillsPerClass.id_class;
CREATE VIEW "GetEffectAOEs" AS 
SELECT
EffectAOEs.id_effect,
AreasOfEffect.name,
EffectAOEs.range,
EffectAOEs.targetsSelf
FROM
EffectAOEs
INNER JOIN AreasOfEffect ON EffectAOEs.id_aoe_type = AreasOfEffect._id;
CREATE VIEW "GetSpells" AS 
SELECT DISTINCT
Spells._id,
Spells.name AS spellName,
SpellSchools.schoolName,
Spells.level,
Spells.castingTime,
Spells.isInstantaneous,
Spells.requiresConcentration,
Spells.hasVerbalComponent,
Spells.hasSomaticComponent,
Spells.hasMaterialComponent,
Spells.detail
FROM
Spells
INNER JOIN SpellSchools ON Spells.id_school = SpellSchools._id;
CREATE VIEW "GetTools" AS SELECT Tools._id, Items.name, Items.weight, Items.cost FROM Tools
JOIN Items
WHERE Tools._id = Items._id;
CREATE VIEW "GetWeapons" AS SELECT DISTINCT Items._id, Items.name, Equipment.proficiencyGroup, Weapons.dice, Weapons.dieSize, Weapons.id_damageType, RangedWeapons.shortRange, RangedWeapons.longRange, Items.weight, Items.cost
FROM Equipment, Weapons, Items
LEFT JOIN RangedWeapons
ON RangedWeapons._id = Weapons._id
WHERE Equipment.id_item = Weapons._id
AND Items._id = Weapons._id;
CREATE TRIGGER "insert_effect_aoe" INSTEAD OF INSERT ON "GetEffectAOEs"
BEGIN

INSERT OR IGNORE INTO AreasOfEffect (name)
SELECT new.name
WHERE NOT EXISTS
(
SELECT 1 FROM AreasOfEffect
WHERE name = new.name
);

INSERT OR IGNORE INTO EffectAOEs (
id_aoe_type,
range,
targetsSelf)
SELECT
AreasOfEffect._id,
new.range,
new.targetsSelf
FROM AreasOfEffect
WHERE AreasOfEffect.name = new.name;

END;
CREATE TRIGGER insert_equipment_armor
INSTEAD OF INSERT 
ON GetArmors

BEGIN

INSERT OR IGNORE INTO Items (name, weight, cost)
SELECT new.name, new.weight, new.cost
WHERE NOT EXISTS
(
SELECT 1 FROM Items
WHERE name = new.name
);

INSERT OR IGNORE INTO Equipment (proficiencyGroup)
SELECT new.proficiencyGroup
FROM Items
WHERE Items.name = new.name;

INSERT OR IGNORE INTO     Armors (_id, bonus, impairsStealth, requiredStr, maxDexBonus)
SELECT Items._id, new.bonus, new.impairsStealth, new.requiredStr, new.maxDexBonus
FROM Items 
WHERE Items.name = new.name;

END;
CREATE TRIGGER insert_equipment_tools
INSTEAD OF INSERT 
ON GetTools

BEGIN

INSERT OR IGNORE INTO     Items (name, weight, cost)
SELECT new.name, new.weight, new.cost
WHERE NOT EXISTS
(
SELECT 1 FROM Items
WHERE name = new.name
);

INSERT OR IGNORE INTO     Tools (_id)
SELECT Items._id
FROM Items 
WHERE Items.name = new.name;

END;
CREATE TRIGGER insert_equipment_weapon
INSTEAD OF INSERT 
ON GetWeapons

BEGIN

INSERT OR IGNORE INTO Items (name, weight, cost)
SELECT new.name, new.weight, new.cost
WHERE NOT EXISTS
(
SELECT 1 FROM Items
WHERE name = new.name
);

INSERT OR IGNORE INTO Equipment (proficiencyGroup)
SELECT new.proficiencyGroup
FROM Items
WHERE Items.name = new.name;

INSERT OR IGNORE INTO Weapons (_id, dice, dieSize, id_damageType)
SELECT Items._id, new.dice, new.dieSize, new.id_damageType
FROM Items 
WHERE Items.name = new.name;

INSERT OR IGNORE INTO RangedWeapons (_id, shortRange, longRange)
SELECT Items._id, new.shortRange, new.longRange
FROM Items 
WHERE new.shortRange NOTNULL 
AND new.name = Items.name;

END;
CREATE TRIGGER "insert_spell" INSTEAD OF INSERT ON "GetSpells"
BEGIN

INSERT OR IGNORE INTO SpellSchools (schoolName)
SELECT new.schoolName
WHERE NOT EXISTS
(
SELECT 1 FROM SpellSchools
WHERE schoolName = new.schoolName
);

INSERT OR IGNORE INTO Spells (
name, 
id_school, 
level, 
castingTime, 
isInstantaneous, 
requiresConcentration, 
hasVerbalComponent, 
hasSomaticComponent, 
hasMaterialComponent,
detail)
SELECT 
new.spellName, 
SpellSchools._id, 
new.level, 
new.castingTime, 
new.isInstantaneous, 
new.requiresConcentration, 
new.hasVerbalComponent, 
new.hasSomaticComponent, 
new.hasMaterialComponent,
new.detail
FROM SpellSchools
WHERE SpellSchools.schoolName = new.schoolName;

END;
CREATE INDEX "IX_Relationship1"
ON "Spells" ("id_school" ASC);
