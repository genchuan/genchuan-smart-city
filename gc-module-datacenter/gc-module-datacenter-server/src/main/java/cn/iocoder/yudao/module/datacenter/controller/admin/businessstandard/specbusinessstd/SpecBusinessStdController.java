package cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo.SpecBusinessStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo.SpecBusinessStdRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo.SpecBusinessStdSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.specbusinessstd.SpecBusinessStdDO;
import cn.iocoder.yudao.module.datacenter.service.businessstandard.specbusinessstd.SpecBusinessStdService;

@Tag(name = "管理后台 - 专项业务标准")
@RestController
@RequestMapping("/datacenter/spec-business-std")
@Validated
public class SpecBusinessStdController {

    @Resource
    private SpecBusinessStdService specBusinessStdService;

    @PostMapping("/create")
    @Operation(summary = "创建专项业务标准")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-std:create')")
    public CommonResult<Long> createSpecBusinessStd(@Valid @RequestBody SpecBusinessStdSaveReqVO createReqVO) {
        return success(specBusinessStdService.createSpecBusinessStd(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新专项业务标准")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-std:update')")
    public CommonResult<Boolean> updateSpecBusinessStd(@Valid @RequestBody SpecBusinessStdSaveReqVO updateReqVO) {
        specBusinessStdService.updateSpecBusinessStd(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除专项业务标准")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-std:delete')")
    public CommonResult<Boolean> deleteSpecBusinessStd(@RequestParam("id") Long id) {
        specBusinessStdService.deleteSpecBusinessStd(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得专项业务标准")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-std:query')")
    public CommonResult<SpecBusinessStdRespVO> getSpecBusinessStd(@RequestParam("id") Long id) {
        SpecBusinessStdDO specBusinessStd = specBusinessStdService.getSpecBusinessStd(id);
        return success(BeanUtils.toBean(specBusinessStd, SpecBusinessStdRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得专项业务标准分页")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-std:query')")
    public CommonResult<PageResult<SpecBusinessStdRespVO>> getSpecBusinessStdPage(@Valid SpecBusinessStdPageReqVO pageReqVO) {
        PageResult<SpecBusinessStdDO> pageResult = specBusinessStdService.getSpecBusinessStdPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SpecBusinessStdRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出专项业务标准 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:spec-business-std:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSpecBusinessStdExcel(@Valid SpecBusinessStdPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SpecBusinessStdDO> list = specBusinessStdService.getSpecBusinessStdPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "专项业务标准.xls", "数据", SpecBusinessStdRespVO.class,
                        BeanUtils.toBean(list, SpecBusinessStdRespVO.class));
    }

}