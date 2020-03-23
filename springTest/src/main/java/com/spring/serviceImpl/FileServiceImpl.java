package com.spring.serviceImpl;

import com.spring.IService.IFileService;
import com.spring.bean.File;
import com.spring.mapper.FileMapper;
import com.spring.mapper.UserMapper;
import com.spring.utils.MybatisUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/19 15:58
 */
public class FileServiceImpl implements IFileService {
    private FileMapper getMapper(){
        return MybatisUtils.getMapper(FileMapper.class);
    }
    @Override
    public File findFileById(Integer id) {
        File fileById = getMapper().findFileById(id);
        if (fileById!=null){
            return fileById;
        }
        throw new RuntimeException("空字段");
    }

    @Override
    public Integer insertFile(File file) {
        if (ObjectUtils.isNotEmpty(file))
          return  getMapper().insertFile(file);
        return null;
    }

    @Override
    public Integer updateFile(File file) {
        if (ObjectUtils.isNotEmpty(file))
           return getMapper().updateFile(file);
        return null;
    }

    @Override
    public Integer deleteFile(Integer id) {
        if (id!=null)
           return getMapper().deleteFile(id);
        return null;
    }

    @Override
    public List<File> queryFileAll(File file) {
        if (ObjectUtils.isNotEmpty(file))
        return getMapper().queryFileAll(file);
        return null;
    }
}
