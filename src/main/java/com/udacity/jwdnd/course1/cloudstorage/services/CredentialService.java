package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    @Autowired
    private EncryptionService encryptionService;

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

    public List<Credential> getCredentials(Integer userId){

        List<Credential> lc=credentialMapper.getCredentials(userId);

        for(Credential credential: lc)
        {
            String decryptedPassword=encryptionService.decryptValue(credential.getPassword(),credential.getKey());
            credential.setKey(null);
            credential.setPassword(decryptedPassword);
        }
        return lc;
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
}

