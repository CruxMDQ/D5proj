DROP TABLE IF EXISTS "Armors";
CREATE TABLE Armors
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
