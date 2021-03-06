package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;
    @Autowired
    private UserService userService;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int addNote(Note note) {
        return noteMapper.insert(note);
    }

    public List<Note> getNotes(Integer userId){

        return noteMapper.getNotes(userId);
    }

    public void updateNote(Note note) {
        noteMapper.update(note);
    }

    public boolean searchNote(Integer noteId)
    {
        return noteMapper.getNote(noteId)!=null;
    }

    public void deleteNote(Integer noteId)

    {
        noteMapper.delete(noteId);
    }

    public String getUserforNote(Integer noteId)
    {
        Integer userId=noteMapper.getUserIdFromNote(noteId);
        return userService.getUsernameForId(userId);
    }
}
