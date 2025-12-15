package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo.SceneStatusIconPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo.SceneStatusIconRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.scenestatusicon.vo.SceneStatusIconSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.scenestatusicon.SceneStatusIconDO;
import cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.scenestatusicon.SceneStatusIconService;
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



@Tag(name = "管理后台 - 场景状态字段图标配置")
@RestController
@RequestMapping("/industry/scene-status-icon")
@Validated
public class SceneStatusIconController {

    @Resource
    private SceneStatusIconService sceneStatusIconService;

    @PostMapping("/create")
    @Operation(summary = "创建场景状态字段图标配置")
    @PreAuthorize("@ss.hasPermission('industry:scene-status-icon:create')")
    public CommonResult<Long> createSceneStatusIcon(@Valid @RequestBody SceneStatusIconSaveReqVO createReqVO) {
        return success(sceneStatusIconService.createSceneStatusIcon(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新场景状态字段图标配置")
    @PreAuthorize("@ss.hasPermission('industry:scene-status-icon:update')")
    public CommonResult<Boolean> updateSceneStatusIcon(@Valid @RequestBody SceneStatusIconSaveReqVO updateReqVO) {
        sceneStatusIconService.updateSceneStatusIcon(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除场景状态字段图标配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:scene-status-icon:delete')")
    public CommonResult<Boolean> deleteSceneStatusIcon(@RequestParam("id") Long id) {
        sceneStatusIconService.deleteSceneStatusIcon(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得场景状态字段图标配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:scene-status-icon:query')")
    public CommonResult<SceneStatusIconRespVO> getSceneStatusIcon(@RequestParam("id") Long id) {
        SceneStatusIconDO sceneStatusIcon = sceneStatusIconService.getSceneStatusIcon(id);
        return success(BeanUtils.toBean(sceneStatusIcon, SceneStatusIconRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得场景状态字段图标配置分页")
    @PreAuthorize("@ss.hasPermission('industry:scene-status-icon:query')")
    public CommonResult<PageResult<SceneStatusIconRespVO>> getSceneStatusIconPage(@Valid SceneStatusIconPageReqVO pageReqVO) {
        PageResult<SceneStatusIconDO> pageResult = sceneStatusIconService.getSceneStatusIconPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SceneStatusIconRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出场景状态字段图标配置 Excel")
    @PreAuthorize("@ss.hasPermission('industry:scene-status-icon:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSceneStatusIconExcel(@Valid SceneStatusIconPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SceneStatusIconDO> list = sceneStatusIconService.getSceneStatusIconPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "场景状态字段图标配置.xls", "数据", SceneStatusIconRespVO.class,
                        BeanUtils.toBean(list, SceneStatusIconRespVO.class));
    }

}
