-- 09:00, create today's accounts
DELIMITER $$
CREATE EVENT IF NOT EXISTS start_event
ON SCHEDULE
  EVERY 1 DAY
  STARTS CURRENT_DATE + INTERVAL 9 HOUR
DO
    CALL CREATE_TODAYS_ACCOUNTS();
DELIMITER ;


-- 10:00 ~ 23:40, check every thirty minutes 
DELIMITER $$
CREATE EVENT mid_event
  ON SCHEDULE
    EVERY 30 MINUTE
    STARTS CURRENT_DATE + INTERVAL 10 HOUR
    ENDS CURRENT_DATE + INTERVAL 23 HOUR + INTERVAL 40 MINUTE	
  DO
      CALL CHECK_EVERY_THIRTY_MINUTES(0);
DELIMITER ;


-- 00:00, check every thirty minutes final
DELIMITER $$
CREATE EVENT end_event
  ON SCHEDULE
    EVERY 1 DAY
    STARTS CURRENT_DATE
  DO
      CALL CHECK_EVERY_THIRTY_MINUTES(1);
DELIMITER ;





SELECT @@event_scheduler;
SHOW EVENTS;

DELIMITER $$
CREATE EVENT TEST4
  ON SCHEDULE
    EVERY 1 SECOND
  DO
	CALL CHECK_EVERY_THIRTY_MINUTES(0);
DELIMITER ;

DROP EVENT CHECK_EVERY_THIRTY_MINUTES;
DROP EVENT CHECK_EVERY_THIRTY_MINUTES_FINAL;
DROP EVENT CREATE_TODAYS_ACCOUNTS;
DROP EVENT TEST2;
DROP EVENT TEST3;
DROP EVENT TEST4;





SELECT * FROM loanContracts;