package cn.iocoder.yudao.module.envirhealth.dal.dataobject.user;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;


/**
 * 岗位类型字典 DO
 *
 * @author 芋道源码
 */
@TableName("sys_job_type")
@KeySequence("sys_job_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobTypeDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 业务主键（UUID）
     */
    private String jobTypeId;
    /**
     * 岗位名称（可选值：清扫工/保洁员/督导员/驾驶员/维修工/管理员/考核员/转运工）
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
}