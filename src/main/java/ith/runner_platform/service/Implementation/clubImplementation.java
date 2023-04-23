package ith.runner_platform.service.Implementation;

import ith.runner_platform.dto.ClubDto;
import ith.runner_platform.models.Club;
import ith.runner_platform.service.ClubService;
import ith.runner_platform.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class clubImplementation implements ClubService {
    private ClubRepository clubRepository;
   // @Autowired
    public clubImplementation(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }
    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    private  ClubDto mapToClubDto(Club club){
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updateOn(club.getUpdateOn())
                .build();
        return clubDto;
    }
}
