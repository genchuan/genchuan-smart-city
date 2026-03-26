package cn.iocoder.yudao.module.waterdetection.dal.dataobject.responsibilitymanagement;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 责任单位及责任人管理 DO
 *
 * @author zcq
 */
@TableName("gc_responsibility_management")
@KeySequence("gc_responsibility_management_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsibilityManagementDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 责任类型(主体责任/监管责任/运行管理责任)
     */
    private String responsibilityType;
    /**
     * 责任单位
     */
    private String responsibleUnit;
    /**
     * 责任人姓名
     */
    private String responsiblePerson;
    /**
     * 职务
     */
    private String position;
    /**
     * 联系方式
     */
    private String contactInfo;
    /**
     * 责任范围
     */
    private String responsibilityScope;

}