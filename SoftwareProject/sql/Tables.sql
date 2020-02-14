/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2008                    */
/* Created on:     1/28/2020 11:00:22 PM                        */
/*==============================================================*/

use RoboDB
go

drop table OPERATION
go
drop table PATIENT
go
drop table SURGERY
go
drop table MACHINE
go
drop table ASSISTANT
go
drop table DOCTOR
go
drop table [USER]
go
drop table PROFILE 
go
/*==============================================================*/
/* Table: PROFILE                                               */
/*==============================================================*/
create table PROFILE (
   PROFILEID            int  IDENTITY(1,1)   not null,
   FIRSTNAME            char(30)             not null,
   MIDDLENAME           char(30)             not null,
   LASTNAME             char(30)             not null,
   EMAIL                char(100)            not null,
   PHONE                char(20)             null,
   DATEOFBIRTH          date                 null,
   ADDRESS              char(200)            null,
   BLOODTYPE            char(3)              null,
   constraint PK_PROFILE primary key nonclustered (PROFILEID)
)
go

/*==============================================================*/
/* Table: "USER"                                                */
/*==============================================================*/
create table [USER] (
   USERID               int IDENTITY(1,1)    not null,
   PROFILEID            int                  null,
   USERNAME             char(50)             not null,
   PASSWORD             char(50)             not null,
   ROLE                 char(20)             not null,
   constraint PK_USER primary key nonclustered (USERID),
   constraint FK_USER_HAS_PROFILE foreign key (PROFILEID)
      references PROFILE (PROFILEID)
)
go

/*==============================================================*/
/* Table: DOCTOR                                                */
/*==============================================================*/
create table DOCTOR (
   DOCTORID             int IDENTITY(1,1)    not null,
   USERID               int                  not null,
   SPECIALTY            char(100)            null,
   constraint PK_DOCTOR primary key nonclustered (DOCTORID),
   constraint FK_DOCTOR_IS_A_USER_USER foreign key (USERID)
      references [USER] (USERID)
)
go

/*==============================================================*/
/* Table: ASSISTANT                                             */
/*==============================================================*/
create table ASSISTANT (
   ASSISTANTID          int IDENTITY(1,1)    not null,
   USERID               int                  not null,
   DOCTORID             int                  null,
   CONTRACTTYPE         char(100)            null,
   constraint PK_ASSISTANT primary key nonclustered (ASSISTANTID),
   constraint FK_ASSISTAN_IS_A_USER foreign key (USERID)
      references [USER] (USERID),
   constraint FK_ASSISTAN_WORKS_WIT_DOCTOR foreign key (DOCTORID)
      references DOCTOR (DOCTORID)
)
go

/*==============================================================*/
/* Table: MACHINE                                               */
/*==============================================================*/
create table MACHINE (
   MACHINEID            int  IDENTITY(1,1)   not null,
   ROOMNUMBER           int                  null,
   MACHINESTATE         char(15)             null,
   DATEBOUGHT           date                 null,
   SERIALNUMBER         int                  null,
   constraint PK_MACHINE primary key nonclustered (MACHINEID)
)
go

/*==============================================================*/
/* Table: SURGERY                                               */
/*==============================================================*/
create table SURGERY (
   SURGERYID            int  IDENTITY(1,1)   not null,
   NAME                 char(100)            null,
   DESCRIPTION          char(500)            null,
   DURATION             char(10)             null,
   constraint PK_SURGERY primary key nonclustered (SURGERYID)
)
go


/*==============================================================*/
/* Table: PATIENT                                               */
/*==============================================================*/
create table PATIENT (
   PATIENTID            int IDENTITY(1,1)    not null,
   PROFILEID            int                  not null,
   PRESSURE             char(10)             null,
   TEMPERATURE          float                null,
   PULSE                int                  null,
   OXYGEN               int                  null,
   NORMALPRESSURE       char(10)             null,
   NORMALTEMPERATURE    float                null,
   NORMALPULSE          int                  null,
   NORMALOXYGEN         int                  null,
   WEIGHT               int                  null,
   HEIGHT               int                  null,
   constraint PK_PATIENT primary key nonclustered (PATIENTID),
   constraint FK_PATIENT_HAS_PROFI_PROFILE foreign key (PROFILEID)
      references PROFILE (PROFILEID)
)
go

/*==============================================================*/
/* Table: OPERATION                                             */
/*==============================================================*/
create table OPERATION (
   DOCTORID             int                  not null,
   PATIENTID            int                  not null,
   APPOINTMENTDATE      datetime             not null,
   MACHINEID            int                  null,
   SURGERYID            int                  not null,
   REGISTERATIONDATE    datetime             null,
   STARTTIME            datetime             null,
   ENDTIME              datetime             null,
   constraint PK_OPERATION primary key nonclustered (PATIENTID, DOCTORID, APPOINTMENTDATE),
   constraint FK_OPERATIO_HAS_MACHI_MACHINE foreign key (MACHINEID)
      references MACHINE (MACHINEID),
   constraint FK_OPERATIO_IS_A_SURG_SURGERY foreign key (SURGERYID)
      references SURGERY (SURGERYID),
   constraint FK_OPERATIO_WITH_DOCTOR foreign key (DOCTORID)
      references DOCTOR (DOCTORID),
   constraint FK_PATIENT foreign key (PATIENTID)
      references PATIENT (PATIENTID)
)
go
