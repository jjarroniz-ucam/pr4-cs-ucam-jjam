package edu.ucam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase BibliotecaMusical
 * 
 * Esta clase representa la biblioteca central del servicio de música.
 * Se encarga de gestionar el catálogo de canciones y los usuarios registrados.
 * Proporciona métodos para agregar canciones, registrar usuarios y
 * realizar búsquedas por título, artista o nombre de playlist.
 */
public class BibliotecaMusical {

    // Lista que almacena todas las canciones del catálogo
    private List<Cancion> catalogoCanciones;

    // Lista que almacena todos los usuarios registrados en el sistema
    private List<Usuario> usuarios

    /**
     * Constructor de la clase BibliotecaMusical.
     * Inicializa las listas de canciones y usuarios vacías.
     */
    public BibliotecaMusical() {
        catalogoCanciones = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    /**
     * Agrega una canción al catálogo general.
     * @param cancion Objeto Cancion a agregar al catálogo
     */
    public void agregarCancionACatalogo(Cancion cancion) {
        catalogoCanciones.add(cancion);
    }

    /**
     * Registra un nuevo usuario en la biblioteca.
     * @param usuario Objeto Usuario a registrar
     */
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    
    /**
     * Devuelve la lista de usuarios registrados en la biblioteca musical.
     * Este método permite acceder al estado interno de la clase para su consulta,
     * por ejemplo, en pruebas unitarias para verificar que la lista se actualiza
     * correctamente tras registrar nuevos usuarios.
     *
     * @return Lista de objetos Usuario actualmente registrados
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Busca canciones por su título.
     * La búsqueda no distingue mayúsculas y minúsculas.
     * @param titulo Título de la canción a buscar
     * @return Lista de canciones que coinciden con el título
     */
    public List<Cancion> buscarCancionPorTitulo(String titulo) {
        return catalogoCanciones.stream().filter(c -> c.getTitulo().equalsIgnoreCase(titulo)).collect(Collectors.toList());
    }

    /**
     * Busca canciones por su artista.
     * La búsqueda no distingue mayúsculas y minúsculas.
     * @param artista Nombre del artista
     * @return Lista de canciones que coinciden con el artista
     */
    public List<Cancion> buscarCancionPorArtista(String artista) {
        return catalogoCanciones.stream()
                .filter(c -> c.getArtista().equalsIgnoreCase(artista))
                .collect(Collectors.toList());
    }

    /**
     * Busca playlists por su nombre en todos los usuarios registrados.
     * La búsqueda no distingue mayúsculas y minúsculas.
     * @param nombre Nombre de la playlist a buscar
     * @return Lista de playlists que coinciden con el nombre
     */
    public List<Playlist> buscarPlaylistPorNombre(String nombre) {
        List<Playlist> resultado = new ArrayList<>();
        for (Usuario u : usuarios) {
            for (Playlist p : u.getPlaylists()) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    resultado.add(p);
                }
            }
        }
        return resultado;
    }
}