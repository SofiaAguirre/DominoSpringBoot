package com.domino.app.dao;

import com.domino.app.Repository.PartidaRepository;
import com.domino.app.models.Partida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidasDAO {

    @Autowired
    private PartidaRepository partidaRepository;

    public Optional<Partida> getPartidaById(int id){
        return partidaRepository.findById(id);
    }

    public Partida savePartida(Partida partida){
        return partidaRepository.save(partida);
    }



}
