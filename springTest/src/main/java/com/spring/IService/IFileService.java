package com.spring.IService;

import com.spring.bean.File;

import java.util.List;

public interface IFileService {
    public File findFileById(Integer id);
    public Integer insertFile(File file);
    public Integer updateFile(File file);
    public Integer deleteFile(Integer id);
    public List<File> queryFileAll(File file);
}
