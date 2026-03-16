package cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data.vo.DataPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data.vo.DataRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data.vo.DataSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.data.DataDO;
import cn.iocoder.yudao.module.evaluate.service.data.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 上报数据")
@RestController
@RequestMapping("/evaluate/data")
@Validated
public class DataController {

    @Resource
    private DataService dataService;

    @PostMapping("/create")
    @Operation(summary = "创建上报数据")
    @PreAuthorize("@ss.hasPermission('evaluate:data:create')")
    public CommonResult<Long> createData(@Valid @RequestBody DataSaveReqVO createReqVO) {
        return success(dataService.createData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新上报数据")
    @PreAuthorize("@ss.hasPermission('evaluate:data:update')")
    public CommonResult<Boolean> updateData(@Valid @RequestBody DataSaveReqVO updateReqVO) {
        dataService.updateData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除上报数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:data:delete')")
    public CommonResult<Boolean> deleteData(@RequestParam("id") Long id) {
        dataService.deleteData(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除上报数据")
                @PreAuthorize("@ss.hasPermission('evaluate:data:delete')")
    public CommonResult<Boolean> deleteDataList(@RequestParam("ids") List<Long> ids) {
        dataService.deleteDataListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得上报数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:data:query')")
    public CommonResult<DataRespVO> getData(@RequestParam("id") Long id) {
        DataDO data = dataService.getData(id);
        return success(BeanUtils.toBean(data, DataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得上报数据分页")
    @PreAuthorize("@ss.hasPermission('evaluate:data:query')")
    public CommonResult<PageResult<DataRespVO>> getDataPage(@Valid DataPageReqVO pageReqVO) {
        PageResult<DataDO> pageResult = dataService.getDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出上报数据 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDataExcel(@Valid DataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DataDO> list = dataService.getDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "上报数据.xls", "数据", DataRespVO.class,
                        BeanUtils.toBean(list, DataRespVO.class));
    }

}