package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantinfo.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.write.style.HeadFontStyle;
import cn.iocoder.yudao.module.usermerchant.framework.annotation.ImportRequired;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=false)// 设置 chain = false，避免用户导入有问题
public class MerchantInfoImportExcelVO {

    private static final short RED_COLOR = 10;

    @Schema(description = "商户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("商户名称")
    @ImportRequired
    @HeadFontStyle(color = RED_COLOR)
    private String name;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系人")
    @ImportRequired
    @HeadFontStyle(color = RED_COLOR)
    private String contact;

    @Schema(description = "联系手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系手机号")
    @ImportRequired
    @HeadFontStyle(color = RED_COLOR)
    private String phone;

    @Schema(description = "商户类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("商户类型")
    @ImportRequired
    @HeadFontStyle(color = RED_COLOR)
    private String merchantType;

    @Schema(description = "商户地址")
    @ExcelProperty("商户地址")
    private String address;

    @Schema(description = "注册时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("注册时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    @ImportRequired
    @HeadFontStyle(color = RED_COLOR)
    private LocalDateTime registerTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

}
