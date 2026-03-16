package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测事件类别 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EventTypeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12288")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "上级事件类别ID", example = "28494")
    @ExcelProperty("上级事件类别ID")
    private Long parentTypeId;

    @Schema(description = "唯一事件编码")
    @ExcelProperty("唯一事件编码")
    private String eventCode;

    @Schema(description = "事件名称", example = "王五")
    @ExcelProperty("事件名称")
    private String eventName;

    @Schema(description = "关联监测部件类别ID", example = "11891")
    @ExcelProperty("关联监测部件类别ID")
    private Long relatedMonitorTypeId;

    @Schema(description = "默认告警等级：提示/一般/严重/紧急")
    @ExcelProperty("默认告警等级：提示/一般/严重/紧急")
    private String alarmLevel;

    @Schema(description = "事件处理规则")
    @ExcelProperty("事件处理规则")
    private String handleRule;

    @Schema(description = "状态：启用/停用", example = "1")
    @ExcelProperty("状态：启用/停用")
    private String eventStatus;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String eventRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
