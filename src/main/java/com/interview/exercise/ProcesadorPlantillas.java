package com.interview.exercise;

import com.interview.exercise.model.Empleado;

import java.util.List;

public class ProcesadorPlantillas {

    private ProveedorMiembrosPlanilla proveedorMiembrosPlanilla;
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public ProcesadorPlantillas(ProveedorMiembrosPlanilla proveedorMiembrosPlanilla) {
        this.proveedorMiembrosPlanilla = proveedorMiembrosPlanilla;
    }
    public float procesarPlantilla() throws IllegalArgumentException {
        float aPagar = new Float(0);
        final float[] totalAPagar = {0};
        List<Empleado> empleados = proveedorMiembrosPlanilla.obtenerListaEmpleados();
        empleados.stream().filter(Empleado::isActivo).forEach(e -> {
            if(e.getId() == 0) {
                throw new IllegalArgumentException("ID inválido: " +
                        e);
            } else if (e.getMontoMensual() < 0) {
                throw new IllegalArgumentException("Monto mensial inválido: " +
                        e);
            } else if (e.getNombre() == null || e.getNombre().isEmpty()) {
                throw new IllegalArgumentException("Nombre inválido: " +
                        e);
            }
            float montoTemp = e.getMontoMensual();
            totalAPagar[0] += montoTemp;
        });
        return totalAPagar[0];
    }
}