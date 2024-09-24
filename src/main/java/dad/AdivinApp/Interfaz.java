package dad.AdivinApp;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Interfaz extends Application {

	private TextField numeroRespuesta;
	
	private Button comprobarButton;
	
	private Label enunciadoLabel;
	
	private Alert errorAlert;
	private Alert falloAlert;
	private Alert aciertoAlert;
	
	Random random = new Random();
	int nuevoNumero = random.nextInt(100) + 1;
	int intento = 0;
	
	public void start(Stage stage) throws Exception {
		
		numeroRespuesta = new TextField();
		numeroRespuesta.setText("0");
		numeroRespuesta.setStyle("-fx-alignment: center");
		
		HBox labelHBox = new HBox(10);
		HBox textFieldHBox = new HBox(10);
		HBox botonHBox = new HBox(10);
		
		comprobarButton = new Button();
		comprobarButton.setText("Comprobar");
		comprobarButton.setDefaultButton(true);
		comprobarButton.setOnAction(this::onComprobarAction);
		
		enunciadoLabel = new Label();
		enunciadoLabel.setText("Introduce un número del 1 al 100");
		
		labelHBox.getChildren().addAll(enunciadoLabel);
		textFieldHBox.getChildren().addAll(numeroRespuesta);
		botonHBox.getChildren().addAll(comprobarButton);
		
		VBox root = new VBox();
		root.setPadding(new Insets(5));
		root.setSpacing(5);
		root.setFillWidth(false);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(labelHBox, textFieldHBox, botonHBox);
		
		errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("AdivinApp");
        errorAlert.setHeaderText("Error");
        errorAlert.setContentText("El número introducido no es válido.");
        
        falloAlert = new Alert(Alert.AlertType.WARNING);
        falloAlert.setTitle("AdivinApp");
        falloAlert.setHeaderText("¡Has fallado!");
        
        aciertoAlert = new Alert(Alert.AlertType.INFORMATION);
        aciertoAlert.setTitle("AdivinApp");
        aciertoAlert.setHeaderText("¡Has ganado!");
		
		Scene scene = new Scene(root, 640, 480);
		
		stage.setTitle("AdivinApp");
		stage.setScene(scene);
		stage.show();
		
		}
		
	private void onComprobarAction(ActionEvent e) {
		int respuesta;
		int resultado;
		
		try {
			respuesta = Integer.parseInt(numeroRespuesta.getText());
			numeroRespuesta.setText("");
			if(respuesta > 100 || respuesta < 1) {
				errorAlert.show();
			} else {
				resultado = Adivinar.comprobarNumero(respuesta, nuevoNumero);
				intento++;
				if(resultado == 0) {
					aciertoAlert.setContentText("Sólo has necesitado " + intento + " intentos." + "\n" + "\n" + "Vuelve a jugar y hazlo mejor.");
					aciertoAlert.show();
					intento = 0;
					nuevoNumero = random.nextInt(100) + 1;
				} else if (resultado == 1) {
					falloAlert.setContentText("El número a adivinar es menor que " + respuesta + "\n" + "\n" + "Vuelve a intentarlo." );
					falloAlert.show();
				} else {
					falloAlert.setContentText("El número a adivinar es mayor que " + respuesta + "\n" + "\n" + "Vuelve a intentarlo.");
					falloAlert.show();
				}
			}
			
		} catch (NumberFormatException e2) {
			errorAlert.show();
		}
		
		
		
	}
		
}



