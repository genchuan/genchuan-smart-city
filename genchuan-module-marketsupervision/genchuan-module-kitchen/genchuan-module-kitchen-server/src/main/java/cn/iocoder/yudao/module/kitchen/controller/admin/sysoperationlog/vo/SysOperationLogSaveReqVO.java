package cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 系统操作审计日志表，存储平台全模块所有操作的审计日志信息新增/修改 Request VO")
@Data
public class SysOperationLogSaveReqVO {

    @Schema(description = "[主键ID] 审计记录唯一标识，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "6742")
    private Long id;

    @Schema(description = "[操作人ID] 操作人唯一标识，关联park_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32468")
    @NotNull(message = "[操作人ID] 操作人唯一标识，关联park_user.id不能为空")
    private Long operUserId;

    @Schema(description = "[操作人名称] 操作人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "[操作人名称] 操作人姓名不能为空")
    private String operUserName;

    @Schema(description = "[操作时间] 操作发生时间，默认当前时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[操作时间] 操作发生时间，默认当前时间不能为空")
    private LocalDateTime operTime;

    @Schema(description = "[操作类型] 如：查询/新增/编辑/删除/导出/复审/批量操作/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[操作类型] 如：查询/新增/编辑/删除/导出/复审/批量操作/其他不能为空")
    private String operType;

    @Schema(description = "[操作对象] 如：整改复审台账/处罚复审台账/企业信息/设备状态/风险评估统计/违规分析统计/自定义报表/企业整改记录/企业缴款记录/字典表维护", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[操作对象] 如：整改复审台账/处罚复审台账/企业信息/设备状态/风险评估统计/违规分析统计/自定义报表/企业整改记录/企业缴款记录/字典表维护不能为空")
    private String operObject;

    @Schema(description = "[操作结果] 如：成功/失败", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[操作结果] 如：成功/失败不能为空")
    private String operResult;

    @Schema(description = "[批量操作选中条目信息] JSON格式varchar，存储批量操作选中的条目ID列表等信息")
    private String batchSelectInfo;

    @Schema(description = "[操作IP地址] 客户端IP地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[操作IP地址] 客户端IP地址不能为空")
    private String operIp;

    @Schema(description = "[操作详细描述] 详细的操作内容描述，可为空")
    private String operDesc;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}