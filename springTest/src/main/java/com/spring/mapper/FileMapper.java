package com.spring.mapper;

import com.spring.bean.File;

import java.util.List;

public interface FileMapper {
    public File findFileById(Integer id);
    public Integer insertFile(File file);
    public Integer updateFile(File file);
    public Integer deleteFile(Integer id);
    public List<File> queryFileAll(File file);
    public List<File> queryFileByUserId(Integer userId);
}
