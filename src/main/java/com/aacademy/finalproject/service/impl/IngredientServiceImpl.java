package com.aacademy.finalproject.service.impl;

import com.aacademy.finalproject.entity.Ingredient;
import com.aacademy.finalproject.exception.DuplicateRecordException;
import com.aacademy.finalproject.exception.ResourceNotFoundException;
import com.aacademy.finalproject.repository.IngredientRepository;
import com.aacademy.finalproject.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public Ingredient findByName(String name) {
        return ingredientRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Ingredient with name: %s does not exists", name)));
    }

    @Override
    public Ingredient findById(Long id) {
            return ingredientRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("Ingredient with id %d does not exists", id)));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        try {
            return ingredientRepository.save(ingredient);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateRecordException(String.format("Ingredient with name %s already exists", ingredient.getName()));
        }
    }

    @Override
    public void delete(Long id) {
        ingredientRepository.findById(id);
    }



}
