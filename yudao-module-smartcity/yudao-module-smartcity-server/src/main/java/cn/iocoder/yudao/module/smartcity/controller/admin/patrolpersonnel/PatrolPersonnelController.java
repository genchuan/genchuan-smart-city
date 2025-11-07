package cn.iocoder.yudao.module.smartcity.controller.admin.patrolpersonnel;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.patrolpersonnel.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolpersonnel.PatrolPersonnelDO;
import cn.iocoder.yudao.module.smartcity.service.patrolpersonnel.PatrolPersonnelService;

@Tag(name = "管理后台 - 巡查人员")
@RestController
@RequestMapping("/smartcity/patrol-personnel")
@Validated
public class PatrolPersonnelController {

    @Resource
    private PatrolPersonnelService patrolPersonnelService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查人员")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-personnel:create')")
    public CommonResult<Long> createPatrolPersonnel(@Valid @RequestBody PatrolPersonnelSaveReqVO createReqVO) {
        return success(patrolPersonnelService.createPatrolPersonnel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查人员")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-personnel:update')")
    public CommonResult<Boolean> updatePatrolPersonnel(@Valid @RequestBody PatrolPersonnelSaveReqVO updateReqVO) {
        patrolPersonnelService.updatePatrolPersonnel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查人员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-personnel:delete')")
    public CommonResult<Boolean> deletePatrolPersonnel(@RequestParam("id") Long id) {
        patrolPersonnelService.deletePatrolPersonnel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查人员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-personnel:query')")
    public CommonResult<PatrolPersonnelRespVO> getPatrolPersonnel(@RequestParam("id") Long id) {
        PatrolPersonnelDO patrolPersonnel = patrolPersonnelService.getPatrolPersonnel(id);
        return success(BeanUtils.toBean(patrolPersonnel, PatrolPersonnelRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查人员分页")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-personnel:query')")
    public CommonResult<PageResult<PatrolPersonnelRespVO>> getPatrolPersonnelPage(@Valid PatrolPersonnelPageReqVO pageReqVO) {
        PageResult<PatrolPersonnelDO> pageResult = patrolPersonnelService.getPatrolPersonnelPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PatrolPersonnelRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查人员 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-personnel:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPatrolPersonnelExcel(@Valid PatrolPersonnelPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PatrolPersonnelDO> list = patrolPersonnelService.getPatrolPersonnelPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查人员.xls", "数据", PatrolPersonnelRespVO.class,
                        BeanUtils.toBean(list, PatrolPersonnelRespVO.class));
    }

}