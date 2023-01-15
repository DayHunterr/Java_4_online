#!/bin/sh
echo 'Maven start testing.....'
sleep 1s
mvn clean test
read -p "Press [Enter] to close this window"

