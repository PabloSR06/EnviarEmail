package dad.enviarEmail;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Controller implements Initializable {

	// MODEL

	private Model model = new Model();

	private BooleanProperty ejecutando = new SimpleBooleanProperty();

	// VIEW
	@FXML
	private Button closeButton;

	@FXML
	private CheckBox conexionCheck;

	@FXML
	private TextField emailFromField;

	@FXML
	private TextField emailToField;

	@FXML
	private Button emptyButton;

	@FXML
	private TextArea messageButton;

	@FXML
	private TextField serverField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField portField;

	@FXML
	private Button sendButton;

	@FXML
	private TextField subjectField;

	@FXML
	private GridPane view;

	private Alert alerta;

	private Task<Void> tarea;

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings
		serverField.textProperty().bindBidirectional(model.serverProperty());
		portField.textProperty().bindBidirectional(model.portProperty());
		emailFromField.textProperty().bindBidirectional(model.emailFromProperty());
		passwordField.textProperty().bindBidirectional(model.passwordProperty());
		emailToField.textProperty().bindBidirectional(model.emailToProperty());
		messageButton.textProperty().bindBidirectional(model.messageProperty());
		subjectField.textProperty().bindBidirectional(model.subjectProperty());
		conexionCheck.selectedProperty().bindBidirectional(model.sslProperty());

//		model.setServer("smtp.gmail.com");
//		model.setSsl(true);
//		model.setPort("465");
//		model.setEmailFrom("dad.iesdpm@gmail.com");
//		model.setEmailTo("jaxeigrobugu-5237@yopmail.com");
//		model.setSubject("TestMail");
//		model.setMessage("sdafsdfaasf");

		sendButton.disableProperty().bind(ejecutando);

	}

	@FXML
	void onSendButton(ActionEvent event) {

		Email email = new SimpleEmail();

		email.setHostName(model.getServer().toString());
		email.setSmtpPort(Integer.parseInt(model.getPort().toString()));
		email.setAuthenticator(new DefaultAuthenticator(model.getEmailFrom(), model.getpassword().toString()));
		email.setSSLOnConnect(model.isSsl());

		

		tarea = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				email.setFrom(model.getEmailFrom());

				email.setSubject(model.getSubject());
				email.setMsg(model.getMessage());
				email.addTo(model.getEmailTo());
				email.send();
				return null;
			}
		};

		tarea.setOnSucceeded(e -> {
			alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Mensaje enviado");
			alerta.setHeaderText("Mensaje enviado con éxito a '" + model.getEmailTo() + "'");
			alerta.showAndWait();

			model.emailToProperty().set("");
			model.subjectProperty().set("");
			model.messageProperty().set("");
		});

		tarea.setOnFailed(e -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fallo");
			alert.setHeaderText("Algo no fue bien");
			alert.setContentText(e.getSource().getException().getMessage());
			alert.showAndWait();
		});

		ejecutando.bind(tarea.runningProperty());

		new Thread(tarea).start();

	}

	@FXML
	void onCloseButton(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void onEmptyButton(ActionEvent event) {
		model.serverProperty().set("");
		model.portProperty().set("");
		model.sslProperty().set(false);
		model.emailFromProperty().set("");
		model.passwordProperty().set("");
		model.emailToProperty().set("");
		model.subjectProperty().set("");
		model.messageProperty().set("");

	}

	public GridPane getView() {
		return view;
	}

}
