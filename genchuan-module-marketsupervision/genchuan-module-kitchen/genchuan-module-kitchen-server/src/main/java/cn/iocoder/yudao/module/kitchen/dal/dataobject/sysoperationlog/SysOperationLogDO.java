package cn.iocoder.yudao.module.kitchen.dal.dataobject.sysoperationlog;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 系统操作审计日志表，存储平台全模块所有操作的审计日志信息 DO
 *
 * @author 亘川智城
 */
@TableName("sys_operation_log")
@KeySequence("sys_operation_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysOperationLogDO extends BaseDO {

    /**
     * [主键ID] 审计记录唯一标识，自增
     */
    @TableId
    private Long id;
    /**
     * [操作人ID] 操作人唯一标识，关联park_user.id
     */
    private Long operUserId;
    /**
     * [操作人名称] 操作人姓名
     */
    private String operUserName;
    /**
     * [操作时间] 操作发生时间，默认当前时间
     */
    private LocalDateTime operTime;
    /**
     * [操作类型] 如：查询/新增/编辑/删除/导出/复审/批量操作/其他
     */
    private String operType;
    /**
     * [操作对象] 如：整改复审台账/处罚复审台账/企业信息/设备状态/风险评估统计/违规分析统计/自定义报表/企业整改记录/企业缴款记录/字典表维护
     */
    private String operObject;
    /**
     * [操作结果] 如：成功/失败
     */
    private String operResult;
    /**
     * [批量操作选中条目信息] JSON格式varchar，存储批量操作选中的条目ID列表等信息
     */
    private String batchSelectInfo;
    /**
     * [操作IP地址] 客户端IP地址
     */
    private String operIp;
    /**
     * [操作详细描述] 详细的操作内容描述，可为空
     */
    private String operDesc;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}