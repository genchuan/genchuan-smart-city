package cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingpersonnel;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 检测人员信息管理 DO
 *
 * @author zcq
 */
@TableName("gc_testing_personnel")
@KeySequence("gc_testing_personnel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestingPersonnelDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 人员编号
     */
    private String staffNo;
    /**
     * 姓名
     */
    private String staffName;
    /**
     * 职称
     */
    private String position;
    /**
     * 资格证书编号
     */
    private String certificateNo;
    /**
     * 培训记录
     */
    private String trainingRecord;
    /**
     * 所属机构编号
     */
    private String agencyCode;

}