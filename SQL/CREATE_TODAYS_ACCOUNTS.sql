DROP PROCEDURE CREATE_TODAYS_ACCOUNTS;

DELIMITER $$
CREATE PROCEDURE CREATE_TODAYS_ACCOUNTS()
BEGIN
	DROP TABLE IF EXISTS TODAYS_REPAYMENT_ACCOUNTS;
	SET @today := DAYOFMONTH(CURDATE());

	CREATE TABLE TODAYS_REPAYMENT_ACCOUNTS AS
	SELECT lc_id, a_id,
		CAST(
			REPLACE(
				REPLACE(
					SUBSTRING_INDEX(
						SUBSTRING_INDEX(repayment_amount, ',', 
							FLOOR(TIMESTAMPDIFF(MONTH, DATE_FORMAT(start_date, '%Y-%m-%d 00:00:00.000000'), NOW(6)))
                            ),',', -1
						), '[', ''
					), ']', ''
				) AS SIGNED
			) + delinquent_amount AS todays_repayment_amount,
		0 AS is_completed
	FROM loanContracts
	WHERE payment_date = @today AND completed = 0;

	ALTER TABLE TODAYS_REPAYMENT_ACCOUNTS ADD PRIMARY KEY (lc_id);
END $$
DELIMITER ;

