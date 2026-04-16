package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationconfig;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 场站配置 DO
 *
 * @author 亘川智城
 */
@TableName("station_config")
@KeySequence("station_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationConfigDO extends BaseDO {

    /**
     * [主键ID] 主键，场站配置记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [所属场站] 关联场站信息表 station_info.id
     */
    private Long stationId;
    /**
     * [配置类型] 如：通行规则/收费规则/联动规则，关联芋道字典表：station_config_type
     */
    private String type;
    /**
     * [配置内容] 配置内容JSON格式varchar
     */
    private String content;
    /**
     * [状态] 如：未生效/已生效，关联芋道字典表：station_config_status
     */
    private String status;
    /**
     * [审核时间] 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * [审核人] 关联芋道用户表 system_user.id
     */
    private Long auditUserId;
    /**
     * [同步时间] 同步时间
     */
    private LocalDateTime syncTime;
    /**
     * [备注] 场站配置相关备注说明
     */
    private String remark;
    /**
     * [备用字段1] 备用字段1
     */
    private String reserve1;
    /**
     * [备用字段2] 备用字段2
     */
    private String reserve2;


}
