package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户车辆 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserCarExportRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "用户名称")
    @ExcelProperty("用户名称")
    private String nickname;

    @Schema(description = "车牌号码", example = "闽 C12345")
    @ExcelProperty("车牌号码")
    private String plateNo;

    @Schema(description = "车牌颜色", example = "蓝牌")
    @ExcelProperty("车牌颜色")
    private String plateColor;

    @Schema(description = "车辆类型", example = "小型车")
    @ExcelProperty("车辆类型")
    private String carType;

    @Schema(description = "绑定时间")
    @ExcelProperty("绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "绑定状态", example = "已绑定")
    @ExcelProperty("绑定状态")
    private String status;

    @Schema(description = "审核人ID", example = "1")
    @ExcelProperty("审核人ID")
    private Long auditorId;

    @Schema(description = "审核人名称")
    @ExcelProperty("审核人名称")
    private String auditorName;

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

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}