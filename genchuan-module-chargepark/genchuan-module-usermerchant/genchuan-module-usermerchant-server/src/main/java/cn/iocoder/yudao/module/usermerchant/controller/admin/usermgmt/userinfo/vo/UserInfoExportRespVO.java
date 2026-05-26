package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserInfoExportRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "用户姓名", example = "张三")
    @ExcelProperty("用户姓名")
    private String nickname;

    @Schema(description = "手机号")
    @ExcelProperty("手机号")
    private String phone;

    @Schema(description = "用户类型", example = "个人用户")
    @ExcelProperty("用户类型")
    private String userType;

    @Schema(description = "状态", example = "正常")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "注册时间")
    @ExcelProperty("注册时间")
    private LocalDateTime registerTime;

    @Schema(description = "最后登录时间")
    @ExcelProperty("最后登录时间")
    private LocalDateTime loginTime;

    @Schema(description = "钱包余额", example = "100")
    @ExcelProperty("钱包余额")
    private BigDecimal walletBalance;

    @Schema(description = "绑定车辆数", example = "2")
    @ExcelProperty("绑定车辆数")
    private Integer carCount;

    @Schema(description = "备注", example = "普通用户")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}