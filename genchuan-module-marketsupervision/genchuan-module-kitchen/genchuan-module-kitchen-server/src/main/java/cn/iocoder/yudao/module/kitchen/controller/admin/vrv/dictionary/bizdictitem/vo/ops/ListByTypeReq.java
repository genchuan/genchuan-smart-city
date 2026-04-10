package cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.ops;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 业务字典项分页 Request VO")
@Data
public class ListByTypeReq {

    @Schema(description = "[关联类型编码] ")
    private String typeCode;

    @Schema(description = "[关联类型名称] 只能看不能用，因为类型名称可能改变")
    private String typeName;



}
