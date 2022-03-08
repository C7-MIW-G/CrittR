package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.dto.CrittrUserDTO;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.CrittrUserCriteria;
import com.miw.databeestjes.crittr.model.CrittrUserSearchResponse;
import com.miw.databeestjes.crittr.service.implementation.CrittrUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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


    /* This method creates a response object consisting of a list of user-dto's and a message.
    * If no role is selected, it assumes it is being accessed through the filter-bar and performs a search by email/username;
    * Else, it lists users by role. Setting role to null in requests should be done in the front-end  */
    @PostMapping("/api/users/search")
    @Secured("ROLE_ADMIN")
    protected ResponseEntity<?> showUsersFound(@Valid @RequestBody CrittrUserCriteria request, Errors errors) {
        CrittrUserSearchResponse response = new CrittrUserSearchResponse();

        if (errors.hasErrors()) {
            response.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(response);
        }

        List<CrittrUser> userList;
        if(request.getRole() == null) {
            userList = crittrUserDetailsService.searchByEmail(request.getEmail());
        } else {
            userList = crittrUserDetailsService.listByRole(request.getRole());
        }
        setResponseBody(userList, response);
        return ResponseEntity.ok(response);
    }

    private void setResponseBody(List<CrittrUser> userList, CrittrUserSearchResponse response) {
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
    }
}
