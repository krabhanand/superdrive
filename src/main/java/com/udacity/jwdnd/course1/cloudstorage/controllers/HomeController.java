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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String getHome(Model model)
    {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        int userId=userService.getUser(username).getUserId();
        if(noteService.getNotes(userId).size()>0) {
            //for(Note note: noteService.getNotes(userId))
            //System.out.println(note.noteId + ":  " + note.getNoteTitle() + ":  " + note.getNoteDescription());
            model.addAttribute("notes",noteService.getNotes(userId));
            //model.addAttribute("cnote",new Note());

        }
        else
        {
            //Note temp=new Note(null,"Sample Title","Sample Description",userId);
            //temp.noteId=0;
            //List<Note> nsl=new ArrayList<Note>();
            //nsl.add(temp);
            //model.addAttribute("notes",nsl);
            //model.addAttribute("cnote",new Note());
        }
        return "home";
    }
}
