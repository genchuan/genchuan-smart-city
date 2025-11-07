package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo.MonEvtInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo.MonEvtInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo.MonEvtInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtinfo.MonEvtInfoDO;
import cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtinfo.MonEvtInfoService;

@Tag(name = "管理后台 - 监测事件信息")
@RestController
@RequestMapping("/datacenter/mon-evt-info")
@Validated
public class MonEvtInfoController {

    @Resource
    private MonEvtInfoService monEvtInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件信息")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-info:create')")
    public CommonResult<Long> createMonEvtInfo(@Valid @RequestBody MonEvtInfoSaveReqVO createReqVO) {
        return success(monEvtInfoService.createMonEvtInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件信息")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-info:update')")
    public CommonResult<Boolean> updateMonEvtInfo(@Valid @RequestBody MonEvtInfoSaveReqVO updateReqVO) {
        monEvtInfoService.updateMonEvtInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-info:delete')")
    public CommonResult<Boolean> deleteMonEvtInfo(@RequestParam("id") Long id) {
        monEvtInfoService.deleteMonEvtInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-info:query')")
    public CommonResult<MonEvtInfoRespVO> getMonEvtInfo(@RequestParam("id") Long id) {
        MonEvtInfoDO monEvtInfo = monEvtInfoService.getMonEvtInfo(id);
        return success(BeanUtils.toBean(monEvtInfo, MonEvtInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-info:query')")
    public CommonResult<PageResult<MonEvtInfoRespVO>> getMonEvtInfoPage(@Valid MonEvtInfoPageReqVO pageReqVO) {
        PageResult<MonEvtInfoDO> pageResult = monEvtInfoService.getMonEvtInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonEvtInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonEvtInfoExcel(@Valid MonEvtInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonEvtInfoDO> list = monEvtInfoService.getMonEvtInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件信息.xls", "数据", MonEvtInfoRespVO.class,
                        BeanUtils.toBean(list, MonEvtInfoRespVO.class));
    }

}