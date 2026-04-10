package cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdicttype;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 业务字典分类 DO
 *
 * @author 亘川智城
 */
@TableName("biz_dict_type")
@KeySequence("biz_dict_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizDictTypeDO extends BaseDO {

    /**
     * [主键ID] 字典分类唯一标识
     */
    @TableId
    private Long id;
    /**
     * [类型编码] 如：sex、status
     */
    private String uniCode;
    /**
     * [类型名称] 如：性别、状态
     */
    private String name;
    /**
     * [分类排序]
     */
    private Integer sort;
    /**
     * [类型描述] 字典分类的详细说明
     */
    private String description;
    /**
     * [分类备注] 如：“性别字典，用于用户表性别字段”
     */
    private String remark;
    /**
     * [状态]如:0-禁用/1-启用
     */
    private Integer status;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;


}
