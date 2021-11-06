package dad.enviarEmail;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
    
    private StringProperty server = new SimpleStringProperty();
    private StringProperty port = new SimpleStringProperty();
    private StringProperty emailFrom = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty emailTo = new SimpleStringProperty();
    private StringProperty subject = new SimpleStringProperty();
    private StringProperty message = new SimpleStringProperty();
    private BooleanProperty ssl = new SimpleBooleanProperty();


    public final StringProperty passwordProperty() {
		return this.password;
	}
	
	public final String getpassword() {
		return this.passwordProperty().get();
	}
	
	public final void setpassword(final String password) {
		this.passwordProperty().set(password);
	}

	public final StringProperty serverProperty() {
		return this.server;
	}
	
	public final String getServer() {
		return this.serverProperty().get();
	}
	
	public final void setServer(final String server) {
		this.serverProperty().set(server);
	}
	
	public final StringProperty portProperty() {
		return this.port;
	}
	
	public final String getPort() {
		return this.portProperty().get();
	}
	
	public final void setPort(final String port) {
		this.portProperty().set(port);
	}
	
	public final StringProperty emailFromProperty() {
		return this.emailFrom;
	}
	
	public final String getEmailFrom() {
		return this.emailFromProperty().get();
	}
	
	public final void setEmailFrom(final String emailFrom) {
		this.emailFromProperty().set(emailFrom);
	}
	
	public final StringProperty emailToProperty() {
		return this.emailTo;
	}
	
	public final String getEmailTo() {
		return this.emailToProperty().get();
	}
	
	public final void setEmailTo(final String emailTo) {
		this.emailToProperty().set(emailTo);
	}
	
	public final StringProperty subjectProperty() {
		return this.subject;
	}
	
	public final String getSubject() {
		return this.subjectProperty().get();
	}
	
	public final void setSubject(final String subject) {
		this.subjectProperty().set(subject);
	}
	
	public final StringProperty messageProperty() {
		return this.message;
	}
	
	public final String getMessage() {
		return this.messageProperty().get();
	}
	
	public final void setMessage(final String message) {
		this.messageProperty().set(message);
	}
	
	public final BooleanProperty sslProperty() {
		return this.ssl;
	}
	
	public final boolean isSsl() {
		return this.sslProperty().get();
	}
	
	public final void setSsl(final boolean ssl) {
		this.sslProperty().set(ssl);
	}
	
}
