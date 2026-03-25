package cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionobject;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 双随机行政检查 DO
 *
 * @author 朱聪权
 */
@TableName("smartcity_inspection_object")
@KeySequence("smartcity_inspection_object_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionObjectDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 企业名称
     */
    private String entName;
    /**
     * 统一社会信用代码
     */
    private String creditCode;
    /**
     * 法定代表人
     */
    private String legalPerson;
    /**
     * 注册地址
     */
    private String regAddress;
    /**
     * 经营范围
     */
    private String businessScope;
    /**
     * 行业类型
     */
    private String industryType;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系电话
     */
    private String contactPhone;

}