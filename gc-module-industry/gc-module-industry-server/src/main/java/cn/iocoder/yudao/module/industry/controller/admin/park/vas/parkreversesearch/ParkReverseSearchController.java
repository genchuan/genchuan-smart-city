package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch.vo.ParkReverseSearchPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch.vo.ParkReverseSearchRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreversesearch.vo.ParkReverseSearchSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkreversesearch.ParkReverseSearchDO;
import cn.iocoder.yudao.module.industry.service.park.vas.parkreversesearch.ParkReverseSearchService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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


@Tag(name = "漳州停车管理-增值服务域 - 反向寻车记录")
@RestController
@RequestMapping("/industry/park-reverse-search")
@Validated
public class ParkReverseSearchController {

    @Resource
    private ParkReverseSearchService parkReverseSearchService;

    @PostMapping("/create")
    @Operation(summary = "创建反向寻车记录")
    @PreAuthorize("@ss.hasPermission('industry:park-reverse-search:create')")
    public CommonResult<Long> createParkReverseSearch(@Valid @RequestBody ParkReverseSearchSaveReqVO createReqVO) {
        return success(parkReverseSearchService.createParkReverseSearch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新反向寻车记录")
    @PreAuthorize("@ss.hasPermission('industry:park-reverse-search:update')")
    public CommonResult<Boolean> updateParkReverseSearch(@Valid @RequestBody ParkReverseSearchSaveReqVO updateReqVO) {
        parkReverseSearchService.updateParkReverseSearch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除反向寻车记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-reverse-search:delete')")
    public CommonResult<Boolean> deleteParkReverseSearch(@RequestParam("id") Long id) {
        parkReverseSearchService.deleteParkReverseSearch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得反向寻车记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-reverse-search:query')")
    public CommonResult<ParkReverseSearchRespVO> getParkReverseSearch(@RequestParam("id") Long id) {
        ParkReverseSearchDO parkReverseSearch = parkReverseSearchService.getParkReverseSearch(id);
        return success(BeanUtils.toBean(parkReverseSearch, ParkReverseSearchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得反向寻车记录分页")
    @PreAuthorize("@ss.hasPermission('industry:park-reverse-search:query')")
    public CommonResult<PageResult<ParkReverseSearchRespVO>> getParkReverseSearchPage(@Valid ParkReverseSearchPageReqVO pageReqVO) {
        PageResult<ParkReverseSearchDO> pageResult = parkReverseSearchService.getParkReverseSearchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkReverseSearchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出反向寻车记录 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-reverse-search:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkReverseSearchExcel(@Valid ParkReverseSearchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkReverseSearchDO> list = parkReverseSearchService.getParkReverseSearchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "反向寻车记录.xls", "数据", ParkReverseSearchRespVO.class,
                        BeanUtils.toBean(list, ParkReverseSearchRespVO.class));
    }

}
