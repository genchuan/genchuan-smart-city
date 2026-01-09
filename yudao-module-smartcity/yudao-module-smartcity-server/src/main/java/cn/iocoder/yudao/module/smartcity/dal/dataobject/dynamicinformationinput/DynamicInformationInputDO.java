package cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationinput;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 动态信息录入 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_dynamic_information_input")
@KeySequence("smartcity_dynamic_information_input_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicInformationInputDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 发布时间
     */
    private LocalDateTime releaseTime;
    /**
     * 发布主体
     */
    private String publishingSubject;
    /**
     * 内容概述
     */
    private String contentOverview;

}