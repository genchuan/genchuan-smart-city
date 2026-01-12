package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 黑白名单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkBlackWhiteListRespVO {

    @Schema(description = "[主键ID] 黑白名单记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "13853")
    @ExcelProperty("[主键ID] 黑白名单记录唯一标识")
    private Long id;

    @Schema(description = "[名单类型] 如：黑名单/白名单", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[名单类型] 如：黑名单/白名单")
    private String listType;

    @Schema(description = "[目标类型] 如：用户/车辆", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[目标类型] 如：用户/车辆")
    private String targetType;

    @Schema(description = "[目标ID] 可为用户ID", example = "20952")
    @ExcelProperty("[目标ID] 可为用户ID")
    private Long targetId;

    @Schema(description = "[车牌] 车辆车牌号")
    @ExcelProperty("[车牌] 车辆车牌号")
    private String targetCarNumber;

    @Schema(description = "[列入原因] 被列入黑白名单原因", example = "不喜欢")
    @ExcelProperty("[列入原因] 被列入黑白名单原因")
    private String reason;

    @Schema(description = "[生效时间] 规则生效时间")
    @ExcelProperty("[生效时间] 规则生效时间")
    private LocalDateTime startTime;

    @Schema(description = "[失效时间] 规则失效时间，永久有效为 NULL")
    @ExcelProperty("[失效时间] 规则失效时间，永久有效为 NULL")
    private LocalDateTime endTime;

    @Schema(description = "[状态] 如：生效/失效", example = "2")
    @ExcelProperty("[状态] 如：生效/失效")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

    @Schema(description = "[备注] 黑白名单相关备注说明", example = "你猜")
    @ExcelProperty("[备注] 黑白名单相关备注说明")
    private String remark;

}
