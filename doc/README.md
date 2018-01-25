---
title: API Reference

language_tabs: # must be one of https://git.io/vQNgJ
  - json
  - curl

toc_footers:
  - 

includes:
  - errors

search: true
---

# API.ARCA.ACACIA.RED

---

### api.arca.acacia.red API Documentation (version 3.1.0)

---

---

# 1. REST API

**"https://api.arca.acacia.red"**

---

# 1.1. /insert

## /insert/Class

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### ClassRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/Class" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Subject": "Math_101",
  "Description": "Introduction to the basics of Mathematics",
  "Student": [
    "Student_123",
    "Student_321"
  ],
  "Teacher": [
    "Teacher_007"
  ],
}
```

#### *ClassRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Subject |  string |  | true |  |
| Description |  string |  | false |  |
| Student |  string array |  | true |  |
| Teacher |  string array |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

## /insert/Teacher

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### TeacherRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/Teacher" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Gender": "Male",
  "Name": "Hulk",
  "Age": "45",
  "Race_Ethnicity": "White"
}
```

#### *TeacherRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Gender |  string |  | true | Male/Female |
| Name |  string |  | true |  |
| Age |  string |  | true |  |
| Race_Ethnicity |  string |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

> response_body:

```json
  ["Teacher_9"]
```

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

## /insert/Admin

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### AdminRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/Admin" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Gender": "Male",
  "Name": "Morgan Freeman",
  "Age": "100",
  "Race_Ethnicity": "White"
}
```

#### *AdminRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Gender |  string |  | true | Male/Female |
| Name |  string |  | true |  |
| Age |  string |  | true |  |
| Race_Ethnicity |  string |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

> response_body:

```json
  ["Admin_9"]
```

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

## /insert/Annalist

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### AnnalistRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/Annalist" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Gender": "Female",
  "Name": "Lara Croft",
  "Age": "35",
  "Race_Ethnicity": "White"
}
```

#### *AnnalistRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Gender |  string |  | true | Male/Female |
| Name |  string |  | true |  |
| Age |  string |  | true |  |
| Race_Ethnicity |  string |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

> response_body:

```json
  ["Annalist_9"]
```

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

## /insert/session

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### sessionRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/session" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Date_Time": "2017-07-06T14:00:00",
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
| Date_Time |  string |  | true | YYYY-MM-ddTHH:mm:ss |
| Duration |  string |  | true | HH:mm:ss |
| Observation_Sample_Rate |  string |  | true |  |
| Scenario |  string |  | true |  |
| Student | items array |  | true |  |
| Teacher | items array |  | true |  |
| Sensory_Component | items array |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

> response_body:

```json
  ["Session_2017-07-06_14-00-00"]
```

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>
<br><br>

---

## /insert/student_issue

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### sessionRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/student_issue" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Date_Time": "2017-07-06T14:00:00",
  "Duration": "01:00:00",
  "Session": ["Session_2017-07-06_14-00-00", "Session_2017-07-13_14-00-00"],
  "Scenario": "Affective_States_Automatic_Detection_",
  "Student": "Student_123",
  "Teacher": "Teacher_007",
  "Issue": {
					"Low_Attention_" : "0.5",
					"Agression_" : "0.3"
	}
}
```

#### *sessionRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Date_Time |  string |  | true | YYYY-MM-ddTHH:mm:ss |
| Duration |  string |  | true | HH:mm:ss |
| Session |  string array |  | true |  |
| Scenario |  string |  | true |  |
| Student | string |  | true |  |
| Teacher | string |  | true |  |
| Issue | json object |  | true | "{"Issue_name" : "Value"}" |

### Response code: 201
Success

#### Response (application/json) 

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

### StudentRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/Student" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Gender": "Male",
  "Name": "Peter Pan",
  "Age": "20",
  "Race_Ethnicity": "White"
}
```  

#### *StudentRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Gender |  string |  | true | Male/Female |
| Name |  string |  | true |  |
| Age |  string |  | true |  |
| Race_Ethnicity |  string |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

> response_body:

```json
["Student_9"]
```

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

## /insert/emotion

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### emotionRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/emotion" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

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

#### Response (application/json) 

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

### behaviourRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/behaviour" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

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

#### Response (application/json) 

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

### affectRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/affect" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

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

#### Response (application/json) 

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

### Human_ObservationRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/observation/Human" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Date_Time": "2017-07-06T14:00:00",
  "Duration": "00:10:00",
  "Scenario": "Analysis_of_Music_in_E-Learning_1",
  "Session": ["Session_2017-07-04_14-00-00"],
  "Student": "Student_123",
  "Teacher": "Teacher_007"
}
```

#### *Human_ObservationRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Date_Time |  string |  | true | YYYY-MM-ddTHH:mm:ss |
| Duration |  string |  | true | HH:mm:ss |
| Scenario |  string |  | true |  |
| Session |  string |  | true |  |
| Student |  string |  | true |  |
| Teacher |  string |  | true |  |

### Response code: 201
Success


#### Human_ObservationResponse (application/json) 

> response_body:

```json
{
  "Observation_ID": "8"
}
```

##### *Human_ObservationResponse*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

## /insert/observation/Digital

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Digital_ObservationRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/observation/Digital" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Date_Time": "2017-07-06T14:00:00",
  "Duration": "00:10:00",
  "Scenario": "Analysis_of_Music_in_E-Learning_1",
  "Session": ["Session_2017-07-04_14-00-00"],
  "Student": "Student_123",
  "Sensory_Component": "Facial_Expression_Recognition_1"
}
```

