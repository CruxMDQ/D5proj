INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 1,'Barbarian',12,3
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 1
    AND name = 'Barbarian'
    AND dieSize = 12
    AND skills = 3);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 2,'Bard',8,3
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 2
    AND name = 'Bard'
    AND dieSize = 8
    AND skills = 3);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 3,'Cleric',8,2
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 3
    AND name = 'Cleric'
    AND dieSize = 8
    AND skills = 2);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 4,'Druid',8,2
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 4
    AND name = 'Druid'
    AND dieSize = 8
    AND skills = 2);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 5,'Fighter',10,2
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 5
    AND name = 'Fighter'
    AND dieSize = 10
    AND skills = 2);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 6,'Monk',8,2
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 6
    AND name = 'Monk'
    AND dieSize = 8
    AND skills = 2);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 7,'Paladin',10,2
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 7
    AND name = 'Paladin'
    AND dieSize = 10
    AND skills = 2);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 8,'Ranger',10,3
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 8
    AND name = 'Ranger'
    AND dieSize = 10
    AND skills = 3);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 9,'Rogue',8,4
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 9
    AND name = 'Rogue'
    AND dieSize = 8
    AND skills = 4);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 10,'Sorcerer',6,2
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 10
    AND name = 'Sorcerer'
    AND dieSize = 6
    AND skills = 2);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 11,'Warlock',8,2
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 11
    AND name = 'Warlock'
    AND dieSize = 8
    AND skills = 2);

INSERT INTO "CharacterClasses" (_id, name, dieSize, skills)
SELECT 12,'Wizard',6,2
WHERE NOT EXISTS(
    SELECT 1 FROM "CharacterClasses"
    WHERE _id = 12
    AND name = 'Wizard'
    AND dieSize = 6
    AND skills = 2);