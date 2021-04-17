package principal;
;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	   organizador organizador = new organizador();
	    // Leo y almaceno los datos en una lista
        ArrayList<ArrayList<String>> lista = organizador.crear_listas("/media/jordan/DATOS/Programacion/SO/Practica_1/prueba.csv");

        organizador.escribir_archivos(lista,"/home/jordan/Documentos/");
    }
}