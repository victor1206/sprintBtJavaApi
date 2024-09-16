package org.proyecto.servicios.interfaces;

import org.proyecto.dtos.proyecto.ProyectoGuardar;
import org.proyecto.dtos.proyecto.ProyectoModificar;
import org.proyecto.dtos.proyecto.ProyectoSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProyectoService {
    List<ProyectoSalida> obtenerTodos();

    Page<ProyectoSalida> obtenerTodosPaginados(Pageable pPageable);

    ProyectoSalida obtenerPorId(Integer pId);

    ProyectoSalida guardar(ProyectoGuardar proyectoGuardar);

    ProyectoSalida editar(ProyectoModificar proyectoModificar);

    void eliminarPorId(Integer pId);
}
