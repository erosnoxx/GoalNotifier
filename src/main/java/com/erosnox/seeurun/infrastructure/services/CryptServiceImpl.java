package com.erosnox.seeurun.infrastructure.services;

import com.erosnox.seeurun.application.contracts.services.CryptService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CryptServiceImpl implements CryptService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CryptServiceImpl() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encrypt(String plainText) {
        return bCryptPasswordEncoder.encode(plainText);
    }
}
