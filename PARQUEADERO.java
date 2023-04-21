import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

class Vehiculo {
    private String placa;
    private String tipo;
    private int horaIngreso;
    private int numero;

    public Vehiculo(String placa, String tipo, int horaIngreso, int numero) {
        this.placa = placa;
        this.tipo = tipo;
        this.horaIngreso = horaIngreso;
        this.numero = numero;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipo() {
        return tipo;
    }

    public int getHoraIngreso() {
        return horaIngreso;
    }

    public int getNumero() {
        return numero;
    }

    public int calcularValor(int valorMinuto) {
        int tiempo = (int) Math.ceil((System.currentTimeMillis() - horaIngreso) / 60000.0);
        return tiempo * valorMinuto;
    }

    @Override
    public String toString() {
        return String.format("%d. %m %s (hora ingreso: %d)", numero, tipo, placa, horaIngreso);
    }
}


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;


public class Parqueadero {
    private List<Vehiculo> vehiculos;
    private Stack<Vehiculo> motosBicicletas;
    private Stack<Vehiculo> carros;

    public Parqueadero() {
        vehiculos = new ArrayList<>();
        motosBicicletas = new Stack<>();
        carros = new Stack<>();
    }

    public void ingresarVehiculo(String placa, String tipo) {
        int horaIngreso = (int) System.currentTimeMillis();
        int numero = vehiculos.size() + 1;
        Vehiculo vehiculo = new Vehiculo(placa, tipo, horaIngreso, numero);
        vehiculos.add(vehiculo);
        if (tipo.equals("Bicicleta") || tipo.equals("Ciclomotor") || tipo.equals("Motocicleta")) {
            motosBicicletas.push(vehiculo);
        } else {
            carros.push(vehiculo);
        }
        System.out.printf("Vehículo %d ingresado correctamente\n", numero);
    }

    public void mostrarTabla() {
        System.out.println("Tabla de vehículos:");
        System.out.println("No.  Tipo        Placa       Hora ingreso  Valor a pagar");
        for (Vehiculo vehiculo : vehiculos) {
            int valorMinuto = 0;
            switch (vehiculo.getTipo()) {
                case "Bicicleta":
                case "Ciclomotor":
                    valorMinuto = 20;
                    break;
                case "Motocicleta":
                    valorMinuto = 30;
                    break;
                case "Automóvil":
                case "Camioneta":
                    valorMinuto = 60;
                    break;
            }
            int valorPagar = vehiculo.calcularValor(valorMinuto);
            System.out.printf("%-4d %-11s %-11s %-13d $%-4d\n", vehiculo.getNumero(), vehiculo.getTipo(),
                    vehiculo.getPlaca(), vehiculo.getHoraIngreso(), valorPagar);
        }
    }

    public void mostrarVehiculos2Ruedas() {
        System.out.println("Vehículos de 2 ruedas:");
        int totalPagar = 0;
        while (!motosBicicletas.isEmpty()) {
            Vehiculo vehiculo = motosBicicletas.pop();
            int valorMinuto = 0;
            switch (vehiculo.getTipo()) {
                case "Bicicleta":
                case "Ciclomotor":
                    valorMinuto = 20;
                    break;
                case "Motocicleta":
                    valorMinuto = 30;
                    break;
            }
            int valorPagar = vehiculo.calcularValor(valorMinuto);
            totalPagar += valorPagar;
            System.out.printf("%s (valor a pagar: $%d)\n", vehiculo, valorPagar);
        }
        System.out.printf("Total a pagar por vehículos de 2 ruedas: $%d\n", totalPagar);
    }

    public void mostrarVehiculos4Ruedas() {
        System.out.println("Vehículos de   4 ruedas:");
        int totalPagar = 0;
        while (!carros.isEmpty()) {
            Vehiculo vehiculo = carros.pop();
            int valorMinuto = 0;
            switch (vehiculo.getTipo()) {
                case "Automóvil":
                case "Camioneta":
                    valorMinuto = 60;
                    break;
            }
            int valorPagar = vehiculo.calcularValor(valorMinuto);
            totalPagar += valorPagar;
            System.out.printf("%s (valor a pagar: $%d)\n", vehiculo, valorPagar);
        }
        System.out.printf("Total a pagar por vehículos de 4 ruedas: $%d\n", totalPagar);
    }

    public void mostrarCantidadVehiculosYPagoTotal() {
        int cantidadVehiculos = vehiculos.size();
        int totalPagar = 0;
        for (Vehiculo vehiculo : vehiculos) {
            int valorMinuto = 0;
            switch (vehiculo.getTipo()) {
                case "Bicicleta":
                case "Ciclomotor":
                    valorMinuto = 20;
                    break;
                case "Motocicleta":
                    valorMinuto = 30;
                    break;
                case "Automóvil":
                case "Camioneta":
                    valorMinuto = 60;
                    break;
            }
            int valorPagar = vehiculo.calcularValor(valorMinuto);
            totalPagar += valorPagar;
        }
        System.out.printf("Cantidad de vehículos en parqueadero: %d\n", cantidadVehiculos);
        System.out.printf("Valor total a pagar: $%d\n", totalPagar);
    }

    public void eliminarVehiculo(int numero) {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getNumero() == numero) {
                vehiculos.remove(vehiculo);
                if (vehiculo.getTipo().equals("Bicicleta") || vehiculo.getTipo().equals("Ciclomotor")
                        || vehiculo.getTipo().equals("Motocicleta")) {
                    motosBicicletas.remove(vehiculo);
                } else {
                    carros.remove(vehiculo);
                }
                System.out.printf("Vehículo %d eliminado correctamente\n", numero);
                return;
            }
        }
        System.out.printf("No se encontró el vehículo %d\n", numero);
    }

    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 7) {
            System.out.println("Menú:");
            System.out.println("1. Ingreso de vehículo");
            System.out.println("2. Visualizar tabla actualizada con la información ingresada e incluya el valor a pagar");
            System.out.println("3. Visualizar en una lista los vehículos de 2 ruedas e incluir el valor a pagar en total");
            System.out.println("4. Visualizar en una lista los vehículos de 4 ruedas e incluir el valor a pagar en total");
            System.out.println("5. Cantidad de vehículos en parqueadero y valor total a pagar");
            System.out.println("6. Eliminar algún vehículo");
            System.out.println("7. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la placa del vehículo: ");
                    String placa = scanner.nextLine();
                    System.out.print("Ingrese el tipo de vehículo (Bicicleta, Ciclomotor, Motocicleta, Automóvil o Camioneta): ");
                    String tipo = scanner.nextLine();
                    parqueadero.ingresarVehiculo(placa, tipo);
                    break;
                case 2:
                    parqueadero.mostrarTabla();
                    break;
                case 3:
                    parqueadero.mostrarVehiculos2Ruedas();
                    break;
                case 4:
                    parqueadero.mostrarVehiculos4Ruedas();
                    break;
                case 5:
                    parqueadero.mostrarCantidadVehiculosYPagoTotal();
                    break;
                case 6:
                    System.out.print("Ingrese el número del vehículo a eliminar: ");
                    int numero = scanner.nextInt();
                    parqueadero.eliminarVehiculo(numero);
                    break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }
}
      
