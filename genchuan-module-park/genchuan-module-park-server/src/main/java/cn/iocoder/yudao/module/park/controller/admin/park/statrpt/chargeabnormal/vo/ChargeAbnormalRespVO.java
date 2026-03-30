package cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 收费异常 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChargeAbnormalRespVO {

    @Schema(description = "[主键ID] 自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "9789")
    @ExcelProperty("[主键ID] 自增主键")
    private Long id;

    @Schema(description = "[异常订单编号]")
    @ExcelProperty("[异常订单编号]")
    private String orderNo;

    @Schema(description = "[车牌号码]")
    @ExcelProperty("[车牌号码]")
    private String carNumber;

    @Schema(description = "[区域ID] 关联sys_area.id", example = "16943")
    @ExcelProperty("[区域ID] 关联sys_area.id")
    private Long areaId;

    @Schema(description = "[车场ID] 关联park_lot.id", example = "20676")
    @ExcelProperty("[车场ID] 关联park_lot.id")
    private Long lotId;

    @Schema(description = "[异常时间]")
    @ExcelProperty("[异常时间]")
    private LocalDateTime abnormalTime;

    @Schema(description = "[异常金额]")
    @ExcelProperty("[异常金额]")
    private BigDecimal abnormalAmount;

    @Schema(description = "[异常类型] 如:金额错误/计费缺失/重复收费/其他", example = "2")
    @ExcelProperty("[异常类型] 如:金额错误/计费缺失/重复收费/其他")
    private String abnormalType;

    @Schema(description = "[异常原因]", example = "不香")
    @ExcelProperty("[异常原因]")
    private String abnormalReason;

    @Schema(description = "[处置状态] 如:未处置/处理中/已处置", example = "1")
    @ExcelProperty("[处置状态] 如:未处置/处理中/已处置")
    private String disposalStatus;

    @Schema(description = "[处置结果] 如:已纠错/无法纠错")
    @ExcelProperty("[处置结果] 如:已纠错/无法纠错")
    private String disposalResult;

    @Schema(description = "[处置人ID] 关联park_user.id")
    @ExcelProperty("[处置人ID] 关联park_user.id")
    private Long disposalBy;

    @Schema(description = "[处置时间]")
    @ExcelProperty("[处置时间]")
    private LocalDateTime disposalTime;

    @Schema(description = "[备注]", example = "你猜")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

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
