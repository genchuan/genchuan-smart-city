package cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerplanlib;

import lombok.*;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预案库 DO
 *
 * @author 亘川智城
 */
@TableName("biz_emer_plan_lib")
@KeySequence("biz_emer_plan_lib_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmerPlanLibDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 预案ID，唯一编码，UUID生成
     */
    private String planId;
    /**
     * 预案编号，系统自动生成，格式“PLAN+年份(4位)+6位流水号”
     */
    private String planNo;
    /**
     * 预案标准名称，如“XX市2025年城市内涝应急预案”
     */
    private String planName;
    /**
     * 分类ID，关联预案分类配置表
     */
    private String catId;
    /**
     * 分类名称，拼接“大类 - 中类 - 小类”名称
     */
    private String catName;
    /**
     * 适用区域代码，关联行政区划表
     */
    private String applyRegionCode;
    /**
     * 适用区域名称，与区域代码同步
     */
    private String applyRegionName;
    /**
     * 预案版本，如“V1.0”“V2.1”
     */
    private String planVersion;
    /**
     * 生效时间，格式YYYYMMDD，预案生效日期
     */
    private LocalDate effectiveTime;
    /**
     * 失效时间，格式YYYYMMDD，预案失效日期
     */
    private LocalDate expireTime;
    /**
     * 预案文档路径，PDF格式预案存储路径
     */
    private String planDocPath;

}