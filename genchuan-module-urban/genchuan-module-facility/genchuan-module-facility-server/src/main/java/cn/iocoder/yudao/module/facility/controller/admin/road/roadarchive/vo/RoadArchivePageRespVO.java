package cn.iocoder.yudao.module.facility.controller.admin.road.roadarchive.vo;

import cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo.SysArchiveRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.workorder.vo.WorkOrderRespVO;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 工单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RoadArchivePageRespVO extends SysArchiveRespVO {
}
