package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 业务字典分类新增/修改 Request VO")
@Data
public class AddReq {

    @Schema(description = "[类型编码] 如：sex、status", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[类型编码] 如：sex、status不能为空")
    private String uniCode;

    @Schema(description = "[类型名称] 如：性别、状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "[类型名称] 如：性别、状态不能为空")
    private String name;

    //============================非必填但重要
    @Schema(description = "[类型描述] 字典分类的详细说明", example = "你说的对")
    private String description;

    //============================非必填但不重要
    @Schema(description = "[分类备注] 如：“性别字典，用于用户表性别字段”", example = "随便")
    private String remark;

}
