import mysql.connector



conn=mysql.connector.connect(
    host="localhost",
    user="root",
    password="Vd&44044404",
    database="signifydb"
)

cursor=conn.cursor()

insertQ="""insert into table Employees values(1,'Sreya',21)"""

cursor.execute(insertQ)