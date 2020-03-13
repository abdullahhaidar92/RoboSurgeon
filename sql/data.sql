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
		   ('Hiba', 'Hussein', 'Haidar','hiba@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Farah', 'Al', 'Wardani','farah@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Chris', 'Ahmad', 'Hemthworth','chris@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Adam', 'Ahmad', 'Levine','adam@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Hassan', 'Ali', 'Haidar','hassan@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Yahia', 'Ali', 'Assaf','yahia@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Khaled', 'Hadi', 'Jaafar','khaled@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Hadi', 'Zein', 'Dein','hadi@gmail.com','78451236','1998-08-21', 'Beirut', 'A+'),
		   ('Issa', 'Ayman', 'Jabr','issa@gmail.com','78451236','1998-08-21', 'Beirut', 'A+')
		 

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
 (9, 'JoudiehNajwa','123','Doctor'),
 (10, 'HaidarAbdullah','123','Doctor'),
 (11, 'HaidarAli','123','Doctor'),
 (12, 'HaidarHiba','123','Doctor'),
 (13, 'HaidarNada','123','Doctor'),
 (14, 'WardaniFarah','123','Doctor'),
 (15, 'HemthorthChris','123','Doctor')
	go

INSERT INTO [dbo].[DOCTOR]
           ([USERID]
           ,[SPECIALTY])
     VALUES
           (2, 'NeuroSciences'),
		   (3, 'TumorScience'),
		   (9, 'Cancerology'),
		   (10, 'Brain'),
		   (11, 'TumorScience'),
		   (12, 'Cancerology'),
		   (13, 'NeuroSciences'),
		   (14, 'Brain'),
		   (15, 'TumorScience')
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
			 values('SURGERY ONE','Surgery One Description','20 hours'),
			 ('SURGERY TWO','Surgery TWO Description','20 hours'),
			 ('SURGERY THREE','Surgery THREE Description','20 hours'),
			 ('SURGERY FOUR','Surgery FOUR Description','20 hours'),
			 ('SURGERY FIVE','Surgery FIVE Description','20 hours'),
			 ('SURGERY SIX','Surgery SIX Description','20 hours')
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
   values(16,120,37,60,75,120,37,60,75,60,170),
   (17,120,37,60,75,120,37,60,75,60,170),
   (18,120,37,60,75,120,37,60,75,60,170),
   (19,120,37,60,75,120,37,60,75,60,170),
   (20,120,37,60,75,120,37,60,75,60,170),
   (21,120,37,60,75,120,37,60,75,60,170)
			 

go
			 
/*==============================================================*/
/* Table: OPERATION                                             */
/*==============================================================*/
insert into OPERATION (
   DOCTORID            ,
   PATIENTID           ,
   APPOINTMENTDATE     ,
   MACHINEID           ,
   SURGERYID           ,
   MAXRADIATION		   ,
   MAXTIMER			   ,		
   REGISTERATIONDATE   )		
			 
   values (1,1,"2020-5-1 3:30:00",1,1,100,12,"2020-2-1 3:30:00"),
		  (2,2,"2020-5-2 3:30:00",2,2,100,12,"2020-2-2 3:30:00"),
		  (2,3,"2020-5-3 3:30:00",3,3,100,12,"2020-2-3 3:30:00"),
		  (2,4,"2020-5-4 3:30:00",2,4,100,12,"2020-2-4 3:30:00"),
		  (2,5,"2020-5-5 3:30:00",3,5,100,12,"2020-2-5 3:30:00"),
		  (2,1,"2020-5-6 3:30:00",3,5,100,12,"2020-2-6 3:30:00"),
		  (2,2,"2020-5-7 3:30:00",3,5,100,12,"2020-2-7 3:30:00"),
		  (2,3,"2020-5-8 3:30:00",3,5,100,12,"2020-2-8 3:30:00"),
		  (2,4,"2020-5-9 3:30:00",3,5,100,12,"2020-2-9 3:30:00"),
		  (2,5,"2020-5-10 3:30:00",3,5,100,12,"2020-2-10 3:30:00"),
		  (2,1,"2020-5-11 3:30:00",3,5,100,12,"2020-2-11 3:30:00")
go
		 
			 
			 
			 

