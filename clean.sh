cf delete -f fortune-teller-ui
cf delete -f fortune-teller-fortune-service
cf delete-service -f circuit-breaker
cf delete-service -f config-server
cf delete-service -f mongodb
cf delete-service -f service-registry
