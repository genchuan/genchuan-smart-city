package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.unitgridcode;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 单元网格编码 DO
 *
 * @author zcq
 */
@TableName("biz_unit_grid_code")
@KeySequence("biz_unit_grid_code_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitGridCodeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 编码ID
     */
    private String unitCodeId;
    /**
     * 单元网格标识码
     */
    private String unitGridCode;
    /**
     * 所属单元网格ID
     */
    private String unitGridId;
    /**
     * 行政区划完整代码
     */
    private String areaFullCode;
    /**
     * 顺序码
     */
    private String seqCode;
    /**
     * 编码生成时间
     */
    private LocalDateTime generateTime;
    /**
     * 编码状态
     */
    private String codeStatus;
    /**
     * 备注
     */
    private String remark;
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