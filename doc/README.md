---
title: API Reference

language_tabs: # must be one of https://git.io/vQNgJ
  - json

toc_footers:
  - 

includes:
  - errors

search: true
---

# ARCA.ACACIA.RED

---

### arca.acacia.red API Documentation version 2.1.0

---

---

# 1. REST API

---

# 1.1. /insert

## /insert/Teacher

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### TeacherRequest (text/plain) 

```json
{
  "Gender": "Male",
  "Name": "Hulk",
  "Age": "45",
  "ID": "555",
  "Education_Degree": "PhD",
  "Area_of_Degree": "Physics"
}
```

#### *TeacherRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Gender |  string |  | true |  |
| Name |  string |  | true |  |
| Age |  string |  | true |  |
| ID |  string |  | true |  |
| Education_Degree |  string |  | true |  |
| Area_of_Degree |  string |  | true |  |

### Response code: 201
Success

#### Response (text/plain) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /insert/Admin

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### AdminRequest (text/plain) 

```json
{
  "Gender": "Male",
  "Name": "Morgan Freeman",
  "Age": "100",
  "ID": "999",
  "Education_Degree": "DSc",
  "Area_of_Degree": "Physics"
}
```

#### *AdminRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Gender |  string |  | true |  |
| Name |  string |  | true |  |
| Age |  string |  | true |  |
| ID |  string |  | true |  |
| Education_Degree |  string |  | true |  |
| Area_of_Degree |  string |  | true |  |

### Response code: 201
Success

#### Response (text/plain) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /insert/Annalist

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### AnnalistRequest (text/plain) 

```json
{
  "Gender": "Female",
  "Name": "Lara Croft",
  "Age": "35",
  "ID": "777",
  "Education_Degree": "PhD",
  "Area_of_Degree": "Physics"
}
```

#### *AnnalistRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Gender |  string |  | true |  |
| Name |  string |  | true |  |
| Age |  string |  | true |  |
| ID |  string |  | true |  |
| Education_Degree |  string |  | true |  |
| Area_of_Degree |  string |  | true |  |

### Response code: 201
Success

#### Response (text/plain) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /insert/session

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### sessionRequest (text/plain) 

```json
{
  "Date_Time": "7/6/2017 2:00:00 PM",
  "Duration": "01:00:00",
  "Observation_Sample_Rate": "30000",
  "Scenario": "Affective_States_Automatic_Detection_",
  "Student": [
    "Student_123",
    "Student_321"
  ],
  "Teacher": [
    "Teacher_007"
  ],
  "Sensory_Component": [
    "Eye_Tracker_1",
    "Facial_Expression_Recognition_1"
  ]
}
```

#### *sessionRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Date_Time |  string |  | true |  |
| Duration |  string |  | true |  |
| Observation_Sample_Rate |  string |  | true |  |
| Scenario |  string |  | true |  |
| Student | items array |  | true |  |
| Teacher | items array |  | true |  |
| Sensory_Component | items array |  | true |  |

### Response code: 201
Success

#### Response (text/plain) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /insert/Student

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### StudentRequest (text/plain) 

```json
{
  "Gender": "Male",
  "Name": "Peter Pan",
  "Age": "20",
  "ID": "213",
  "Education_Degree": "12",
  "Area_of_Degree": "Physics"
}
```  

#### *StudentRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Gender |  string |  | true |  |
| Name |  string |  | true |  |
| Age |  string |  | true |  |
| ID |  string |  | true |  |
| Education_Degree |  string |  | true |  |
| Area_of_Degree |  string |  | true |  |

### Response code: 201
Success

#### Response (text/plain) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /insert/emotion

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### emotionRequest (text/plain) 

```json
{
  "ObservationID": "Digital_Observation_3",
  "Anger": "0",
  "Contempt": "0",
  "Disgust": "0",
  "Fear": "0",
  "Happiness": "0",
  "Joy": "0",
  "Neutral_Emotion": "0",
  "Sadness": "0",
  "Surprise": "0"
}
```

#### *emotionRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Happiness |  string |  | true |  |
| Fear |  string |  | true |  |
| Joy |  string |  | true |  |
| Neutral_Emotion |  string |  | true |  |
| Sadness |  string |  | true |  |
| ObservationID |  string |  | true |  |
| Contempt |  string |  | true |  |
| Surprise |  string |  | true |  |
| Anger |  string |  | true |  |
| Disgust |  string |  | true |  |

