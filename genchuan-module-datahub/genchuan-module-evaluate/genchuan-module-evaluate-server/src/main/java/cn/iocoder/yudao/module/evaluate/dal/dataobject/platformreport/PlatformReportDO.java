package cn.iocoder.yudao.module.evaluate.dal.dataobject.platformreport;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 平台上报 DO
 *
 * @author 亘川智城
 */
@TableName("platform_report")
@KeySequence("platform_report_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformReportDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 上报UUID
     */
    private String reportId;
    /**
     * 上报批次号
     */
    private String batchNo;
    /**
     * 关联评价任务ID
     */
    private String taskId;
    /**
     * 上报人
     */
    private String reportBy;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 上报文件名称
     */
    private String fileName;
    /**
     * 数据条数
     */
    private Integer dataCount;
    /**
     * 成功条数
     */
    private Integer successCount;
    /**
     * 失败条数
     */
    private Integer failCount;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 校验时间
     */
    private LocalDateTime checkTime;
    /**
     * 校验操作人
     */
    private String checkBy;
    /**
     * 错误文件下载链接
     */
    private String errorFileUrl;
    /**
     * 模板下载状态：可下载/已下载
     */
    private String templateStatus;
    /**
     * 文件预览链接：可预览/无
     */
    private String filePreviewUrl;
    /**
     * 重新上传次数
     */
    private Integer reuploadCount;
    /**
     * 最近重新上传时间
     */
    private LocalDateTime lastReuploadTime;
    /**
     * 失败原因摘要
     */
    private String failReason;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}