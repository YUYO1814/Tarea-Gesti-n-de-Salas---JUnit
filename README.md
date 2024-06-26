# Gestión de Reservas

## Descripción

Este proyecto implementa un sistema de gestión de reservas de salas de reuniones en una organización. Permite gestionar salas, usuarios y reservas a través de una interfaz de línea de comandos.

## Funcionalidades

### Gestión de Salas
- **Crear Sala**: Permite la creación de salas especificando un código, nombre y ubicación.
- **Mostrar Salas**: Muestra una lista de todas las salas disponibles, incluyendo su estado (reservada o disponible).
- **Actualizar Sala**: Permite actualizar la información de una sala existente.
- **Eliminar Sala**: Permite eliminar una sala especificando su código.

### Gestión de Usuarios
- **Crear Usuario**: Permite la creación de usuarios especificando un identificador, nombre, departamento y descripción.
- **Mostrar Usuarios**: Muestra una lista de todos los usuarios registrados.
- **Actualizar Usuario**: Permite actualizar la información de un usuario existente.
- **Eliminar Usuario**: Permite eliminar un usuario especificando su identificador.

### Gestión de Reservas
- **Crear Reserva**: Permite la reserva de una sala por parte de un usuario en una fecha determinada.
- **Mostrar Reservas**: Muestra una lista de todas las reservas realizadas.
- **Actualizar Reserva**: Permite actualizar la fecha de una reserva existente.
- **Eliminar Reserva**: Permite eliminar una reserva especificando la sala y la fecha.

## Supuestos y Consideraciones
- No se permite la creación de dos salas con el mismo código.
- No se permite la creación de dos usuarios con el mismo identificador.
- No se permite la creación de una reserva para una sala que ya está reservada en la misma fecha.

## Instalación y Ejecución

### Prerrequisitos
- Java 22
- Apache Maven 3.8.1 o superior

### Compilación del Proyecto
mvn clean install
mvn exec:java -Dexec.mainClass="com.mycompany.gestionreservas.GestorSalas"

## Ejecución de Pruebas Unitarias
Desafortunadamente, las pruebas unitarias no pudieron ser ejecutadas correctamente debido a problemas de configuración. A continuación se presentan los archivos de prueba que se escribieron pero no se pudieron correr:

```package com.mycompany.gestionreservas;

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
        Sala sala = GestorSalas.buscarSala("001");
        assertNotNull(sala, "La sala debe ser encontrada");
        assertEquals("Sala de Conferencias", sala.getNombre(), "El nombre de la sala debe coincidir");
    }

    @Test
    public void testCrearUsuario() {
        GestorSalas.crearUsuario("u001", "Juan Perez", "IT", "Ingeniero de software");
        Usuario usuario = GestorSalas.buscarUsuario("u001");
        assertNotNull(usuario, "El usuario debe ser encontrado");
        assertEquals("Juan Perez", usuario.getNombre(), "El nombre del usuario debe coincidir");
    }

    @Test
    public void testCrearReserva() {
        GestorSalas.crearSala("002", "Sala de Juntas", "Planta 2");
        GestorSalas.crearUsuario("u002", "Ana Gomez", "Marketing", "Directora de Marketing");
        GestorSalas.crearReserva("002", "u002", LocalDate.parse("2024-12-15"));
        assertFalse(GestorSalas.listaDeReservas.isEmpty(), "Debería haber al menos una reserva");
        assertNotNull(GestorSalas.buscarReserva("002", "u002", LocalDate.parse("2024-12-15")), "La reserva debe existir");
    }
}
```
## Problemas Encontrados y Próximos Pasos
### Problemas Encontrados
- Configuración de Maven: Se encontraron dificultades para configurar Maven y ejecutar las pruebas unitarias correctamente.
- Ejecución de Pruebas Unitarias: A pesar de que se escribieron las pruebas unitarias, no se pudieron ejecutar debido a problemas de configuración.

### Próximos Pasos
- Investigación Adicional: Investigar más sobre la configuración de Maven Surefire Plugin para asegurarse de que las pruebas se ejecuten correctamente.
- Asistencia Técnica: Utilizar la herramienta de foros para resolver problemas de configuración.
- Refinamiento del Código: Continuar refinando el código y asegurarse de que todas las funcionalidades cumplan con los requisitos del proyecto.
