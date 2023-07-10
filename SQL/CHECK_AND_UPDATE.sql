DROP PROCEDURE CHECK_AND_UPDATE;

DELIMITER $$
CREATE PROCEDURE CHECK_AND_UPDATE(
    IN p_loanContractId INT,
    IN p_accountId INT,
    IN p_repaymentAmount INT,
    IN p_isCompleted BIT
)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
	
    IF p_isCompleted = 0 AND p_repaymentAmount <= (SELECT account_balance FROM accounts WHERE a_id = p_accountId) THEN
		UPDATE loanContracts
		SET balance = balance - p_repaymentAmount, delinquent_amount = 0
		WHERE lc_id = p_loanContractId;

		CALL TransactionProcedure(NOW(6), p_accountId, '출금', p_repaymentAmount, '대출상환');

		INSERT loanRepayments
		(lc_id, a_id, trade_datetime, trade_amount)
		VALUES (p_loanContractId, p_accountId, NOW(6), p_repaymentAmount);

		UPDATE TODAYS_REPAYMENT_ACCOUNTS
		SET is_completed = 1
		WHERE lc_id = p_loanContractId;
        
        UPDATE loanContracts
        SET completed = 1
        WHERE lc_id = p_loanContractId and balance <= 0;
	END IF;

	COMMIT;
END$$
DELIMITER ;

