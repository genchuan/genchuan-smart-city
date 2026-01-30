package cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.apptype;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 行业应用类别 DO
 *
 * @author zhucongquan
 */
@TableName("industry_app_type")
@KeySequence("industry_app_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 上级应用类别ID
     */
    private Long parentTypeId;
    /**
     * 唯一应用编码
     */
    private String appCode;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 所属业务域
     */
    private String bizDomain;
    /**
     * 核心功能描述
     */
    private String functionDesc;
    /**
     * 访问权限编码
     */
    private String accessPermCode;
    /**
     * 状态：启用/停用
     */
    private String appStatus;
    /**
     * 业务备注
     */
    private String appRemark;

}