DROP TABLE IF EXISTS SUBURB_POSTCODE;

CREATE TABLE SUBURB_POSTCODE
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    postCode        VARCHAR(50) NOT NULL,
    state            VARCHAR(100) NOT NULL,
    suburb VARCHAR(100) NOT NULL
);

INSERT INTO SUBURB_POSTCODE (postCode, state, suburb) 
    VALUES ('3000', 'VIC','MELBOURNE'), ('3024', 'VIC', 'WYNDHAM VALE');