package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsService {

    @Autowired
    CredentialMapper credentialMapper;

    @Autowired
    EncryptionService encryptionService;

    public List<Credential> getAllCredentials(int userid){
        return credentialMapper.getAllCredentials(userid);
    }

    public int addCredential(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);
        return credentialMapper.addCredential(credential);
    }

    public int updateCredentials(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        return credentialMapper.updateCredential(credential);
    }

    public String findById(int id){
        Credential credential = credentialMapper.findById(id);
        String decryptPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(decryptPassword);
        return credential.getPassword();
    }

    public void deleteCredential(int credentialId){
        credentialMapper.deleteCredential(credentialId);
    }
}
