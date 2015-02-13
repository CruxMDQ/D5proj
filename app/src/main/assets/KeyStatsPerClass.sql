CREATE TABLE IF NOT EXISTS KeyStatsPerClass
(
  _id INTEGER NOT NULL,
  id_class INTEGER NOT NULL,
  stat TEXT NOT NULL CHECK (stat IN('STR', 'DEX', 'CON', 'INT', 'WIS', 'CHA')),
  type TEXT NOT NULL CHECK (type IN('Primary', 'Save')),
  CONSTRAINT Key25 PRIMARY KEY (_id)
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