### Response code: 201
Success

#### Response (text/plain) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /insert/behaviour

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### behaviourRequest (text/plain) 

```json
{
  "ObservationID": "Digital_Observation_3",
  "Active_Participation": "0",
  "Attention": "0",
  "Disengaged": "0",
  "Engaged": "0",
  "Inactive_Participation": "0",
  "Off_Task": "0",
  "On_Task": "0",
  "Other_Behaviour": "0",
  "Other_Behaviour_Name": "0"
}
```

#### *behaviourRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Other_Behaviour_Name |  string |  | true |  |
| Inactive_Participation |  string |  | true |  |
| Disengaged |  string |  | true |  |
| On_Task |  string |  | true |  |
| Attention |  string |  | true |  |
| Off_Task |  string |  | true |  |
| Engaged |  string |  | true |  |
| Active_Participation |  string |  | true |  |
| ObservationID |  string |  | true |  |
| Other_Behaviour |  string |  | true |  |

### Response code: 201
Success

#### Response (text/plain) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /insert/affect

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### affectRequest (text/plain) 

```json
{
  "ObservationID": "Human_Observation_4",
  "Bored": "0",
  "Concentrated": "0",
  "Confused": "0",
  "Exited": "0",
  "Frustrated": "0",
  "Meditation": "0",
  "Neutral_Affect": "0",
  "Satisfaction": "0",
  "Other_Affect": "0",
  "Other_Affect_Name": "0"
}
```

#### *affectRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Bored |  string |  | true |  |
| Concentrated |  string |  | true |  |
| Frustrated |  string |  | true |  |
| Satisfaction |  string |  | true |  |
| ObservationID |  string |  | true |  |
| Other_Affect |  string |  | true |  |
| Other_Affect_Name |  string |  | true |  |
| Neutral_Affect |  string |  | true |  |
| Meditation |  string |  | true |  |
| Exited |  string |  | true |  |
| Confused |  string |  | true |  |

### Response code: 201
Success

#### Response (text/plain) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /insert/observation/Human

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Human_ObservationRequest (text/plain) 

```json
{
  "Date_Time": "7/4/2017 2:10:00 PM",
  "Duration": "00:10:00",
  "Scenario": "Analysis_of_Music_in_E-Learning_1",
  "Session": "Session_2017-7-04_14-00-00",
  "Student": "Student_123",
  "Teacher": "Teacher_007"
}
```

#### *Human_ObservationRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Date_Time |  string |  | true |  |
| Duration |  string |  | true |  |
| Scenario |  string |  | true |  |
| Session |  string |  | true |  |
| Student |  string |  | true |  |
| Teacher |  string |  | true |  |

### Response code: 201
Success

#### Response (text/plain) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /insert/observation/Digital

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Digital_ObservationRequest (application/json) 

```json
{
  "Date_Time": "7/4/2017 2:10:00 PM",
  "Duration": "00:10:00",
  "Scenario": "Analysis_of_Music_in_E-Learning_1",
  "Session": "Session_2017-7-04_14-00-00",
  "Student": "Student_123",
  "Sensory_Component": "Facial_Expression_Recognition_1"
}
```

#### *Digital_ObservationRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Date_Time |  string |  | true |  |
| Duration |  string |  | true |  |
| Scenario |  string |  | true |  |
| Session |  string |  | true |  |
| Student |  string |  | true |  |
| Sensory_Component |  string |  | true |  |

### Response code: 201
Success

#### Digital_ObservationResponse (application/json) 

```json
{
  "Observation_ID": "9"
}
```

##### *Digital_ObservationResponse*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Observation_ID |  string |  | true |  |

---

# 1.2. /list

## /list/Student

### **GET**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

```json
[
  {
    "Student": "Student_123",
    "Name": "John Doe"
  },
  {
    "Student": "Student_321",
    "Name": "Mary Jane"
  }
]
```

#### Response (application/json) 

##### List of *StudentResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Student |  string |  | true |  |
| Name |  string |  | true |  |

<br><br>

---

## /list/Teacher

### **GET**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

```json
[
  {
    "Teacher": "Teacher_007",
    "Name": "James Bond"
  }
]
```

#### Response (application/json) 

##### List of *TeacherResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Teacher |  string |  | true |  |
| Name |  string |  | true |  |

---

## /list/Affect

### **GET**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

```json
[
  {
    "Affect": "Affect_4"
  }
]
```

