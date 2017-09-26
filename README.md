# ACACIA ontology

Microservice for the general usage of the ACACIA Ontology. Receives user-input via REST, queries an OWL ontology with SPARQL and outputs data as JSON. Uses Websockets to control the sensors and for notification alerts.

Built with [Dropwizard framework](www.dropwizard.io/) and [Apache Jena](https://jena.apache.org/).

## [API Documentation](doc/README.md)

## Building, Running and Deploying

### Building

`mvn package`

Builds both a jar for local testing and a Debian package for deployment.

### Running

`java -jar target/ontology-2.1.0.jar server dev.yml`

Runs locally in development mode.

### Deploying
   
Copy `target/ontology-2.1.0.deb` into a Debian-based server and install with:

`dpkg -i ontology-2.1.0.deb`

This will deploy the microservice into a system service and run it in production mode.

## License and authorship

ACACIA ontology code licensed under [GNU AGPL v3](/LICENSE.md) or later. Copyright belongs to:

- [Jorge Calado](https://github.com/jms-calado) (ontology, SPARQL queries and updates, adapted classes used for the Jackson serialization),
- [David Ludovino](https://github.com/dllud) (initial REST microservice),
- [aquasmart-training](http://git-gris.uninova.pt/Sudeep/aquasmart-training) (used as ground work and original code).

