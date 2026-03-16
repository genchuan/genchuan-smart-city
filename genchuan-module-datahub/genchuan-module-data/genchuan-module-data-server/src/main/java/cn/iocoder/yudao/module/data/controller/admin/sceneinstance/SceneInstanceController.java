package cn.iocoder.yudao.module.data.controller.admin.sceneinstance;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstanceRespVO;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.sceneinstance.vo.SceneInstanceUpdateStatusReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.sceneinstance.SceneInstanceDO;
import cn.iocoder.yudao.module.data.service.sceneinstance.SceneInstanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 应用场景实例")
@RestController
@RequestMapping("/data/scene-instance")
@Validated
public class SceneInstanceController {

    @Resource
    private SceneInstanceService sceneInstanceService;

    @PostMapping("/create")
    @Operation(summary = "创建应用场景实例")
    @PreAuthorize("@ss.hasPermission('data:scene-instance:create')")
    public CommonResult<Long> createSceneInstance(@Valid @RequestBody SceneInstanceSaveReqVO createReqVO) {
        return success(sceneInstanceService.createSceneInstance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应用场景实例")
    @PreAuthorize("@ss.hasPermission('data:scene-instance:update')")
    public CommonResult<Boolean> updateSceneInstance(@Valid @RequestBody SceneInstanceSaveReqVO updateReqVO) {
        sceneInstanceService.updateSceneInstance(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应用场景实例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:scene-instance:delete')")
    public CommonResult<Boolean> deleteSceneInstance(@RequestParam("id") Long id) {
        sceneInstanceService.deleteSceneInstance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应用场景实例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:scene-instance:query')")
    public CommonResult<SceneInstanceRespVO> getSceneInstance(@RequestParam("id") Long id) {
        SceneInstanceDO sceneInstance = sceneInstanceService.getSceneInstance(id);
        return success(BeanUtils.toBean(sceneInstance, SceneInstanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应用场景实例分页")
    @PreAuthorize("@ss.hasPermission('data:scene-instance:query')")
    public CommonResult<PageResult<SceneInstanceRespVO>> getSceneInstancePage(@Valid SceneInstancePageReqVO pageReqVO) {
        PageResult<SceneInstanceDO> pageResult = sceneInstanceService.getSceneInstancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SceneInstanceRespVO.class));
    }

    @PutMapping("/update-status-batch")
    @Operation(summary = "批量更新应用场景实例状态")
    @PreAuthorize("@ss.hasPermission('data:scene-instance:update')")
    public CommonResult<Integer> updateSceneInstanceStatusBatch(@Valid @RequestBody SceneInstanceUpdateStatusReqVO updateReqVO) {
        Integer updatedCount = sceneInstanceService.updateSceneInstanceStatusBatch(updateReqVO);
        return success(updatedCount);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应用场景实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:scene-instance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSceneInstanceExcel(@Valid SceneInstancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SceneInstanceDO> list = sceneInstanceService.getSceneInstancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应用场景实例.xls", "数据", SceneInstanceRespVO.class,
                        BeanUtils.toBean(list, SceneInstanceRespVO.class));
    }

    @PostMapping("/import")
    @Operation(summary = "导入应用场景实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:scene-instance:import')")
    public CommonResult<String> importSceneInstanceExcel(@RequestParam("file") MultipartFile file) throws IOException {
        String result = sceneInstanceService.importSceneInstanceExcel(file);
        return success(result);
    }

}