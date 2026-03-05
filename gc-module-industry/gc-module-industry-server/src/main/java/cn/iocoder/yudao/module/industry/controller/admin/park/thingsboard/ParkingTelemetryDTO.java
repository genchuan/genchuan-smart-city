package cn.iocoder.yudao.module.industry.controller.admin.park.thingsboard;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 停车遥测数据 DTO
 *
 * @author zhucongquan
 */
@Data
public class ParkingTelemetryDTO {

    @Schema(description = "时间戳", example = "1768354716449")
    private Long ts;

    @Schema(description = "记录时间", example = "2026-01-14 09:38:36")
    private String timestamp;

    @Schema(description = "入口编号", example = "GGP552641-01-01")
    private String entranceNo;

    @Schema(description = "车牌类型", example = "0")
    private String plateType;

    @Schema(description = "数据类型", example = "inpark")
    private String dataType;

    @Schema(description = "操作员姓名", example = "-")
    private String operaterName;

    @Schema(description = "签名", example = "9731af3029c04f8b6168383fac6c702d")
    private String sign;

    @Schema(description = "车牌号", example = "冀AF13943")
    private String plateNumber;

    @Schema(description = "版本", example = "1.0")
    private String version;

    @Schema(description = "记录ID", example = "1768354672607")
    private String recordId;

    @Schema(description = "空车位", example = "0")
    private String emptyPlot;

    @Schema(description = "服务类型", example = "inpark")
    private String service;

    @Schema(description = "操作员ID", example = "-")
    private String operaterId;

    @Schema(description = "入口名称", example = "1号-入口")
    private String entranceName;

    @Schema(description = "入场时间", example = "2026-01-14 09:37:52")
    private String driveInTime;

    @Schema(description = "应付金额", example = "300")
    private String shouldPay;

    @Schema(description = "出场时间", example = "2026-01-14 09:34:10")
    private String driveOutTime;

    @Schema(description = "出场类型", example = "0")
    private String outType;

    @Schema(description = "入场照片URL", example = "http://...")
    private String driveInPhoto;

    @Schema(description = "出场备注", example = "-")
    private String outRemark;

    @Schema(description = "支付方式", example = "5")
    private String payMethod;

    @Schema(description = "出口编号", example = "GGP552641-01-02")
    private String exitNo;

    @Schema(description = "出口名称", example = "1号-出口")
    private String exitName;

    @Schema(description = "出场照片URL", example = "http://...")
    private String driveOutPhoto;

    @Schema(description = "实付金额", example = "300")
    private String actualPay;
}
