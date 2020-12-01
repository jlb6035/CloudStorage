package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid};")
    List<Note> getAllNotes(int userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{notetitle}, #{notedescription}, #{userid});")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int addNote(Note note);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    int updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    void deleteNote(int noteId);
}
