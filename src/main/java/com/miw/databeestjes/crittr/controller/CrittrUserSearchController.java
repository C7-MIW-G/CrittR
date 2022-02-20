package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.dto.CrittrUserDTO;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.CrittrUserCriteria;
import com.miw.databeestjes.crittr.model.CrittrUserSearchResponse;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class manages requests regarding searches of the users in the database
 */

@RestController
public class CrittrUserSearchController {

    CrittrUserDetailsService crittrUserDetailsService;

    public CrittrUserSearchController(CrittrUserDetailsService crittrUserDetailsService) {
        this.crittrUserDetailsService = crittrUserDetailsService;
    }

    @PostMapping("/api/users/search")
//    @Secured("ROLE_ADMIN")
    protected ResponseEntity<?> showUsersFound(@Valid @RequestBody CrittrUserCriteria email, Errors errors) {
        CrittrUserSearchResponse response = new CrittrUserSearchResponse();

        if (errors.hasErrors()) {
            response.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(response);
        }

        List<CrittrUser> userList = crittrUserDetailsService.searchByEmail(email.getEmail());
        List<CrittrUserDTO> userDTOS = new ArrayList<>();
        for (CrittrUser crittrUser : userList) {
            userDTOS.add(new CrittrUserDTO(crittrUser));
        }

        if(userDTOS.isEmpty()) {
            response.setMsg("No users found");
        } else {
            response.setMsg("Users found");
        }

        response.setDtos(userDTOS);
        return ResponseEntity.ok(response);
    }

}
