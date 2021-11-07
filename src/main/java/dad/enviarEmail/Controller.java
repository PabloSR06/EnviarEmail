package dad.enviarEmail;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Controller implements Initializable {

    // MODEL

    private Model model = new Model();

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
    private HBox view;

    private Email email = new SimpleEmail();

    private Alert alerta;

    public Controller() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
        loader.setController(this);
        loader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //bindings
        serverField.textProperty().bindBidirectional(model.serverProperty());
        portField.textProperty().bindBidirectional(model.portProperty());
        emailFromField.textProperty().bindBidirectional(model.emailFromProperty());
        passwordField.textProperty().bindBidirectional(model.passwordProperty());
        emailToField.textProperty().bindBidirectional(model.emailToProperty());
        subjectField.textProperty().bindBidirectional(model.subjectProperty());
        conexionCheck.selectedProperty().bindBidirectional(model.sslProperty());

    }

    @FXML
    void onSendButton(ActionEvent event) {
        try {
            System.out.println(email.isSendPartial());

            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("dad.iesdpm@gmail.com", "Minikit0$"));
            email.setSSLOnConnect(false);
            email.setFrom("dad.iesdpm@gmail.com");
            email.setSubject("TestMail");
            email.setMsg("This is a test mail ... :-)");
            email.addTo("cettebrouquauhe-1209@yopmail.com");

            // email.setHostName(model.getServer().toString());
            // email.setSmtpPort(Integer.parseInt(model.getPort().toString()));
            // email.setAuthenticator(new DefaultAuthenticator(model.getEmailFrom().toString(), model.getpassword().toString()));
            // email.setSSLOnConnect(model.isSsl());
            // email.setFrom(model.getEmailFrom().toString());
            // email.setSubject(model.getSubject().toString());
            // email.setMsg(model.getMessage().toString());
            // email.addTo(model.getEmailTo());

        
            model.emailToProperty().set("");
            model.subjectProperty().set("");
            model.messageProperty().set("");

            if(email.isSendPartial() == true){

                alerta = new Alert(AlertType.INFORMATION);
                alerta.setTitle("Mensaje enviado");
                alerta.setHeaderText("Mensaje enviado con éxito a '" + model.getEmailTo() + "'");
                
                alerta.showAndWait();

                model.emailToProperty().set("");
                model.subjectProperty().set("");
                model.messageProperty().set("");

            }

           

            email.send();
            
            System.out.println(email.isSendPartial());
        } catch (EmailException e) {

            alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("No se pudo enviar el email");
            alerta.setContentText(e.getMessage());

            alerta.showAndWait();
        }
        
    }

    @FXML
    void onCloseButton(ActionEvent event) {

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

    public HBox getView() {
        return view;
    }

}
