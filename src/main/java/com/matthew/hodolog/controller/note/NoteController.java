package com.matthew.hodolog.controller.note;

import com.matthew.hodolog.request.note.NoteCreate;
import com.matthew.hodolog.response.NoteResponse;
import com.matthew.hodolog.service.NoteService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/notes")
    public void post(@RequestBody @Valid NoteCreate request, HttpServletResponse response) {
        log.info("Request: {}", request);
        Long id = noteService.registerNote(request);
        response.addHeader("Location", String.valueOf(id));
    }

    @GetMapping("/notes/{noteID}")
    public NoteResponse getNote(@PathVariable Long noteID) {
        log.info("Request: /notes/{}", noteID);
        return noteService.get(noteID);
    }
}
