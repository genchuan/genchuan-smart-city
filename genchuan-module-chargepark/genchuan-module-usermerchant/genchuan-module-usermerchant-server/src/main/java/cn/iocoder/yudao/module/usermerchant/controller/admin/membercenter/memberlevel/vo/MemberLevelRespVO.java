package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 会员等级 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MemberLevelRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "5898")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "等级名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("等级名称")
    private String name;

    @Schema(description = "等级数值（排序用）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("等级数值（排序用）")
    private Integer levelValue;

    @Schema(description = "升级条件（JSON）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("升级条件（JSON）")
    private String upgradeCondition;

    @Schema(description = "权益内容（JSON）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("权益内容（JSON）")
    private String benefits;

    @Schema(description = "状态：0-未生效，1-已生效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：0-未生效，1-已生效")
    private Integer status;

    @Schema(description = "该等级下的会员用户数量")
    @ExcelProperty("该等级下的会员用户数量")
    private Integer memberCount;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}