package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 联合追缴拓场配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DebtExpandRespVO {

    @Schema(description = "[主键ID] 主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7485")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[合作场站] 关联场站信息表 station_info", requiredMode = Schema.RequiredMode.REQUIRED, example = "27111")
    @ExcelProperty("[合作场站]")
    private Long stationId;

    @Schema(description = "[合作类型] 如：社会停车场拓场/联合追缴", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[合作类型]")
    private String type;

    @Schema(description = "[追缴范围] 如：本区域/跨区域/全平台", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[追缴范围]")
    private String range;

    @Schema(description = "[拓场进度] 单位：%")
    @ExcelProperty("[拓场进度]")
    private Integer progress;

    @Schema(description = "[状态] 如：未生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[状态]")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    @ExcelProperty("[审核时间]")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "8820")
    @ExcelProperty("[审核人]")
    private Long auditUserId;

    @Schema(description = "[完成时间] 拓场完成时间")
    @ExcelProperty("[完成时间]")
    private LocalDateTime finishTime;

    @Schema(description = "[追缴完成率] 追缴完成比例")
    @ExcelProperty("[追缴完成率]")
    private BigDecimal recoveryRate;

    @Schema(description = "[备注] 补充说明", example = "你猜")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[备用字段1]")
    @ExcelProperty("[备用字段1]")
    private String reserve1;

    @Schema(description = "[备用字段2]")
    @ExcelProperty("[备用字段2]")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    @ExcelProperty("[创建者]")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    @ExcelProperty("[更新者]")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间]")
    private LocalDateTime createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @ExcelProperty("[更新时间]")
    private LocalDateTime updateTime;

}
