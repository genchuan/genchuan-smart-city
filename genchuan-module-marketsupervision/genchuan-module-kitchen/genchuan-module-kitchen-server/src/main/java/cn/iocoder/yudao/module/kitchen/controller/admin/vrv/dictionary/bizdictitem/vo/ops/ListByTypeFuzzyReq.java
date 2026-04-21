package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 字典项严格模糊搜索 Request VO")
@Data
public class ListByTypeFuzzyReq {

    @Schema(description = "模糊搜索 - 类型编码（biz_dict_type.uni_code）")
    private String typeCode;

    @Schema(description = "模糊搜索 - 类型名称（biz_dict_type.name）")
    private String typeName;

    @Schema(description = "模糊搜索 - 同时匹配类型编码 + 类型名称")
    private String all;

    @Schema(description = "是否允许返回多种字典类型：true=允许，false=严格模式（默认）")
    private Boolean allowMultiType = false;

}
