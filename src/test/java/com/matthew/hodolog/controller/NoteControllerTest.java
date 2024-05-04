package com.matthew.hodolog.controller;

import com.matthew.hodolog.controller.note.NoteController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("PBS 노트 기록")
    void postNote() throws Exception {
        String requestJsonBody = "{\n" +
                "  \"title\": \"Esther 6\",\n" +
                "  \"structure\": \"1. 1-2. hi 2. 3-6. body 3. 7-10. bye\",\n" +
                "  \"keyword\": \"following\"\n" +
                "}";
        mockMvc.perform(post("/notes").contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("검증 오류 발생")
    void invalidRequest() throws Exception {
        String requestJsonBody = "{\n" +
                "  \"title\": \"Esther 6\",\n" +
                "  \"structure\": \"1. 1-2. hi 2. 3-6. body 3. 7-10. bye\" \n" +
                "}";
        mockMvc.perform(post("/notes").contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andDo(print());
    }

    @Test
    @DisplayName("노트 읽기")
    void readNote() throws Exception {
        String noteId = "2";
        mockMvc.perform(get("/notes/" + noteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("sample title"))
                .andDo(print());
    }
}
