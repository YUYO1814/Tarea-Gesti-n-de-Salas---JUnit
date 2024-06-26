package com.mycompany.gestionreservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class GestorSalasTest {

    @BeforeEach
    public void setUp() {
        GestorSalas.listaDeSalas.clear();
        GestorSalas.listaDeUsuarios.clear();
        GestorSalas.listaDeReservas.clear();
    }

    @Test
    public void testCrearSala() {
        GestorSalas.crearSala("001", "Sala de Conferencias", "Planta 1");
        assertNotNull(GestorSalas.buscarSala("001"), "La sala debe ser creada y no nula");
        assertEquals("Sala de Conferencias", GestorSalas.buscarSala("001").getNombre(), "El nombre de la sala debe coincidir");
    }

    @Test
    public void testCrearUsuario() {
        GestorSalas.crearUsuario("u001", "Juan Pérez", "Ventas", "Responsable de ventas regionales");
        assertNotNull(GestorSalas.buscarUsuario("u001"), "El usuario debe ser creado y no nulo");
        assertEquals("Juan Pérez", GestorSalas.buscarUsuario("u001").getNombre(), "El nombre del usuario debe coincidir");
    }

    @Test
    public void testCrearReserva() {
        GestorSalas.crearSala("002", "Sala de Juntas", "Planta 2");
        GestorSalas.crearUsuario("u002", "Ana Gomez", "Marketing", "Directora de Marketing");
        GestorSalas.crearReserva("002", "u002", "2024-12-15");
        assertFalse(GestorSalas.listaDeReservas.isEmpty(), "Debería haber al menos una reserva");
        assertNotNull(GestorSalas.buscarReserva("002", "u002", LocalDate.parse("2024-12-15")), "La reserva debe existir");
    }
}
