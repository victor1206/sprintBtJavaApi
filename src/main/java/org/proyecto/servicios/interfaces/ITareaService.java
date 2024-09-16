package org.proyecto.servicios.interfaces;

import org.proyecto.dtos.tarea.TareaCambiarEstado;
import org.proyecto.dtos.tarea.TareaGuardar;
import org.proyecto.dtos.tarea.TareaModificar;
import org.proyecto.dtos.tarea.TareaSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITareaService {
    List<TareaSalida> obtenerTodos();

    Page<TareaSalida> obtenerTodosPaginados(Pageable pPageable);

    TareaSalida obtenerPorId(Integer pId);

    List<TareaSalida> obtenerPorProyectoId(Integer id);

    TareaSalida guardar(TareaGuardar tareaGuardar);

    TareaSalida editar(TareaModificar tareaModificar);

    TareaSalida cambiarEstado(TareaCambiarEstado tareaCambiarEstado);

    void eliminarPorId(Integer pId);
}
