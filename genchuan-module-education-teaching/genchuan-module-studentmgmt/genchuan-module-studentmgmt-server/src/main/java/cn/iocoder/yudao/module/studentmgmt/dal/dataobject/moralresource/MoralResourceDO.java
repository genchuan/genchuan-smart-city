package cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralresource;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 德育资源 DO
 *
 * @author 芋道源码
 */
@TableName("moral_resource")
@KeySequence("moral_resource_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoralResourceDO extends BaseDO {

    /**
     * 主键 ID
     */
    @TableId
    private Long id;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源类型：课程/图书/专题包
     */
    private String resourceType;
    /**
     * 资源地址
     */
    private String resourceUrl;
    /**
     * 学习人数
     */
    private Integer learnNum;
    /**
     * 学习完成率
     */
    private BigDecimal learnRate;
    /**
     * 上架时间
     */
    private LocalDateTime publishTime;
    /**
     * 下架时间
     */
    private LocalDateTime offTime;
    /**
     * 状态：未上架/已上架
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