#### *Digital_ObservationRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Date_Time |  string |  | true | YYYY-MM-ddTHH:mm:ss |
| Duration |  string |  | true | HH:mm:ss |
| Scenario |  string |  | true |  |
| Session |  string |  | true |  |
| Student |  string |  | true |  |
| Sensory_Component |  string |  | true |  |

### Response code: 201
Success

#### Digital_ObservationResponse (application/json) 

> response_body:

```json
{
  "Observation_ID": "9"
}
```

##### *Digital_ObservationResponse*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Observation_ID |  string |  | true |  |

<br><br>

---

## /insert/profile/Academic

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### AcademicRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/profile/Academic" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
	"Education_Degree":"Bachelor",
	"Area_of_Degree":"Arts",
	"Average_Course_Grade":"12",
	"Income_Class":"High",
	"University":"UFOPA",
	"Student_Status":"Normal",
	"User":"Student_123"
}
```

#### *AcademicRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Education_Degree |  string |  | true | High_School/Bachelor/Master/Doctor |
| Area_of_Degree |  string |  | true | Science/Engineering/Arts/Humanities |
| Average_Course_Grade |  string |  | true |  |
| Income_Class |  string |  | true | High/Medium/Low |
| University |  string |  | true | UFOPA/UDFJC/UNAN LEON |
| Student_Status |  string |  | true | Worker/Normal/Athlete/Parent/Other |
| User |  string |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

## /insert/profile/Learning

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### LearningRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/profile/Learning" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
	"Active_Reflective":"4",
	"Sensing_Intuitive":"-5",
	"Sequential_Global":"6",
	"Visual_Verbal":"-8",
	"User":"Student_123"
}
```

#### *LearningRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Active_Reflective |  string |  | true |  |
| Sensing_Intuitive |  string |  | true |  |
| Sequential_Global |  string |  | true |  |
| Visual_Verbal |  string |  | true |  |
| User |  string |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

## /insert/profile/Diversity

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### DiversityRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/profile/Diversity" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
	"Disability":"Blind",
	"User":"Student_123"
}
```

#### *DiversityRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Disability |  string |  | true | Blind/Visually_Impaired/Deaf/Hearing_Impaired/Speech_Disorder/Physical_Disability/Other |
| User |  string |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

## /insert/profile/Musical

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### MusicalRequest (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/insert/profile/Musical" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
	"Musical_Instrument":"Keys",
	"User":"Student_123"
}
```

#### *MusicalRequest*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Musical_Instrument |  string |  | true | String/Percussion/Keys/Wind |
| User |  string |  | true |  |

### Response code: 201
Success

#### Response (application/json) 

##### *Response*:
| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

---

# 1.2. /list

## /list/Class

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Class"
```

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

> response_body: 

```json
[
    {
        "Class": "Class_1"
    },
    {
        "Class": "Class_Math"
    }
]
```

#### Response (application/json) 

##### List of *ClassResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Class |  string |  | true |  |

<br><br>

---

## /list/Student

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Student"
```

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

> response_body: 

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

```curl
curl -X GET "https://api.arca.acacia.red/list/Teacher"
```

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

> response_body: 

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

```curl
curl -X GET "https://api.arca.acacia.red/list/Affect"
```

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

> response_body: 

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

<br><br>

---

## /list/Behaviour

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Behaviour"
```

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

> response_body: 

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

<br><br>

---

## /list/Emotion

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Emotion"
```

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

> response_body: 

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

<br><br>

---
## /list/Issue

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Issue"
```

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

> response_body: 

```json
[
  {
    "Issue": "Low_Attention_1"
  },
  {
    "Issue": "Drop_Out_1"
  },
  {
    "Issue": "Low_Performance_1"
  }
]
```

#### Response (application/json) 

##### List of *ObservationResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Issue |  string |  | true |  |

<br><br>
<br><br>
<br><br>

---

## /list/Observation

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Observation"
```

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

### Response code: 200
Success

> response_body: 

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

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Session"
```

<br><br>

### Response code: 200
Success

> response_body: 

```json
[
  {
    "Session": "Session_2017-07-04_14-00-00"
  }
]
```

#### Response (application/json) 

##### List of *SessionResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Session |  string |  | true |  |

<br><br>

---

## /list/Admin

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Admin"
```

### Response code: 200
Success

#### Response (application/json) 


