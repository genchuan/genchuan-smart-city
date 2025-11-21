package cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectpersoninfo;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检人员信息 DO
 *
 * @author zcq
 */
@TableName("biz_inspect_person_info")
@KeySequence("biz_inspect_person_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectPersonInfoDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 人员ID
     */
    private String personId;
    /**
     * 人员姓名
     */
    private String personName;
    /**
     * 人员账号
     */
    private String personAccount;
    /**
     * 所属部门代码
     */
    private String deptCode;
    /**
     * 所属部门名称
     */
    private String deptName;
    /**
     * 联系电话
     */
    private String personPhone;
    /**
     * 负责网格ID
     */
    private String gridId;
    /**
     * 负责网格名称
     */
    private String gridName;
    /**
     * 人员角色
     */
    private String personRole;
    /**
     * 关联设备编码
     */
    private String deviceCode;
    /**
     * 人员状态
     */
    private Boolean personStatus;
    /**
     * 入职日期
     */
    private LocalDate entryDate;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间（业务）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间（业务）
     */
    private LocalDateTime bizUpdateTime;
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