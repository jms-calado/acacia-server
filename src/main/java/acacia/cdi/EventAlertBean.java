package acacia.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EventAlertBean {
	
	@Inject
	@AlertEvent
	private Event<String> alertEvent;
	
	public void fireAlertEvent(String message) {
		alertEvent.fire(message);
	}
}