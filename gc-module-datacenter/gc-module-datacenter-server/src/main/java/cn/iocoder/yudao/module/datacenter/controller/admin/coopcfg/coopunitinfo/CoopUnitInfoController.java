package cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo.CoopUnitInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo.CoopUnitInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo.CoopUnitInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopunitinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopunitinfo.CoopUnitInfoDO;
import cn.iocoder.yudao.module.datacenter.service.coopcfg.coopunitinfo.CoopUnitInfoService;

@Tag(name = "管理后台 - 联动单位信息")
@RestController
@RequestMapping("/datacenter/coop-unit-info")
@Validated
public class CoopUnitInfoController {

    @Resource
    private CoopUnitInfoService coopUnitInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建联动单位信息")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-unit-info:create')")
    public CommonResult<Long> createCoopUnitInfo(@Valid @RequestBody CoopUnitInfoSaveReqVO createReqVO) {
        return success(coopUnitInfoService.createCoopUnitInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新联动单位信息")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-unit-info:update')")
    public CommonResult<Boolean> updateCoopUnitInfo(@Valid @RequestBody CoopUnitInfoSaveReqVO updateReqVO) {
        coopUnitInfoService.updateCoopUnitInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除联动单位信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:coop-unit-info:delete')")
    public CommonResult<Boolean> deleteCoopUnitInfo(@RequestParam("id") Long id) {
        coopUnitInfoService.deleteCoopUnitInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得联动单位信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-unit-info:query')")
    public CommonResult<CoopUnitInfoRespVO> getCoopUnitInfo(@RequestParam("id") Long id) {
        CoopUnitInfoDO coopUnitInfo = coopUnitInfoService.getCoopUnitInfo(id);
        return success(BeanUtils.toBean(coopUnitInfo, CoopUnitInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得联动单位信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-unit-info:query')")
    public CommonResult<PageResult<CoopUnitInfoRespVO>> getCoopUnitInfoPage(@Valid CoopUnitInfoPageReqVO pageReqVO) {
        PageResult<CoopUnitInfoDO> pageResult = coopUnitInfoService.getCoopUnitInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CoopUnitInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出联动单位信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-unit-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCoopUnitInfoExcel(@Valid CoopUnitInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CoopUnitInfoDO> list = coopUnitInfoService.getCoopUnitInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "联动单位信息.xls", "数据", CoopUnitInfoRespVO.class,
                        BeanUtils.toBean(list, CoopUnitInfoRespVO.class));
    }

}