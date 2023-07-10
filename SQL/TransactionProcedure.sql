DROP PROCEDURE TransactionProcedure;

DELIMITER $$
CREATE PROCEDURE TransactionProcedure(
    IN p_date TIMESTAMP(6),
    IN p_accountID INT,
    IN p_type ENUM('입금', '출금'),
    IN p_amount BIGINT,
    IN p_location VARCHAR(255)
)
transact_label: BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    IF p_amount <= 0 THEN
        LEAVE transact_label;
    END IF;

    START TRANSACTION;

	UPDATE accounts
    SET account_balance = account_balance + IF(p_type = '입금', p_amount, -p_amount)
    WHERE a_ID = p_accountID;
    
    SET @balance = (SELECT account_balance FROM accounts WHERE a_ID = p_accountID);
    
	IF(@balance < 0) THEN
		LEAVE transact_label;
    END IF;
    
    INSERT INTO transactions (transaction_date, a_id, transaction_type, transaction_amount, transaction_location, balance)
    VALUES (p_date, p_accountID, p_type, p_amount, p_location, @balance);

    COMMIT;
    
END transact_label$$
DELIMITER ;