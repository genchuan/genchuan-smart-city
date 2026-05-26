package cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.comparemgmt.CompareMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.comparemgmt.CompareMgmtService;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
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

@Tag(name = "学生管理后台 - 评比管理")
@RestController
@RequestMapping("/studentmgmt/compare-mgmt")
@Validated
public class CompareMgmtController {

    @Resource
    private CompareMgmtService compareMgmtService;
    @Resource
    private DictDataApi dictDataApi;
    @PostMapping("/create")
    @Operation(summary = "创建评比管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:create')")
    public CommonResult<Long> createCompareMgmt(@Valid @RequestBody CompareMgmtSaveReqVO createReqVO) {
        return success(compareMgmtService.createCompareMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评比管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:update')")
    public CommonResult<Boolean> updateCompareMgmt(@Valid @RequestBody CompareMgmtUpdateReqVO updateReqVO) {
        boolean isSuccess = compareMgmtService.updateCompareMgmt(updateReqVO);
        return success(isSuccess);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评比管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:delete')")
    public CommonResult<Boolean> deleteCompareMgmt(@RequestParam("id") Long id) {
        compareMgmtService.deleteCompareMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除评比管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:delete')")
    public CommonResult<Boolean> deleteCompareMgmtList(@RequestParam("ids") List<Long> ids) {
        compareMgmtService.deleteCompareMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评比管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:query')")
    public CommonResult<CompareMgmtRespVO> getCompareMgmt(@RequestParam("id") Long id) {
        CompareMgmtDO compareMgmt = compareMgmtService.getCompareMgmt(id);
        return success(BeanUtils.toBean(compareMgmt, CompareMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评比管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:query')")
    public CommonResult<PageResult<CompareMgmtRespVO>> getCompareMgmtPage(@Valid CompareMgmtPageReqVO pageReqVO) {
        PageResult<CompareMgmtDO> pageResult = compareMgmtService.getCompareMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompareMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评比管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompareMgmtExcel(@Valid CompareMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompareMgmtDO> list = compareMgmtService.getCompareMgmtPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> cycleDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.COMPARE_MGMT_CYCLE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.COMPARE_MGMT_STATUS.getType());
        list = list.stream().map(item -> {
            String applyType = item.getCycle();
            if (cycleDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : cycleDictDataList.getData()) {
                    if (dictData.getValue().equals(applyType)) {
                        applyType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setCycle(applyType);
            String status = item.getStatus();
            if (statusDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : statusDictDataList.getData()) {
                    if (dictData.getValue().equals(status)) {
                        status = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setStatus(status);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "评比管理.xls", "数据", CompareMgmtRespVO.class,
                        BeanUtils.toBean(list, CompareMgmtRespVO.class));
    }

    @PutMapping("/score")
    @Operation(summary = "打分")
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:score')")
    public CommonResult<Boolean> score(@Valid @RequestBody CompareMgmtScoreReqVO reqVO) {
        boolean isSuccess = compareMgmtService.score(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/award")
    @Operation(summary = "授予")
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:award')")
    public CommonResult<Boolean> award(@Valid @RequestBody CompareMgmtAwardReqVO reqVO) {
        boolean isSuccess = compareMgmtService.award(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "德育评比排名看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:query')")
    public CommonResult<CompareMgmtChartRespVO> chart(@Valid CompareMgmtChartReqVO reqVO) {
        CompareMgmtChartRespVO vo = compareMgmtService.chart(reqVO);
        return success(vo);
    }

    @GetMapping("/chart/scoreRank")
    @Operation(summary = "德育评比排名看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:compare-mgmt:query')")
    public CommonResult<CompareMgmtScoreRankRespVO> scoreRank(@Valid @RequestBody CompareMgmtChartReqVO reqVO) {
        CompareMgmtScoreRankRespVO vo = compareMgmtService.scoreRank(reqVO);
        return success(vo);
    }

}
