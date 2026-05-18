package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.idev.excel.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 人脸信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FaceMgmtRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("人员姓名")
    private String userName;

    @Schema(description = "手机号，敏感数据已脱敏")
    @ExcelProperty("手机号")
    private String phone;

    @Schema(description = "所属企业")
    @ExcelProperty("所属企业")
    private String company;

    @Schema(description = "通行区域")
    @ExcelProperty("通行区域")
    private String accessArea;

    @Schema(description = "权限有效期，格式时间戳")
    @ExcelProperty("权限有效期")
    private LocalDateTime authValidity;

    @Schema(description = "权限状态（已授权/未授权/已过期）")
    @ExcelProperty("权限状态")
    private String authStatus;

    @Schema(description = "通行次数")
    @ExcelProperty("通行次数")
    private Integer accessCount;

    @Schema(description = "最后通行时间，格式时间戳")
    @ExcelProperty("最后通行时间")
    private LocalDateTime lastAccessTime;

    @Schema(description = "验证准确率")
    @ExcelProperty("验证准确率")
    private BigDecimal verifyAccuracy;

    @Schema(description = "操作人账号")
    @ExcelProperty("操作人账号")
    private String handleUser;

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