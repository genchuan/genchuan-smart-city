package cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdevicecat;

import lombok.*;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 救援设备分类配置 DO
 *
 * @author 亘川智城
 */
@TableName("biz_emer_device_cat")
@KeySequence("biz_emer_device_cat_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmerDeviceCatDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 分类ID，唯一编码，UUID
     */
    private String deviceCatId;
    /**
     * 父分类ID，关联本表“分类ID”
     */
    private String parentCatId;
    /**
     * 分类层级，1（大类）/2（中类）/3（小类）
     */
    private String catLevel;
    /**
     * 分类编码，001 - 999（大类01 - 99、中类01 - 99、小类001 - 999）
     */
    private String catCode;
    /**
     * 分类名称，标准名称
     */
    private String catName;
    /**
     * 分类说明，描述分类范围
     */
    private String catDesc;
    /**
     * 适用区域代码，关联行政区划表
     */
    private String applyRegionCode;
    /**
     * 适用区域名称，与区域代码同步
     */
    private String applyRegionName;

}