package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 调度效果反馈 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DispatchEffectFeedbackRespVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23561")
    @ExcelProperty("自增主键ID")
    private Long id;

    @Schema(description = "反馈ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "4638")
    @ExcelProperty("反馈ID，唯一编码，UUID生成")
    private String feedbackId;

    @Schema(description = "关联指令ID，关联调度指令表", requiredMode = Schema.RequiredMode.REQUIRED, example = "10353")
    @ExcelProperty("关联指令ID，关联调度指令表")
    private String commandId;

    @Schema(description = "指令编号，与指令ID同步，关联调度指令表（biz_dispatch_command）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指令编号，与指令ID同步，关联调度指令表（biz_dispatch_command）")
    private String commandNo;

    @Schema(description = "执行单位ID，与指令ID同步，联动单位信息表（biz_coop_unit_info）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20638")
    @ExcelProperty("执行单位ID，与指令ID同步，联动单位信息表（biz_coop_unit_info）")
    private String execUnitId;

    @Schema(description = "执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）")
    private String execUnitName;

    @Schema(description = "执行效果描述，如“已按指令完成2辆救援车调度，转移被困人员20名，设备正常”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("执行效果描述，如“已按指令完成2辆救援车调度，转移被困人员20名，设备正常”")
    private String effectDesc;

    @Schema(description = "反馈附件路径，执行现场照片/视频路径，多个用逗号分隔")
    @ExcelProperty("反馈附件路径，执行现场照片/视频路径，多个用逗号分隔")
    private String feedbackAttachPath;

    @Schema(description = "反馈人ID，执行单位反馈人账号，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "9610")
    @ExcelProperty("反馈人ID，执行单位反馈人账号，关联用户信息表（sys_user）")
    private String feedbackUserId;

    @Schema(description = "反馈人姓名，与反馈人ID同步，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("反馈人姓名，与反馈人ID同步，关联用户信息表（sys_user）")
    private String feedbackUserName;

    @Schema(description = "反馈时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("反馈时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成")
    private LocalDateTime feedbackTime;

    @Schema(description = "评估结果，有效/部分有效/无效，管理员评估结果")
    @ExcelProperty("评估结果，有效/部分有效/无效，管理员评估结果")
    private String evalResult;

    @Schema(description = "评估说明，评估理由，如“救援及时，转移人员到位，效果有效”")
    @ExcelProperty("评估说明，评估理由，如“救援及时，转移人员到位，效果有效”")
    private String evalDesc;

    @Schema(description = "评估人ID，管理员账号，评估后必填，关联用户信息表（sys_user）", example = "28022")
    @ExcelProperty("评估人ID，管理员账号，评估后必填，关联用户信息表（sys_user）")
    private String evalUserId;

    @Schema(description = "评估时间，格式yyyy-MM-dd HH:mm:ss，评估后必填")
    @ExcelProperty("评估时间，格式yyyy-MM-dd HH:mm:ss，评估后必填")
    private LocalDateTime evalTime;

    @Schema(description = "分类扩展字段1，预留，如“效果评分（1-5分）”")
    @ExcelProperty("分类扩展字段1，预留，如“效果评分（1-5分）”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“效果评分（1-5分）”")
    @ExcelProperty("分类扩展字段2，预留，如“效果评分（1-5分）”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储评估报告路径")
    @ExcelProperty("通用扩展字段1，预留，存储评估报告路径")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储评估报告路径")
    @ExcelProperty("通用扩展字段2，预留，存储评估报告路径")
    private String extCommon2;

}