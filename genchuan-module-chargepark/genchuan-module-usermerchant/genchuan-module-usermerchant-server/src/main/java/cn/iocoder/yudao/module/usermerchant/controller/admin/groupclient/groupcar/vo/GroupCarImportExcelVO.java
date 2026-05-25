package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.write.style.HeadFontStyle;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=false)
public class GroupCarImportExcelVO {

    private static final short RED_COLOR = 10;

    @Schema(description = "集团名称")
    @ExcelProperty("集团名称")
    @HeadFontStyle(color = RED_COLOR)
    private String name;

    @Schema(description = "车牌号码")
    @ExcelProperty("车牌号码")
    @HeadFontStyle(color = RED_COLOR)
    private String plateNo;

    @Schema(description = "车牌颜色")
    @ExcelProperty("车牌颜色")
    @HeadFontStyle(color = RED_COLOR)
    private String plateColor;

    @Schema(description = "车辆类型")
    @ExcelProperty("车辆类型")
    @HeadFontStyle(color = RED_COLOR)
    private String carType;

    @Schema(description = "绑定时间")
    @ExcelProperty("绑定时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    @HeadFontStyle(color = RED_COLOR)
    private LocalDateTime bindTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

}