#!/bin/sh
NOHUP_FILE=./digi-sign.pid

kill -9 `cat $NOHUP_FILE`
echo "Stopped ..."