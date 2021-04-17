package principal;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class organizador {

    public ArrayList<ArrayList<String>> crear_listas(String direccion_archivo){

        //Creamos la lista de los grupos
        ArrayList<ArrayList<String>> lista_grupos = new ArrayList<>();

        //Creamos el lector
        Path filePath = Paths.get(direccion_archivo);
        String[] datos_linea = null;
        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String linea;

            //Leemos linea a linea el documento
            while ((linea = br.readLine()) != null){
                datos_linea = linea.split(",");

                int i = 0;
                while (i != lista_grupos.size()){

                    //Crea el primer grupo y le agrega el primer dato
                    if (lista_grupos.size() == 0){
                        lista_grupos.add(new ArrayList<String>());
                        lista_grupos.get(0).add(linea);
                        System.out.println("primera lista creada");
                    }

                    String[] dato_grupo = lista_grupos.get(i).get(0).split(",");
                    if ( Integer.parseInt(datos_linea[0]) == Integer.parseInt(dato_grupo[0])){
                        lista_grupos.get(i).add(linea);
                        break;
                    }else{
                        i++;
                    }
                }

                if (i == lista_grupos.size()){
                    lista_grupos.add(new ArrayList<String>());
                    lista_grupos.get(i).add(linea);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lista_grupos);
        return lista_grupos;
    }

    public void escribir_archivos(ArrayList<ArrayList<String>> listas, String lugar_carpetas){
        for (int i = 0; i < listas.size(); i++) {
            String nombre = "lista_origen_";
            String origen = listas.get(i).get(0).split(",")[0];
            String nombre_archivo = nombre+origen;
            try {
                //Creamos el archivo
                File archivo = new File(lugar_carpetas+nombre_archivo+".csv");
                FileWriter escribir = new FileWriter(archivo, true);
                for (int j = 0; j < listas.get(i).size(); j++) {

                    //Obtenemos la linea de la lista actual
                    String cadena_a_escribir = listas.get(i).get(j);

                    //Agreagamos ceros al final para tener siempre 40 caracteres por linea
                    if (listas.get(i).get(j).length() != 40){
                        int tam_antiguo = listas.get(i).get(j).length();
                        int ceros_a_agregar = 40-tam_antiguo;
                        for (int k = 0; k < ceros_a_agregar; k++) {
                            cadena_a_escribir = cadena_a_escribir.concat("0");
                        }
                    }
                    escribir.write(cadena_a_escribir);
                    escribir.write("\n");
                }
                escribir.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
