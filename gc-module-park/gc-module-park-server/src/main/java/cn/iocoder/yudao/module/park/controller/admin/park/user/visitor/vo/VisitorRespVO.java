package cn.iocoder.yudao.module.park.controller.admin.park.user.visitor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 访客 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VisitorRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1555")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[访客姓名]", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("[访客姓名]")
    private String visitorName;

    @Schema(description = "[手机号]")
    @ExcelProperty("[手机号]")
    private String phone;

    @Schema(description = "[身份证号] 脱敏存储")
    @ExcelProperty("[身份证号] 脱敏存储")
    private String idCard;

    @Schema(description = "[访问资源ID] 关联tb_asset_extend.asset_extend_id", example = "22891")
    @ExcelProperty("[访问资源ID] 关联tb_asset_extend.asset_extend_id")
    private Long visitResourceId;

    @Schema(description = "[访问事由]", example = "不香")
    @ExcelProperty("[访问事由]")
    private String visitReason;

    @Schema(description = "[访问时间]")
    @ExcelProperty("[访问时间]")
    private LocalDateTime visitTime;

    @Schema(description = "[预计离开时间]")
    @ExcelProperty("[预计离开时间]")
    private LocalDateTime expectLeaveTime;

    @Schema(description = "[实际离开时间] 可为NULL")
    @ExcelProperty("[实际离开时间] 可为NULL")
    private LocalDateTime leaveTime;

    @Schema(description = "[登记人ID] 关联park_user.id")
    @ExcelProperty("[登记人ID] 关联park_user.id")
    private Long registerBy;

    @Schema(description = "[登记时间]")
    @ExcelProperty("[登记时间]")
    private LocalDateTime registerTime;

    @Schema(description = "[状态] 如:在访/已离场/未入场", example = "1")
    @ExcelProperty("[状态] 如:在访/已离场/未入场")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "你猜")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
