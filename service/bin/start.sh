#!/bin/sh
NOHUP_LOG=./digi-sign.log
NOHUP_FILE=./digi-sign.pid

git pull origin master
./mvnw clean package
nohup java -jar target/DigitalSignage-0.0.1-SNAPSHOT.jar > $NOHUP_LOG 2>&1 &
echo $! > $NOHUP_FILE
echo "Started .."