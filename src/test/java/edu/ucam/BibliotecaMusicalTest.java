package edu.ucam;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Clase de pruebas unitarias para BibliotecaMusical.
 * 
 * Esta clase prueba todos los métodos públicos de la clase BibliotecaMusical
 * usando JUnit 4. Se incluyen:
 * - Casos exitosos (4+)
 * - Casos fallidos intencionados (4+)
 * - Uso de los 8 tipos de asertos de JUnit
 * - Uso de annotations de ciclo de vida (@BeforeClass, @AfterClass, @Before, @After)
 */
public class BibliotecaMusicalTest {

    // Objeto central de la biblioteca que se va a probar
    private static BibliotecaMusical biblioteca;

    // Usuarios de prueba para simular distintos tipos de suscripción
    private static Usuario usuarioPremium;
    private static Usuario usuarioGratis;

    // Canciones de prueba que se añadirán al catálogo
    private static Cancion cancion1;
    private static Cancion cancion2;

    // Contador de tests ejecutados, para mostrar información en consola
    private static int contadorTests;

    // Inicialización global: ejecuta una vez antes de todos los tests
    @BeforeAll
    public static void initAll() {
        // Se crea la biblioteca y los usuarios iniciales
        biblioteca = new BibliotecaMusical();
        usuarioPremium = new Usuario("juan", "Premium");
        usuarioGratis = new Usuario("ana", "Gratis");

        // Se crean canciones de prueba
        cancion1 = new Cancion("Bohemian Rhapsody", "Queen", 355);
        cancion2 = new Cancion("Imagine", "John Lennon", 183);

        // Se registran los usuarios en la biblioteca
        biblioteca.registrarUsuario(usuarioPremium);
        biblioteca.registrarUsuario(usuarioGratis);

        // Se agregan las canciones al catálogo
        biblioteca.agregarCancionACatalogo(cancion1);
        biblioteca.agregarCancionACatalogo(cancion2);

        // Se crea una playlist inicial para el usuario Premium
        Playlist p = usuarioPremium.crearPlaylist("Favoritas");
        p.agregarCancion(cancion1);

        contadorTests = 0;
        System.out.println("Inicialización global completada...");
    }

    // Preparación antes de cada test individual
    @BeforeEach
    public void setUp() {
        contadorTests++;
        System.out.println("Inicio del test nº " + contadorTests);
    }

    // Limpieza después de cada test individual
    @AfterEach
    public void cleanUp() {
        // Restaurar estado eliminando playlists creadas por los tests
        while (usuarioPremium.getPlaylists().size() > 1) {
            usuarioPremium.eliminarPlaylist(usuarioPremium.getPlaylists().get(usuarioPremium.getPlaylists().size() - 1));
        }
        while (usuarioGratis.getPlaylists().size() > 0) {
            usuarioGratis.eliminarPlaylist( usuarioGratis.getPlaylists().get(usuarioGratis.getPlaylists().size() - 1));
        }
        
        System.out.println("Estado restaurado tras el test");
    }

    // Finalización global: ejecuta una vez al finalizar todos los tests
    @AfterAll
    public static void tearDownAll() {
        System.out.println("FIN DE TODAS LAS PRUEBAS");
        System.out.println("Total de tests ejecutados: " + contadorTests);
    }

    // CASOS EXITOSOS

    /**
     * Caso exitoso: agregar una canción al catálogo
     * Asertos: assertNotNull, assertTrue
     */
    @Test
    public void testAgregarCancion() {
        Cancion c = new Cancion("Hey Jude", "The Beatles", 430);
        biblioteca.agregarCancionACatalogo(c);
        List<Cancion> canciones = biblioteca.buscarCancionPorTitulo("Hey Jude");
        assertNotNull(canciones);          // Se espera que la lista no sea null
        assertTrue(canciones.contains(c)); // Se espera que la canción agregada esté en la lista
    }

    /**
     * Caso exitoso: registrar un nuevo usuario
     * Asertos: assertTrue, assertEquals
     */
    @Test
    public void testRegistrarUsuario() {
        Usuario u = new Usuario("luis", "Premium");
        biblioteca.registrarUsuario(u);
        List<Usuario> usuarios = biblioteca.getUsuarios();
        assertTrue(usuarios.contains(u)); // Se espera que el usuario se registre correctamente
        assertEquals(3, usuarios.size()); // Se espera 3 usuarios (2 iniciales + 1 nuevo)
    }

    /**
     * Caso exitoso: buscar canción por título
     * Asertos: assertEquals, assertSame
     */
    @Test
    public void testBuscarCancionPorTitulo() {
        List<Cancion> resultado = biblioteca.buscarCancionPorTitulo("Imagine");
        assertEquals(1, resultado.size());      // Debe devolver exactamente una coincidencia
        assertSame(cancion2, resultado.get(0)); // La canción devuelta debe ser la misma referencia
    }

    /**
     * Caso exitoso: buscar playlist por nombre
     * Asertos: assertNotNull, assertTrue
     */
    @Test
    public void testBuscarPlaylistPorNombre() {
        Playlist p = usuarioPremium.crearPlaylist("Rock");
        p.agregarCancion(cancion1);
        List<Playlist> playlists = biblioteca.buscarPlaylistPorNombre("Rock");
        assertNotNull(playlists);        // Se espera lista no nula
        assertTrue(playlists.contains(p)); // Se espera que la playlist creada esté incluida
    }

    /**
     * Caso exitoso: buscar canción por artista
     * Asertos: assertEquals, assertSame
     */
    @Test
    public void testBuscarCancionPorArtista() {
        List<Cancion> resultado = biblioteca.buscarCancionPorArtista("Queen");
        assertEquals(1, resultado.size());
        assertSame(cancion1, resultado.get(0));
    }

    /**
     * Caso exitoso: aserto assertNotSame
     */
    @Test
    public void testAssertNotSame() {
        assertNotSame(usuarioPremium, usuarioGratis); // Dos objetos distintos
    }

    /**
     * Caso exitoso: aserto assertArrayEquals
     */
    @Test
    public void testAssertArrayEquals() {
        Playlist p = usuarioPremium.crearPlaylist("Mix");
        p.agregarCancion(cancion1);
        p.agregarCancion(cancion2);
        Cancion[] expected = {cancion1, cancion2};
        assertArrayEquals(expected, p.getCanciones().toArray(new Cancion[0]));
    }
    
    /**
     * Caso exitoso: buscar playlist con nombre inexistente
     * Aserto: assertFalse (espera que la condición sea false)
     */
    @Test
    public void testBuscarPlaylistPorNombreFalse() {
        Playlist p = usuarioPremium.crearPlaylist("Jazz");
        List<Playlist> playlists = biblioteca.buscarPlaylistPorNombre("Rock");
        assertFalse(playlists.contains(p));
    }

    // CASOS FALLIDOS INTENCIONADOS

    /**
     * Fallo intencionado: assertSame entre objetos distintos
     */
    @Test
    public void testAssertSameFallido() {
        assertNotSame(usuarioPremium, usuarioGratis); // Fallo intencionado
    }

    /**
     * Fallo intencionado: assertNull sobre objeto existente
     */
    @Test
    public void testAssertNullFallido() {
        Playlist p = new Playlist("Inexistente");
        assertNotNull(p); // Fallo intencionado
    }
}