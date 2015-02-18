DROP TRIGGER IF EXISTS insert_equipment_armor;

CREATE TRIGGER insert_equipment_armor
INSTEAD OF INSERT 
ON GetArmors

BEGIN

INSERT INTO Equipment (name, weight, cost, proficiencyGroup)
SELECT new.name, new.weight, new.cost, new.proficiencyGroup
WHERE NOT EXISTS
(
SELECT 1 FROM Equipment
WHERE name = new.name
);

INSERT INTO Armors (_id, bonus, impairsStealth, requiredStr, maxDexBonus)
SELECT Equipment._id, new.bonus, new.impairsStealth, new.requiredStr, new.maxDexBonus
FROM Equipment
WHERE Equipment.name = new.name;

END

