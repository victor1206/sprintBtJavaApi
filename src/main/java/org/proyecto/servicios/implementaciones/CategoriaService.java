package org.proyecto.servicios.implementaciones;

import org.modelmapper.ModelMapper;
import org.proyecto.dtos.categoria.CategoriaGuardar;
import org.proyecto.dtos.categoria.CategoriaModificar;
import org.proyecto.dtos.categoria.CategoriaSalida;
import org.proyecto.modelos.Categoria;
import org.proyecto.repositorios.ICategoriaRepository;
import org.proyecto.servicios.interfaces.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    ICategoriaRepository categoriaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CategoriaSalida> obtenerTodos()
    {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<CategoriaSalida> obtenerTodosPaginados(Pageable pPageable) {
        Page<Categoria> page = categoriaRepository.findAll(pPageable);
        List<CategoriaSalida> categoriaDto = page.stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaSalida.class))
                .toList();

        return new PageImpl<>(categoriaDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public CategoriaSalida obtenerPorId(Integer pId) {
        return modelMapper.map(categoriaRepository.findById(pId).get(), CategoriaSalida.class);
    }

    @Override
    public CategoriaSalida guardar(CategoriaGuardar categoriaGuardar) {
        Categoria categoria = categoriaRepository.save(modelMapper.map(categoriaGuardar, Categoria.class));
        return modelMapper.map(categoria, CategoriaSalida.class);
    }

    @Override
    public CategoriaSalida editar(CategoriaModificar categoriaModificar) {
        Categoria categoria = categoriaRepository.save(modelMapper.map(categoriaModificar, Categoria.class));
        return modelMapper.map(categoria, CategoriaSalida.class);
    }

    @Override
    public void eliminarPorId(Integer pId) {
        categoriaRepository.deleteById(pId);
    }
}
