package dosw.semana_5.patrones.ejercicios.ejercicio11_combo5;

public class AutoUSAAdapter implements AutoUSA {
    private AutoEuropeo autoEuropeo;

    public AutoUSAAdapter(AutoEuropeo auto) {
        this.autoEuropeo = auto;
    }

    @Override
    public double getMillas() {
        return autoEuropeo.getKilometraje() * 0.621371;
    }

    @Override
    public double getGalones() {
        return autoEuropeo.getCapacidadTanqueLitros() * 0.264172;
    }

    @Override
    public void mostrarInfoUSA() {
        System.out.printf("Auto Exportado a USA [%s] -> %.2f Millas, %.2f Galones.\n", 
                          autoEuropeo.getModelo(), getMillas(), getGalones());
    }
}
