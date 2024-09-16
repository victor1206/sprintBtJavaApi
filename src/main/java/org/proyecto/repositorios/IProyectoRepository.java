package org.proyecto.repositorios;

import org.proyecto.modelos.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProyectoRepository extends JpaRepository<Proyecto,Integer> {
}
