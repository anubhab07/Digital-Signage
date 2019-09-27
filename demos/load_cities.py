import pandas as pd
import getpass
import mysql.connector

df = pd.read_csv("cities.csv")
print("data read. Num rows : ", len(df))

cnx = mysql.connector.connect(
  host="rm-gs5697xbivin2b98ovo.mysql.singapore.rds.aliyuncs.com",
  user=getpass.getpass("Enter SQL Server user_id : "),
  passwd=getpass.getpass("Enter SQL Server password : "),
  database='digi_sign'
)

print("Connected ")

insert_query = ''' INSERT INTO city 
               (city_id, country, state, city) 
               VALUES (%s, %s, %s, %s) '''
i = 1
cursor = cnx.cursor()
for row in df.itertuples(index=False):
    try:
        cursor.execute(insert_query , (i, row.country, row.state, row.name))
    except Exception as ex:
        print(ex)
        print(row)

    if (i%100 == 0): 
        cnx.commit()
        print(i)
    i += 1

cnx.close()
print("done")