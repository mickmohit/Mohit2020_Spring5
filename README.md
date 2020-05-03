# Mohit2020_Spring5

###
#Mongo Db run scripts

in one cmd---start DB server
"C:\Program Files\MongoDB\Server\4.2\bin\mongod.exe" --dbpath="C:\{yourDirectory}\data\db"

in 2nd cmd--connect to db
"C:\Program Files\MongoDB\Server\4.2\bin\mongo.exe"



#kafka run scripts

run zookeeper 
---C:\Softwares\kafka_2.13-2.5.0\bin\windows>zookeeper-server-start.bat C:\Softwares\kafka_2.13-2.5.0\config\zookeeper.properties

run kafka server
---C:\Softwares\kafka_2.13-2.5.0\bin\windows>kafka-server-start.bat C:\Softwares\kafka_2.13-2.5.0\config\server.properties

create topic in kafka
---C:\Softwares\kafka_2.13-2.5.0\bin\windows>kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic {topic_name}

run producer/consumer from application.

to check consumer group created
---C:\Softwares\kafka_2.13-2.5.0\bin\windows>kafka-consumer-groups --bootstrap-server localhost:9092 --describe --all-groups
