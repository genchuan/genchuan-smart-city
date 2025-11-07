package cn.iocoder.yudao.module.smartcity.controller.admin.parking.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 停车管理用户管理与服务新增/修改 Request VO")
@Data
public class ParkingSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "12106")
    private Long id;

    @Schema(description = "姓名", example = "李四")
    private String fullName;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "手机号码")
    private String phoneNumber;

    @Schema(description = "电子邮箱")
    private String eMail;

    @Schema(description = "身份证号")
    private String idNumber;

    @Schema(description = "注册时间")
    private LocalDateTime registrationTime;

    @Schema(description = "用户类型", example = "2")
    private String customerType;

    @Schema(description = "用户等级")
    private String userLevel;

    @Schema(description = "积分")
    private String points;

    @Schema(description = "余额")
    private String balance;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "服务评价")
    private String serviceEvaluation;

}