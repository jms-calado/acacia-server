window.onload = init;
var socket = new WebSocket("ws://localhost:5904/actions");
socket.onmessage = onMessage;

function onMessage(event) {
    var device = JSON.parse(event.data);
    if (device.action === "add") {
        printDeviceElement(device);
    }
    if (device.action === "remove") {
        document.getElementById(device.id).remove();
        //device.parentNode.removeChild(device);
    }
    if (device.action === "toggleOnOff") {
        var node = document.getElementById(device.id);
        var statusText = node.children[2];
        if (device.statusOnOff === "On") {
            statusText.innerHTML = "<b>Status:</b> " + device.statusOnOff + " (<a href=\"#\" OnClick=toggleOffDevice(" + device.id + ")>Turn off</a>)";
        } else if (device.statusOnOff === "Off") {
            statusText.innerHTML = "<b>Status:</b> " + device.statusOnOff + " (<a href=\"#\" OnClick=toggleOnDevice(" + device.id + ")>Turn on</a>)";
        }
    }
    if (device.action === "toggleStartStop") {
        var node = document.getElementById(device.id);
        var recording = node.children[3];
        if (device.statusStartStop === "Start") {
            recording.innerHTML = "<b>Recording:</b> Yes (<a href=\"#\" OnClick=toggleStopDevice(" + device.id + ")>Stop</a>)";
        } else if (device.statusStartStop === "Stop") {
            recording.innerHTML = "<b>Recording:</b> No (<a href=\"#\" OnClick=toggleStartDevice(" + device.id + ")>Start</a>)";
        }
    }
}

function toggleOnDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "on",
        id: id,
		Session: "session_",//
		Scenario: "Scenario",//
		Digital_Observation_Sample_Rate: "30000",//
		Sensory_Component: ["dev1", "dev2"]//
    };
    socket.send(JSON.stringify(DeviceAction));
}

function toggleOffDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "off",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function toggleStartDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "start",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function toggleStopDevice(element) {
    var id = element;
    var DeviceAction = {
        action: "stop",
        id: id
    };
    socket.send(JSON.stringify(DeviceAction));
}

function printDeviceElement(device) {
    var content = document.getElementById("content");
    
    var deviceDiv = document.createElement("div");
    deviceDiv.setAttribute("id", device.id);
    deviceDiv.setAttribute("class", "device " + device.type);
    content.appendChild(deviceDiv);

    var deviceName = document.createElement("span");
    deviceName.setAttribute("class", "deviceName");
    deviceName.innerHTML = device.name;
    deviceDiv.appendChild(deviceName);

    var deviceType = document.createElement("span");
    deviceType.innerHTML = "<b>Type:</b> " + device.type;
    deviceDiv.appendChild(deviceType);

    var deviceStatus = document.createElement("span");
    if (device.statusOnOff === "On") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.statusOnOff + " (<a href=\"#\" OnClick=toggleOffDevice(" + device.id + ")>Turn off</a>)";
    } else if (device.statusOnOff === "Off") {
        deviceStatus.innerHTML = "<b>Status:</b> " + device.statusOnOff + " (<a href=\"#\" OnClick=toggleOnDevice(" + device.id + ")>Turn on</a>)";
    }
    deviceDiv.appendChild(deviceStatus);
	
    var deviceRecording = document.createElement("span");
    if ((device.statusStartStop === "Start") && (device.statusOnOff === "On")) {
        deviceRecording.innerHTML = "<b>Recording:</b> Yes (<a href=\"#\" OnClick=toggleStopDevice(" + device.id + ")>Stop</a>)";
    } else if (device.statusStartStop === "Stop") {
        deviceRecording.innerHTML = "<b>Recording:</b> No (<a href=\"#\" OnClick=toggleStartDevice(" + device.id + ")>Start</a>)";
    }
    deviceDiv.appendChild(deviceRecording);
	
    var deviceSensors = document.createElement("span");
	var sensorsList = "<br><table><tr><th rowspan=\"" + device.sensors.length + "\">Sensors:</th>";
	for (var i = 0; i < device.sensors.length; i++) { //device.sensors.lenght
		sensorsList += "<td>" + device.sensors[i] + "</td></tr><tr>";
	}
	sensorsList += "</tr></table>";
    deviceSensors.innerHTML = sensorsList;
    deviceDiv.appendChild(deviceSensors);
}

function showForm() {
    document.getElementById("addDeviceForm").style.display = '';
}

function hideForm() {
    document.getElementById("addDeviceForm").style.display = "none";
}

function init() {
    hideForm();
}