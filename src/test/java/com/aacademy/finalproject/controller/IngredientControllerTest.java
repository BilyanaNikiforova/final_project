package com.aacademy.finalproject.controller;

import com.aacademy.finalproject.converter.IngredientConverter;
import com.aacademy.finalproject.dto.IngredientDto;
import com.aacademy.finalproject.entity.Ingredient;
import com.aacademy.finalproject.service.IngredientService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = IngredientController.class)
public class IngredientControllerTest extends BaseControllerTest {

    @MockBean
    private IngredientService ingredientService;

    @MockBean
    private IngredientConverter ingredientConverter;

    @Test
    public void save() throws Exception {
        IngredientDto ingredientDto = IngredientDto.builder().id(1L).name("sugar").build();
        String requestJson = objectMapper.writeValueAsString(ingredientDto);

        when(ingredientConverter.toIngredient(any(IngredientDto.class))).thenReturn(Ingredient.builder().build());
        when(ingredientService.save(any(Ingredient.class))).thenReturn(Ingredient.builder().build());
        when(ingredientConverter.toIngredientDto(any(Ingredient.class))).thenReturn(IngredientDto.builder().id(1L).name("sugar").build());

        mockMvc.perform(post("/ingredient")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("sugar")));
    }

    @Test
    public void findById() throws Exception {
        when(ingredientService.findById(any(Long.class))).thenReturn(Ingredient.builder().build());
        when(ingredientConverter.toIngredientDto(any(Ingredient.class))).thenReturn(IngredientDto.builder()
                .id(1L)
                .name("sugar")
                .build());

        mockMvc.perform(get("/ingredient/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("sugar")));

    }

    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/ingredient/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        IngredientDto ingredientDto = IngredientDto.builder().id(1L).name("sugar").build();
        String requestJson = objectMapper.writeValueAsString(ingredientDto);

        when(ingredientConverter.toIngredientDto(any())).thenReturn(ingredientDto);

        mockMvc.perform(put("/ingredient/1")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("sugar")));
    }


}
