package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatterext;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理事项扩展管理事项配置 DO
 *
 * @author 亘川智城
 */
@TableName("gc_biz_mng_matter_ext")
@KeySequence("gc_biz_mng_matter_ext_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizMngMatterExtDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 扩展ID，唯一编码，UUID生成
     */
    private String mngMatterExtId;
    /**
     * 归属大类ID，无则归“其他管理事项”，关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)
     */
    private String matterMajorId;
    /**
     * 归属大类名称，与大类ID同步，关联管理事项大类表(gc_biz_mng_matter_major)
     */
    private String matterMajorName;
    /**
     * 扩展小类代码，080 - 999，倒排编码，避免与标准类冲突
     */
    private String extMinorCode;
    /**
     * 扩展小类名称，名称加“(自定义)”，如“共享单车乱停放(自定义)”
     */
    private String extMinorName;
    /**
     * 扩展小类说明，描述用途，如“共享单车未停指定区域，影响市容”
     */
    private String extMinorDesc;
    /**
     * 建议主管部门代码，建议主管部门信用代码，关联部门信息表(sys_org)
     */
    private String suggestDeptCode;
    /**
     * 建议主管部门名称，与部门代码同步，关联部门信息表(sys_org)
     */
    private String suggestDeptName;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}