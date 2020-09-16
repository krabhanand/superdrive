package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteReciever;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Base64;

@RequestMapping("/result")
@Controller
public class ResultController {
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private FileService fileService;

    @PostMapping("/files")
    public String postFilesResult(@RequestParam("fileUpload") MultipartFile file) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUser(username).getUserId();
        fileService.save(file, userId);
        return "result";
    }

    @GetMapping("files/view")
    public ResponseEntity<ByteArrayResource> viewFile(@RequestParam("fileId") Integer fileId) {
        File file=fileService.getFile(fileId);
        ByteArrayResource resource = new ByteArrayResource(file.getFile());
        return ResponseEntity.ok().contentType(new MediaType(file.getContentType()))
                .contentLength(Long.parseLong(file.getFileSize()))
                .body(resource);
    }
    @GetMapping("files/delete")
    public String deleteFile(@RequestParam("fileId") Integer fileId) {
        return "result";
    }

    @PostMapping("/notes")
    public String postNotesResult(@ModelAttribute(value = "cnote") Note note, Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUser(username).getUserId();
        note.setUserId(userId);
        int noteId = note.getNoteId();
        if (noteId == 0)
            noteService.addNote(new Note(null, note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
        else {

            noteService.updateNote(note);
        }

        return "result";
    }

    @GetMapping("notes/delete")
    public String deleteNote(@RequestParam("noteId") Integer noteId) {
        //System.out.println(noteId);
        if (noteService.searchNote(noteId))
            noteService.deleteNote(noteId);
        return "result";
    }

    @PostMapping("/credentials")
    public String postCredentialsResult(@ModelAttribute(value = "credential") Credential credential) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUser(username).getUserId();
        credential.setUserId(userId);


        //String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
        System.out.println("id credential before putting in database " + credential.getCredentialId());
        if (credential.getCredentialId() == null)
            credentialService.addCredential(credential);
        else credentialService.updateCredential(credential);
        System.out.println(credential.credentialId + "   " + credential.getUrl() + "  " + credential.getUsername() + "  " + credential.getPassword());
        return "result";
    }

    @GetMapping("/credentials/delete")
    public String deleteCredential(@RequestParam(value = "credentialId") Integer credentialId) {
        if (credentialService.searchCredential(credentialId))
            credentialService.deleteCredential(credentialId);
        return "result";
    }
}
