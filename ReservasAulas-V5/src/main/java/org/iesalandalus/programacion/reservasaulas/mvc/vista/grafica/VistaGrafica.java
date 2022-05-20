package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores.ControladorPaginaPrincipal;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VistaGrafica extends Application implements IVista{

	private static IControlador controladorMVC = null;
	@Override
	public void setControlador(IControlador controlador) {
		// TODO Auto-generated method stub
		controladorMVC = controlador;
	}

	@Override
	public void comenzar() {
		// TODO Auto-generated method stub
		launch(this.getClass());
	}

	@Override
	public void salir() {
		// TODO Auto-generated method stub
		controladorMVC.terminar();
	}

	@Override
	public void start(Stage paginaPrincipal) throws Exception {
		// TODO Auto-generated method stub
		try {
		FXMLLoader cargadorPaginaPrincipal = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/PaginaPrincipal.fxml"));
		Parent root = cargadorPaginaPrincipal.load();
		ControladorPaginaPrincipal controladorPaginaPrincipal = cargadorPaginaPrincipal.getController();
		controladorPaginaPrincipal.setControladorMVC(controladorMVC);
		controladorPaginaPrincipal.inicializa();
		
		Scene escena = new Scene(root);
		escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilos.css").toExternalForm());
		paginaPrincipal.setScene(escena);
		paginaPrincipal.setTitle("Escenario Principal");
		paginaPrincipal.setOnCloseRequest(e -> confirmarSalida(paginaPrincipal, e));
		paginaPrincipal.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void confirmarSalida(Stage paginaPrincipal, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", paginaPrincipal)) 		
			paginaPrincipal.close();
		else
			e.consume();	
	}
}