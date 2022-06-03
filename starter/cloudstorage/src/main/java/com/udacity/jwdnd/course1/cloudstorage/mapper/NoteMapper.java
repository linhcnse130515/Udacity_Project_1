package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} where noteid = #{noteId}")
    int updateNote(Note note);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{note.noteTitle}, #{note.noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "note.noteId")
    int insertNote(Note note, Integer userId);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    int deleteNote(Integer noteId);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<Note> getNotes(Integer userId);

}
