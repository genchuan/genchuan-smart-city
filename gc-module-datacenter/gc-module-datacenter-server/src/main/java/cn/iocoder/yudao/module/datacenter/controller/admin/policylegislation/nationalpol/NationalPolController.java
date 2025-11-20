package cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.nationalpol;

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.nationalpol.vo.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.nationalpol.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.policylegislation.nationalpol.NationalPolDO;
import cn.iocoder.yudao.module.datacenter.service.policylegislation.nationalpol.NationalPolService;

@Tag(name = "管理后台 - 国家政策信息")
@RestController
@RequestMapping("/datacenter/national-pol")
@Validated
public class NationalPolController {

    @Resource
    private NationalPolService nationalPolService;

    @PostMapping("/create")
    @Operation(summary = "创建国家政策信息")
    @PreAuthorize("@ss.hasPermission('datacenter:national-pol:create')")
    public CommonResult<Long> createNationalPol(@Valid @RequestBody NationalPolSaveReqVO createReqVO) {
        return success(nationalPolService.createNationalPol(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新国家政策信息")
    @PreAuthorize("@ss.hasPermission('datacenter:national-pol:update')")
    public CommonResult<Boolean> updateNationalPol(@Valid @RequestBody NationalPolSaveReqVO updateReqVO) {
        nationalPolService.updateNationalPol(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除国家政策信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:national-pol:delete')")
    public CommonResult<Boolean> deleteNationalPol(@RequestParam("id") Long id) {
        nationalPolService.deleteNationalPol(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得国家政策信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:national-pol:query')")
    public CommonResult<NationalPolRespVO> getNationalPol(@RequestParam("id") Long id) {
        NationalPolDO nationalPol = nationalPolService.getNationalPol(id);
        return success(BeanUtils.toBean(nationalPol, NationalPolRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得国家政策信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:national-pol:query')")
    public CommonResult<PageResult<NationalPolRespVO>> getNationalPolPage(@Valid NationalPolPageReqVO pageReqVO) {
        PageResult<NationalPolDO> pageResult = nationalPolService.getNationalPolPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, NationalPolRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出国家政策信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:national-pol:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportNationalPolExcel(@Valid NationalPolPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<NationalPolDO> list = nationalPolService.getNationalPolPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "国家政策信息.xls", "数据", NationalPolRespVO.class,
                        BeanUtils.toBean(list, NationalPolRespVO.class));
    }

}