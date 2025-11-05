package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo.MngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo.MngCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo.MngCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.mngcomp.MngCompDO;
import cn.iocoder.yudao.module.datacenter.service.mngcompcfg.mngcomp.MngCompService;

@Tag(name = "管理后台 - 管理部件统计")
@RestController
@RequestMapping("/datacenter/mng-comp")
@Validated
public class MngCompController {

    @Resource
    private MngCompService mngCompService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件统计")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-comp:create')")
    public CommonResult<Long> createMngComp(@Valid @RequestBody MngCompSaveReqVO createReqVO) {
        return success(mngCompService.createMngComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件统计")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-comp:update')")
    public CommonResult<Boolean> updateMngComp(@Valid @RequestBody MngCompSaveReqVO updateReqVO) {
        mngCompService.updateMngComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mng-comp:delete')")
    public CommonResult<Boolean> deleteMngComp(@RequestParam("id") Long id) {
        mngCompService.deleteMngComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-comp:query')")
    public CommonResult<MngCompRespVO> getMngComp(@RequestParam("id") Long id) {
        MngCompDO mngComp = mngCompService.getMngComp(id);
        return success(BeanUtils.toBean(mngComp, MngCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-comp:query')")
    public CommonResult<PageResult<MngCompRespVO>> getMngCompPage(@Valid MngCompPageReqVO pageReqVO) {
        PageResult<MngCompDO> pageResult = mngCompService.getMngCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MngCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mng-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMngCompExcel(@Valid MngCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MngCompDO> list = mngCompService.getMngCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件统计.xls", "数据", MngCompRespVO.class,
                        BeanUtils.toBean(list, MngCompRespVO.class));
    }

}