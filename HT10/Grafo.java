import java.util.*;
import java.io.*;

class Grafo {
    private int[][] matrizAdyacencia;
    private HashMap<String, Integer> ciudades;
    private String[] nombresCiudades;

    // Constructor
    public Grafo(int numCiudades) {
        matrizAdyacencia = new int[numCiudades][numCiudades];
        ciudades = new HashMap<>();
        nombresCiudades = new String[numCiudades];
        for (int i = 0; i < numCiudades; i++) {
            Arrays.fill(matrizAdyacencia[i], Integer.MAX_VALUE);
            matrizAdyacencia[i][i] = 0;
        }
    }

    // Métodos
    public void agregarArco(String ciudad1, String ciudad2, int distancia) {
        int indiceCiudad1 = obtenerIndiceCiudad(ciudad1);
        int indiceCiudad2 = obtenerIndiceCiudad(ciudad2);
        matrizAdyacencia[indiceCiudad1][indiceCiudad2] = distancia;
    }

    public void eliminarArco(String ciudad1, String ciudad2) {
        int indiceCiudad1 = obtenerIndiceCiudad(ciudad1);
        int indiceCiudad2 = obtenerIndiceCiudad(ciudad2);
        matrizAdyacencia[indiceCiudad1][indiceCiudad2] = Integer.MAX_VALUE;
    }

    public void floydWarshall() {
        int numCiudades = matrizAdyacencia.length;
        for (int k = 0; k < numCiudades; k++) {
            for (int i = 0; i < numCiudades; i++) {
                for (int j = 0; j < numCiudades; j++) {
                    if (matrizAdyacencia[i][k] != Integer.MAX_VALUE && matrizAdyacencia[k][j] != Integer.MAX_VALUE
                            && matrizAdyacencia[i][k] + matrizAdyacencia[k][j] < matrizAdyacencia[i][j]) {
                        matrizAdyacencia[i][j] = matrizAdyacencia[i][k] + matrizAdyacencia[k][j];
                    }
                }
            }
        }
    }

    public String obtenerCentroGrafo() {
        int numCiudades = matrizAdyacencia.length;
        int[] maxDistancias = new int[numCiudades];
        int minIndice = 0;
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                if (matrizAdyacencia[i][j] != Integer.MAX_VALUE && matrizAdyacencia[i][j] > maxDistancias[i]) {
                    maxDistancias[i] = matrizAdyacencia[i][j];
                }
            }
            if (maxDistancias[i] < maxDistancias[minIndice]) {
                minIndice = i;
            }
        }
        return nombresCiudades[minIndice];
    }

    private int obtenerIndiceCiudad(String ciudad) {
        Integer indice = ciudades.get(ciudad);
        if (indice == null) {
            indice = ciudades.size();
            ciudades.put(ciudad, indice);
            nombresCiudades[indice] = ciudad;
        }
        return indice;
    }
}
