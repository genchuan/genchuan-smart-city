package cn.iocoder.yudao.module.waterdetection.dal.dataobject.positionresponsibility;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 岗位职责划分管理 DO
 *
 * @author zcq
 */
@TableName("gc_position_responsibility")
@KeySequence("gc_position_responsibility_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionResponsibilityDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 岗位名称
     */
    private String positionName;
    /**
     * 岗位职责描述
     */
    private String responsibilityDesc;
    /**
     * 任职要求
     */
    private String qualificationReq;
    /**
     * 所属单位
     */
    private String belongUnit;
    /**
     * 负责人
     */
    private String manager;

}