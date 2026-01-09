package cn.iocoder.yudao.module.smartcity.dal.dataobject.reportofillegalconstructions;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 违建上报 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_report_of_illegal_constructions")
@KeySequence("smartcity_report_of_illegal_constructions_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportOfIllegalConstructionsDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 上报编号
     */
    private String reportNumber;
    /**
     * 上报人
     */
    private String reportperson;
    /**
     * 上报人联系方式
     */
    private String contactReporter;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 违建详细地址
     */
    private String detailedAddressConstruction;
    /**
     * 违建所在区域
     */
    private String constructionIsLocated;

}