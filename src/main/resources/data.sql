CREATE TABLE IF NOT EXISTS vehicle (
                                       id INT AUTO_INCREMENT PRIMARY KEY,
                                       vrn VARCHAR(20) NOT NULL,
    make VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    mfg_year INT NOT NULL,
    fuel_type VARCHAR(50) NOT NULL
    );


INSERT INTO Vehicle (vrn, make, model, mfg_year, fuel_type) VALUES ('XYZ123', 'Toyota', 'Camry', 2022, 'Petrol');
INSERT INTO Vehicle (vrn, make, model, mfg_year, fuel_type) VALUES ('TOY223', 'Toyota', 'Corolla', 2021, 'Petrol');
INSERT INTO Vehicle (vrn, make, model, mfg_year, fuel_type) VALUES ('ABC456', 'Honda', 'Accord', 2021, 'Petrol');
INSERT INTO Vehicle (vrn, make, model, mfg_year, fuel_type) VALUES ('HON123', 'Honda', 'Civic', 2023, 'Hybrid');
INSERT INTO Vehicle (vrn, make, model, mfg_year, fuel_type) VALUES ('TES111', 'Tesla', 'Model 3', 2023, 'Electric');
INSERT INTO Vehicle (vrn, make, model, mfg_year, fuel_type) VALUES ('TES222', 'Tesla', 'Model S', 2022, 'Electric');
INSERT INTO Vehicle (vrn, make, model, mfg_year, fuel_type) VALUES ('BMW123', 'BMW', '3 Series', 2022, 'Diesel');
INSERT INTO Vehicle (vrn, make, model, mfg_year, fuel_type) VALUES ('BMW234', 'BMW', '5 Series', 2023, 'Diesel');