package com.erosnox.seeurun.application.contracts.services;

import com.erosnox.seeurun.application.models.dto.EmailDto;

public interface EmailService {
    void sendEmail(EmailDto email);
}
