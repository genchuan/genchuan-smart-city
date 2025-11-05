package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo.MonCompInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo.MonCompInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo.MonCompInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompinfo.MonCompInfoDO;
import cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompinfo.MonCompInfoService;

@Tag(name = "管理后台 - 监测部件信息")
@RestController
@RequestMapping("/datacenter/mon-comp-info")
@Validated
public class MonCompInfoController {

    @Resource
    private MonCompInfoService monCompInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件信息")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-info:create')")
    public CommonResult<Long> createMonCompInfo(@Valid @RequestBody MonCompInfoSaveReqVO createReqVO) {
        return success(monCompInfoService.createMonCompInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件信息")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-info:update')")
    public CommonResult<Boolean> updateMonCompInfo(@Valid @RequestBody MonCompInfoSaveReqVO updateReqVO) {
        monCompInfoService.updateMonCompInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-info:delete')")
    public CommonResult<Boolean> deleteMonCompInfo(@RequestParam("id") Long id) {
        monCompInfoService.deleteMonCompInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-info:query')")
    public CommonResult<MonCompInfoRespVO> getMonCompInfo(@RequestParam("id") Long id) {
        MonCompInfoDO monCompInfo = monCompInfoService.getMonCompInfo(id);
        return success(BeanUtils.toBean(monCompInfo, MonCompInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-info:query')")
    public CommonResult<PageResult<MonCompInfoRespVO>> getMonCompInfoPage(@Valid MonCompInfoPageReqVO pageReqVO) {
        PageResult<MonCompInfoDO> pageResult = monCompInfoService.getMonCompInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonCompInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonCompInfoExcel(@Valid MonCompInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonCompInfoDO> list = monCompInfoService.getMonCompInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件信息.xls", "数据", MonCompInfoRespVO.class,
                        BeanUtils.toBean(list, MonCompInfoRespVO.class));
    }

}