package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.field.SceneFieldDO;
import cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.field.SceneFieldService;
import org.apache.commons.lang3.StringUtils;
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

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.SCENE_MAP_CONFIG_NOT_EXISTS;


@Tag(name = "管理后台 - 场景大屏2-场景字段")
@RestController
@RequestMapping("/industry/scene-field")
@Validated
public class SceneFieldController {

    @Resource
    private SceneFieldService sceneFieldService;

    @GetMapping("/listFiledBySceneId/{sceneKey}")
    @Operation(summary = "2. 获取场景字段列表接口")
    @PreAuthorize("@ss.hasPermission('industry:scene-field-list-scene:query')")
    public CommonResult<List<SceneFieldRespVO>> listFiledBySceneId(@PathVariable String sceneKey) {
        if (StringUtils.isBlank(sceneKey)) {
            throw exception(new ErrorCode(400, "sceneKey不能为空"));
        }
        String sceneCode=sceneKey;
        List<SceneFieldRespVO> sceneFieldList = sceneFieldService.listFiledBySceneId(sceneCode);
        return success(BeanUtils.toBean(sceneFieldList, SceneFieldRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建场景字段")
    @PreAuthorize("@ss.hasPermission('industry:scene-field:create')")
    public CommonResult<Long> createSceneField(@Valid @RequestBody SceneFieldSaveReqVO createReqVO) {
        return success(sceneFieldService.createSceneField(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新场景字段")
    @PreAuthorize("@ss.hasPermission('industry:scene-field:update')")
    public CommonResult<Boolean> updateSceneField(@Valid @RequestBody SceneFieldSaveReqVO updateReqVO) {
        sceneFieldService.updateSceneField(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除场景字段")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:scene-field:delete')")
    public CommonResult<Boolean> deleteSceneField(@RequestParam("id") Long id) {
        sceneFieldService.deleteSceneField(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得场景字段")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:scene-field:query')")
    public CommonResult<SceneFieldRespVO> getSceneField(@RequestParam("id") Long id) {
        SceneFieldDO sceneField = sceneFieldService.getSceneField(id);
        return success(BeanUtils.toBean(sceneField, SceneFieldRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得场景字段分页")
    @PreAuthorize("@ss.hasPermission('industry:scene-field:query')")
    public CommonResult<PageResult<SceneFieldRespVO>> getSceneFieldPage(@Valid SceneFieldPageReqVO pageReqVO) {
        PageResult<SceneFieldDO> pageResult = sceneFieldService.getSceneFieldPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SceneFieldRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出场景字段 Excel")
    @PreAuthorize("@ss.hasPermission('industry:scene-field:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSceneFieldExcel(@Valid SceneFieldPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SceneFieldDO> list = sceneFieldService.getSceneFieldPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "场景字段.xls", "数据", SceneFieldRespVO.class,
                        BeanUtils.toBean(list, SceneFieldRespVO.class));
    }

}
