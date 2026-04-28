package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 集团信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GroupInfoPageRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16794")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "集团名称，唯一", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("集团名称，唯一")
    private String name;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系人")
    private String contact;

    @Schema(description = "联系手机号(已脱敏）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系手机号")
    private String phone;

    @Schema(description = "集团类型：企业单位/事业单位/政府机构/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("集团类型：企业单位/事业单位/政府机构/其他")
    private String groupType;

    @Schema(description = "集团地址")
    @ExcelProperty("集团地址")
    private String address;

    @Schema(description = "注册时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("注册时间")
    private LocalDateTime registerTime;

    @Schema(description = "集团状态：待审核/正常/禁用/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("集团状态：待审核/正常/禁用/已驳回")
    private String status;

    @Schema(description = "账户余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("账户余额")
    private BigDecimal walletBalance;

    @Schema(description = "审核人ID，关联system_user.id", example = "29669")
    @ExcelProperty("审核人ID，关联system_user.id")
    private Long auditorId;

    @Schema(description = "审核意见", example = "随便")
    @ExcelProperty("审核意见")
    private String auditRemark;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "备注", example = "你说的对")
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