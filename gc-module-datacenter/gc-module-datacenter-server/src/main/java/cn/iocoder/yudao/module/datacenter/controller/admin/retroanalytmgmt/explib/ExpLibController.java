package cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib;

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo.ExpLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo.ExpLibRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo.ExpLibSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.explib.ExpLibDO;
import cn.iocoder.yudao.module.datacenter.service.retroanalytmgmt.explib.ExpLibService;

@Tag(name = "管理后台 - 经验库")
@RestController
@RequestMapping("/datacenter/exp-lib")
@Validated
public class ExpLibController {

    @Resource
    private ExpLibService expLibService;

    @PostMapping("/create")
    @Operation(summary = "创建经验库")
    @PreAuthorize("@ss.hasPermission('datacenter:exp-lib:create')")
    public CommonResult<Long> createExpLib(@Valid @RequestBody ExpLibSaveReqVO createReqVO) {
        return success(expLibService.createExpLib(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新经验库")
    @PreAuthorize("@ss.hasPermission('datacenter:exp-lib:update')")
    public CommonResult<Boolean> updateExpLib(@Valid @RequestBody ExpLibSaveReqVO updateReqVO) {
        expLibService.updateExpLib(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经验库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:exp-lib:delete')")
    public CommonResult<Boolean> deleteExpLib(@RequestParam("id") Long id) {
        expLibService.deleteExpLib(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得经验库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:exp-lib:query')")
    public CommonResult<ExpLibRespVO> getExpLib(@RequestParam("id") Long id) {
        ExpLibDO expLib = expLibService.getExpLib(id);
        return success(BeanUtils.toBean(expLib, ExpLibRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经验库分页")
    @PreAuthorize("@ss.hasPermission('datacenter:exp-lib:query')")
    public CommonResult<PageResult<ExpLibRespVO>> getExpLibPage(@Valid ExpLibPageReqVO pageReqVO) {
        PageResult<ExpLibDO> pageResult = expLibService.getExpLibPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ExpLibRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经验库 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:exp-lib:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExpLibExcel(@Valid ExpLibPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ExpLibDO> list = expLibService.getExpLibPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "经验库.xls", "数据", ExpLibRespVO.class,
                        BeanUtils.toBean(list, ExpLibRespVO.class));
    }

}