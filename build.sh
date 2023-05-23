#!/bin/bash
git pull
export $(cat .env | xargs)
mvn clean package
#systemctl enable eif-api.service
#systemctl start eif-api.service
systemctl restart eif-api.service
#journalctl -u eif-api.service