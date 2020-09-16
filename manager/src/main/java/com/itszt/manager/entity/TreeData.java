package com.itszt.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeData {
    private Integer id;
    private Integer parentId; //父级id
    private Integer type; //1.表示上移 2.表示下移
    private String classfiedName;//分类名称
    private Date CreatedTime;//创建时间
    private Date modifiedTime;//修改时间
}
