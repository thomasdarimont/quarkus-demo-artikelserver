package com.github.thomasdarimont.training.artikelserver.sortiment.service;

import static com.github.thomasdarimont.training.artikelserver.sortiment.ArtikelMapper.MAPPER;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class ArtikelService {

    private final ArtikelRepository artikelRepository;

    public ArtikelModel getFindById(Long id) {
        return MAPPER.entityToModel(artikelRepository.findById(id));
    }

    public List<ArtikelModel> findAll() {
        return artikelRepository.findAll().stream().map(MAPPER::entityToModel).toList();
    }

    public List<ArtikelModel> search(ArtikelSearchModel search) {
        return artikelRepository.query(search).stream().map(MAPPER::entityToModel).toList();
    }

    @Transactional
    public ArtikelModel createArtikel(ArtikelModel model) {
        var entity = MAPPER.modelToEntity(model);
        artikelRepository.persist(entity);
        return MAPPER.entityToModel(entity);
    }
}
