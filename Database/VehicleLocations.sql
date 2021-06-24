-- --------------------------------------------------------------------------------
-- Name:		Neina Cichon
-- Class:		IT-161 Java 1
-- Abstract:	Locations DB for Vehicle Rental Final
-- --------------------------------------------------------------------------------

-- --------------------------------------------------------------------------------
-- Options
-- --------------------------------------------------------------------------------
USE dbHCM		            -- Don't work in master
SET NOCOUNT ON				-- Report only errors

-- ----------------------------------------------------------------------
-- Drops
-- ----------------------------------------------------------------------
IF OBJECT_ID( 'TLocations' )						IS NOT NULL		DROP TABLE TLocations

GO

-- ----------------------------------------------------------------------
-- Tables
-- ----------------------------------------------------------------------
CREATE TABLE TLocations
(
	intLocationID                    INTEGER        NOT NULL,
	strLocationName                  VARCHAR(50)    NOT NULL,
	strAddress                       VARCHAR(50)    NOT NULL,
	strCity				             VARCHAR(50)    NOT NULL,
	strState						 VARCHAR(50)	NOT NULL,
	strZip		                     VARCHAR(50)    NOT NULL,
	CONSTRAINT TLocations_PK PRIMARY KEY CLUSTERED ( intLocationID ))

-- Locations
INSERT INTO TLocations( intLocationID, strLocationName, strAddress, strCity, strState, strZip )
VALUES ( 1, 'Downtown', '2010 Vine Street', 'Cinti', 'OH', '45201' )

INSERT INTO TLocations( intLocationID, strLocationName, strAddress, strCity, strState, strZip )
VALUES( 2, 'Hamilton', '525 Hamilton St', 'Hamilton', 'OH', '45213' )

INSERT INTO TLocations( intLocationID, strLocationName, strAddress, strCity, strState, strZip )
VALUES( 3, 'Loveland', '52 Waterford Ave', 'Loveland', 'OH', '45140' )

INSERT INTO TLocations( intLocationID, strLocationName, strAddress, strCity, strState, strZip )
VALUES( 4, 'Blue Ash', '2514 Cornell Rd', 'Blue Ash', 'OH', '45244' )

GO

-- ----------------------------------------------------------------------
-- Testing
-- ----------------------------------------------------------------------

--SELECT * FROM TLocations order by strLocationName
