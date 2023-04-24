package ith.runner_platform.service;

import ith.runner_platform.dto.ClubDto;
import ith.runner_platform.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(Long clubId);

    void updateClub(ClubDto club);
}
