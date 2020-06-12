# Mohit2020_Spring5


# Mongo Db run scripts

in one cmd---start DB server
"C:\Program Files\MongoDB\Server\4.2\bin\mongod.exe" --dbpath="C:\{yourDirectory}\data\db"

in 2nd cmd--connect to db
"C:\Program Files\MongoDB\Server\4.2\bin\mongo.exe"



# kafka run scripts

run zookeeper 
---C:\Softwares\kafka_2.13-2.5.0\bin\windows>zookeeper-server-start.bat C:\Softwares\kafka_2.13-2.5.0\config\zookeeper.properties

run kafka server
---C:\Softwares\kafka_2.13-2.5.0\bin\windows>kafka-server-start.bat C:\Softwares\kafka_2.13-2.5.0\config\server.properties

create topic in kafka
---C:\Softwares\kafka_2.13-2.5.0\bin\windows>kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic {topic_name}

send some message
--C:\Softwares\kafka_2.13-2.5.0\bin\windows>kafka-console-producer.bat --broker-list localhost:9092 --topic test 

consume the message
--C:\Softwares\kafka_2.13-2.5.0\bin\windows>kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning   

run producer/consumer from application.

to check consumer group created
---C:\Softwares\kafka_2.13-2.5.0\bin\windows>kafka-consumer-groups --bootstrap-server localhost:9092 --describe --all-groups

Execute below command to test the Zookeeper and Kafka broker registration to the Zookeeper server.
--C:\Softwares\kafka_2.13-2.5.0\bin\windows>zookeeper-shell.bat localhost:2181 ls /brokers/ids  

For multi node cluster
Execute below commands for 3 brokers
--kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 3 --partitions 1 --topic Multibrokerapplication

Execute below command for details of brokers 
--kafka-topics.bat --describe --zookeeper localhost:2181 --topic Multibrokerapplication
