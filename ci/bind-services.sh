#!/bin/bash 
cf login -u $CF_USERNAME -p $CF_PASSWORD -a $CF_API -o $CF_ORGANIZATION -s $CF_SPACE --skip-ssl-validation

bounded=false

if  [[ $(cf service service-registry | grep "fortune-teller-fortune-service")  == *"Bound apps"* ]];
then
  echo "already bounded";
  bounded=true
fi


#cf bind-service fortune-teller-fortune-service fortune-db
if  [[ $bounded  == false ]];
then
	cf bind-service fortune-teller-fortune-service service-registry
	cf bind-service fortune-teller-ui service-registry
	#cf bind-service fortune-teller-ui config-server
	cf set-env fortune-teller-fortune-service CF_TARGET https://api.run.pivotal.io
	cf set-env fortune-teller-ui CF_TARGET https://api.run.pivotal.io
	cf restage fortune-teller-fortune-service
	cf restage fortune-teller-ui
fi
