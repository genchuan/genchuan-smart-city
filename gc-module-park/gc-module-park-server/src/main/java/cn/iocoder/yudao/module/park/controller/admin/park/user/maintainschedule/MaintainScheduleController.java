package cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo.MaintainSchedulePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo.MaintainScheduleRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo.MaintainScheduleSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainschedule.MaintainScheduleDO;
import cn.iocoder.yudao.module.park.service.park.user.maintainschedule.MaintainScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 运维排班")
@RestController
@RequestMapping("/park/maintain-schedule")
@Validated
public class MaintainScheduleController {

    @Resource
    private MaintainScheduleService maintainScheduleService;

    @PostMapping("/create")
    @Operation(summary = "创建运维排班")
    @PreAuthorize("@ss.hasPermission('park:maintain-schedule:create')")
    public CommonResult<Long> createMaintainSchedule(@Valid @RequestBody MaintainScheduleSaveReqVO createReqVO) {
        return success(maintainScheduleService.createMaintainSchedule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新运维排班")
    @PreAuthorize("@ss.hasPermission('park:maintain-schedule:update')")
    public CommonResult<Boolean> updateMaintainSchedule(@Valid @RequestBody MaintainScheduleSaveReqVO updateReqVO) {
        maintainScheduleService.updateMaintainSchedule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除运维排班")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:maintain-schedule:delete')")
    public CommonResult<Boolean> deleteMaintainSchedule(@RequestParam("id") Long id) {
        maintainScheduleService.deleteMaintainSchedule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得运维排班")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:maintain-schedule:query')")
    public CommonResult<MaintainScheduleRespVO> getMaintainSchedule(@RequestParam("id") Long id) {
        MaintainScheduleDO maintainSchedule = maintainScheduleService.getMaintainSchedule(id);
        return success(BeanUtils.toBean(maintainSchedule, MaintainScheduleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得运维排班分页")
    @PreAuthorize("@ss.hasPermission('park:maintain-schedule:query')")
    public CommonResult<PageResult<MaintainScheduleRespVO>> getMaintainSchedulePage(@Valid MaintainSchedulePageReqVO pageReqVO) {
        PageResult<MaintainScheduleDO> pageResult = maintainScheduleService.getMaintainSchedulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintainScheduleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出运维排班 Excel")
    @PreAuthorize("@ss.hasPermission('park:maintain-schedule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMaintainScheduleExcel(@Valid MaintainSchedulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintainScheduleDO> list = maintainScheduleService.getMaintainSchedulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "运维排班.xls", "数据", MaintainScheduleRespVO.class,
                        BeanUtils.toBean(list, MaintainScheduleRespVO.class));
    }

}
