package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo.RelMngMatterMajMinPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo.RelMngMatterMajMinRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo.RelMngMatterMajMinSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.relmngmattermajmin.RelMngMatterMajMinDO;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.relmngmattermajmin.RelMngMatterMajMinService;

@Tag(name = "管理后台 - 管理事项大小类关联")
@RestController
@RequestMapping("/datacenter/rel-mng-matter-maj-min")
@Validated
public class RelMngMatterMajMinController {

    @Resource
    private RelMngMatterMajMinService relMngMatterMajMinService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项大小类关联")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-matter-maj-min:create')")
    public CommonResult<Long> createRelMngMatterMajMin(@Valid @RequestBody RelMngMatterMajMinSaveReqVO createReqVO) {
        return success(relMngMatterMajMinService.createRelMngMatterMajMin(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项大小类关联")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-matter-maj-min:update')")
    public CommonResult<Boolean> updateRelMngMatterMajMin(@Valid @RequestBody RelMngMatterMajMinSaveReqVO updateReqVO) {
        relMngMatterMajMinService.updateRelMngMatterMajMin(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项大小类关联")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-matter-maj-min:delete')")
    public CommonResult<Boolean> deleteRelMngMatterMajMin(@RequestParam("id") Long id) {
        relMngMatterMajMinService.deleteRelMngMatterMajMin(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项大小类关联")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-matter-maj-min:query')")
    public CommonResult<RelMngMatterMajMinRespVO> getRelMngMatterMajMin(@RequestParam("id") Long id) {
        RelMngMatterMajMinDO relMngMatterMajMin = relMngMatterMajMinService.getRelMngMatterMajMin(id);
        return success(BeanUtils.toBean(relMngMatterMajMin, RelMngMatterMajMinRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项大小类关联分页")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-matter-maj-min:query')")
    public CommonResult<PageResult<RelMngMatterMajMinRespVO>> getRelMngMatterMajMinPage(@Valid RelMngMatterMajMinPageReqVO pageReqVO) {
        PageResult<RelMngMatterMajMinDO> pageResult = relMngMatterMajMinService.getRelMngMatterMajMinPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RelMngMatterMajMinRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项大小类关联 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-matter-maj-min:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRelMngMatterMajMinExcel(@Valid RelMngMatterMajMinPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RelMngMatterMajMinDO> list = relMngMatterMajMinService.getRelMngMatterMajMinPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项大小类关联.xls", "数据", RelMngMatterMajMinRespVO.class,
                        BeanUtils.toBean(list, RelMngMatterMajMinRespVO.class));
    }

}