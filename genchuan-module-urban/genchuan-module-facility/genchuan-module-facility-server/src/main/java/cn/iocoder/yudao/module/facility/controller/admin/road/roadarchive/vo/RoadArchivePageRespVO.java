package cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo.SysArchiveRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 工单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadArchivePageRespVO extends SysArchiveRespVO {
}