#### Response (application/json) 

##### List of *AffectResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Affect |  string |  | true |  |

---

## /list/Behaviour

### **GET**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

```json
[
  {
    "Behaviour": "Behaviour_3"
  }
]
```

#### Response (application/json) 

##### List of *BehaviourResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Behaviour |  string |  | true |  |

---

## /list/Emotion

### **GET**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

```json
[
  {
    "Emotion": "Emotion_1"
  },
  {
    "Emotion": "Emotion_2"
  }
]
```

#### Response (application/json) 

##### List of *EmotionResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Emotion |  string |  | true |  |

<br><br>

---

## /list/Observation

### **GET**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

```json
[
  {
    "Observation": "Digital_Observation_1"
  },
  {
    "Observation": "Digital_Observation_2"
  },
  {
    "Observation": "Digital_Observation_3"
  },
  {
    "Observation": "Human_Observation_4"
  }
]
```

#### Response (application/json) 

##### List of *ObservationResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Observation |  string |  | true |  |

<br><br>

<br><br>

<br><br>

---

## /list/Session

#### **GET**:

### Response code: 200
Success

```json
[
  {
    "Session": "Session_2017-7-04_14-00-00"
  }
]
```

#### Response (application/json) 

##### List of *SessionResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Session |  string |  | true |  |

---

## /list/Admin

#### **GET**:

### Response code: 200
Success

#### Response (text/plain) 


##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /list/Annalist

#### **GET**:

### Response code: 200
Success

#### Response (text/plain) 

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /list/Human_Observation

#### **GET**:

### Response code: 200
Success

```json
[
  {
    "Human_Observation": "Human_Observation_4"
  }
]
```

#### Response (application/json) 

##### List of *Human_ObservationResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Human_Observation |  string |  | true |  |

---

## /list/Digital_Observation

#### **GET**:

### Response code: 200
Success

```json
[
  {
    "Digital_Observation": "Digital_Observation_1"
  },
  {
    "Digital_Observation": "Digital_Observation_2"
  },
  {
    "Digital_Observation": "Digital_Observation_3"
  }
]
```

#### Response (application/json) 

##### List of *Digital_ObservationResponse19*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Digital_Observation |  string |  | true |  |

<br><br>

<br><br>

---

## /list/Sensory_Component

#### **GET**:

### Response code: 200
Success

```json
[
  {
    "Sensory_Component": "Eye_Tracker_1"
  },
  {
    "Sensory_Component": "Facial_Expression_Recognition_1"
  }
]
```

#### Response (application/json) 

##### List of *Sensory_ComponentResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Sensory_Component |  string |  | true |  |

<br><br>

---

