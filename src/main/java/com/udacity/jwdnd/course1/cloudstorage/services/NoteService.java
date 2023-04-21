package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.UserNote;

@Service
public class NoteService {
    
    private NoteMapper noteMapper;
    private UserService userService;
    
    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public boolean storeNote(String username, UserNote userNote) {
        boolean stored = false;
        userNote.setUserid(userService.getUserId(username));
        
        int row = noteMapper.insertNote(userNote);
        if(row >= 0) {
            stored = true;
        }
        return stored;
    }

    public List<UserNote> getNotes(String username) {
        return noteMapper.getNotes(userService.getUserId(username));
    }

    public boolean checkIfNoteTitleExists(String username, String noteTitle) {
        List<UserNote> userNotes = getNotes(username);
        for(UserNote tmpUserNote: userNotes) {
            String tmpNoteTitle = tmpUserNote.getNotetitle();
            if(noteTitle.equals(tmpNoteTitle)) {
                return true;
            }
        }
        return false;
    }

    public void editNote(UserNote userNote) {
        noteMapper.updateNote(userNote);
    }

    public void deleteNote(Integer noteid) {
        noteMapper.deleteNote(noteid);
    }

}
