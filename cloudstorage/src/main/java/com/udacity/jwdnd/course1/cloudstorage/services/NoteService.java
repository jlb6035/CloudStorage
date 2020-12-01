package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NotesMapper notesMapper;

    public List<Note> getAllNotes(int userid){
        return notesMapper.getAllNotes(userid);
    }

    public int addNote(Note note){
        return notesMapper.addNote(note);
    }

    public void deleteNote(int note){
        notesMapper.deleteNote(note);
    }

    public void updateNote(Note note){
        notesMapper.updateNote(note);
    }
}
