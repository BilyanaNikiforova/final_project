package com.aacademy.finalproject.service;

import com.aacademy.finalproject.entity.Neighborhood;

import java.util.Set;

public interface NeighborhoodService {

    Neighborhood findByName(String name);

    Neighborhood findById(Long id);

    Set<Neighborhood> findAll();

    Neighborhood save(Neighborhood neighborhood);
}
