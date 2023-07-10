DROP PROCEDURE CHECK_EVERY_THIRTY_MINUTES;

DELIMITER $$
CREATE PROCEDURE CHECK_EVERY_THIRTY_MINUTES(
	IN isFinal BIT
)
BEGIN
    DECLARE loanContractId INT;
    DECLARE accountId INT;
    DECLARE todaysRepaymentAmount INT;
    DECLARE isCompleted BIT;
	DECLARE done INT DEFAULT 0;
    DECLARE cur CURSOR FOR SELECT lc_id, a_id, todays_repayment_amount, is_completed FROM TODAYS_REPAYMENT_ACCOUNTS;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

	OPEN cur;
    read_loop: LOOP
		FETCH cur INTO loanContractId, accountId, todaysRepaymentAmount, isCompleted;
        IF done THEN
			LEAVE read_loop;
		END IF;
        If isFinal = 0 THEN
			CALL CHECK_AND_UPDATE(loanContractId, accountId, todaysRepaymentAmount, isCompleted);
		ELSE
			CALL CHECK_AND_UPDATE_FINAL(loanContractId, accountId, todaysRepaymentAmount, isCompleted);
        END IF;    
	END LOOP;
	CLOSE cur;
END$$
DELIMITER ;

