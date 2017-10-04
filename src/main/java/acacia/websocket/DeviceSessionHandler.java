package acacia.websocket;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import acacia.model.Device;

@ApplicationScoped
public class DeviceSessionHandler {
	
    private int deviceId = 0;
    private final Set<Session> sessions = new HashSet<>();
    private final Set<Device> devices = new HashSet<>();
    
    public void addSession(Session session) {
    	session.setMaxIdleTimeout(0);
        sessions.add(session);
        for (Iterator<Device> device = devices.iterator(); device.hasNext();) {
        	Device current = device.next();
            JsonObject addMessage = createAddMessage(current);
            sendToSession(session, addMessage);
        }
    }

    public void removeSession(Session session) {
    	for(Device sessionDevice : devices) {
    		if(session == sessionDevice.getSession()) {
    			removeDevice(sessionDevice.getId());
    		}
    	}    		
        sessions.remove(session);
    }
    public List<Device> getDevices() {
        return new ArrayList<>(devices);
    }

    public void addDevice(Device device) {
        device.setId(deviceId);
        devices.add(device);
        deviceId++;
        JsonObject addMessage = createAddMessage(device);
        sendToAllConnectedSessions(addMessage);
    }

    public void removeDevice(int id) {
        Device device = getDeviceById(id);
        if (device != null) {
            devices.remove(device);
            JsonProvider provider = JsonProvider.provider();
            JsonObject removeMessage = provider.createObjectBuilder()
                    .add("action", "remove")
                    .add("id", id)
                    .build();
            sendToAllConnectedSessions(removeMessage);
        }
    }

    public void toggleOnOffDevice(int id) {
        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            if ("On".equals(device.getStatusOnOff())) {
                device.setStatusOnOff("Off");
            } else {
                device.setStatusOnOff("On");
            }
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "toggleOnOff")
                    .add("id", device.getId())
                    .add("statusOnOff", device.getStatusOnOff())
                    .build();
            sendToAllConnectedSessions(updateDevMessage);
        }
    }
    public void toggleStartStopDevice(int id) {
        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            if ("Start".equals(device.getStatusStartStop())) {
                device.setStatusStartStop("Stop");
            } else {
                device.setStatusStartStop("Start");
            }
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "toggleStartStop")
                    .add("id", device.getId())
                    .add("statusStartStop", device.getStatusStartStop())
                    .build();
            sendToAllConnectedSessions(updateDevMessage);
        }
    }

    private Device getDeviceById(int id) {
        for (Device device : devices) {
            if (device.getId() == id) {
                return device;
            }
        }
        return null;
    }

    private JsonObject createAddMessage(Device device){    	
        JsonProvider provider = JsonProvider.provider();
        JsonObject addMessage = provider.createObjectBuilder()
                .add("action", "add")
                .add("id", device.getId())
                .add("name", device.getName())
                .add("type", device.getType())
                .add("statusOnOff", device.getStatusOnOff())
                .add("statusStartStop", device.getStatusStartStop())
                .add("sensors", device.getSensors())
                .build();
        return addMessage;
    }

    private void sendToAllConnectedSessions(JsonObject message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(DeviceSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	public void deviceOn(int id, String ontoSession, String scenario, String sample_rate, JsonArray sensory_component) {
        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "onoff")
                    .add("id", device.getId())
                    .add("statusOnOff", "On")
                    .add("session", ontoSession)
                    .add("scenario", scenario)
                    .add("sample_rate", sample_rate)
                    .add("sensory_components", sensory_component)
                    .build();
            sendToSession(device.getSession(), updateDevMessage);
        }
	}

	public void deviceOff(int id) {
        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "onoff")
                    .add("id", device.getId())
                    .add("statusOnOff", "Off")
                    .build();
            sendToSession(device.getSession(), updateDevMessage);
        }
	}

	public void deviceStart(int id) {
        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            JsonObject updateDevMessage = provider.createObjectBuilder()
                .add("action", "startstop")
                .add("id", device.getId())
                .add("statusStartStop", "Start")
                .build();
            sendToSession(device.getSession(), updateDevMessage);
        }
	}

	public void deviceStop(int id) {
        JsonProvider provider = JsonProvider.provider();
        Device device = getDeviceById(id);
        if (device != null) {
            JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("action", "startstop")
                    .add("id", device.getId())
                    .add("statusStartStop", "Stop")
                    .build();
            sendToSession(device.getSession(), updateDevMessage);
        }
	}
	
	public void alert(String message) {
        JsonProvider provider = JsonProvider.provider();
        JsonObject updateDevMessage = provider.createObjectBuilder()
                    .add("alert", message)
                    .build();
        sendToAllConnectedSessions(updateDevMessage);
	}
}
