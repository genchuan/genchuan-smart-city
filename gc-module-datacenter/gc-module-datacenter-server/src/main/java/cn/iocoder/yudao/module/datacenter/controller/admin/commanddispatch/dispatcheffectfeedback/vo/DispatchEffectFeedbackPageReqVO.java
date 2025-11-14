package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 调度效果反馈分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DispatchEffectFeedbackPageReqVO extends PageParam {

    @Schema(description = "反馈ID，唯一编码，UUID生成", example = "4638")
    private String feedbackId;

    @Schema(description = "关联指令ID，关联调度指令表", example = "10353")
    private String commandId;

    @Schema(description = "指令编号，与指令ID同步，关联调度指令表（biz_dispatch_command）")
    private String commandNo;

    @Schema(description = "执行单位ID，与指令ID同步，联动单位信息表（biz_coop_unit_info）", example = "20638")
    private String execUnitId;

    @Schema(description = "执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）", example = "张三")
    private String execUnitName;

    @Schema(description = "执行效果描述，如“已按指令完成2辆救援车调度，转移被困人员20名，设备正常”")
    private String effectDesc;

    @Schema(description = "反馈附件路径，执行现场照片/视频路径，多个用逗号分隔")
    private String feedbackAttachPath;

    @Schema(description = "反馈人ID，执行单位反馈人账号，关联用户信息表（sys_user）", example = "9610")
    private String feedbackUserId;

    @Schema(description = "反馈人姓名，与反馈人ID同步，关联用户信息表（sys_user）", example = "李四")
    private String feedbackUserName;

    @Schema(description = "反馈时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] feedbackTime;

    @Schema(description = "评估结果，有效/部分有效/无效，管理员评估结果")
    private String evalResult;

    @Schema(description = "评估说明，评估理由，如“救援及时，转移人员到位，效果有效”")
    private String evalDesc;

    @Schema(description = "评估人ID，管理员账号，评估后必填，关联用户信息表（sys_user）", example = "28022")
    private String evalUserId;

    @Schema(description = "评估时间，格式yyyy-MM-dd HH:mm:ss，评估后必填")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] evalTime;

    @Schema(description = "分类扩展字段1，预留，如“效果评分（1-5分）”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“效果评分（1-5分）”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储评估报告路径")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储评估报告路径")
    private String extCommon2;

}