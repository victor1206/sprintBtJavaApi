package org.proyecto.dtos.tarea;

import lombok.Getter;
import lombok.Setter;
import org.proyecto.dtos.proyecto.ProyectoSalida;

import java.io.Serializable;

@Getter
@Setter
public class TareaSalida implements Serializable {
    private Integer id;
    private String nombre;
    private String descripcion;
    private String duracion;
    private String estado;
    private ProyectoSalida proyecto;
}
