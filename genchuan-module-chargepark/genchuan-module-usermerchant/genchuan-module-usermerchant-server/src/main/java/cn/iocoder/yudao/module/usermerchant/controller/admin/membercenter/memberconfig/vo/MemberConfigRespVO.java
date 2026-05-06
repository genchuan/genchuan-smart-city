package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 会员配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MemberConfigRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19999")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "配置类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("配置类型")
    private String configType;

    @Schema(description = "权益内容（JSON或文本）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("权益内容（JSON或文本）")
    private String content;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "状态：0-未生效，1-已生效", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：0-未生效，1-已生效")
    private Integer status;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "创建者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}