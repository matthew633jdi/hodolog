package com.matthew.hodolog.controller.note;

import com.matthew.hodolog.request.note.NoteSearch;
import com.matthew.hodolog.request.note.NoteCreate;
import com.matthew.hodolog.response.NoteResponse;
import com.matthew.hodolog.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/notes")
    public ResponseEntity post(@RequestBody @Valid NoteCreate request) {
        log.info("Request: {}", request);
        Long id = noteService.registerNote(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/notes/{noteID}")
    public NoteResponse get(@PathVariable Long noteID) {
        log.info("Request: /notes/{}", noteID);
        return noteService.get(noteID);
    }

    @GetMapping("/notes")
    public List<NoteResponse> search(@ModelAttribute NoteSearch noteSearch) {
        log.info("Request: /notes, query string: {}", noteSearch);
        return noteService.getList(noteSearch);
    }
}
