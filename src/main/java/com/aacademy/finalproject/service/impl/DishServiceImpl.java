package com.aacademy.finalproject.service.impl;

import com.aacademy.finalproject.entity.Dish;
import com.aacademy.finalproject.entity.Ingredient;
import com.aacademy.finalproject.exception.ResourceNotFoundException;
import com.aacademy.finalproject.repository.DishRepository;
import com.aacademy.finalproject.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish findById(Long id) {

        return dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Dish with id: %s does not exists")));
    }

    @Override
    public Dish findByName(String name) {
        return dishRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Dish with name: %s does not exists")));
    }

    @Override
    public Set<Dish> findAll() {
        return new HashSet<>(dishRepository.findAll());
    }

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }



    @Override
    public Dish update(Dish dish, Long id) {
        Dish foundDish = findById(id);
        Dish updatedDish = Dish.builder()
                .id(foundDish.getId())
                .name(dish.getName())
                .build();
        return save(updatedDish);
    }

    @Override
    public void detachDishIngredient(Long dishId, Set<Long> ingredientsIds) {
        Dish foundDish = findById(dishId);
        foundDish.getIngredients()
                .removeIf(ingredient -> !ingredientsIds.contains(ingredient.getId()));
        dishRepository.save(foundDish);
    }

    @Override
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
