package edu.ucam;

/**
 * Clase Cancion
 * 
 * Representa una canción individual dentro del catálogo del servicio de música.
 * Contiene información básica como título, artista y duración.
 */
public class Cancion {

    // Título de la canción
    private String titulo;

    // Nombre del artista o grupo musical
    private String artista;

    // Duración de la canción en segundos
    private int duracion;

    /**
     * Constructor de la clase Cancion.
     * Inicializa los atributos con los valores proporcionados.
     * 
     * @param titulo Título de la canción
     * @param artista Artista de la canción
     * @param duracion Duración en segundos
     */
    public Cancion(String titulo, String artista, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
    }

    /**
     * Devuelve el título de la canción.
     * 
     * @return Título de la canción
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Devuelve el nombre del artista de la canción.
     * 
     * @return Artista de la canción
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Devuelve la duración de la canción en segundos.
     * 
     * @return Duración en segundos
     */
    public int getDuracion() {
        return duracion;
    }
}