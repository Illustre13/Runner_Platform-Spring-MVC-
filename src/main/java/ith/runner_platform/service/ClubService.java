package ith.runner_platform.service;

import ith.runner_platform.dto.ClubDto;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
}
