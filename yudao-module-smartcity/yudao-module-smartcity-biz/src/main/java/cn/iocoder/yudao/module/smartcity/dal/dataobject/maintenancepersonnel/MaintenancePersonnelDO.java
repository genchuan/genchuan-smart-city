package cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancepersonnel;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 养护人员 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_maintenance_personnel")
@KeySequence("smartcity_maintenance_personnel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenancePersonnelDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 人员编号
     */
    private String personnelId;
    /**
     * 人员姓名
     */
    private String personnelName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 联系方式
     */
    private String contactInformation;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 养护地块
     */
    private String maintainTheLandParcel;

}