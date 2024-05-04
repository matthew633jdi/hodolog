package com.matthew.hodolog.repository.note;

import com.matthew.hodolog.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long>, NoteRepositoryCustom {
}
