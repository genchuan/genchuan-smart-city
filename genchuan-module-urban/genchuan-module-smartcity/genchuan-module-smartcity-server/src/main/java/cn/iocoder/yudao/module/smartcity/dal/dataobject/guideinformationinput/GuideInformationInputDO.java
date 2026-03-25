package cn.iocoder.yudao.module.smartcity.dal.dataobject.guideinformationinput;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 指南信息录入 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_guide_information_input")
@KeySequence("smartcity_guide_information_input_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuideInformationInputDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 指南名称
     */
    private String guideName;
    /**
     * 适用范围
     */
    private String scopeOfApplication;
    /**
     * 发布单位
     */
    private String publishingUnit;
    /**
     * 发布日期
     */
    private String releaseDate;
    /**
     * 更新日期
     */
    private String updateDate;
    /**
     * 主要内容概述
     */
    private String mainContentOverview;

}