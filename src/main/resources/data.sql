DELETE FROM patient;
ALTER TABLE patient AUTO_INCREMENT = 1;

INSERT INTO patient (nom, prenom, genre, rue, telephone)
VALUES
    ('TestNone', 'Test', 'FEMME', '1 Brookside St', '100-222-3333'),
    ('TestBorderline', 'Test', 'HOMME', '2 High St', '200-333-4444'),
    ('TestInDanger', 'Test', 'HOMME', '3 Club Road', '300-444-5555'),
    ('TestEarlyOnset', 'Test', 'FEMME', '4 Valley Dr', '400-555-6666');
