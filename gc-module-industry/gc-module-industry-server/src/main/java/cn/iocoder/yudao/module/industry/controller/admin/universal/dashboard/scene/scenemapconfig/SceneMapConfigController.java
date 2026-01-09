package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig.vo.SceneMapConfigPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig.vo.SceneMapConfigRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenemapconfig.vo.SceneMapConfigSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenemapconfig.SceneMapConfigDO;
import cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.scenemapconfig.SceneMapConfigService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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



@Tag(name = "管理后台 - 场景地图整体配置")
@RestController
@RequestMapping("/industry/scene-map-config")
@Validated
public class SceneMapConfigController {

    @Resource
    private SceneMapConfigService sceneMapConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建场景地图整体配置")
    @PreAuthorize("@ss.hasPermission('industry:scene-map-config:create')")
    public CommonResult<Long> createSceneMapConfig(@Valid @RequestBody SceneMapConfigSaveReqVO createReqVO) {
        return success(sceneMapConfigService.createSceneMapConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新场景地图整体配置")
    @PreAuthorize("@ss.hasPermission('industry:scene-map-config:update')")
    public CommonResult<Boolean> updateSceneMapConfig(@Valid @RequestBody SceneMapConfigSaveReqVO updateReqVO) {
        sceneMapConfigService.updateSceneMapConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除场景地图整体配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:scene-map-config:delete')")
    public CommonResult<Boolean> deleteSceneMapConfig(@RequestParam("id") Long id) {
        sceneMapConfigService.deleteSceneMapConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得场景地图整体配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:scene-map-config:query')")
    public CommonResult<SceneMapConfigRespVO> getSceneMapConfig(@RequestParam("id") Long id) {
        SceneMapConfigDO sceneMapConfig = sceneMapConfigService.getSceneMapConfig(id);
        return success(BeanUtils.toBean(sceneMapConfig, SceneMapConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得场景地图整体配置分页")
    @PreAuthorize("@ss.hasPermission('industry:scene-map-config:query')")
    public CommonResult<PageResult<SceneMapConfigRespVO>> getSceneMapConfigPage(@Valid SceneMapConfigPageReqVO pageReqVO) {
        PageResult<SceneMapConfigDO> pageResult = sceneMapConfigService.getSceneMapConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SceneMapConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出场景地图整体配置 Excel")
    @PreAuthorize("@ss.hasPermission('industry:scene-map-config:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSceneMapConfigExcel(@Valid SceneMapConfigPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SceneMapConfigDO> list = sceneMapConfigService.getSceneMapConfigPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "场景地图整体配置.xls", "数据", SceneMapConfigRespVO.class,
                        BeanUtils.toBean(list, SceneMapConfigRespVO.class));
    }

}
