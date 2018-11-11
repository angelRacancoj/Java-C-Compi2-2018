package backEnd.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author angelrg
 */
public class ManejadorArchivo {

    BufferedReader lectura;

    public String lecturaArchivo(String path) throws FileNotFoundException, IOException {
        System.out.println(path);
        File file = new File(path);
        String textoTotal = "";
        String linea = "";
        lectura = new BufferedReader(new FileReader(file));
        while ((linea = lectura.readLine()) != null) {
            textoTotal += (linea + "\n");
        }
        System.out.println(textoTotal);
        return textoTotal;
    }

    public void guardarArchivo(String path, String texto) throws IOException {
        FileWriter fichero = null;
        File file = new File(path);
        fichero = new FileWriter(file);
        fichero.write(texto);
        fichero.close();
    }

    public String nameFile(String textoEntrada) {
        int catENTER = 1;
        String textoSelect = "";
        String textoSalida = "";
        for (int i = (textoEntrada.length() - 1); i > -1; i--) {
            char letra = textoEntrada.charAt(i);
            if (catENTER > 0) {
                switch (letra) {
                    case '/':
                        catENTER--;
                        break;
                    default:
                        textoSelect += letra;
                }
            }
        }
        for (int i = (textoSelect.length() - 1); i > -1; i--) {
            char letra = textoSelect.charAt(i);
            textoSalida += letra;
        }
        return textoSalida;
    }

    public String extensionFile(String textoEntrada) {
        int catENTER = 1;
        String textoSelect = "";
        String textoSalida = "";
        for (int i = (textoEntrada.length() - 1); i > -1; i--) {
            char letra = textoEntrada.charAt(i);
            if (catENTER > 0) {
                switch (letra) {
                    case '.':
                        catENTER--;
                        break;
                    default:
                        textoSelect += letra;
                }
            }
        }
        for (int i = (textoSelect.length() - 1); i > -1; i--) {
            char letra = textoSelect.charAt(i);
            textoSalida += letra;
        }
        return textoSalida;
    }
}
