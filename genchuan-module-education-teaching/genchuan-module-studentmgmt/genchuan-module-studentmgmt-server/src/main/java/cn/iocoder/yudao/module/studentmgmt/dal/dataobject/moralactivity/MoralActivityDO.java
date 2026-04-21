package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralactivity;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 德育活动 DO
 *
 * @author 芋道源码
 */
@TableName("moral_activity")
@KeySequence("moral_activity_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoralActivityDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动类型：党团活动/志愿活动/其他
     */
    private String activityType;
    /**
     * 主办部门
     */
    private Long hostDept;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 参与人数
     */
    private Integer joinNum;
    /**
     * 活动照片地址
     */
    private String photo;
    /**
     * 活动详情
     */
    private String content;
    /**
     * 发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 状态：未发布/进行中/已结束
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段 1
     */
    private String reserve1;
    /**
     * 备用字段 2
     */
    private String reserve2;


}