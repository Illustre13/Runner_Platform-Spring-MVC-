package ith.smv_demo_001.service;

import ith.smv_demo_001.dto.ClubDto;
import ith.smv_demo_001.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
}
