package cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolpersonnel;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查人员 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_patrol_personnel")
@KeySequence("smartcity_patrol_personnel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolPersonnelDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 编号
     */
    private String number;
    /**
     * 姓名
     */
    private String fullName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 年龄
     */
    private String age;
    /**
     * 联系方式
     */
    private String contactInformation;
    /**
     * 上岗时间证书编号
     */
    private LocalDateTime certificateTime;
    /**
     * 巡查区域备注
     */
    private String inspectionRemarks;

}