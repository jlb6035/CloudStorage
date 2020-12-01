package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CredentialController {

    @Autowired
    CredentialsService credentialsService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/addCredential", method = RequestMethod.POST)
    public String addCredential(@ModelAttribute @Valid Credential credential, BindingResult result, Authentication authentication){
        User user = userService.getUser(authentication.getPrincipal().toString());
        credential.setUserid(user.getUserid());
        if (credential.getCredentialid() > 0){
            credentialsService.updateCredentials(credential);
        } else {
            credentialsService.addCredential(credential);
        }
        return "redirect:/result?success";
    }

    @RequestMapping("/deleteCredential")
    public String deleteCredential(@RequestParam("id") int credentialId){
        credentialsService.deleteCredential(credentialId);
        return "redirect:/result?success";
    }

    @RequestMapping(value = "/decrypt-credential/{credentialid}")
    @ResponseBody
    public String decryptCredential(@PathVariable("credentialid") int credentialid){
        return credentialsService.findById(credentialid);
    }
}
