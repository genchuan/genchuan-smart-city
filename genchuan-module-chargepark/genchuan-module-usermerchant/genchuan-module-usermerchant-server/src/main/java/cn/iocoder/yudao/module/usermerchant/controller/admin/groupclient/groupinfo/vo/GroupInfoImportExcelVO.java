package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.write.style.HeadFontStyle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=false)
public class GroupInfoImportExcelVO {

    private static final short RED_COLOR = 10;

    @Schema(description = "集团名称，唯一")
    @ExcelProperty("集团名称，唯一")
    @HeadFontStyle(color = RED_COLOR)
    private String name;

    @Schema(description = "联系人" )
    @ExcelProperty("联系人")
    @HeadFontStyle(color = RED_COLOR)
    private String contact;

    @Schema(description = "联系手机号" )
    @ExcelProperty("联系手机号")
    @HeadFontStyle(color = RED_COLOR)
    private String phone;

    @Schema(description = "集团类型：企业单位/事业单位/政府机构/其他" )
    @ExcelProperty("集团类型：企业单位/事业单位/政府机构/其他")
    @HeadFontStyle(color = RED_COLOR)
    private String groupType;

    @Schema(description = "集团地址" )
    @ExcelProperty("集团地址")
    private String address;

    @Schema(description = "备注" )
    @ExcelProperty("备注")
    private String remark;

}
