package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.mngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.mngmatter.vo.MngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.mngmatter.vo.MngMatterRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.mngmatter.vo.MngMatterSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.mngmatter.MngMatterDO;
import cn.iocoder.yudao.module.datacenter.service.mngmattercfg.mngmatter.MngMatterService;

@Tag(name = "管理后台 - 管理事项统计")
@RestController
@RequestMapping("/datacenter/mng-matter")
@Validated
public class MngMatterController {

    @Resource
    private MngMatterService mngMatterService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项统计")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-matter:create')")
    public CommonResult<Long> createMngMatter(@Valid @RequestBody MngMatterSaveReqVO createReqVO) {
        return success(mngMatterService.createMngMatter(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项统计")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-matter:update')")
    public CommonResult<Boolean> updateMngMatter(@Valid @RequestBody MngMatterSaveReqVO updateReqVO) {
        mngMatterService.updateMngMatter(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-matter:delete')")
    public CommonResult<Boolean> deleteMngMatter(@RequestParam("id") Long id) {
        mngMatterService.deleteMngMatter(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-matter:query')")
    public CommonResult<MngMatterRespVO> getMngMatter(@RequestParam("id") Long id) {
        MngMatterDO mngMatter = mngMatterService.getMngMatter(id);
        return success(BeanUtils.toBean(mngMatter, MngMatterRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-matter:query')")
    public CommonResult<PageResult<MngMatterRespVO>> getMngMatterPage(@Valid MngMatterPageReqVO pageReqVO) {
        PageResult<MngMatterDO> pageResult = mngMatterService.getMngMatterPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MngMatterRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-matter:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMngMatterExcel(@Valid MngMatterPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MngMatterDO> list = mngMatterService.getMngMatterPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项统计.xls", "数据", MngMatterRespVO.class,
                        BeanUtils.toBean(list, MngMatterRespVO.class));
    }

}