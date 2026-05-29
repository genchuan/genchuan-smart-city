package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.write.style.HeadFontStyle;
import cn.iocoder.yudao.module.usermerchant.framework.annotation.ImportRequired;
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
@Accessors(chain=false)// 设置 chain = false，避免用户导入有问题
public class MemberTagImportExcelVO {

    private static final short RED_COLOR = 10;

    @Schema(description = "标签名称")
    @ExcelProperty("标签名称")
    @ImportRequired
    @HeadFontStyle(color = RED_COLOR)
    private String name;

    @Schema(description = "标签描述")
    @ExcelProperty("标签描述")
    @ImportRequired
    @HeadFontStyle(color = RED_COLOR)
    private String description;

    @Schema(description = "状态：0-禁用，1-正常")
    @ExcelProperty("状态：0-禁用，1-正常")
    @ImportRequired
    @HeadFontStyle(color = RED_COLOR)
    private Integer status;

}
