package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteReciever;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String postNotesResult(@ModelAttribute(value="cnote") Note note, Model model)
    {

        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        int userId=userService.getUser(username).getUserId();
        note.setUserId(userId);
        int noteId=note.getNoteId();
        if(noteId==0)
        noteService.addNote(new Note(null,note.getNoteTitle(),note.getNoteDescription(),note.getUserId()));
        else {

            noteService.updateNote(note);
        }

        return "result";
    }

    @GetMapping("notes/delete")
    public String deleteNote(@RequestParam("noteId") Integer noteId)
    {
        //System.out.println(noteId);
        if(noteService.searchNote(noteId))
            noteService.deleteNote(noteId);
        return "result";
    }

    @PostMapping("/credentials")
    public String postCredentialsResult()
    {
        return "result";
    }


}
