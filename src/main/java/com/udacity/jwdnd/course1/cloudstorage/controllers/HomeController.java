package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteReciever;
import com.udacity.jwdnd.course1.cloudstorage.models.SendCredential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private FileService fileService;

    @RequestMapping("/home")
    public String getHome(Model model)
    {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        int userId=userService.getUser(username).getUserId();
        if(noteService.getNotes(userId).size()>0) {
            model.addAttribute("notes",noteService.getNotes(userId));
        }
        if(credentialService.getCredentials(userId).size()>0)
        {
            List<SendCredential> lc=credentialService.getCredentials(userId);
               model.addAttribute("credentials",lc);
        }
        if(fileService.getFilesCount(userId)>0)
        {
            model.addAttribute("files",fileService.getFileList(userId));
        }
        return "home";
    }
}
