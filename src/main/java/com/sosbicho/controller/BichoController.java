package com.sosbicho.controller;

import com.sosbicho.domain.Bicho;
import com.sosbicho.domain.BichoDto;
import com.sosbicho.domain.FilterForm;
import com.sosbicho.domain.User;
import com.sosbicho.repository.BichoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class BichoController {

    @Autowired
    private BichoRepository bichoRepository;

    @GetMapping("/")
    public String home(@ModelAttribute("filter") FilterForm filter, Model model) {
        model.addAttribute("filter", filter);
        model.addAttribute("bichos", bichoRepository.findByFilter(filter));
        return "home";
    }

    @GetMapping("/new-bicho")
    public String newBicho(Model model) {
        model.addAttribute("bicho", new BichoDto());
        return "new-bicho";
    }

    @PostMapping("/new-bicho")
    public String signup(@ModelAttribute("bicho") @Valid BichoDto bichoDto, Errors errors, Principal principal) {
        if (errors.hasErrors()) {
            return "new-bicho";
        }
        registerBicho(bichoDto, principal.getName());
        return "redirect:/";
    }

    private void registerBicho(BichoDto bichoDto, String owner) {
        Bicho bicho = new Bicho();
        bicho.setOwner(new User(owner, null));
        BeanUtils.copyProperties(bichoDto, bicho);
        bichoRepository.save(bicho);
    }

    @PostMapping("/adopt")
    public String adopt(Long id, FilterForm filter, Principal principal, RedirectAttributes redirectAttributes) {
        Bicho bicho = bichoRepository.findById(id).get();
        if (principal.getName().equalsIgnoreCase(bicho.getOwner().getUsername())) {
            bicho.setAdopted(!bicho.isAdopted());
            bichoRepository.save(bicho);
        }
        redirectAttributes.addFlashAttribute("filter", filter);
        return "redirect:/";
    }

    @PostMapping("/interest")
    public String interest(Long id, FilterForm filter, Principal principal, RedirectAttributes redirectAttributes) {
        Bicho bicho = bichoRepository.findById(id).get();
        User user = new User(principal.getName(), null);
        if (bicho.getInterested().contains(user)) {
            bicho.getInterested().remove(user);
        } else {
            bicho.getInterested().add(user);
        }
        bichoRepository.save(bicho);
        redirectAttributes.addFlashAttribute("filter", filter);
        return "redirect:/";
    }

}
