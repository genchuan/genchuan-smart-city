package cn.iocoder.yudao.module.industry.dal.dataobject.urban.dashboard.topic.munifac.urbancomp;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件表（城市部件 Urban Component）DO
 */
@TableName("biz_mng_comp")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrbanCompDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 部件ID（唯一编码）
     */
    private String mngCompId;

    /**
     * 部件标识码：6位行政码 + 2位大类码 + 3位小类码 + 5位顺序码
     */
    private String compCode;

    /**
     * 部件名称，如“XX路电力井盖”
     */
    private String compName;

    /**
     * 小类ID
     */
    private String minorId;

    /**
     * 小类名称
     */
    private String minorName;

    /**
     * 主管部门代码
     */
    private String deptCode;

    /**
     * 主管部门名称
     */
    private String deptName;

    /**
     * 单元网格ID
     */
    private String gridId;

    /**
     * 单元网格名称
     */
    private String gridName;

    /**
     * 部件状态：完好 / 破损 / 丢失 / 废弃
     */
    private String compStatus;

    /**
     * 普查日期
     */
    private LocalDate initDate;

    /**
     * 状态/权属变更日期
     */
    private LocalDate changeDate;

    /**
     * 数据来源，如实测/上报/普查
     */
    private String dataSource;

    /**
     * 录入人账号
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人账号
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

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

    /**
     * 创建者
     */
    private String creator;

    /**
     * 更新者
     */
    private String updater;

    /**
     * 删除标识
     */
    private Boolean deleted;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;

    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

    /**
     * 故障原因
     */
    private String faultReason;

    /**
     * 处理状态：未处置 / 已处置
     */
    private String handleStatus;
}
