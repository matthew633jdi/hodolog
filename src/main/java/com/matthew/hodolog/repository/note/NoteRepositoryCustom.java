package com.matthew.hodolog.repository.note;

import com.matthew.hodolog.domain.Note;
import com.matthew.hodolog.request.note.NoteSearch;

import java.util.List;

public interface NoteRepositoryCustom {
    List<Note> getNotes(NoteSearch noteSearch);
}
