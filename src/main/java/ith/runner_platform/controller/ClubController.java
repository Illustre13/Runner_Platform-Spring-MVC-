package ith.runner_platform.controller;

import ith.runner_platform.dto.ClubDto;
import ith.runner_platform.models.Club;
import ith.runner_platform.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@Controller
public class ClubController {
    private final ClubService clubService;
    
    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }
    @GetMapping("/clubs")
    public String listClubs(Model model){
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("club", clubDto);
        return "club-details";
    }

    @GetMapping("/clubs/new")
    public String createClubForms(Model model){
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
        public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("clubs", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
            return "redirect:/clubs";
        }
    @GetMapping("clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId ){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model){
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
        public String updateClub(@PathVariable("clubId") Long clubId,
                                 @Valid @ModelAttribute("club") ClubDto club,
                                 BindingResult result){
            if(result.hasErrors()){
                return "clubs-edit";
            }
            club.setId(clubId);
            clubService.updateClub(club);
            return "redirect:/clubs";
        }

}
