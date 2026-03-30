package cn.iocoder.yudao.module.smartcity.controller.admin.parking.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 停车管理用户管理与服务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkingPageReqVO extends PageParam {

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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] registrationTime;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}