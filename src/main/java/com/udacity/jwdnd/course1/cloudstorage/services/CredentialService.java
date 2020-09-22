package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.SendCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private UserService userService;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public int addCredential(Credential credential) {

        String password=credential.getPassword();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);
        return credentialMapper.insert(credential);
    }

    public List<SendCredential> getCredentials(Integer userId){

        List<Credential> lc=credentialMapper.getCredentials(userId);
        List<SendCredential> slc=new ArrayList<SendCredential>();
        for(Credential credential: lc)
        {
            String decryptedPassword=encryptionService.decryptValue(credential.getPassword(),credential.getKey());
            credential.setKey(null);
            slc.add(new SendCredential(credential.credentialId,credential.getUrl(),credential.getUsername(),credential.getPassword(),decryptedPassword,credential.getUserId()));
            credential.setPassword(decryptedPassword);
        }
        return slc;
    }

    public void updateCredential(Credential credential) {
        String password=credential.getPassword();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);
        credentialMapper.update(credential);
    }

    public boolean searchCredential(Integer credentialId)
    {
        return credentialMapper.getCredential(credentialId)!=null;
    }

    public void deleteCredential(Integer credentialId)

    {
        credentialMapper.delete(credentialId);
    }

    public String getUserforCredential(Integer credentialId) {
        Integer userId=credentialMapper.getUserIdForCredential(credentialId);
        return userService.getUsernameForId(userId);
    }
}

