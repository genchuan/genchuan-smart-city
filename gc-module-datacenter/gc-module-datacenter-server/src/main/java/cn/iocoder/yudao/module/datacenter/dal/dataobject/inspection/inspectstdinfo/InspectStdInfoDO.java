package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.inspectstdinfo;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检标准信息 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_std_info")
@KeySequence("biz_inspect_std_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectStdInfoDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 标准ID
     */
    private String stdId;
    /**
     * 标准名称
     */
    private String stdName;
    /**
     * 标准编码
     */
    private String stdCode;
    /**
     * 标准类型
     */
    private String stdType;
    /**
     * 适用区域代码
     */
    private String areaCode;
    /**
     * 适用区域名称
     */
    private String areaName;
    /**
     * 检查项列表
     */
    private String ckItemList;
    /**
     * 评分规则
     */
    private String scoreRule;
    /**
     * 合格阈值
     */
    private Integer qualifiedThreshold;
    /**
     * 标准状态
     */
    private Boolean stdStatus;
    /**
     * 生效时间
     */
    private LocalDate effectiveTime;
    /**
     * 失效时间
     */
    private LocalDate expireTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private LocalDateTime bizUpdateTime;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}