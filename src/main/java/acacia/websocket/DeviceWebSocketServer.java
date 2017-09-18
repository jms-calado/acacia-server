package acacia.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import acacia.model.Device;

import javax.enterprise.context.ApplicationScoped;

import java.io.IOException;
//import javax.inject.Inject;
import java.io.StringReader;
import java.net.SocketTimeoutException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped    
@ServerEndpoint("/actions")
public class DeviceWebSocketServer {

    //@Inject
    //private DeviceSessionHandler sessionHandler;
	private static DeviceSessionHandler sessionHandler = new DeviceSessionHandler();
    
    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable error) throws IOException {
        Logger.getLogger(DeviceWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
        System.out.println(error.getCause().toString());
        /*if(error.getCause() instanceof SocketTimeoutException)
		{        	
		    sessionHandler.removeSession(session);
		    System.out.println("SocketTimeoutException: Removed session");
		}*/
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();
            //System.out.println(jsonMessage.toString());

            if ("add".equals(jsonMessage.getString("action"))) {
                Device device = new Device();
                device.setName(jsonMessage.getString("name"));
                device.setSensors(jsonMessage.getJsonArray("sensors"));
                device.setType(jsonMessage.getString("type"));
                device.setStatusOnOff(jsonMessage.getString("statusOnOff"));
                device.setStatusStartStop(jsonMessage.getString("statusStartStop"));
                device.setSession(session);
                sessionHandler.addDevice(device);
            }

            if ("remove".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.removeDevice(id);
            }

            if ("toggleOnOff".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.toggleOnOffDevice(id);
            }

            if ("toggleStartStop".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.toggleStartStopDevice(id);
            }
            
            if ("on".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                String ontoSession = jsonMessage.getString("Session");
                String scenario = jsonMessage.getString("Scenario");
                String sample_rate = jsonMessage.getString("Digital_Observation_Sample_Rate");
                JsonArray sensory_component = jsonMessage.getJsonArray("Sensory_Component");
                sessionHandler.deviceOn(id, ontoSession, scenario, sample_rate, sensory_component);
            }
            if ("off".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.deviceOff(id);
            }
            
            if ("start".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.deviceStart(id);
            }
            if ("stop".equals(jsonMessage.getString("action"))) {
                int id = (int) jsonMessage.getInt("id");
                sessionHandler.deviceStop(id);
            }
        }
    }
}    
