package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo.TrainMatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo.TrainMatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo.TrainMatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.trainmat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.trainmat.TrainMatDO;
import cn.iocoder.yudao.module.datacenter.service.operationguide.trainmat.TrainMatService;

@Tag(name = "管理后台 - 培训资料")
@RestController
@RequestMapping("/datacenter/train-mat")
@Validated
public class TrainMatController {

    @Resource
    private TrainMatService trainMatService;

    @PostMapping("/create")
    @Operation(summary = "创建培训资料")
    @PreAuthorize("@ss.hasPermission('datacenter:train-mat:create')")
    public CommonResult<Long> createTrainMat(@Valid @RequestBody TrainMatSaveReqVO createReqVO) {
        return success(trainMatService.createTrainMat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新培训资料")
    @PreAuthorize("@ss.hasPermission('datacenter:train-mat:update')")
    public CommonResult<Boolean> updateTrainMat(@Valid @RequestBody TrainMatSaveReqVO updateReqVO) {
        trainMatService.updateTrainMat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除培训资料")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:train-mat:delete')")
    public CommonResult<Boolean> deleteTrainMat(@RequestParam("id") Long id) {
        trainMatService.deleteTrainMat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得培训资料")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:train-mat:query')")
    public CommonResult<TrainMatRespVO> getTrainMat(@RequestParam("id") Long id) {
        TrainMatDO trainMat = trainMatService.getTrainMat(id);
        return success(BeanUtils.toBean(trainMat, TrainMatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得培训资料分页")
    @PreAuthorize("@ss.hasPermission('datacenter:train-mat:query')")
    public CommonResult<PageResult<TrainMatRespVO>> getTrainMatPage(@Valid TrainMatPageReqVO pageReqVO) {
        PageResult<TrainMatDO> pageResult = trainMatService.getTrainMatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TrainMatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出培训资料 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:train-mat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTrainMatExcel(@Valid TrainMatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TrainMatDO> list = trainMatService.getTrainMatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "培训资料.xls", "数据", TrainMatRespVO.class,
                        BeanUtils.toBean(list, TrainMatRespVO.class));
    }

}