package cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdicttype.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 业务字典分类分页 Request VO")
@Data
public class BizDictTypePageReqVO extends PageParam {

    @Schema(description = "[类型编码] 如：sex、status")
    private String uniCode;

    @Schema(description = "[类型名称] 如：性别、状态", example = "张三")
    private String name;

    @Schema(description = "[分类排序]")
    private Integer sort;

    @Schema(description = "[类型描述] 字典分类的详细说明", example = "你说的对")
    private String description;

    @Schema(description = "[分类备注] 如：“性别字典，用于用户表性别字段”", example = "随便")
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
