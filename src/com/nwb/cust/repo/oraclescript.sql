BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE Transactions';
EXCEPTION
   WHEN OTHERS THEN
      NULL; -- Ignore exceptions (e.g., table doesn't exist)
END;
/

CREATE TABLE Transactions (
    transactionId VARCHAR2(255) NOT NULL PRIMARY KEY,
    senderAccountId VARCHAR2(255) NOT NULL,
    receiverAccountId VARCHAR2(255) NOT NULL,
    transactionType VARCHAR2(50) NOT NULL,
    amount NUMBER(15, 2) NOT NULL CHECK (amount > 0),
    transactionTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, -- Properly using TIMESTAMP datatype
    transactionStatus VARCHAR2(50) NOT NULL,
    description VARCHAR2(255) NOT NULL,
    transactionMode VARCHAR2(500) NOT NULL,
    currency VARCHAR2(200) NOT NULL
);

-- Insert multiple rows into Transactions table
INSERT INTO Transactions (transactionId, senderAccountId, receiverAccountId, transactionType, amount, transactionTime, transactionStatus, description, transactionMode, currency) VALUES 
('TXN003', 'ACC003', 'ACC004', 'TRANSFER', 1250.00, TIMESTAMP '2024-08-03 09:00:00', 'SUCCESS', 'Salary Payment', 'BANK_TRANSFER', 'INR');
INSERT INTO Transactions (transactionId, senderAccountId, receiverAccountId, transactionType, amount, transactionTime, transactionStatus, description, transactionMode, currency) VALUES 
('TXN004', 'ACC004', 'ACC005', 'DEBIT', 340.00, TIMESTAMP '2024-08-04 14:30:00', 'FAILED', 'Groceries', 'DEBIT_CARD', 'INR');
INSERT INTO Transactions (transactionId, senderAccountId, receiverAccountId, transactionType, amount, transactionTime, transactionStatus, description, transactionMode, currency) VALUES 
('TXN005', 'ACC005', 'ACC006', 'CREDIT', 1000.00, TIMESTAMP '2024-08-05 08:00:00', 'SUCCESS', 'Freelance Payment', 'CREDIT_CARD', 'INR');
INSERT INTO Transactions (transactionId, senderAccountId, receiverAccountId, transactionType, amount, transactionTime, transactionStatus, description, transactionMode, currency) VALUES 
('TXN006', 'ACC006', 'ACC007', 'TRANSFER', 400.00, TIMESTAMP '2024-08-06 16:45:00', 'SUCCESS', 'Gift', 'BANK_TRANSFER', 'INR');
INSERT INTO Transactions (transactionId, senderAccountId, receiverAccountId, transactionType, amount, transactionTime, transactionStatus, description, transactionMode, currency) VALUES 
('TXN007', 'ACC007', 'ACC008', 'CREDIT', 80.50, TIMESTAMP '2024-08-07 12:30:00', 'SUCCESS', 'Lunch', 'CREDIT_CARD', 'INR');

