package cn.iocoder.yudao.module.datacenter.dal.dataobject.routeversion;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 路线版本 DO
 *
 * @author zcq
 */
@TableName("gc_route_version")
@KeySequence("gc_route_version_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteVersionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 路线ID
     */
    private String routeId;
    /**
     * 路线名称
     */
    private String routeName;
    /**
     * 版本号
     */
    private String versionNumber;
    /**
     * 版本描述
     */
    private String versionDescription;
    /**
     * 变更原因
     */
    private String changeReason;
    /**
     * 变更内容
     */
    private String changeContent;
    /**
     * 生效时间
     */
    private LocalDateTime effectiveTime;

}