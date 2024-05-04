package com.matthew.hodolog.service;

import com.matthew.hodolog.domain.Note;
import com.matthew.hodolog.exception.PostNotFound;
import com.matthew.hodolog.repository.note.NoteRepository;
import com.matthew.hodolog.request.note.NoteSearch;
import com.matthew.hodolog.request.note.NoteCreate;
import com.matthew.hodolog.response.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Long registerNote(NoteCreate request) {
        Note note = Note.builder()
                .chapter(request.getChapter())
                .title(request.getTitle())
                .structure(request.getStructure())
                .content(request.getContent())
                .keyword(request.getKeyword())
                .build();
        Note savedNote = noteRepository.save(note);
        return savedNote.getId();
    }

    public NoteResponse get(Long id) {
        Note note = noteRepository.findById(id).orElseThrow(PostNotFound::new);
        return new NoteResponse(note);
    }

    public List<NoteResponse> getList(NoteSearch noteSearch) {
        return noteRepository.getNotes(noteSearch).stream().map(NoteResponse::new).toList();
    }
}
