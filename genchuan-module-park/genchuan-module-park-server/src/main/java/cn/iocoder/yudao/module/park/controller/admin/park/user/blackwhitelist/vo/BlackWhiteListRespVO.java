package cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;


@Schema(description = "管理后台 - 黑白名单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BlackWhiteListRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "2347")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[名单类型] 如:黑名单/白名单", example = "2")
    @ExcelProperty("[名单类型] 如:黑名单/白名单")
    private String listType;

    @Schema(description = "[目标类型] 如:用户/车辆", example = "2")
    @ExcelProperty("[目标类型] 如:用户/车辆")
    private String targetType;

    @Schema(description = "[目标ID] 用户ID/车牌号码", example = "1105")
    @ExcelProperty("[目标ID] 用户ID/车牌号码")
    private String targetId;

    @Schema(description = "[列入原因]", example = "不喜欢")
    @ExcelProperty("[列入原因]")
    private String listReason;

    @Schema(description = "[生效时间]")
    @ExcelProperty("[生效时间]")
    private LocalDateTime effectTime;

    @Schema(description = "[失效时间] 永久有效为NULL")
    @ExcelProperty("[失效时间] 永久有效为NULL")
    private LocalDateTime expireTime;

    @Schema(description = "[状态] 如:生效中/已失效/已删除", example = "2")
    @ExcelProperty("[状态] 如:生效中/已失效/已删除")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "随便")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
