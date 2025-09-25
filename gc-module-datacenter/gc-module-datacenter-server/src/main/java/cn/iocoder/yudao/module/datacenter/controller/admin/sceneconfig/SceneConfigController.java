package cn.iocoder.yudao.module.datacenter.controller.admin.sceneconfig;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.sceneconfig.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.sceneconfig.SceneConfigDO;
import cn.iocoder.yudao.module.datacenter.service.sceneconfig.SceneConfigService;

@Tag(name = "管理后台 - 场景分类")
@RestController
@RequestMapping("/datacenter/scene-config")
@Validated
public class SceneConfigController {

    @Resource
    private SceneConfigService sceneConfigService;

    @GetMapping("/tree")
    @Operation(summary = "获得场景分类树形结构")
    @PreAuthorize("@ss.hasPermission('datacenter:scene-config:query')")
    public CommonResult<List<SceneConfigTreeRespVO>> getSceneConfigTree() {
        List<SceneConfigTreeRespVO> tree = sceneConfigService.getSceneConfigTree();
        return success(tree);
    }

    @PostMapping("/create")
    @Operation(summary = "创建场景分类")
    @PreAuthorize("@ss.hasPermission('datacenter:scene-config:create')")
    public CommonResult<Long> createSceneConfig(@Valid @RequestBody SceneConfigSaveReqVO createReqVO) {
        return success(sceneConfigService.createSceneConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新场景分类")
    @PreAuthorize("@ss.hasPermission('datacenter:scene-config:update')")
    public CommonResult<Boolean> updateSceneConfig(@Valid @RequestBody SceneConfigSaveReqVO updateReqVO) {
        sceneConfigService.updateSceneConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除场景分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:scene-config:delete')")
    public CommonResult<Boolean> deleteSceneConfig(@RequestParam("id") Long id) {
        sceneConfigService.deleteSceneConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得场景分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:scene-config:query')")
    public CommonResult<SceneConfigRespVO> getSceneConfig(@RequestParam("id") Long id) {
        SceneConfigDO sceneConfig = sceneConfigService.getSceneConfig(id);
        return success(BeanUtils.toBean(sceneConfig, SceneConfigRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得场景分类列表")
    @PreAuthorize("@ss.hasPermission('datacenter:scene-config:query')")
    public CommonResult<List<SceneConfigRespVO>> getSceneConfigList(@Valid SceneConfigListReqVO listReqVO) {
        List<SceneConfigDO> list = sceneConfigService.getSceneConfigList(listReqVO);
        return success(BeanUtils.toBean(list, SceneConfigRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出场景分类 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:scene-config:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSceneConfigExcel(@Valid SceneConfigListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<SceneConfigDO> list = sceneConfigService.getSceneConfigList(listReqVO);
        // 导出 Excel
//        ExcelUtils.write(response, "场景分类.xls", "数据", SceneConfigRespVO.class,
//                        BeanUtils.toBean(list, SceneConfigRespVO.class));
    }

}