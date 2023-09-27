package com.interview.exercise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    private int id;
    private String nombre;
    private float montoMensual;
    private boolean activo;

}