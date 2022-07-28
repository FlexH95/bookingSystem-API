DROP TABLE lect_TB;

CREATE TABLE lect_TB 
( 
    lecturerName    VARCHAR2 (100)	 NOT NULL,
    placeName       VARCHAR2 (100)   NOT NULL,
    capCnt            NUMBER(3)          NOT NULL,
    dateTimeStamp VARCHAR2 (12)     DEFAULT TO_CHAR(SYSDATE,'YYYYMMDDHH24MI'),
    decs               VARCHAR2 (100),
    CONSTRAINT lect_pk PRIMARY KEY (lecturerName, placeName, capCnt, dateTimeStamp)
);

DROP TABLE lect_Emp_TB;

CREATE TABLE lect_Emp_TB 
( 
    lecturerName  VARCHAR2 (100)	NOT NULL,
    empNo          VARCHAR2 (5)   NOT NULL,
    CONSTRAINT lect_Emp_pk PRIMARY KEY (lecturerName,empNo)
);
