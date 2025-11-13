package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnappscene.vo.EarlyWarnAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnappscene.vo.EarlyWarnAppSceneRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnappscene.vo.EarlyWarnAppSceneSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnappscene.EarlyWarnAppSceneDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnappscene.EarlyWarnAppSceneService;

@Tag(name = "管理后台 - 按应用场景预警告警统计")
@RestController
@RequestMapping("/datacenter/early-warn-app-scene")
@Validated
public class EarlyWarnAppSceneController {

    @Resource
    private EarlyWarnAppSceneService earlyWarnAppSceneService;

    @PostMapping("/create")
    @Operation(summary = "创建按应用场景预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-app-scene:create')")
    public CommonResult<Long> createEarlyWarnAppScene(@Valid @RequestBody EarlyWarnAppSceneSaveReqVO createReqVO) {
        return success(earlyWarnAppSceneService.createEarlyWarnAppScene(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按应用场景预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-app-scene:update')")
    public CommonResult<Boolean> updateEarlyWarnAppScene(@Valid @RequestBody EarlyWarnAppSceneSaveReqVO updateReqVO) {
        earlyWarnAppSceneService.updateEarlyWarnAppScene(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按应用场景预警告警统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-app-scene:delete')")
    public CommonResult<Boolean> deleteEarlyWarnAppScene(@RequestParam("id") Long id) {
        earlyWarnAppSceneService.deleteEarlyWarnAppScene(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按应用场景预警告警统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-app-scene:query')")
    public CommonResult<EarlyWarnAppSceneRespVO> getEarlyWarnAppScene(@RequestParam("id") Long id) {
        EarlyWarnAppSceneDO earlyWarnAppScene = earlyWarnAppSceneService.getEarlyWarnAppScene(id);
        return success(BeanUtils.toBean(earlyWarnAppScene, EarlyWarnAppSceneRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按应用场景预警告警统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-app-scene:query')")
    public CommonResult<PageResult<EarlyWarnAppSceneRespVO>> getEarlyWarnAppScenePage(@Valid EarlyWarnAppScenePageReqVO pageReqVO) {
        PageResult<EarlyWarnAppSceneDO> pageResult = earlyWarnAppSceneService.getEarlyWarnAppScenePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnAppSceneRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按应用场景预警告警统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-app-scene:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnAppSceneExcel(@Valid EarlyWarnAppScenePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnAppSceneDO> list = earlyWarnAppSceneService.getEarlyWarnAppScenePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按应用场景预警告警统计.xls", "数据", EarlyWarnAppSceneRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnAppSceneRespVO.class));
    }

}