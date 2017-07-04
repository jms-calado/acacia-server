# ACACIA ontology

Microservice for the general usage of the ACACIA Ontology. Receives user-input via REST, queries an OWL ontology with SPARQL and outputs data as JSON.

Built with [Dropwizard framework](www.dropwizard.io/) and [Apache Jena](https://jena.apache.org/).

## API


### Find
  Lists the ontology individuals, from the class "user", that contain the exact "name" property.

* **URL**  

  /find/:user/:name

* **Method:**

  `GET`

* **URL Params**

   **Required:**

   `user:` `Student` or `Teacher`
   
   `name:[string]` String with the exact name to be found (spaces replaced by `%20`).

* **Data Params**

   None

* **Success Response:**

  * **Code:** 200  
    **Content:** JSON arrays with each individual of the queried class.

		[
		    {
		        "Student": "Student_123"
		    }
		]

* **Error Response:**

  * **Code:** 400 BAD REQUEST  
    **Content:**

		{
		  "errors": [
			"Error message."
		  ]
		}

* **Sample Call:**

  `curl -s https://ontology.acacia.red:5904/find/Student/John%20Doe`
  
### List
  Lists the available ontology individuals of the following classes:
	Student
	Teacher
	Admin
	Annalist
	Session
	Observation
	Human_Observation
	Digital_Observation
	Emotion
	Behavior
	Affect

* **URL**  

  /list/:class_type

* **Method:**

  `GET`

* **URL Params**

   **Required:**

   `class_type:` `Student`, `Teacher``Admin`, `Annalist`, `Session`, `Observation`, `Human_Observation`, `Digital_Observation`, `Emotion`, `Behavior` or `Affect`

* **Data Params**

   None

* **Success Response:**

  * **Code:** 200  
    **Content:** JSON arrays with each individual of the queried class.

		[
			{
			    "Name": "John Doe"
			},
			{
			    "Name": "Mary Jane"
			}
		]

* **Error Response:**

  * **Code:** 400 BAD REQUEST  
    **Content:**

		{
		  "errors": [
			"Error message."
		  ]
		}

* **Sample Call:**

  `curl -s https://ontology.acacia.red:5904/list/Student`

## Building, Running and Deploying

### Building

`mvn package`

Builds both a jar for local testing and a Debian package for deployment.

### Running

`java -jar target/ontology-1.0.0.jar server dev.yml`

Runs locally in development mode.

### Deploying
   
Copy `target/ontology-1.0.0.deb` into a Debian-based server and install with:

`dpkg -i ontology-1.0.0.deb`

This will deploy the microservice into a system service and run it in production mode.

## License and authorship

ACACIA ontology code licensed under [GNU AGPL v3](/LICENSE.md) or later. Copyright belongs to:

- [Jorge Calado](https://github.com/jms-calado) (ontology, SPARQL queries and updates, adapted classes used for the Jackson serialization),
- [David Ludovino](https://github.com/dllud) (initial REST microservice),
- [aquasmart-training](http://git-gris.uninova.pt/Sudeep/aquasmart-training) (used as ground work and original code).

