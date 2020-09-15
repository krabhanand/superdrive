package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteReciever;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String postNotesResult(@ModelAttribute(value="cnote") NoteReciever note, Model model)
    {

        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        int userId=userService.getUser(username).getUserId();
        note.setUserId(userId);
        int noteId=note.getNoteId();
        if(noteId==0)
        noteService.addNote(new Note(null,note.getNoteTitle(),note.getNoteDescription(),note.getUserId()));
        else {
            Note temp=new Note();
            temp.noteId=noteId;
            temp.setUserId(userId);
            temp.setNoteDescription(note.getNoteDescription());
            temp.setNoteTitle(note.getNoteTitle());
            noteService.updateNote(temp);
        }
        System.out.println("Inside Result Controller:   noteId:"+note.getNoteId());
        //System.out.println("path variable noteid"+note.noteId);
        System.out.println("Inside Result Controller:     userId"+note.getUserId()+"    noteTitle: "+note.getNoteTitle()+"    noteDescription: "+note.getNoteDescription());
        //else noteService.updateNote(note);
        return "result";
    }

    @PostMapping("/credentials")
    public String postCredentialsResult()
    {
        return "result";
    }


}
