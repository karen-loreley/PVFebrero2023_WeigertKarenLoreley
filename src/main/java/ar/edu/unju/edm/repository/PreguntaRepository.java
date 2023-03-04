package ar.edu.unju.edm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Pregunta;


@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {


}
