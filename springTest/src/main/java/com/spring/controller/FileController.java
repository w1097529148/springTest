package com.spring.controller;

import com.spring.bean.Privilege;
import com.spring.mapper.SecurityMapper;
import com.spring.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/19 17:12
 */
public class FileController{
    public static  String elEncode(Integer userId,String name,String uri,String desc){
        SecurityMapper mapper = MybatisUtils.getMapper(SecurityMapper.class);
        Integer integer=0;
        if (!StringUtils.isAllBlank(name,uri,desc)||userId!=null){
             integer = mapper.updateSecurity(new Privilege(userId, name, uri, desc));
        }
            return integer>0?"修改成功":"修改失败";
    }
}
