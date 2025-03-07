package com.erosnox.seeurun.infrastructure.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class EmailTemplateLoader {
    public static String loadTemplate(String templateName) {
        try {
            var resource = new ClassPathResource("assets/email/" + templateName);

            var reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

            var builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o template de e-mail: " + templateName, e);
        }
    }

    public static String processTemplate(String template, String goalName) {
        return template.replace("{{goal_name}}", goalName);
    }
}
