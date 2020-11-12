package com.domino.app.Repository;

import com.domino.app.models.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer> {
    List<Partida> findTopByPartidaId(int partidaId);
}
