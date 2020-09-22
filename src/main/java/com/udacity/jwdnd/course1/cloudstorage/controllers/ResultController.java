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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
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
    public String postFilesResult(@RequestParam("fileUpload") MultipartFile file, Model model) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUser(username).getUserId();
        String fileName=StringUtils.cleanPath(file.getOriginalFilename());
       // System.out.println(fileName);
        if(fileName==null || fileName.equals("")){
            model.addAttribute("emptyfileerror",true);
            return "result";
        }
        if(!fileService.checkForDuplicacy(fileName,userId))
        {
            fileService.save(file, userId);
            model.addAttribute("fileuploadsuccess",true);
        }
        else model.addAttribute("fileduplicatefail",true);

        return "result";
    }


    @GetMapping("files/view")
    public String viewFileReceiver(@RequestParam("fileId") Integer fileId, Model model){
        File file=fileService.getFile(fileId);
      //  System.out.println(file);
        if(file==null || !fileService.getUserForFile(fileId).equals(SecurityContextHolder.getContext().getAuthentication().getName()))
        {
            model.addAttribute("filenotfound",true);
            return "result";
        }
        else {
            return "forward:viewfile/"+fileId;
        }
    }

    @GetMapping("files/viewfile/{fileId}")
    public ResponseEntity viewFile(@PathVariable(value="fileId") String fileId) {
        File file=fileService.getFile(Integer.parseInt(fileId));

        ByteArrayResource resource = new ByteArrayResource(file.getFile());
        return ResponseEntity.ok()
                .contentLength(Long.parseLong(file.getFileSize()))
                .contentType(MediaType.valueOf(file.getContentType()))
                .body(resource);
    }
    @GetMapping("files/delete")
    public String deleteFile(@RequestParam("fileId") Integer fileId, Model model) {
        if(fileService.getFile(fileId)==null || !fileService.getUserForFile(fileId).equals(SecurityContextHolder.getContext().getAuthentication().getName()))
        {
            model.addAttribute("filedeletefail",true);
            return ("result");
        }
        fileService.delete(fileId);
        model.addAttribute("filedeletesuccess",true);

        return "result";
    }


    @PostMapping("/notes")
    public String postNotesResult(@ModelAttribute(value = "cnote") Note note, Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUser(username).getUserId();
        note.setUserId(userId);
        int noteId = note.getNoteId();
        if (noteId == 0)
        { noteService.addNote(new Note(null, note.getNoteTitle(), note.getNoteDescription(), note.getUserId()));
        model.addAttribute("notesavesuccess",true);}
else {
    noteService.updateNote(note);
            model.addAttribute("noteeditsuccess",true);

    }

        return "result";
    }

    @GetMapping("notes/delete")
    public String deleteNote(@RequestParam("noteId") Integer noteId, Model model) {
        //System.out.println(noteId);
        String usernameForNote=noteService.getUserforNote(noteId);
        String currentUsername=SecurityContextHolder.getContext().getAuthentication().getName();
        if (noteService.searchNote(noteId) && usernameForNote.equals(currentUsername)) {
            noteService.deleteNote(noteId);
            model.addAttribute("notedeletesuccess", true);
        }
        else model.addAttribute("notedeletefail",true);
        return "result";
    }

    @PostMapping("/credentials")
    public String postCredentialsResult(@ModelAttribute(value = "credential") Credential credential, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        int userId = userService.getUser(username).getUserId();
        credential.setUserId(userId);


        //String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
       // System.out.println("id credential before putting in database " + credential.getCredentialId());
        if (credential.getCredentialId() == null)
        {
            credentialService.addCredential(credential);
            model.addAttribute("credentialsavesuccess",true);
        }
        else {
            credentialService.updateCredential(credential);
            model.addAttribute("credentialupdatessuccess",true);
        }
       // System.out.println(credential.credentialId + "   " + credential.getUrl() + "  " + credential.getUsername() + "  " + credential.getPassword());

        return "result";
    }

    @GetMapping("/credentials/delete")
    public String deleteCredential(@RequestParam(value = "credentialId") Integer credentialId, Model model) {
        String usernameForNote=credentialService.getUserforCredential(credentialId);
        String currentUsername=SecurityContextHolder.getContext().getAuthentication().getName();
        if (credentialService.searchCredential(credentialId) && usernameForNote.equals(currentUsername))
        {
            credentialService.deleteCredential(credentialId);
            model.addAttribute("credentialdeletesuccess",true);
        }
        else model.addAttribute("credentialdeletefail",true);
        return "result";
    }
}
