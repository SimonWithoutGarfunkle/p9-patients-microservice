DELETE FROM patient;
ALTER TABLE patient AUTO_INCREMENT = 1;

INSERT INTO patient (nom, prenom, genre, rue, telephone, date_naissance)
VALUES
    ('TestNone', 'Test', 'FEMME', '1 Brookside St', '100-222-3333', "1966-12-31T00:00:00.000Z" ),
    ('TestBorderline', 'Test', 'HOMME', '2 High St', '200-333-4444', "1945-06-24T00:00:00.000Z"),
    ('TestInDanger', 'Test', 'HOMME', '3 Club Road', '300-444-5555', "2004-06-18T00:00:00.000Z"),
    ('TestEarlyOnset', 'Test', 'FEMME', '4 Valley Dr', '400-555-6666', "2002-06-28T00:00:00.000Z");
