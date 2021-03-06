package com.aacademy.finalproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TownDetachNeighborhoodDto {

    private Long townId;

    private Set<Long> neighborhoodIds;
}
