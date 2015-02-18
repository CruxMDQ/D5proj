DROP TRIGGER IF EXISTS insert_equipment_weapon;

CREATE TRIGGER insert_equipment_weapon
INSTEAD OF INSERT 
ON GetWeapons

BEGIN

INSERT INTO Equipment (name, weight, cost, proficiencyGroup)
SELECT new.name, new.weight, new.cost, new.proficiencyGroup
WHERE NOT EXISTS
(
SELECT 1 FROM Equipment
WHERE name = new.name
);

INSERT INTO Weapons (_id, dice, dieSize, id_damageType)
SELECT Equipment._id, new.dice, new.dieSize, new.id_damageType
FROM Equipment
WHERE Equipment.name = new.name;

INSERT INTO RangedWeapons (_id, shortRange, longRange)
SELECT Equipment._id, new.shortRange, new.longRange
FROM Equipment
WHERE new.shortRange NOTNULL 
AND new.name = Equipment.name;

END