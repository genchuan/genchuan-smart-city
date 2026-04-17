package cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 业务字典项分页 Request VO")
@Data
public class ListByTypeReq {

    @Schema(description = "[关联类型编码] ")
    private String typeCode;

    @Schema(description = "[关联类型名称] 只能看不能用，因为类型名称可能改变")
    private String typeName;



}
