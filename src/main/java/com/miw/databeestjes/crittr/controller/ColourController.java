package com.miw.databeestjes.crittr.controller;

import com.miw.databeestjes.crittr.model.ColourResponse;
import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserRoleStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Milo Ottenhoff <m.a.ottenhoff@st.hanze.nl
 * <p>
 * This class checks the status of the current user and sends the response back in order to set the colourtheme
 */

@RestController
public class ColourController {

    @GetMapping("/api/colours")
    public ResponseEntity<?> sendColoursResponse(@AuthenticationPrincipal CrittrUser user) {
        ColourResponse response = new ColourResponse();

        if(user == null) {
            response.setCaretaker(false);
            response.setMsg("User is not logged in");
        } else if(user.getRole() == UserRoleStatus.ROLE_CARETAKER) {
            response.setCaretaker(true);
            response.setMsg("This user is a caretaker");
        } else {
            response.setCaretaker(false);
            response.setMsg("This user is not a caretaker");
        }

        return ResponseEntity.ok(response);
    }
}