##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /list/Annalist

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Annalist"
```

### Response code: 200
Success

#### Response (application/json) 

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

---

## /list/Human_Observation

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Human_Observation"
```

<br><br>

### Response code: 200
Success

> response_body: 

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

<br><br>

---

## /list/Digital_Observation

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Digital_Observation"
```

<br><br>

### Response code: 200
Success

> response_body: 

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

### **GET**:

```curl
curl -X GET "https://api.arca.acacia.red/list/Sensory_Component"
```

<br><br>

### Response code: 200
Success

> response_body: 

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
<br>

---

## /list/individual_properties

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/list/individual_properties" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "Session_2017-07-06_14-00-00"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>
<br><br>
<br><br>

### Response code: 200
Success

> response_body: 

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
    "Value": "2017-07-06T14:00:00",
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

<br><br>

---

## /list/observations_of_session

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/list/observations_of_session" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "Session_2017-07-04_14-00-00"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

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

## /list/students_of_class

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/list/students_of_class" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "Class_1"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
|  |  string |Class individual identifier | true | single element array |

	<aside class="notice">
	string acquired as a response of POST: <a href="#list-class">/list/Class</a>
	</aside>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

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

##### List of *students_of_classResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Individual |  string |  | true |  |
| Name |  string |  | true |  |

<br><br>

---

## /list/students_of_session

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/list/students_of_session" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "Session_2017-07-04_14-00-00"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

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

```curl
curl -X POST "https://api.arca.acacia.red/list/students_of_observation" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "Digital_Observation_1"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

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

## /list/sessions_of_student

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/list/sessions_of_student" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "Student_123"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

```json
[
  {
    "Session": "Session_2017-07-04_14-00-00"
  },
  {
    "Session": "Session_2017-07-11_14-00-00"
  }
]
```

#### Response (application/json) 

##### List of *sessions_of_studentResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Session |  string |  | true |  |

<br><br>

---

# 1.3. /find

## /find/Student/

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/find/Student" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "John Doe"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

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

<br><br>

---

## /find/Teacher

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/find/Teacher" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "James Bond"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

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

<br><br>

---

# 1.4. /plot

## /plot/session/Emotion

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/plot/session/Emotion" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "Session_2017-07-04_14-00-00"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

```json
{
    "Student_123": {
        "Anger": "0.01235",
        "Sadness": "0.13123",
        "Disgust": "0.35268"
    },
    "Student_321": {
        "Anger": "0.011744999",
        "Contempt": "0.055955",
        "Joy": "0.18924001",
        "Surprise": "0.239945",
        "Sadness": "0.272385",
        "Disgust": "0.30952",
        "Fear": "0.523335"
    }
}
or
{
    "Error": "No Data Found"
}
```

#### Response (application/json) 

##### List of *plot/session/EmotionResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Student_X |  string |  | false |  |

<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>

---

## /plot/student/Emotion

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/plot/student/Emotion" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
[
  "Student_123"
]
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|

<br><br>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

```json
{
    "Session_2017-07-06_14-00-00": {
        "Anger": "0.01235",
        "Sadness": "0.13123",
        "Disgust": "0.35268"
    },
    "Session_2017-10-05_14-00-00": {
        "Anger": "0.12345",
        "Sadness": "0.12345",
        "Contempt": "0.12346",
        "Surprise": "0.21345",
        "Disgust": "0.23456",
        "Fear": "0.34561",
        "Joy": "0.54321"
    }
}
or
{
    "Error": "No Data Found"
}
```

#### Response (application/json) 

##### List of *plot/student/EmotionResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Session_X |  string |  | false |  |

<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>
<br><br>

---

## /plot/student_session/Emotion

### **POST**:

##### Headers

| Name | Type | Description | Required | Examples |
|:-----|:----:|:------------|:--------:|---------:|
| Content-Type | string |  | true | ``` application/json ```  |

#### Body (application/json) 

```curl
curl -X POST "https://api.arca.acacia.red/plot/student_session/Emotion" \
	-H "Content-Type: application/json" \
	-d @request_body
```

>request_body:

```json
{
  "Student":"Student_123",
  "Session":"Session_2017-07-04_14-00-00"
}
```

##### List of *items*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Student |  string |  | true |  |
| Session |  string |  | true |  |

<br><br>

<br><br>

<br><br>

### Response code: 200
Success

> response_body: 

```json
{
    "Digital_Observation_1": {
        "Sadness": "0.13123"
    },
    "Digital_Observation_2": {
        "Disgust": "0.35268",
        "Anger": "0.01235"
    }
}
or
{
    "Error": "No Data Found"
}
```

#### Response (application/json) 

##### List of *plot/student_session/EmotionResponse*:

| Name | Type | Description | Required | Pattern |
|:-----|:----:|:------------|:--------:|--------:|
| Digital_Observation_X |  string |  | false |  |

<br><br>
<br><br>
<br><br>
<br><br>

---

---

# 2. Websocket API

**"wss://api.arca.acacia.red/actions"**

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
<br><br>

---

