package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 站点用户 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StationUserRespVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "20464")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "用户账号", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("用户账号")
    private String username;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("密码")
    private String password;

    @Schema(description = "用户昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("用户昵称")
    private String nickname;

    @Schema(description = "性别 0-未知 1-男 2-女")
    @ExcelProperty("性别 0-未知 1-男 2-女")
    private Integer sex;

    @Schema(description = "头像")
    @ExcelProperty("头像")
    private String avatar;

    @Schema(description = "手机号")
    @ExcelProperty("手机号")
    private String phone;

    @Schema(description = "邮箱")
    @ExcelProperty("邮箱")
    private String email;

    @Schema(description = "状态 0-正常 1-禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态 0-正常 1-禁用")
    private Integer status;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    @ExcelProperty("[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    @ExcelProperty("[备用字段2] 备用字段2")
    private String reserve2;

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
