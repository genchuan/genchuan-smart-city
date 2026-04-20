package cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectuser;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡检人员 DO
 *
 * @author zhucongquan
 */
@TableName("inspect_user")
@KeySequence("inspect_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectUserDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 所属片区
     */
    private String area;
    /**
     * 绑定设备ID
     */
    private Long deviceId;
    /**
     * 人员状态
     */
    private String status;
    /**
     * 在线状态
     */
    private String onlineStatus;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}