package cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources.vo.PatrolResourcesPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources.vo.PatrolResourcesRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources.vo.PatrolResourcesSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolresources.PatrolResourcesDO;
import cn.iocoder.yudao.module.smartcity.service.patrolresources.PatrolResourcesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 巡查资源")
@RestController
@RequestMapping("/smartcity/patrol-resources")
@Validated
public class PatrolResourcesController {

    @Resource
    private PatrolResourcesService patrolResourcesService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查资源")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-resources:create')")
    public CommonResult<Long> createPatrolResources(@Valid @RequestBody PatrolResourcesSaveReqVO createReqVO) {
        return success(patrolResourcesService.createPatrolResources(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查资源")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-resources:update')")
    public CommonResult<Boolean> updatePatrolResources(@Valid @RequestBody PatrolResourcesSaveReqVO updateReqVO) {
        patrolResourcesService.updatePatrolResources(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查资源")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-resources:delete')")
    public CommonResult<Boolean> deletePatrolResources(@RequestParam("id") Long id) {
        patrolResourcesService.deletePatrolResources(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查资源")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-resources:query')")
    public CommonResult<PatrolResourcesRespVO> getPatrolResources(@RequestParam("id") Long id) {
        PatrolResourcesDO patrolResources = patrolResourcesService.getPatrolResources(id);
        return success(BeanUtils.toBean(patrolResources, PatrolResourcesRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查资源分页")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-resources:query')")
    public CommonResult<PageResult<PatrolResourcesRespVO>> getPatrolResourcesPage(@Valid PatrolResourcesPageReqVO pageReqVO) {
        PageResult<PatrolResourcesDO> pageResult = patrolResourcesService.getPatrolResourcesPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PatrolResourcesRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查资源 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-resources:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPatrolResourcesExcel(@Valid PatrolResourcesPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PatrolResourcesDO> list = patrolResourcesService.getPatrolResourcesPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查资源.xls", "数据", PatrolResourcesRespVO.class,
                        BeanUtils.toBean(list, PatrolResourcesRespVO.class));
    }

}