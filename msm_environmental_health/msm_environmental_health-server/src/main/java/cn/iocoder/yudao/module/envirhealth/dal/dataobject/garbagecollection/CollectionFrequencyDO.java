package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 收运频次字典 DO
 *
 * @author 亘川智城
 */
@TableName("sys_collection_frequency")
@KeySequence("sys_collection_frequency_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionFrequencyDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 频次编码（如：uuid-frequency-001）
     */
    private String frequencyCode;
    /**
     * 频次名称（如：每日/每周/每月/应急）
     */
    private String frequencyName;
    /**
     * 排序号
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;

}