CREATE TABLE IF NOT EXISTS Skills
(
  _id INTEGER NOT NULL
        CONSTRAINT Key13 PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  keyStat TEXT NOT NULL
        CONSTRAINT CheckConstraintA1 CHECK (keyStat IN ('STR', 'DEX', 'CON', 'INT', 'WIS', 'CHA'))
);

INSERT INTO "Skills" (_id, name, stat)
SELECT 1,'Athletics','STR'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 1
    AND name = 'Athletics'
    AND stat = 'STR');

INSERT INTO "Skills" (_id, name, stat)
SELECT 2,'Acrobatics','DEX'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 2
    AND name = 'Acrobatics'
    AND stat = 'DEX');

INSERT INTO "Skills" (_id, name, stat)
SELECT 3,'Sleight of Hand','DEX'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 3
    AND name = 'Sleight of Hand'
    AND stat = 'DEX');

INSERT INTO "Skills" (_id, name, stat)
SELECT 4,'Stealth','DEX'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 4
    AND name = 'Stealth'
    AND stat = 'DEX');

INSERT INTO "Skills" (_id, name, stat)
SELECT 5,'Arcana','INT'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 5
    AND name = 'Arcana'
    AND stat = 'INT');

INSERT INTO "Skills" (_id, name, stat)
SELECT 6,'History','INT'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 6
    AND name = 'History'
    AND stat = 'INT');

INSERT INTO "Skills" (_id, name, stat)
SELECT 7,'Investigation','INT'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 7
    AND name = 'Investigation'
    AND stat = 'INT');

INSERT INTO "Skills" (_id, name, stat)
SELECT 8,'Nature','INT'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 8
    AND name = 'Nature'
    AND stat = 'INT');

INSERT INTO "Skills" (_id, name, stat)
SELECT 9,'Religion','INT'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 9
    AND name = 'Religion'
    AND stat = 'INT');

INSERT INTO "Skills" (_id, name, stat)
SELECT 10,'Animal Handling','WIS'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 10
    AND name = 'Animal Handling'
    AND stat = 'WIS');

INSERT INTO "Skills" (_id, name, stat)
SELECT 11,'Insight','WIS'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 11
    AND name = 'Insight'
    AND stat = 'WIS');

INSERT INTO "Skills" (_id, name, stat)
SELECT 12,'Medicine','WIS'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 12
    AND name = 'Medicine'
    AND stat = 'WIS');

INSERT INTO "Skills" (_id, name, stat)
SELECT 13,'Perception','WIS'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 13
    AND name = 'Perception'
    AND stat = 'WIS');

INSERT INTO "Skills" (_id, name, stat)
SELECT 14,'Survival','WIS'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 14
    AND name = 'Survival'
    AND stat = 'WIS');

INSERT INTO "Skills" (_id, name, stat)
SELECT 15,'Deception','CHA'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 15
    AND name = 'Deception'
    AND stat = 'CHA');

INSERT INTO "Skills" (_id, name, stat)
SELECT 16,'Intimidation','CHA'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 16
    AND name = 'Intimidation'
    AND stat = 'CHA');

INSERT INTO "Skills" (_id, name, stat)
SELECT 17,'Performance','CHA'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 17
    AND name = 'Performance'
    AND stat = 'CHA');

INSERT INTO "Skills" (_id, name, stat)
SELECT 18,'Persuasion','CHA'
WHERE NOT EXISTS(
    SELECT 1 FROM "Skills"
    WHERE _id = 18
    AND name = 'Persuasion'
    AND stat = 'CHA');