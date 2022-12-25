#!/bin/sh
echo 'Maven start packaging.....'
sleep 1s
mvn package
echo 'Maven ended packaging.....'
echo 'Now app will start working :)'
sleep 5s
cd target
java -jar LevchenkoAPP_about_class.jar
echo 'Scroll up and look how is this app working step by step.'
read -p "Press [Enter] to close this window"

