package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcheck.DormCheckDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.dormcheck.DormCheckService;
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

@Tag(name = "学生管理后台 - 宿舍考勤")
@RestController
@RequestMapping("/studentmgmt/dorm-check")
@Validated
public class DormCheckController {

    @Resource
    private DormCheckService dormCheckService;
    @Resource
    private DictDataApi dictDataApi;
    @PostMapping("/create")
    @Operation(summary = "打卡")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:create')")
    public CommonResult<Boolean> createDormCheck(@Valid @RequestBody DormCheckCreateReqVO createReqVO) {
        return success(dormCheckService.createDormCheck(createReqVO));
    }

    @PutMapping("/recheck")
    @Operation(summary = "补卡")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:recheck')")
    public CommonResult<Boolean> recheck(@Valid @RequestBody DormCheckRecheckReqVO reqVO) {
        return success(dormCheckService.recheck(reqVO));
    }

    @PutMapping("/push")
    @Operation(summary = "推送")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:push')")
    public CommonResult<Boolean> push(@Valid @RequestBody DormCheckPushReqVO reqVO) {
        return success(dormCheckService.push(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新宿舍考勤")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:update')")
    public CommonResult<Boolean> updateDormCheck(@Valid @RequestBody DormCheckSaveReqVO updateReqVO) {
        dormCheckService.updateDormCheck(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除宿舍考勤")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:delete')")
    public CommonResult<Boolean> deleteDormCheck(@RequestParam("id") Long id) {
        dormCheckService.deleteDormCheck(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除宿舍考勤")
                @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:delete')")
    public CommonResult<Boolean> deleteDormCheckList(@RequestParam("ids") List<Long> ids) {
        dormCheckService.deleteDormCheckListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得宿舍考勤")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:query')")
    public CommonResult<DormCheckRespVO> getDormCheck(@RequestParam("id") Long id) {
        DormCheckDO dormCheck = dormCheckService.getDormCheck(id);
        return success(BeanUtils.toBean(dormCheck, DormCheckRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得宿舍考勤分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:query')")
    public CommonResult<PageResult<DormCheckRespVO>> getDormCheckPage(@Valid DormCheckPageReqVO pageReqVO) {
//        PageResult<DormCheckDO> pageResult = dormCheckService.getDormCheckPage(pageReqVO);
        return success(dormCheckService.getDormCheckJoinPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出宿舍考勤 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDormCheckExcel(@Valid DormCheckPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DormCheckDO> list = dormCheckService.getDormCheckPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> typeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.DORM_CHECK_ABNORMAL_TYPE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.DORM_CHECK_STATUS.getType());
        CommonResult<List<DictDataRespDTO>> checkStatusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.DORM_CHECK_CHECK_STATUS.getType());
        list = list.stream().map(item -> {
            String abnormalType = item.getAbnormalType();
            if (typeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : typeDictDataList.getData()) {
                    if (dictData.getValue().equals(abnormalType)) {
                        abnormalType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setAbnormalType(abnormalType);
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
            String checkStatus = item.getCheckStatus();
            if (checkStatusDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : checkStatusDictDataList.getData()) {
                    if (dictData.getValue().equals(checkStatus)) {
                        checkStatus = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setCheckStatus(checkStatus);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "宿舍考勤.xls", "数据", DormCheckRespVO.class,
                        BeanUtils.toBean(list, DormCheckRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "宿舍考勤预警看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:query')")
    public CommonResult<DormCheckChartRespVO> chart(@Valid DormCheckChartReqVO reqVO) {
        DormCheckChartRespVO vo = dormCheckService.chart(reqVO);
        return success(vo);
    }
    @GetMapping("/checkCount")
    @Operation(summary = "各班级考勤异常人数 / 在寝率统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-check:query')")
    public CommonResult<DormCheckChartCountRespVO> checkCount(@Valid DormCheckChartCountReqVO reqVO) {
        DormCheckChartCountRespVO vo = dormCheckService.checkCount(reqVO);
        return success(vo);
    }
}