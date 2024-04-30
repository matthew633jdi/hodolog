package com.matthew.hodolog.controller;

import com.matthew.hodolog.request.NoteCreate;
import com.matthew.hodolog.response.NoteResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class NoteController {

    @PostMapping("/notes")
    public void post(@RequestBody @Valid NoteCreate request, HttpServletResponse response) {
        log.info("Request: {}", request);
        response.addHeader("location", "1");
    }

    @GetMapping("/notes/{noteID}")
    public NoteResponse getNote(@PathVariable Long noteID) {
        log.info("Request: /notes/{}", noteID);
        NoteResponse response = new NoteResponse("sample title", "empty", "love");
        return response;
    }
}
