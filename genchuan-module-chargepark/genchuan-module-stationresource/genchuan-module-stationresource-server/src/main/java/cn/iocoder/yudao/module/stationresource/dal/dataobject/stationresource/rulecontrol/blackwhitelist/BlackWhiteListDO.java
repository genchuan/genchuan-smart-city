package cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.blackwhitelist;

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
 * 黑白名单 DO
 *
 * @author 亘川智城
 */
@TableName("black_white_list")
@KeySequence("black_white_list_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlackWhiteListDO extends BaseDO {

    /**
     * [主键ID] 主键，自增
     */
    @TableId
    private Long id;
    /**
     * [车牌号码] 唯一车牌号
     */
    private String plateNo;
    /**
     * [名单类型] 如：白名单/黑名单
     */
    private String type;
    /**
     * [细分类型] 如：公务车/业主车/残疾人车/欠费车/逃费车
     */
    private String subType;
    /**
     * [生效时间] 名单生效开始时间
     */
    private LocalDateTime startTime;
    /**
     * [失效时间] 名单失效截止时间
     */
    private LocalDateTime endTime;
    /**
     * [状态] 如：待生效/已生效/已禁用
     */
    private String status;
    /**
     * [审核时间] 审核通过的时间
     */
    private LocalDateTime auditTime;
    /**
     * [审核人] 关联芋道用户表 system_user
     */
    private Long auditUserId;
    /**
     * [拦截次数] 黑名单车辆被拦截次数
     */
    private Integer interceptCount;
    /**
     * [证件信息] 相关证件信息描述
     */
    private String certInfo;
    /**
     * [备注] 扩展说明
     */
    private String remark;
    /**
     * [备用字段1] 预留扩展
     */
    private String reserve1;
    /**
     * [备用字段2] 预留扩展
     */
    private String reserve2;


}
