package cn.iocoder.yudao.module.vehiclepass.controller.admin.usercar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 用户车辆 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserCarRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "所属用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("所属用户ID")
    private Long userId;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽C12345")
    @ExcelProperty("车牌号码")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "蓝牌")
    @ExcelProperty("车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌")
    private String plateColor;

    @Schema(description = "车辆类型：小型车/大型车/新能源/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "小型车")
    @ExcelProperty("车辆类型：小型车/大型车/新能源/其他")
    private String carType;

    @Schema(description = "绑定时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "绑定状态：待审核/已绑定/已解绑", requiredMode = Schema.RequiredMode.REQUIRED, example = "已绑定")
    @ExcelProperty("绑定状态：待审核/已绑定/已解绑")
    private String status;

    @Schema(description = "审核人ID", example = "1")
    @ExcelProperty("审核人ID")
    private Long auditorId;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核备注", example = "审核通过")
    @ExcelProperty("审核备注")
    private String auditRemark;

    @Schema(description = "备注", example = "用户自有车辆")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者", example = "admin")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
