package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 车牌认证 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PlateAuthPageRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "所属用户ID，关联 user_info 表 id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("所属用户ID，关联 user_info 表 id")
    private Long userId;

    @Schema(description = "关联车辆ID，关联 user_car 表 id", example = "1")
    @ExcelProperty("关联车辆ID，关联 user_car 表 id")
    private Long carId;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽 C12345")
    @ExcelProperty("车牌号码")
    private String plateNo;

    @Schema(description = "行驶证图片地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "/genchuan/chargePark/userMerchant/driving/123.jpg")
    @ExcelProperty("行驶证图片地址")
    private String drivingLicense;

    @Schema(description = "认证申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("认证申请时间")
    private LocalDateTime applyTime;

    @Schema(description = "认证状态：待审核/已认证/已驳回，关联芋道字典表 plate_auth_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "待审核")
    @ExcelProperty("认证状态：待审核/已认证/已驳回，关联芋道字典表 plate_auth_status")
    private String status;

    @Schema(description = "审核人ID，关联芋道用户表 system_user", example = "1")
    @ExcelProperty("审核人ID，关联芋道用户表 system_user")
    private Long auditorId;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核备注")
    @ExcelProperty("审核备注")
    private String auditRemark;

    @Schema(description = "备注", example = "新车认证申请")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者", example = "user1")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}