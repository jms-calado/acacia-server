package acacia.dataobjects;

import javax.json.JsonArray;
import javax.websocket.Session;

public class Device {

    private int id;
    private String name;
    private String statusOnOff;
    private String statusStartStop;
    private String type;
    private JsonArray sensors;
    private Session session;

    public Device() {
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getStatusOnOff() {
        return statusOnOff;
    }

    public String getStatusStartStop() {
        return statusStartStop;
    }

    public String getType() {
        return type;
    }
    
    public JsonArray getSensors() {
        return sensors;
    }
    
    public Session getSession() {
        return session;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setStatusOnOff(String status) {
        this.statusOnOff = status;
    }

    public void setStatusStartStop(String status) {
        this.statusStartStop = status;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setSensors(JsonArray sensors) {
        this.sensors = sensors;
    }
    
    public void setSession(Session session) {
        this.session = session;
    }
}
