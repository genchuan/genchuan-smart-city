package cn.iocoder.yudao.module.waterdetection.dal.dataobject.testingagency;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 检测机构资质管理 DO
 *
 * @author zcq
 */
@TableName("gc_testing_agency")
@KeySequence("gc_testing_agency_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestingAgencyDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 机构编号
     */
    private String agencyCode;
    /**
     * 机构名称
     */
    private String agencyName;
    /**
     * 资质证书编号
     */
    private String certificateNo;
    /**
     * 检测范围
     */
    private String testingScope;
    /**
     * 有效期至
     */
    private LocalDateTime validDate;
    /**
     * 发证单位
     */
    private String issuingAuthority;

}