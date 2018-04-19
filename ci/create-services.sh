#!/bin/bash 



cf login -u $CF_USERNAME -p $CF_PASSWORD -a $CF_API -o $CF_ORGANIZATION -s $CF_SPACE --skip-ssl-validation
#cf unbind-service fortune-teller-fortune-service service-registry
#cf unbind-service fortune-teller-ui service-registry
#cf delete-service -f service-registry

#cf unbind-service fortune-teller-ui config-server
#cf delete-service -f config-server

service_registry_created=false

if  [[ $(cf service service-registry | grep Status)  == *"create succeeded"* ]];
then
  echo "Registry already created";
  service_registry_created=true
fi

if  [[ $service_registry_created  == false ]];
then
	cf create-service p-service-registry standard service-registry
	while [[ $(cf service service-registry | grep Status)  == *"progress"* ]]
	do
	sleep 5
  	echo "Registry creation in progress";
	done
	echo "Registry created"
fi

config_server_created=false

if  [[ $(cf service config-server | grep Status)  == *"create succeeded"* ]];
then
  echo "Config Server already created";
  config_server_created=true
fi

if  [[ $config_server_created  == false ]];
then
	cf create-service -c '{"git": { "uri": "https://github.com/ebornier-pivotal/cnt-fortune-teller-config.git" }}' p-config-server standard config-server
	while [[ $(cf service config-server | grep Status)  == *"progress"* ]]
	do
	sleep 5
        echo "Circuit breaker creation in progress";
	done
	echo "Circuit breaker created"
fi


circuit_breaker_created=false

if  [[ $(cf service cnt-circuit-breaker | grep Status)  == *"create succeeded"* ]];
then
  echo "Circuit Breaker already created";
  circuit_breaker_created=true
fi

if  [[ $circuit_breaker_created  == false ]];
then
        cf create-service  p-circuit-breaker-dashboard  standard circuit-breaker
        while [[ $(cf service circuit-breaker | grep Status)  == *"progress"* ]]
        do
        sleep 5     
        echo "Config server creation in progress";
        done
        echo "Config server created"
fi


cf add-network-policy fortune-teller-ui  --destination-app fortune-teller-fortune-service --protocol tcp --port 8080