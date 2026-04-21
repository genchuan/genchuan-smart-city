package cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 业务字典项分页 Request VO")
@Data
public class BizDictItemPageReqVO extends PageParam {

    @Schema(description = "[关联类型编码] 关联park_dict_type.uni_code")
    private String typeCode;

    @Schema(description = "[字典键] 如：1、0、success")
    private String dictKey;

    @Schema(description = "[字典显示名] 如：男、女、成功")
    private String dictLabel;

    @Schema(description = "[颜色] 如：#1890ff")
    private String color;

    @Schema(description = "[同类型内排序]")
    private Integer sort;

    @Schema(description = "[字典项描述]", example = "你猜")
    private String description;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    @Schema(description = "[状态]如:0-禁用/1-启用", example = "2")
    private Integer status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
