package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo;

import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 黑白名单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class BlackWhiteListRespVO {

    @Schema(description = "[主键ID] 主键，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "13688")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "[车牌号码] 唯一车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌号码")
    private String plateNo;

    @Schema(description = "[名单类型] 如：白名单/黑名单", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("名单类型")
    private String type;

    @Schema(description = "[细分类型] 如：公务车/业主车/残疾人车/欠费车/逃费车", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("细分类型")
    private String subType;

    @Schema(description = "[生效时间] 名单生效开始时间")
    @ExcelProperty("生效时间")
    private LocalDateTime startTime;

    @Schema(description = "[失效时间] 名单失效截止时间")
    @ExcelProperty("失效时间")
    private LocalDateTime endTime;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "7329")
    @ExcelIgnore
    private Long auditUserId;

    @Schema(description = "审核人名称")
    @ExcelProperty("审核人")
    private String auditUserName;

    @Schema(description = "[拦截次数] 黑名单车辆被拦截次数", example = "3994")
    @ExcelProperty("拦截次数")
    private Integer interceptCount;

    @Schema(description = "[证件信息] 相关证件信息描述")
    @ExcelProperty("证件信息")
    private String certInfo;

    @Schema(description = "[备注] 扩展说明", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展")
//    @ExcelProperty("备用字段1")
    @ExcelIgnore
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展")
//    @ExcelProperty("备用字段2")
    @ExcelIgnore
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
