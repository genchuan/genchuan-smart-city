package cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.enterprisefile;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 企业档案 DO
 *
 * @author zhucongquan
 */
@TableName("enterprise_file")
@KeySequence("enterprise_file_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseFileDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 统一社会信用代码
     */
    private String creditCode;
    /**
     * 注册地址
     */
    private String registerAddr;
    /**
     * 企业类型
     */
    private String enterpriseType;
    /**
     * 企业规模
     */
    private String enterpriseScale;
    /**
     * 档案状态
     */
    private String fileStatus;
    /**
     * 员工总数
     */
    private Integer staffCount;
    /**
     * 审核人账号
     */
    private String checkUser;
    /**
     * 审核通过率
     */
    private BigDecimal checkRate;
    /**
     * 操作人账号
     */
    private String handleUser;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;


}