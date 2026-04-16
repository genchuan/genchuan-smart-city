package cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "汽车充电 - 费率设置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RateSettingRespVO {

    @Schema(description = "[主键ID] 主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19547")
    @ExcelProperty("[主键ID] 主键ID")
    private Long id;

    @Schema(description = "[方案编号] 方案编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[方案编号] 方案编号")
    private String rateCode;

    @Schema(description = "[方案名称] 方案名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("[方案名称] 方案名称")
    private String rateName;

    @Schema(description = "[适用场景] 适用场景", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[适用场景] 适用场景")
    private String applyScene;

    @Schema(description = "[费率规则] 费率规则", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[费率规则] 费率规则")
    private String rateRule;

    @Schema(description = "[生效时间] 生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[生效时间] 生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "[失效时间] 失效时间")
    @ExcelProperty("[失效时间] 失效时间")
    private LocalDateTime expireTime;

    @Schema(description = "[适用场站] 适用场站")
    @ExcelProperty("[适用场站] 适用场站")
    private String applyStation;

    @Schema(description = "[适用集团] 适用集团")
    @ExcelProperty("[适用集团] 适用集团")
    private String applyGroup;

    @Schema(description = "[费率状态]如:未生效/已生效/已失效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[费率状态]如:未生效/已生效/已失效")
    private String rateStatus;

    @Schema(description = "[备注] 备注", example = "你说的对")
    @ExcelProperty("[备注] 备注")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    @ExcelProperty("[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    @ExcelProperty("[备用字段2] 备用字段2")
    private String reserve2;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "适用场站名称（逗号分隔）")
    @ExcelProperty("适用场站名称")
    private String stationNames; // 新增 ✅

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator; // 新增 ✅

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updater; // 新增 ✅

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime; // 新增 ✅
}
