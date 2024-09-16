package org.proyecto.servicios.implementaciones;

import org.modelmapper.ModelMapper;
import org.proyecto.dtos.proyecto.ProyectoGuardar;
import org.proyecto.dtos.proyecto.ProyectoModificar;
import org.proyecto.dtos.proyecto.ProyectoSalida;
import org.proyecto.modelos.Proyecto;
import org.proyecto.repositorios.IProyectoRepository;
import org.proyecto.servicios.interfaces.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService implements IProyectoService {

    @Autowired
    IProyectoRepository proyectoService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ProyectoSalida> obtenerTodos() {
        List<Proyecto> proyectos = proyectoService.findAll();
        return proyectos.stream()
                .map(proyecto -> modelMapper.map(proyecto, ProyectoSalida.class))
                .toList();
    }

    @Override
    public Page<ProyectoSalida> obtenerTodosPaginados(Pageable pPageable) {
        Page<Proyecto> page = proyectoService.findAll(pPageable);
        List<ProyectoSalida> proyectoDto = page.stream()
                .map(proyecto -> modelMapper.map(proyecto, ProyectoSalida.class))
                .toList();

        return new PageImpl<>(proyectoDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public ProyectoSalida obtenerPorId(Integer pId) {
        Optional<Proyecto> proyecto = proyectoService.findById(pId);
        if(proyecto.isPresent())
            return modelMapper.map(proyecto.get(), ProyectoSalida.class);
        return null;
    }

    @Override
    public ProyectoSalida guardar(ProyectoGuardar proyectoGuardar) {
        Proyecto proyecto = modelMapper.map(proyectoGuardar, Proyecto.class);
        proyecto.setId(null);

        return modelMapper.map(proyectoService.save(proyecto), ProyectoSalida.class);
    }

    @Override
    public ProyectoSalida editar(ProyectoModificar proyectoModificar) {
        Proyecto proyecto = proyectoService.save(modelMapper.map(proyectoModificar, Proyecto.class));
        return modelMapper.map(proyecto, ProyectoSalida.class);
    }

    @Override
    public void eliminarPorId(Integer pId) {
        proyectoService.deleteById(pId);
    }
}
