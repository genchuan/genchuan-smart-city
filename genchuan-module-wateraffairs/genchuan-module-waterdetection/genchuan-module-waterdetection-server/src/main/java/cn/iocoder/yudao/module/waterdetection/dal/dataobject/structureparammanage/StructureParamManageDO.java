package cn.iocoder.yudao.module.waterdetection.dal.dataobject.structureparammanage;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 构建筑物参数管理 DO
 *
 * @author zcq
 */
@TableName("gc_structure_param_manage")
@KeySequence("gc_structure_param_manage_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructureParamManageDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 构建筑物名称
     */
    private String structureName;
    /**
     * 类型(沉淀池/滤池/清水池等)
     */
    private String structureType;
    /**
     * 长度(米)
     */
    private Double length;
    /**
     * 宽度(米)
     */
    private Double width;
    /**
     * 深度(米)
     */
    private Double depth;
    /**
     * 有效容积(立方米)
     */
    private Double effectiveVolume;
    /**
     * 建设时间
     */
    private LocalDateTime constructionTime;

}