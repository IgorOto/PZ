package com.kino.kino.controller.api;

import com.kino.kino.service.LangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/lang")
public class LangController {

    LangService langService;

    @Autowired
    public LangController(LangService langService) {
        this.langService = langService;
    }

    @RequestMapping(
            value = "{langSlug}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> getJson(@PathVariable String langSlug) throws IOException {
        return createResponseWithJsonResourceAndStatus(langService.getLangResource(langSlug), HttpStatus.OK);
    }

    private ResponseEntity<?> createResponseWithJsonResourceAndStatus(Resource resource, HttpStatus status) throws IOException {
        return ResponseEntity.status(status)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_JSON)
                .body(new InputStreamResource(resource.getInputStream()));
    }
}
