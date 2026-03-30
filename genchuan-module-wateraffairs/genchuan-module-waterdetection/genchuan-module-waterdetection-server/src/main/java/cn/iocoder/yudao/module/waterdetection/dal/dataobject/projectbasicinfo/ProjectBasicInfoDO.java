package cn.iocoder.yudao.module.waterdetection.dal.dataobject.projectbasicinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 工程基本信息管理 DO
 *
 * @author zcq
 */
@TableName("gc_project_basic_info")
@KeySequence("gc_project_basic_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectBasicInfoDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 工程编码
     */
    private String projectCode;
    /**
     * 工程名称
     */
    private String projectName;
    /**
     * 设计供水规模(吨/日)
     */
    private String designCapacity;
    /**
     * 工艺类型
     */
    private String processType;
    /**
     * 投产日期
     */
    private LocalDateTime commissioningDate;
    /**
     * 管理单位
     */
    private String managementUnit;
    /**
     * 工程状态
     */
    private String projectStatus;
    /**
     * 所属行政区
     */
    private String administrativeRegion;

}