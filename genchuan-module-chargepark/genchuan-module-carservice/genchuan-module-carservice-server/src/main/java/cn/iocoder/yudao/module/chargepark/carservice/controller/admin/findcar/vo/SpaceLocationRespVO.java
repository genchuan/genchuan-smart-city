package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.desensitize.core.slider.annotation.CarLicenseDesensitize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车位定位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SpaceLocationRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户 ID")
    private Long userId;

    @Schema(description = "用户名（关联 system_user.nickname）")
    @ExcelProperty("用户名")
    private String userName;

    @Schema(description = "车牌号码（已脱敏，例：闽C***5）")
    @ExcelProperty("车牌号码")
    @CarLicenseDesensitize
    private String plateNo;

    @Schema(description = "查询时间")
    @ExcelProperty("查询时间")
    private LocalDateTime queryTime;

    @Schema(description = "车位 ID")
    @ExcelProperty("车位 ID")
    private Long spaceId;

    @Schema(description = "定位结果,关联字典 space_location_location_result")
    @ExcelProperty("定位结果")
    private String locationResult;

    @Schema(description = "响应时长（毫秒）")
    @ExcelProperty("响应时长（毫秒）")
    private Integer responseDuration;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
