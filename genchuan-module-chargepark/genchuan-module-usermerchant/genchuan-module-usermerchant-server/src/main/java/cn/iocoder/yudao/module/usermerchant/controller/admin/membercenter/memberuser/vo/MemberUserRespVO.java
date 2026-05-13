package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 会员用户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MemberUserRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25640")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "手机号")
    @ExcelProperty("手机号")
    private String mobile;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("密码")
    private String password;

    @Schema(description = "状态：0-禁用，1-正常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：0-禁用，1-正常")
    private Integer status;

    @Schema(description = "注册IP", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("注册IP")
    private String registerIp;

    @Schema(description = "注册终端")
    @ExcelProperty("注册终端")
    private Integer registerTerminal;

    @Schema(description = "最后登录IP", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("最后登录IP")
    private String loginIp;

    @Schema(description = "最后登录时间")
    @ExcelProperty("最后登录时间")
    private LocalDateTime loginDate;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("用户昵称")
    private String nickname;

    @Schema(description = "头像", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("头像")
    private String avatar;

    @Schema(description = "真实姓名", example = "张三")
    @ExcelProperty("真实姓名")
    private String name;

    @Schema(description = "性别：0-未知，1-男，2-女")
    @ExcelProperty("性别：0-未知，1-男，2-女")
    private Integer sex;

    @Schema(description = "所在地区ID", example = "3680")
    @ExcelProperty("所在地区ID")
    private Long areaId;

    @Schema(description = "出生日期")
    @ExcelProperty("出生日期")
    private LocalDateTime birthday;

    @Schema(description = "会员备注")
    @ExcelProperty("会员备注")
    private String mark;

    @Schema(description = "积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("积分")
    private Integer point;

    @Schema(description = "用户标签编号列表，逗号分隔")
    @ExcelProperty("用户标签编号列表，逗号分隔")
    private String tagIds;

    @Schema(description = "会员等级ID", example = "17077")
    @ExcelProperty("会员等级ID")
    private Long levelId;

    @Schema(description = "经验值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("经验值")
    private Integer experience;

    @Schema(description = "用户分组ID", example = "9201")
    @ExcelProperty("用户分组ID")
    private Long groupId;

    @Schema(description = "会员到期时间")
    @ExcelProperty("会员到期时间")
    private LocalDateTime expireTime;

    @Schema(description = "自动续费：0-关闭，1-开启", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("自动续费：0-关闭，1-开启")
    private Integer autoRenew;

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