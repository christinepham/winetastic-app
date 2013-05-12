.mode columns
.headers on

CREATE TABLE users (
  Email_Address TEXT PRIMARY KEY,
  Password_Hash TEXT,
  Date_Created TEXT
);

CREATE TABLE wines (
  Wine_ID NUMERIC PRIMARY KEY,
  Color TEXT,
  Country TEXT,
  Grape_Variety TEXT,
  Region TEXT,
  Vineyard TEXT,
  Year TEXT,
  Wine_Name TEXT,
  Price TEXT,
  List_of_Descriptors TEXT,
  URL_of_Photo TEXT,
  User_Entered TEXT
);

CREATE TABLE events (
  Event_ID NUMERIC PRIMARY KEY,
  Start_Date TEXT,
  End_Date TEXT,
  Start_Time TEXT,
  End_Time TEXT,
  Type TEXT,
  Repeat_Frequency TEXT,
  Title TEXT,
  Description TEXT,
  Duration_Days NUMERIC,
  Event_Occurance_Count NUMERIC,
  Category TEXT
);

CREATE TABLE my_wines (
  Email_Address TEXT,
  Wine_ID NUMERIC
);

CREATE TABLE user_calendar (
  Email_Address TEXT,
  Event_ID NUMERIC
);

CREATE TABLE wine_descriptors (
  Wine_ID NUMERIC,
  Season_Winter NUMERIC,
  Season_Spring NUMERIC,
  Season_Summer NUMERIC,
  Season_Fall NUMERIC,
  Light_Bodied NUMERIC,
  Medium_Bodied NUMERIC,
  Full_Bodied NUMERIC,
  Young NUMERIC,
  Old NUMERIC,
  Acidic NUMERIC,
  Acrid NUMERIC,
  Ageworthy NUMERIC,
  Aggressive NUMERIC,
  Alcoholic NUMERIC,
  Aromatic NUMERIC,
  Astringent NUMERIC,
  Austere NUMERIC,
  Awkward NUMERIC,
  Winery TEXT,
  Brand TEXT
);

CREATE TABLE wineries (
  Winery_ID NUMERIC PRIMARY KEY,
  Wine_ID NUMERIC,
  Name TEXT,
  Open_Mondays NUMERIC,
  Open_Tuesdays NUMERIC,
  Open_Wednesdays NUMERIC,
  Open_Thursdays NUMERIC,
  Open_Fridays NUMERIC,
  Open_Saturdays NUMERIC,
  Open_Sundays NUMERIC
);