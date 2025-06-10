package cn.iocoder.yudao.module.smartcity.dal.dataobject.institutionalinformationinput;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 机构信息录入 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_institutional_information_input")
@KeySequence("smartcity_institutional_information_input_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionalInformationInputDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 机构名称
     */
    private String institutionName;
    /**
     * 统一社会信用代码
     */
    private String unifiedSocialCreditCode;
    /**
     * 成立时间
     */
    private LocalDateTime establishmentTime;
    /**
     * 机构性质
     */
    private String natureOfInstitution;
    /**
     * 经营范围
     */
    private String natureOfBusiness;
    /**
     * 注册资本
     */
    private String registeredCapital;
    /**
     * 法定代表人
     */
    private String legalRepresentative;
    /**
     * 注册地址
     */
    private String companyRegisteredAddress;

}