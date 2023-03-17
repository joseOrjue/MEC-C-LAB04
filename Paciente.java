class Paciente {
    private String nombre;
    private int edad;
    private String afiliacion;
    private boolean condicionEspecial;

    public Paciente(String nombre, int edad, String afiliacion, boolean condicionEspecial) {
        this.nombre = nombre;
        this.edad = edad;
        this.afiliacion = afiliacion;
        this.condicionEspecial = condicionEspecial;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getAfiliacion() {
        return this.afiliacion;
    }

    public boolean getCondicionEspecial() {
        return this.condicionEspecial;
    }
}

public class EPS {
    private Queue<Paciente> cola = new LinkedList<>();
    private int turno = 0;
    private Paciente pacienteEnCurso = null;
    private int tiempoRestante = 5;


    public void nuevoPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre y apellidos: ");
        String nombre = scanner.nextLine();

        System.out.print("Edad: ");
        int edad = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Afiliación (POS o PC): ");
        String afiliacion = scanner.nextLine();

        System.out.print("Condición especial (Sí o No): ");
        String condicionEspecialString = scanner.nextLine();
        boolean condicionEspecial = condicionEspecialString.equalsIgnoreCase("Sí");

        Paciente paciente = new Paciente(nombre, edad, afiliacion, condicionEspecial);
        asignarTurno(paciente);
    }

    public void siguientePaciente() {
        if (pacienteEnCurso == null && !cola.isEmpty()) {
            pacienteEnCurso = cola.poll();
            turno++;
            System.out.println("Turno actual: " + turno);
            System.out.println("Paciente en curso: " + pacienteEnCurso.getNombre());
            System.out.println("Tiempo restante: " + tiempoRestante + " segundos");
            System.out.println("Turnos pendientes: " + cola.size());
        }
    }
}

