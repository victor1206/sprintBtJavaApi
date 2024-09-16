package org.proyecto.servicios.implementaciones;

import org.modelmapper.ModelMapper;
import org.proyecto.dtos.tarea.TareaCambiarEstado;
import org.proyecto.dtos.tarea.TareaGuardar;
import org.proyecto.dtos.tarea.TareaModificar;
import org.proyecto.dtos.tarea.TareaSalida;
import org.proyecto.modelos.Tarea;
import org.proyecto.repositorios.ITareaRepository;
import org.proyecto.servicios.interfaces.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService implements ITareaService {

    @Autowired
    private ITareaRepository tareaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TareaSalida> obtenerTodos() {
        List<Tarea> tareas = tareaRepository.findAll();

        return tareas.stream()
                .map(tarea -> modelMapper.map(tarea, TareaSalida.class))
                .toList();
    }

    @Override
    public Page<TareaSalida> obtenerTodosPaginados(Pageable pPageable) {
        Page<Tarea> page = tareaRepository.findAll(pPageable);
        List<TareaSalida> tareaDto = page.stream()
                .map(tarea -> modelMapper.map(tarea, TareaSalida.class))
                .toList();

        return new PageImpl<>(tareaDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public TareaSalida obtenerPorId(Integer pId) {
        Optional<Tarea> tarea = tareaRepository.findById(pId);
        if(tarea.isPresent())
            return modelMapper.map(tarea.get(), TareaSalida.class);
        return null;
    }

    @Override
    public List<TareaSalida> obtenerPorProyectoId(Integer id) {
        List<Tarea> tareas = tareaRepository.findByProyectoId(id);

        return tareas.stream()
                .map(tarea -> modelMapper.map(tarea, TareaSalida.class))
                .toList();
    }

    @Override
    public TareaSalida guardar(TareaGuardar tareaGuardar) {
        Tarea tarea = modelMapper.map(tareaGuardar, Tarea.class);
        tarea.setId(null);
        tarea.setEstado(Tarea.Status.APROBADA);
        return modelMapper.map(tareaRepository.save(tarea), TareaSalida.class);
    }

    @Override
    public TareaSalida editar(TareaModificar tareaModificar) {
        Tarea tarea = tareaRepository.save(modelMapper.map(tareaModificar, Tarea.class));
        return modelMapper.map(tarea, TareaSalida.class);
    }

    @Override
    public TareaSalida cambiarEstado(TareaCambiarEstado tareaCambiarEstado) {
        Tarea tarea = tareaRepository.findById(tareaCambiarEstado.getId()).get();
        tarea.setEstado(Tarea.Status.valueOf(tareaCambiarEstado.getNombre()));
        return modelMapper.map(tareaRepository.save(tarea), TareaSalida.class);
    }

    @Override
    public void eliminarPorId(Integer pId) {
        tareaRepository.deleteById(pId);
    }
}
