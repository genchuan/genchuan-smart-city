package cn.iocoder.yudao.module.kitchen.controller.admin.sysoperationlog.vo.add;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 系统操作审计日志表，存储平台全模块所有操作的审计日志信息新增/修改 Request VO")
@Data
public class SysOperationLogAddReqVO {

//    @Schema(description = "[主键ID] 审计记录唯一标识，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "6742")
//    private Long id;


    @Schema(description = "[操作人ID] 操作人唯一标识", example = "32468",hidden = true)
//    @NotNull(message = "[操作人ID] 操作人唯一标识")
    private Long operUserId;

    @Schema(description = "[操作人名称] 操作人姓名",  example = "李四",hidden = true)
//    @NotEmpty(message = "[操作人名称] 操作人姓名不能为空")
    private String operUserName;

    @Schema(description = "[操作时间] 操作发生时间，默认当前时间", hidden = true)
//    @NotNull(message = "[操作时间] 操作发生时间，默认当前时间不能为空")
    private LocalDateTime operTime;

    // TODO
    @Schema(description = "[操作类型] 如：查询/新增/编辑/删除/导出/复审/批量操作/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "查询")
    @NotEmpty(message = "[操作类型] 如：查询/新增/编辑/删除/导出/复审/批量操作/其他不能为空")
    private String operType;

    // TODO
    @Schema(description = "[操作对象] 如：整改复审台账/处罚复审台账/企业信息/设备状态/风险评估统计/违规分析统计/自定义报表/企业整改记录/企业缴款记录/字典表维护", requiredMode = Schema.RequiredMode.REQUIRED,example = "整改复审台账")
    @NotEmpty(message = "[操作对象] 如：整改复审台账/处罚复审台账/企业信息/设备状态/风险评估统计/违规分析统计/自定义报表/企业整改记录/企业缴款记录/字典表维护不能为空")
    private String operObject;

    // 操作默认成功
    @Schema(description = "[操作结果] 如：成功/失败", requiredMode = Schema.RequiredMode.REQUIRED,example = "成功",hidden = true)
//    @NotEmpty(message = "[操作结果] 如：成功/失败不能为空")
    private String operResult = "成功";

    // TODO
    @Schema(description = "[批量操作选中条目信息] JSON格式varchar，存储批量操作选中的条目ID列表等信息",example = "[1,2]")
    private String batchSelectInfo;

    @Schema(description = "[操作IP地址] 客户端IP地址", hidden = true)
//    @NotEmpty(message = "[操作IP地址] 客户端IP地址不能为空")
    private String operIp;

    @Schema(description = "[操作详细描述] 详细的操作内容描述，可为空",hidden = true)
    private String operDesc;

}