## /list/individual_properties

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```json
[
  "Session_2017-7-04_14-00-00"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

### Response code: 200
Success

```json
[
  {
    "Value": "Student_123",
    "Property": "Has_Student"
  },
  {
    "Value": "Student_321",
    "Property": "Has_Student"
  },
  {
    "Value": "Eye_Tracker_1",
    "Property": "Has_Sensory_Component"
  },
  {
    "Value": "Facial_Expression_Recognition_1",
    "Property": "Has_Sensory_Component"
  },
  {
    "Value": "-T01:00:00",
    "Property": "Duration"
  },
  {
    "Value": "7/4/2017 2:00:00 PM",
    "Property": "Date_Time"
  },
  {
    "Value": "Affective_States_Automatic_Detection_",
    "Property": "Belongs_to_Scenario"
  },
  {
    "Value": "30000",
    "Property": "Observation_Sample_Rate"
  }
]
```

#### Response (application/json) 

##### List of *individual_propertiesResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Value |  string |  | true |  |
| Property |  string |  | true |  |

<br><br>

<br><br>

<br><br>

<br><br>

<br><br>

<br><br>

<br><br>

<br><br>

---

## /list/observations_of_session

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```json
[
  "Session_2017-7-04_14-00-00"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

### Response code: 200
Success

```json
[
  {
    "Individual": "Human_Observation_4"
  },
  {
    "Individual": "Digital_Observation_2"
  },
  {
    "Individual": "Digital_Observation_1"
  },
  {
    "Individual": "Digital_Observation_3"
  }
]
```

#### Response (application/json) 

##### List of *observations_of_sessionResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Individual |  string |  | true |  |

<br><br>

<br><br>

<br><br>

---

## /list/students_of_session

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```json
[
  "Session_2017-7-04_14-00-00"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

### Response code: 200
Success

```json
[
  {
    "Individual": "Student_123",
    "Name": "John Doe"
  },
  {
    "Individual": "Student_321",
    "Name": "Mary Jane"
  }
]
```

#### Response (application/json) 

##### List of *students_of_sessionResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Individual |  string |  | true |  |
| Name |  string |  | true |  |

<br><br>

---

## /list/students_of_observation

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```json
[
  "Digital_Observation_1"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

### Response code: 200
Success

```json
[
  {
    "Individual": "Student_123",
    "Name": "John Doe"
  }
]
```

#### Response (application/json) 

##### List of *student_of_observationResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Individual |  string |  | true |  |
| Name |  string |  | true |  |

---

# 1.3. /find

## /find/Student/

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```json
[
  "John Doe"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

### Response code: 200
Success

```json
[
  {
    "Student": "Student_123"
  }
]
```

#### Response (application/json) 

##### List of *find/StudentResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Student |  string |  | true |  |

---

## /find/Teacher

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```json
[
  "James Bond"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

### Response code: 200
Success

```json
[
  {
    "Teacher": "Teacher_007"
  }
]
```

#### Response (application/json) 

##### List of *find/TeacherResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Teacher |  string |  | true |  |

---

---

# 2. Websocket API

**"ws://api.arca.acacia.red:5904/actions"**

---

# 2.1. objects

## Session:
>	**type:** string

>	**example:**

```json
	"Session_2017-07-06_14-00-00"
```
	<aside class="notice">
	string acquired as a response of POST: /list/Session
	</aside>

## Scenario:
>	**type:** string

>	**example:**

```json
	"Affective_States_Automatic_Detection_"
```	
	<aside class="notice">
	**note:** string acquired as a response of POST: /list/individual_properties  with body: [Session]
	</aside>
	
## Digital_Observation_Sample_Rate:
>	**type:** string

>	**example:**

```json
	"30000"
```	
	<aside class="notice">
	**note:** string acquired as a response of POST: /list/individual_properties  with body: [Session]
	</aside>
	
## Sensory_Component:
>	**type:** array

>	**example:**

```json
	["GP3", "Affectiva"]
```
	<aside class="notice">
	**note:** array acquired as a response of POST: /list/individual_properties  with body: [Session]
	</aside>
	
## ID:
>	**type:** integer

	<aside class="notice">
	**note:** defined by subscribe action "add"
	</aside>
___	
# 2.2. publish

**When sending json messages to the ws server, "action" value can be:**

## on:

**Command to turn ON the program in student pc (defined by ID)**

```json
	{
		"action": "on",
		"id": ID,
		"Session": Session,
		"Scenario": Scenario,
		"Digital_Observation_Sample_Rate": Digital_Observation_Sample_Rate,
		"Sensory_Component": Sensory_Component
	}
```

## off:

**Command to turn OFF the program in students pc (defined by ID)**

```json
	{
		"action": "off",
		"id": ID
	}
```

## start:

**Command to START the recording (in the student pc defined by ID)**

```json
	{
		"action": "start",
		"id": ID
	}
```

## stop:

**Command to STOP the recording (in the student pc defined by ID)**

```json
	{
		"action": "stop",
		"id": ID
	}
```

<br><br>

<br><br>

___
# 2.3. subscribe

**When receiving json messages from the ws server, "action" value can be:**

## add:

**Notification that a new student pc (defined by ID) was ADDed in the server**

```json
	{
		"action": "add",
		"id": ID,
		"name": "username",
		"type": "Student",
		"statusOnOff": "Off",
		"statusStartStop": "Stop",
		"sensors": ["GP3", "Affectiva"]
	}
```

## remove:

**Notification that an existing student pc (defined by ID) was REMOVEd from the server**

```json
	{
		"action": "remove",
		"id": ID
	}
```

## toggleOnOff:

**Notification that the program in the student pc (defined by ID) was turned ON/OFF**

```json
	{
		"action": "toggleOnOff",
		"id": ID,
		"statusOnOff": "On"
	}
```

## toggleStartStop:

**Notification that the recording in the student pc (defined by ID) was STARTed/STOPed**

```json
	{
		"action": "toggleStartStop",
		"id": ID,
		"statusStartStop": "Stop"
	}
```

<br><br>

<br><br>
<br><br>

---

