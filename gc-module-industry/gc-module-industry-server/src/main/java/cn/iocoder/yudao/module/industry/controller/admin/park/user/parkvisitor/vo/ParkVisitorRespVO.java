package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 访客 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkVisitorRespVO {

    @Schema(description = "主键ID[访客唯一标识]", requiredMode = Schema.RequiredMode.REQUIRED, example = "31458")
    @ExcelProperty("主键ID[访客唯一标识]")
    private Long id;

    @Schema(description = "访客姓名[访客真实姓名]", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("访客姓名[访客真实姓名]")
    private String visitorName;

    @Schema(description = "手机号[访客手机号]")
    @ExcelProperty("手机号[访客手机号]")
    private String phone;

    @Schema(description = "身份证号[访客身份证号]")
    @ExcelProperty("身份证号[访客身份证号]")
    private String idCard;

    @Schema(description = "访问资源ID[访问资源标识，关联 tb_asset_extend.asset_extend_id]", example = "7349")
    @ExcelProperty("访问资源ID[访问资源标识，关联 tb_asset_extend.asset_extend_id]")
    private Long visitAssetId;

    @Schema(description = "访问事由[本次访问的具体事由说明]", example = "不对")
    @ExcelProperty("访问事由[本次访问的具体事由说明]")
    private String visitReason;

    @Schema(description = "访问时间[访客进入访问的时间]")
    @ExcelProperty("访问时间[访客进入访问的时间]")
    private LocalDateTime visitTime;

    @Schema(description = "离开时间[访客离开访问资源的时间]")
    @ExcelProperty("离开时间[访客离开访问资源的时间]")
    private LocalDateTime leaveTime;

    @Schema(description = "状态[待审核/已通过/已拒绝/已结束]", example = "2")
    @ExcelProperty("状态[待审核/已通过/已拒绝/已结束]")
    private String status;

    @Schema(description = "审核人[审核人，关联 park_user.id]")
    @ExcelProperty("审核人[审核人，关联 park_user.id]")
    private Long approveBy;

    @Schema(description = "审核时间[审核操作发生时间]")
    @ExcelProperty("审核时间[审核操作发生时间]")
    private LocalDateTime approveTime;

    @Schema(description = "创建时间[记录创建时间]")
    @ExcelProperty("创建时间[记录创建时间]")
    private LocalDateTime createTime;

    @Schema(description = "通用扩展字段1[预留扩展字段]")
    @ExcelProperty("通用扩展字段1[预留扩展字段]")
    private String extCommon1;

    @Schema(description = "通用扩展字段2[预留扩展字段]")
    @ExcelProperty("通用扩展字段2[预留扩展字段]")
    private String extCommon2;

    @Schema(description = "通用扩展字段3[预留扩展字段]")
    @ExcelProperty("通用扩展字段3[预留扩展字段]")
    private String extCommon3;

    @Schema(description = "通用扩展字段4[预留扩展字段]")
    @ExcelProperty("通用扩展字段4[预留扩展字段]")
    private String extCommon4;

    @Schema(description = "备注[访客相关备注说明]", example = "随便")
    @ExcelProperty("备注[访客相关备注说明]")
    private String remark;

}
