package acacia.cdi;

import java.io.Serializable;

public class CDImessage implements Serializable{
	
	private static final long serialVersionUID = -1945223835245811534L;
	
	public String cdimessage;
	
	public CDImessage() {
	}

    public String getCDImessage() {
        return cdimessage;
    }

    public void setCDImessage(String cdimessage) {
        this.cdimessage = cdimessage;
    }
}
