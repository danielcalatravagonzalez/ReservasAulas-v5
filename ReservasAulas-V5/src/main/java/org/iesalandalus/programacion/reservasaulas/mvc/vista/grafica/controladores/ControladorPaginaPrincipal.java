package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorPaginaPrincipal {
	private IControlador controladorMVC;
	@FXML
	private MenuItem miSalir;
	  @FXML
	    private TableColumn<Aula, String> tcNombreAula;
	  @FXML
	    private TableColumn<Aula, String> tcNumPuestos;
	  @FXML
	    private TableView<Aula> tvAulas;
	  
	  @FXML
	    private TableColumn<Profesor, String> tcNombreProfesor;
	  @FXML
	    private TableColumn<Profesor, String> tcCorreo;
	  @FXML
	    private TableColumn<Profesor, String> tcTelefono;
	  @FXML
	    private TableView<Profesor> tvProfesores;
	 
	   @FXML
	    private TableColumn<Reserva, String> tcNombreProfesorReservas;
	   @FXML
	    private TableColumn<Reserva, String> tcNombreAulaReservas;
	  @FXML
	    private TableColumn<Reserva, String> tcPermanencia;
	    @FXML
	    private TableColumn<Reserva, String> tcPuntos;
	    @FXML
	    private TableView<Reserva> tvReservas;
	  
	  private ObservableList<Aula> aulas = FXCollections.observableArrayList();
	  private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
	  private ObservableList<Reserva> reservas = FXCollections.observableArrayList();

	  @FXML
	  private void initialize() {
		  tcNombreAula.setCellValueFactory(aula -> new SimpleStringProperty(aula.getValue().getNombre()));
		  tcNumPuestos.setCellValueFactory(aula -> new SimpleStringProperty(Integer.toString(aula.getValue().getPuestos())));
		  tvAulas.setItems(aulas);
		  
		  tcNombreProfesor.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getNombre()));
		  tcCorreo.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getCorreo()));
		  tcTelefono.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getTelefono()));
		  tvProfesores.setItems(profesores);
		  
		  tcNombreProfesorReservas.setCellValueFactory(reserva -> new SimpleStringProperty(reserva.getValue().getProfesor().getNombre()));
		  tcNombreAulaReservas.setCellValueFactory(reserva -> new SimpleStringProperty(reserva.getValue().getAula().getNombre()));
		  tcPermanencia.setCellValueFactory(reserva -> new SimpleStringProperty(reserva.getValue().getPermanencia().toString()));
		  tcPuntos.setCellValueFactory(reserva -> new SimpleStringProperty(String.valueOf(reserva.getValue().getPuntos())));
		  tvReservas.setItems(reservas);
	  }
	  
	  public void setControladorMVC (IControlador controladorMVC) {
		  this.controladorMVC = controladorMVC;
	  }
	  public void inicializa () {
		  aulas.setAll(controladorMVC.getAulas());
		  profesores.setAll(controladorMVC.getProfesores());
		  reservas.setAll(controladorMVC.getReservas());
	  }
	  
	  @FXML
	    void acMiSalir(ActionEvent event) {
	    	if(Dialogos.mostrarDialogoConfirmacion("Salir", "¿Seguro que desea cerrar la aplicación?", null)) {
	    		controladorMVC.terminar();
	    		System.exit(0);
	    	}
	    }
}
