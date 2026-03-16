package cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn.vo;

import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnRespVO;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 预警 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadWarnPageRespVO extends SysWarnRespVO {
}
