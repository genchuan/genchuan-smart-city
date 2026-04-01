package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "充电站 Excel VO")
public class ChargingStationExcelVO {

    @ExcelProperty("场站编号")
    private String stationCode;

    @ExcelProperty("场站名称")
    private String stationName;

    @ExcelProperty("场站地址")
    private String address;

    @ExcelProperty("合作模式")
    private String coopMode;

    @ExcelProperty("开放时间")
    private String openTime;

    @ExcelProperty("电价服务费")
    private String priceService;

    @ExcelProperty("负责人")
    private String manager;

    @ExcelProperty("场站状态")
    private String stationStatus;

    @ExcelProperty("经度")
    private String lon;

    @ExcelProperty("纬度")
    private String lat;

    @ExcelProperty("停用原因")
    private String stopReason;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private java.time.LocalDateTime createTime;
}