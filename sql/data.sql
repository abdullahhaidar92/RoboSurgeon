use RoboDB
go

INSERT INTO [dbo].[USER]
           ([USERNAME]
           ,[PASSWORD]
           ,[ROLE])
     VALUES
	 ('Surgeon','Robo','Admin')
go

INSERT INTO [dbo].[PROFILE]
           ([FIRSTNAME]
           ,[MIDDLENAME]
           ,[LASTNAME]
           ,[EMAIL]
           ,[PHONE]
           ,[DATEOFBIRTH]
           ,[ADDRESS]
           ,[BLOODTYPE])
     VALUES
		   ('Noura', 'Amine', 'Joudieh','nourajoudieh@gmail.com','76654123','1998-08-21', 'Lebanon', 'A+'),
		   ('Jad', 'Ghayth', 'Rizk','JadRizk@gmail.com','71852963','1995-10-21', 'Rashaya', 'AB-'),
           	   ('Yara', 'Fadi', 'Ghosn','yaraGhosn@gmail.com','78655236','1995-10-25', 'Lebanon', 'AB+'),
		   ('Zeina', 'Amine', 'Joudieh','zeinajoudieh@gmail.com','75121236','1997-03-07', 'Tripoli', 'A+'),
		   ('Hadi', 'Amine', 'Joudieh','hadijoudieh@gmail.com','71458236','2002-04-18', 'Jounieh', 'A+'),
		   ('Nada', 'Ayman', 'Jabr','nadaJabr@gmail.com','79852963','1998-07-18', 'Baalbak', 'O-'),
		   ('Omar', 'Kamal', 'Haddad','OmarHaddad@gmail.com','72984365','1993-08-21', 'Jnoub', 'B+'),
		   ('Ali', 'Ahmad', 'Ghosn','AliGhosn@gmail.com','03885741','1986-09-18', 'Tripoli', 'AB+'),
		   ('Najwa', 'Amine', 'Joudieh','najwajoudieh@gmail.com','78451236','1998-08-21', 'Jbeil', 'A+'),
		   ('Abdullah', 'Hussein', 'Haidar','abdullah@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Ali', 'Hussein', 'Haidar','ali@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Nada', 'Hussein', 'Haidar','nada@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Hiba', 'Hussein', 'Haidar','hiba@gmail.com','78451236','1998-08-21', 'Beirut', 'A+')
		 

go

INSERT INTO [dbo].[USER]
           ([PROFILEID]
           ,[USERNAME]
           ,[PASSWORD]
           ,[ROLE])
     VALUES
  (1, 'JoudiehNoura','123','Doctor'),
 (2, 'RizkJad','123','Doctor'),
           (3, 'GhosnYara','123','Assistant'),
 (4, 'JoudiehZeina','123','Assistant'),	
		  (5, 'JoudiehHadi','123','Assistant'),
		   (6, 'AymanNada','123','Assitant'),
		   (7, 'KamalOmar','123','Assistant'),
		   (8, 'AhmadAli','123','Doctor'),
		     (9, 'JoudiehNajwa','123','Doctor')
		 
		   
go

INSERT INTO [dbo].[DOCTOR]
           ([USERID]
           ,[SPECIALTY])
     VALUES
           (2, 'NeuroSciences'),
		   (3, 'TumorScience'),
		   (9, 'Cancerology'),
		   (10, 'Brain')
go

INSERT INTO [dbo].[ASSISTANT]
           ([USERID]
           ,[DOCTORID]
           ,[CONTRACTTYPE])
     VALUES
          (5,1,'FULL TIME'),
		  (4,2, 'FULL TIME'),
		  (6,3,'PART TIME'),
		  (8,4,'PART TIME')
go

			 
			 
/*==============================================================*/
/* Table: MACHINE                                               */
/*==============================================================*/
insert into MACHINE (
   ROOMNUMBER,
   MACHINESTATE,
   DATEBOUGHT,
   SERIALNUMBER)
			 values(1,'ACTIVE','2019-3-3',1234567),
			 (2,'ACTIVE','2019-3-3',1234563),
			 (3,'ACTIVE','2019-3-3',1234565),
			 (4,'ACTIVE','2019-3-3',1234561)
go
			 
			 

/*==============================================================*/
/* Table: SURGERY                                               */
/*==============================================================*/
insert into SURGERY (
   NAME,
   DESCRIPTION,
   DURATION )
			 values('SURGEY ONE','Hello Im Surgery One','20 hours'),
			 ('SURGEY TWO','Hello Im Surgery TWO','20 hours'),
			 ('SURGEY THREE','Hello Im Surgery THREE','20 hours'),
			 ('SURGEY FOUR','Hello Im Surgery FOUR','20 hours'),
			 ('SURGEY FIVE','Hello Im Surgery FIVE','20 hours'),
			 ('SURGEY SIX','Hello Im Surgery SIX','20 hours')
go


/*==============================================================*/
/* Table: PATIENT                                               */
/*==============================================================*/
insert into PATIENT (
   PROFILEID ,
   PRESSURE          ,
   TEMPERATURE       ,
   PULSE             ,
   OXYGEN            ,
   NORMALPRESSURE    ,
   NORMALTEMPERATURE ,
   NORMALPULSE       ,
   NORMALOXYGEN      ,
   WEIGHT            ,
   HEIGHT            )
   values(10,0,0,0,0,0,0,0,0,0,0),
			 (11,0,0,0,0,0,0,0,0,0,0),
			 (12,0,0,0,0,0,0,0,0,0,0),
			 (13,0,0,0,0,0,0,0,0,0,0)

go

