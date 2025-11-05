package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmattermajor;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理事项大类 DO
 *
 * @author 亘川智城
 */
@TableName("gc_biz_mng_matter_major")
@KeySequence("gc_biz_mng_matter_major_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizMngMatterMajorDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 管理事项大类ID
     */
    private String mngMatterMajorId;
    /**
     * 大类代码
     */
    private String matterMajorCode;
    /**
     * 大类名称
     */
    private String matterMajorName;
    /**
     * 大类说明
     */
    private String matterMajorDesc;
    /**
     * 排序序号
     */
    private Integer sortNum;
    /**
     * 启用状态
     */
    private String enableStatus;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}