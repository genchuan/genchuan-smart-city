package cn.iocoder.yudao.module.smartcity.dal.dataobject.drainageuser;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 排水户信息 DO
 *
 * @author 超级管理员
 */
@TableName("smartcity_drainage_user")
@KeySequence("smartcity_drainage_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrainageUserDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 统一社会信用代码
     */
    private String creditCode;
    /**
     * 排水户名称
     */
    private String userName;
    /**
     * 行业类别
     *
     * 枚举 {@link TODO sm_Industry_category 对应的类}
     */
    private String industryType;
    /**
     * 排水户分类
     *
     * 枚举 {@link TODO sm_drainage_user 对应的类}
     */
    private String userType;
    /**
     * 月均用水量（吨）
     */
    private String waterUsage;
    /**
     * 排水管网接入点坐标
     */
    private String drainagePoint;
    /**
     * 预处理设施清单
     */
    private String preTreatment;

}