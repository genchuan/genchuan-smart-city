package cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo;

import cn.iocoder.yudao.module.usermerchant.framework.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 用户车辆 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserCarImportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("用户信息ID")
    private Long userId;

    @ExcelProperty("车牌号码")
    private String plateNo;

    @ExcelProperty("车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌")
    private String plateColor;

    @ExcelProperty("车辆类型：小型车/大型车/新能源/其他")
    private String carType;

    @ExcelProperty("绑定时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime bindTime;

    @ExcelProperty("绑定状态：待审核/已绑定/已解绑")
    private String status;

    @ExcelProperty("审核人ID")
    private Long auditorId;

    @ExcelProperty("审核时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime auditTime;

    @ExcelProperty("审核备注")
    private String auditRemark;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("备用字段1")
    private String reserve1;

    @ExcelProperty("备用字段2")
    private String reserve2;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime updateTime;

}