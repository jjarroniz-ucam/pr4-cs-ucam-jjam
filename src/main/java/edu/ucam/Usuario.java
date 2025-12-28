package edu.ucam;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Usuario
 * 
 * Representa a un usuario del servicio de música.
 * Cada usuario tiene un nombre único, un tipo de suscripción
 * y puede crear y gestionar sus propias playlists.
 */
public class Usuario {

    // Nombre de usuario único
    private String nombreUsuario;

    // Tipo de suscripción: puede ser "Gratis", "Premium", etc.
    private String tipoSuscripcion;

    // Lista de playlists creadas por el usuario
    private List<Playlist> playlists;

    /**
     * Constructor de Usuario.
     * Inicializa el nombre, tipo de suscripción y la lista de playlists vacía.
     * 
     * @param nombreUsuario Identificador único del usuario
     * @param tipoSuscripcion Tipo de suscripción del usuario
     */
    public Usuario(String nombreUsuario, String tipoSuscripcion) {
        this.nombreUsuario = nombreUsuario;
        this.tipoSuscripcion = tipoSuscripcion;
        this.playlists = new ArrayList<>();
    }

    /**
     * Crea una nueva playlist con el nombre especificado
     * y la añade a la lista de playlists del usuario.
     * 
     * @param nombrePlaylist Nombre de la nueva playlist
     * @return La playlist creada
     */
    public Playlist crearPlaylist(String nombrePlaylist) {
        Playlist p = new Playlist(nombrePlaylist);
        playlists.add(p);
        return p;
    }

    /**
     * Elimina una playlist existente del usuario.
     * 
     * @param playlist Playlist a eliminar
     */
    public void eliminarPlaylist(Playlist playlist) {
        playlists.remove(playlist);
    }

    /**
     * Devuelve la lista de playlists del usuario.
     * 
     * @return Lista de playlists
     */
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Devuelve el tipo de suscripción del usuario.
     * 
     * @return Tipo de suscripción (por ejemplo, "Gratis" o "Premium")
     */
    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }
    
    /**
     * Comparte una playlist del usuario con otro usuario.
     * Solo los usuarios con suscripción "Premium" pueden compartir playlists.
     * 
     * @param playlist Playlist a compartir
     * @param otroUsuario Usuario con el que se quiere compartir la playlist
     * @return true si la playlist se compartió correctamente, false en caso contrario
     */
    public boolean compartirPlaylist(Playlist playlist, Usuario otroUsuario) {
        if ("Premium".equalsIgnoreCase(this.tipoSuscripcion) && playlists.contains(playlist)) {
            otroUsuario.getPlaylists().add(playlist);
            return true;
        }
        return false; // No es Premium o no posee la playlist
    }
}