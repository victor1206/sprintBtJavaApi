package org.proyecto.controladores;

import org.proyecto.dtos.categoria.CategoriaGuardar;
import org.proyecto.dtos.categoria.CategoriaModificar;
import org.proyecto.dtos.categoria.CategoriaSalida;
import org.proyecto.servicios.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<CategoriaSalida>> mostrarTodosPaginados(Pageable pPageable)
    {
        Page<CategoriaSalida> categorias = categoriaService.obtenerTodosPaginados(pPageable);
        if(categorias.hasContent())
            return ResponseEntity.ok(categorias);

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CategoriaSalida>> mostrarTodos()
    {
        List<CategoriaSalida> categorias = categoriaService.obtenerTodos();
        if(!categorias.isEmpty())
        {
           return ResponseEntity.ok(categorias);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaSalida> buscarPorId(@PathVariable Integer id)
    {
        CategoriaSalida categoria = categoriaService.obtenerPorId(id);
        if(categoria != null)
        {
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaSalida> crear(@RequestBody CategoriaGuardar categoriaGuardar)
    {
        CategoriaSalida categoria = categoriaService.guardar(categoriaGuardar);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaSalida> editar(@PathVariable Integer id,
            @RequestBody CategoriaModificar categoriaModificar)
    {
        CategoriaSalida categoria = categoriaService.editar(categoriaModificar);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id)
    {
        categoriaService.eliminarPorId(id);
        return ResponseEntity.ok("Categoria eliminada correctamente");
    }
}
