package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IModelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.texto.VistaTexto;

public class MainApp {		

		public static void main(String[] args) {
			System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
			IModelo modelo = new Modelo(procesarArgumentosModelo(args));
			IVista vista = procesarArgumentosVista(args);
			IControlador controlador = new Controlador(modelo, vista);
			controlador.comenzar();
		}

		private static IFuenteDatos procesarArgumentosModelo(String[] args) {
			IFuenteDatos fuenteDatos = null;
			for (String argumento : args) {
				if (argumento.equals("-fMemoria")) {
					fuenteDatos = FactoriaFuenteDatos.MEMORIA.crear();
				} else if (argumento.equals("-fFicheros")) {
					fuenteDatos = FactoriaFuenteDatos.FICHEROS.crear();
				} else if (argumento.equals("-fMongodb")) {
					fuenteDatos = FactoriaFuenteDatos.MONGODB.crear();
				}
			}
			return fuenteDatos;
		}

		private static IVista procesarArgumentosVista(String[] args) {
			IVista vista=null;
			for(String argumento : args) {
				if(argumento.equals("-vgrafica")) {
					vista=FactoriaVista.GRAFICA.crear();
				}
				else if (argumento.equals("-vtexto")) {
					vista=FactoriaVista.TEXTO.crear();
				}
			}
			return vista;
		}

}
