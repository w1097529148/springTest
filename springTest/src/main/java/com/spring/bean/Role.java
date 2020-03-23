package com.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import netscape.security.Privilege;

import java.util.List;

/**
 * @Description T000
 * @Author Mr.Li
 * @Date 2020/3/18 22:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer id;
    private String roleName;
    private String roleDescription;
    private List<Privilege> privileges;
}
