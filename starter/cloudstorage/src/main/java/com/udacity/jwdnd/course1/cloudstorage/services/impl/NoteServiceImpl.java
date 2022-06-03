package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonConstant;
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
                return CommonConstant.UPLOAD_NOTE_ERROR;
            }
        } else {
            int result = noteMapper.insertNote(note, userId);
            if (result < 0) {
                return CommonConstant.EDIT_NOTE_ERROR;
            }
        }
        return null;
    }

    @Override
    public String deleteNote(Integer noteId) {
        int result = noteMapper.deleteNote(noteId);
        if (result < 0) {
            return CommonConstant.DELETE_NOTE_ERROR;
        }
        return null;
    }
}
