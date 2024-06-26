package com.mycompany.gestionreservas;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class GestorSalas {
    public static List<Sala> listaDeSalas = new ArrayList<>();
    public static List<Usuario> listaDeUsuarios = new ArrayList<>();
    public static List<Reserva> listaDeReservas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\nBienvenido al sistema de gestión de reservas");
            System.out.println("1. Gestión de Salas");
            System.out.println("2. Gestión de Usuarios");
            System.out.println("3. Gestión de Reservas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    gestionSalas(scanner);
                    break;
                case 2:
                    gestionUsuarios(scanner);
                    break;
                case 3:
                    gestionReservas(scanner);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
                    break;
            }
        } while (opcion != 4);
        scanner.close();
    }

    private static void gestionSalas(Scanner scanner) {
        System.out.println("1. Crear Sala");
        System.out.println("2. Mostrar Salas");
        System.out.println("3. Actualizar Sala");
        System.out.println("4. Eliminar Sala");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el código de la sala: ");
                String codigo = scanner.nextLine();
                System.out.print("Ingrese el nombre de la sala: ");
                String nombre = scanner.nextLine();
                System.out.print("Ingrese la ubicación de la sala: ");
                String ubicacion = scanner.nextLine();
                crearSala(codigo, nombre, ubicacion);
                break;
            case 2:
                mostrarSalas();
                break;
            case 3:
                System.out.print("Ingrese el código de la sala a actualizar: ");
                String cod = scanner.nextLine();
                actualizarSala(cod, scanner);
                break;
            case 4:
                System.out.print("Ingrese el código de la sala a eliminar: ");
                String codEliminar = scanner.nextLine();
                eliminarSala(codEliminar);
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                break;
        }
    }

    public static void crearSala(String codigo, String nombre, String ubicacion) {
        Sala nuevaSala = new Sala(codigo, nombre, ubicacion);
        listaDeSalas.add(nuevaSala);
        System.out.println("Sala creada exitosamente!");
    }

    private static void mostrarSalas() {
        System.out.println("Listado de salas:");
        if (listaDeSalas.isEmpty()) {
            System.out.println("No hay salas disponibles.");
        } else {
            for (Sala sala : listaDeSalas) {
                System.out.println("Código: " + sala.getCodigo() + " - Nombre: " + sala.getNombre() + " - Ubicación: " + sala.getUbicacion() + " - Estado: " + (sala.isReservada() ? "Reservada" : "Disponible"));
            }
        }
    }

    private static void actualizarSala(String codigo, Scanner scanner) {
        Sala sala = buscarSala(codigo);
        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }
        System.out.print("Ingrese el nuevo nombre de la sala: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la nueva ubicación de la sala: ");
        String ubicacion = scanner.nextLine();
        sala.setNombre(nombre);
        sala.setUbicacion(ubicacion);
        System.out.println("Sala actualizada exitosamente!");
    }

    private static void eliminarSala(String codigo) {
        Sala sala = buscarSala(codigo);
        if (sala != null) {
            listaDeSalas.remove(sala);
            System.out.println("Sala eliminada exitosamente!");
        } else {
            System.out.println("Sala no encontrada.");
        }
    }

    private static void gestionUsuarios(Scanner scanner) {
        System.out.println("1. Crear Usuario");
        System.out.println("2. Mostrar Usuarios");
        System.out.println("3. Actualizar Usuario");
        System.out.println("4. Eliminar Usuario");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el identificador del usuario: ");
                String id = scanner.nextLine();
                System.out.print("Ingrese el nombre del usuario: ");
                String nombre = scanner.nextLine();
                System.out.print("Ingrese el departamento del usuario: ");
                String departamento = scanner.nextLine();
                System.out.print("Ingrese una descripción del usuario: ");
                String descripcion = scanner.nextLine();
                crearUsuario(id, nombre, departamento, descripcion);
                break;
            case 2:
                mostrarUsuarios();
                break;
            case 3:
                System.out.print("Ingrese el identificador del usuario a actualizar: ");
                String idActualizar = scanner.nextLine();
                actualizarUsuario(idActualizar, scanner);
                break;
            case 4:
                System.out.print("Ingrese el identificador del usuario a eliminar: ");
                String idEliminar = scanner.nextLine();
                eliminarUsuario(idEliminar);
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                break;
        }
    }

    public static void crearUsuario(String identificador, String nombre, String departamento, String descripcion) {
        if (existeUsuario(identificador)) {
            System.out.println("Error: Ya existe un usuario con este identificador.");
            return;
        }
        Usuario nuevoUsuario = new Usuario(identificador, nombre, departamento, descripcion);
        listaDeUsuarios.add(nuevoUsuario);
        System.out.println("Usuario creado exitosamente!");
    }

    private static boolean existeUsuario(String identificador) {
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getIdentificador().equals(identificador)) {
                return true;
            }
        }
        return false;
    }
    
    private static void mostrarUsuarios() {
        System.out.println("Listado de usuarios:");
        if (listaDeUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario usuario : listaDeUsuarios) {
                System.out.println("ID: " + usuario.getIdentificador() + " - Nombre: " + usuario.getNombre() + " - Departamento: " + usuario.getDepartamento() + " - Descripción: " + usuario.getDescripcion());
            }
        }
    }

    private static void actualizarUsuario(String identificador, Scanner scanner) {
        Usuario usuario = buscarUsuario(identificador);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        System.out.print("Ingrese el nuevo nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo departamento del usuario: ");
        String departamento = scanner.nextLine();
        System.out.print("Ingrese la nueva descripción del usuario: ");
        String descripcion = scanner.nextLine();
        usuario.setNombre(nombre);
        usuario.setDepartamento(departamento);
        usuario.setDescripcion(descripcion);
        System.out.println("Usuario actualizado exitosamente!");
    }

    private static void eliminarUsuario(String identificador) {
        Usuario usuario = buscarUsuario(identificador);
        if (usuario != null) {
            listaDeUsuarios.remove(usuario);
            System.out.println("Usuario eliminado exitosamente!");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void gestionReservas(Scanner scanner) {
        System.out.println("1. Crear Reserva");
        System.out.println("2. Mostrar Reservas");
        System.out.println("3. Actualizar Reserva");
        System.out.println("4. Eliminar Reserva");
        System.out.println("5. Volver");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el código de la sala: ");
                String codigoSala = scanner.nextLine();
                System.out.print("Ingrese el identificador del usuario: ");
                String identificadorUsuario = scanner.nextLine();
                System.out.print("Ingrese la fecha de la reserva (yyyy-mm-dd): ");
                String fechaReserva = scanner.nextLine();
                crearReserva(codigoSala, identificadorUsuario, fechaReserva);
                break;
            case 2:
                mostrarReservas();
                break;
            case 3:
                System.out.print("Ingrese el código de la sala de la reserva a actualizar: ");
                String codigoReserva = scanner.nextLine();
                System.out.print("Ingrese la fecha de la reserva actual (yyyy-mm-dd): ");
                String fechaActual = scanner.nextLine();
                System.out.print("Ingrese la nueva fecha para la reserva (yyyy-mm-dd): ");
                String nuevaFecha = scanner.nextLine();
                actualizarReserva(codigoReserva, fechaActual, nuevaFecha);
                break;
            case 4:
                System.out.print("Ingrese el código de la sala de la reserva a eliminar: ");
                String codigoReservaEliminar = scanner.nextLine();
                System.out.print("Ingrese la fecha de la reserva (yyyy-mm-dd): ");
                String fechaEliminar = scanner.nextLine();
                eliminarReserva(codigoReservaEliminar, fechaEliminar);
                break;
            case 5:
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
                break;
        }
    }

    public static void crearReserva(String codigoSala, String identificadorUsuario, String fechaReserva) {
        Sala sala = buscarSala(codigoSala);
        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }
        Usuario usuario = buscarUsuario(identificadorUsuario);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaReserva);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Utilice el formato yyyy-mm-dd.");
            return;
        }
        if (reservaExiste(sala, fecha)) {
            System.out.println("Ya existe una reserva para esta sala en esta fecha.");
            return;
        }
        Reserva nuevaReserva = new Reserva(sala, usuario, fecha);
        listaDeReservas.add(nuevaReserva);
        System.out.println("Reserva creada exitosamente!");
    }

    private static void mostrarReservas() {
        System.out.println("Listado de reservas:");
        if (listaDeReservas.isEmpty()) {
            System.out.println("No hay reservas.");
        } else {
            for (Reserva reserva : listaDeReservas) {
                System.out.println("Sala: " + reserva.getSala().getNombre() + ", Usuario: " + reserva.getUsuario().getNombre() + ", Fecha: " + reserva.getFecha());
            }
        }
    }

    private static void actualizarReserva(String codigoSala, String fechaActual, String nuevaFecha) {
        Sala sala = buscarSala(codigoSala);
        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }
        LocalDate fecha, nuevaFechaParsed;
        try {
            fecha = LocalDate.parse(fechaActual);
            nuevaFechaParsed = LocalDate.parse(nuevaFecha);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Utilice el formato yyyy-mm-dd.");
            return;
        }
        for (Reserva reserva : listaDeReservas) {
            if (reserva.getSala().equals(sala) && reserva.getFecha().isEqual(fecha)) {
                reserva.setFecha(nuevaFechaParsed);
                System.out.println("Reserva actualizada exitosamente!");
                return;
            }
        }
        System.out.println("No se encontró la reserva para actualizar.");
    }

    private static void eliminarReserva(String codigoSala, String fechaReserva) {
        Sala sala = buscarSala(codigoSala);
        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaReserva);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Utilice el formato yyyy-mm-dd.");
            return;
        }
        for (Reserva reserva : listaDeReservas) {
            if (reserva.getSala().equals(sala) && reserva.getFecha().isEqual(fecha)) {
                listaDeReservas.remove(reserva);
                System.out.println("Reserva eliminada exitosamente!");
                return;
            }
        }
        System.out.println("No se encontró la reserva para eliminar.");
    }

    public static Sala buscarSala(String codigo) {
        for (Sala sala : listaDeSalas) {
            if (sala.getCodigo().equals(codigo)) {
                return sala;
            }
        }
        return null;
    }

    public static Usuario buscarUsuario(String identificador) {
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getIdentificador().equals(identificador)) {
                return usuario;
            }
        }
        return null;
    }

    private static boolean reservaExiste(Sala sala, LocalDate fecha) {
        for (Reserva reserva : listaDeReservas) {
            if (reserva.getSala().equals(sala) && reserva.getFecha().isEqual(fecha)) {
                return true;
            }
        }
        return false;
    }

    static Object buscarReserva(String string, String u002, LocalDate parse) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
