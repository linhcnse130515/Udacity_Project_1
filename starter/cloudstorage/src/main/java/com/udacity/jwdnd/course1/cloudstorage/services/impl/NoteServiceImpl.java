package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {
    private final UserService userService;

    private final NoteMapper noteMapper;

    public NoteServiceImpl(UserService userService, NoteMapper noteMapper) {
        this.userService = userService;
        this.noteMapper = noteMapper;
    }

    @Override
    public String insertNote(Note note, String userName) {
        Integer userId = userService.getUser(userName).getUserId();

        if (note.getNoteId() != null) {
            int result = noteMapper.updateNote(note);
            if (result < 0) {
                return "There was an error upload your note. Please try again.";
            }
        } else {
            int result = noteMapper.insertNote(note, userId);
            if (result < 0) {
                return "There was an error edit your note. Please try again.";
            }
        }
        return null;
    }

    @Override
    public String deleteNote(Integer noteId) {
        int result = noteMapper.deleteNote(noteId);
        if (result < 0) {
            return "There was an error delete your note. Please try again.";
        }
        return null;
    }
}
