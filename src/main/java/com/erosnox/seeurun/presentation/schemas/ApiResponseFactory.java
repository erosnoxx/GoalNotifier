package com.erosnox.seeurun.presentation.schemas;

import com.erosnox.seeurun.presentation.schemas.common.ApiSchema;
import com.erosnox.seeurun.presentation.schemas.common.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

public class ApiResponseFactory {
    public static <T> ResponseEntity<ApiSchema<T>> ok(
            T data,
            Map<String, Link> links,
            String message) {
        return ResponseEntity.ok(new ApiSchema<>(data, links, message));
    }

    public static <T> ResponseEntity<ApiSchema<T>> created(
            T data,
            Map<String, Link> links,
            String message,
            String uriPath) {
        var location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(uriPath)
                .buildAndExpand(data)
                .toUri();

        return ResponseEntity.created(location)
                .body(new ApiSchema<>(data, links, message));
    }
}
