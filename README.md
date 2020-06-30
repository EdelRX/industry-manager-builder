# industry-manager-builder
Spring Boot application with  REST interface and  JMS queue sender service.

Petitions arrive by the REST controller to add an industry object to a database via en external service. A JMS sender with build a data message and send it to the queue for another service 
to pick up.
