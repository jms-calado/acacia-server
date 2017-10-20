package acacia.cdi;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named
//@RequestScoped
//@SessionScoped
@ApplicationScoped
public class EventAlertBean implements Serializable{

	private static final long serialVersionUID = 8053071077905965240L;
	
	private String messageEAB;
	
	@Inject	@Alert
	Event<CDImessage> alertEvent;
	
	public void fireAlertEvent() {
		CDImessage CDImsg = new CDImessage();
		CDImsg.setCDImessage(messageEAB);
		alertEvent.fire(CDImsg);
	}
	
    public String getMssageEAB() {
        return messageEAB;
    }

    public void setMessageEAB(String messageEAB) {
        this.messageEAB = messageEAB;
    }
    
    @PostConstruct
    public void init() {
        System.out.println(alertEvent);
    }
}