package edu.ucam;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Playlist
 * 
 * Representa una lista de reproducción de canciones creada por un usuario.
 * Cada playlist tiene un nombre y puede contener múltiples canciones.
 */
public class Playlist {

    // Nombre de la playlist
    private String nombre;

    // Lista de canciones que forman parte de la playlist
    private List<Cancion> canciones;

    /**
     * Constructor de Playlist.
     * Inicializa la playlist con un nombre y una lista vacía de canciones.
     * 
     * @param nombre Nombre de la playlist
     */
    public Playlist(String nombre) {
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    /**
     * Devuelve el nombre de la playlist.
     * 
     * @return Nombre de la playlist
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Agrega una canción a la playlist.
     * 
     * @param c Canción a añadir
     */
    public void agregarCancion(Cancion c) {
        canciones.add(c);
    }

    /**
     * Devuelve la lista de canciones contenidas en la playlist.
     * 
     * @return Lista de canciones
     */
    public List<Cancion> getCanciones() {
        return canciones;
    }
}
