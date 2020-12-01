package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userid};")
    List<Files> getAllFiles(int userid);

    @Select("SELECT fileName FROM FILES WHERE fileName = #{fileName}")
    String getFileName(String fileName);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userid, fileData) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userid}, #{fileData});")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int addFile(Files files);

    @Select("SELECT * FROM FILES WHERE fileName = #{fileName}")
    Files findByName(String fileName);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    void deleteFile(int fileid);
}
