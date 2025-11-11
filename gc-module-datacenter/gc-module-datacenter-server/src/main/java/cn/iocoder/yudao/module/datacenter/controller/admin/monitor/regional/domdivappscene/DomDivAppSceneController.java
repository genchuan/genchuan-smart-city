package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene.vo.DomDivAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene.vo.DomDivAppSceneRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene.vo.DomDivAppSceneSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivappscene.DomDivAppSceneDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivappscene.DomDivAppSceneService;

@Tag(name = "管理后台 - 应用场景分域")
@RestController
@RequestMapping("/datacenter/dom-div-app-scene")
@Validated
public class DomDivAppSceneController {

    @Resource
    private DomDivAppSceneService domDivAppSceneService;

    @PostMapping("/create")
    @Operation(summary = "创建应用场景分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-app-scene:create')")
    public CommonResult<Long> createDomDivAppScene(@Valid @RequestBody DomDivAppSceneSaveReqVO createReqVO) {
        return success(domDivAppSceneService.createDomDivAppScene(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应用场景分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-app-scene:update')")
    public CommonResult<Boolean> updateDomDivAppScene(@Valid @RequestBody DomDivAppSceneSaveReqVO updateReqVO) {
        domDivAppSceneService.updateDomDivAppScene(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应用场景分域")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-app-scene:delete')")
    public CommonResult<Boolean> deleteDomDivAppScene(@RequestParam("id") Long id) {
        domDivAppSceneService.deleteDomDivAppScene(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应用场景分域")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-app-scene:query')")
    public CommonResult<DomDivAppSceneRespVO> getDomDivAppScene(@RequestParam("id") Long id) {
        DomDivAppSceneDO domDivAppScene = domDivAppSceneService.getDomDivAppScene(id);
        return success(BeanUtils.toBean(domDivAppScene, DomDivAppSceneRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应用场景分域分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-app-scene:query')")
    public CommonResult<PageResult<DomDivAppSceneRespVO>> getDomDivAppScenePage(@Valid DomDivAppScenePageReqVO pageReqVO) {
        PageResult<DomDivAppSceneDO> pageResult = domDivAppSceneService.getDomDivAppScenePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomDivAppSceneRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应用场景分域 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-app-scene:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomDivAppSceneExcel(@Valid DomDivAppScenePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomDivAppSceneDO> list = domDivAppSceneService.getDomDivAppScenePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应用场景分域.xls", "数据", DomDivAppSceneRespVO.class,
                        BeanUtils.toBean(list, DomDivAppSceneRespVO.class));
    }

}