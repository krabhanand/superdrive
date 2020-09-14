package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/result")
@Controller
public class ResultController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @PostMapping("/files")
    public String postFilesResult()
    {
        return "result";
    }

    @PostMapping("/notes")
    public String postNotesResult(@ModelAttribute Note note, Model model)
    {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        int userId=userService.getUser(username).getUserId();
        note.setUserId(userId);
        System.out.println("Inside Result Controller:     userId"+note.getUserId()+"    noteTitle: "+note.getNoteTitle()+"    noteDescription: "+note.getNoteDescription());
        noteService.addNote(note);
        return "result";
    }

    @PostMapping("/credentials")
    public String postCredentialsResult()
    {
        return "result";
    }

}
