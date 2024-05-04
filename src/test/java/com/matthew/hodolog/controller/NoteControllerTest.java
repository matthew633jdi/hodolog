package com.matthew.hodolog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matthew.hodolog.controller.note.NoteController;
import com.matthew.hodolog.request.note.NoteCreate;
import com.matthew.hodolog.response.NoteResponse;
import com.matthew.hodolog.service.NoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    NoteService noteService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("PBS 노트 기록")
    void postNote() throws Exception {
        NoteCreate request = NoteCreate.builder()
                .chapter("Esther 6")
                .content("Content test")
                .keyword("keyword test")
                .build();

        when(noteService.registerNote(any(NoteCreate.class))).thenReturn(1L);
        String requestBody = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/notes").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isOk())
                .andExpect(header().exists("Location"))
                .andDo(print());
    }

    @Test
    @DisplayName("검증 오류 발생")
    void invalidRequest() throws Exception {
        NoteCreate request = NoteCreate.builder()
                .content("Content test")
                .build();

        String requestJsonBody = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/notes").contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andDo(print());
    }

    @Test
    @DisplayName("노트 읽기")
    void readNote() throws Exception {
        NoteResponse response = NoteResponse.builder()
                .chapter("Esther 6")
                .title("title test")
                .content("content test")
                .keyword("keyword test")
                .createdDate(LocalDateTime.now())
                .build();
        String noteId = "2";

        when(noteService.get(2L)).thenReturn(response);

        mockMvc.perform(get("/notes/" + noteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(response.getTitle()))
                .andDo(print());
    }
}
