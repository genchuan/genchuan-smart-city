package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import cn.idev.excel.annotation.ExcelIgnore;



/**
 * 场站信息 - 创建 Request VO
 */
@Schema(description = "场站信息 - 创建 Request VO")
@Data
public class StationInfoCreateReqVO {

    @Schema(description = "场站编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "ST-20250401-002")
    @NotBlank(message = "场站编号不能为空")
    @Size(max = 100, message = "场站编号长度不能超过100个字符")
    private String stationNo;

    @Schema(description = "场站名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "泉州泰禾充电站")
    @NotBlank(message = "场站名称不能为空")
    @Size(max = 64, message = "场站名称长度不能超过64个字符")
    private String name;

    @Schema(description = "场站类型：公共/商业/小区/产业", requiredMode = Schema.RequiredMode.REQUIRED, example = "商业")
    @NotBlank(message = "场站类型不能为空")
    private String type;

    @Schema(description = "场站地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "福建省泉州市丰泽区安吉路")
    @NotBlank(message = "场站地址不能为空")
    @Size(max = 255, message = "场站地址长度不能超过255个字符")
    private String address;

    @Schema(description = "泊位总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "60")
    @NotNull(message = "泊位总数不能为空")
    private Integer spaceTotal;

    @Schema(description = "负责人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "负责人不能为空")
    private Long userId;

    @Schema(description = "收费标准", example = "首小时6元，后续2.5元/小时")
    @Size(max = 255, message = "收费标准长度不能超过255个字符")
    private String feeStandard;

    @Schema(description = "所属片区ID", example = "1")
    private Long areaId;

    @Schema(description = "运营类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "直接管理")
    @NotBlank(message = "运营类型不能为空")
    private String operateType;

    @Schema(description = "备注", example = "城东商圈场站")
    private String remark;

    @Schema(description = "备用字段1")
    @Size(max = 100, message = "备用字段1长度不能超过100个字符")
    @ExcelIgnore
    private String reserve1;

    @Schema(description = "备用字段2")
    @Size(max = 100, message = "备用字段2长度不能超过100个字符")
    @ExcelIgnore
    private String reserve2;

}
