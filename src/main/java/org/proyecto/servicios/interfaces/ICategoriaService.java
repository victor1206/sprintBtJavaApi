package org.proyecto.servicios.interfaces;

import org.proyecto.dtos.categoria.CategoriaGuardar;
import org.proyecto.dtos.categoria.CategoriaModificar;
import org.proyecto.dtos.categoria.CategoriaSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriaService {
    List<CategoriaSalida> obtenerTodos();

    Page<CategoriaSalida> obtenerTodosPaginados(Pageable pPageable);

    CategoriaSalida obtenerPorId(Integer pId);

    CategoriaSalida guardar(CategoriaGuardar categoriaGuardar);

    CategoriaSalida editar(CategoriaModificar categoriaModificar);

    void eliminarPorId(Integer pId);
}
