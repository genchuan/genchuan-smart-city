package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.ops;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 业务字典项分页 Request VO")
@Data
public class ListByTypeReq extends PageParam {

    @Schema(description = "[关联类型编码] 关联park_dict_type.uni_code")
    private String typeCode;

}
