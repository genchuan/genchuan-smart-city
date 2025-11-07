package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo.EmerMatInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo.EmerMatInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo.EmerMatInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emermatinfo.EmerMatInfoDO;
import cn.iocoder.yudao.module.datacenter.service.resschedmanage.emermatinfo.EmerMatInfoService;

@Tag(name = "管理后台 - 应急物资信息")
@RestController
@RequestMapping("/datacenter/emer-mat-info")
@Validated
public class EmerMatInfoController {

    @Resource
    private EmerMatInfoService emerMatInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建应急物资信息")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-info:create')")
    public CommonResult<Long> createEmerMatInfo(@Valid @RequestBody EmerMatInfoSaveReqVO createReqVO) {
        return success(emerMatInfoService.createEmerMatInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应急物资信息")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-info:update')")
    public CommonResult<Boolean> updateEmerMatInfo(@Valid @RequestBody EmerMatInfoSaveReqVO updateReqVO) {
        emerMatInfoService.updateEmerMatInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应急物资信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-info:delete')")
    public CommonResult<Boolean> deleteEmerMatInfo(@RequestParam("id") Long id) {
        emerMatInfoService.deleteEmerMatInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应急物资信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-info:query')")
    public CommonResult<EmerMatInfoRespVO> getEmerMatInfo(@RequestParam("id") Long id) {
        EmerMatInfoDO emerMatInfo = emerMatInfoService.getEmerMatInfo(id);
        return success(BeanUtils.toBean(emerMatInfo, EmerMatInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应急物资信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-info:query')")
    public CommonResult<PageResult<EmerMatInfoRespVO>> getEmerMatInfoPage(@Valid EmerMatInfoPageReqVO pageReqVO) {
        PageResult<EmerMatInfoDO> pageResult = emerMatInfoService.getEmerMatInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerMatInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应急物资信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-mat-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerMatInfoExcel(@Valid EmerMatInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerMatInfoDO> list = emerMatInfoService.getEmerMatInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应急物资信息.xls", "数据", EmerMatInfoRespVO.class,
                        BeanUtils.toBean(list, EmerMatInfoRespVO.class));
    }

}