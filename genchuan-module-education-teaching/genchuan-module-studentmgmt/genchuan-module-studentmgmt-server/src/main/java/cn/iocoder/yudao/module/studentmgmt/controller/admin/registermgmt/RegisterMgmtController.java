package cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.registermgmt.RegisterMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.registermgmt.RegisterMgmtService;
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

@Tag(name = "学生管理后台 - 报名管理")
@RestController
@RequestMapping("/studentmgmt/register-mgmt")
@Validated
public class RegisterMgmtController {

    @Resource
    private RegisterMgmtService registerMgmtService;
    @Resource
    private DictDataApi dictDataApi;

    @PostMapping("/create")
    @Operation(summary = "创建报名管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:create')")
    public CommonResult<Long> createRegisterMgmt(@Valid @RequestBody RegisterMgmtSaveReqVO createReqVO) {
        return success(registerMgmtService.createRegisterMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报名管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:update')")
    public CommonResult<Boolean> updateRegisterMgmt(@Valid @RequestBody RegisterMgmtSaveReqVO updateReqVO) {
        registerMgmtService.updateRegisterMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报名管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:delete')")
    public CommonResult<Boolean> deleteRegisterMgmt(@RequestParam("id") Long id) {
        registerMgmtService.deleteRegisterMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除报名管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:delete')")
    public CommonResult<Boolean> deleteRegisterMgmtList(@RequestParam("ids") List<Long> ids) {
        registerMgmtService.deleteRegisterMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报名管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:query')")
    public CommonResult<RegisterMgmtRespVO> getRegisterMgmt(@RequestParam("id") Long id) {
        RegisterMgmtDO registerMgmt = registerMgmtService.getRegisterMgmt(id);
        return success(BeanUtils.toBean(registerMgmt, RegisterMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报名管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:query')")
    public CommonResult<PageResult<RegisterMgmtRespVO>> getRegisterMgmtPage(@Valid RegisterMgmtPageReqVO pageReqVO) {
        PageResult<RegisterMgmtDO> pageResult = registerMgmtService.getRegisterMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RegisterMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报名管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRegisterMgmtExcel(@Valid RegisterMgmtPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RegisterMgmtDO> list = registerMgmtService.getRegisterMgmtPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.REGISTER_MGMT_STATUS.getType());
        list = list.stream().map(item -> {

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
        ExcelUtils.write(response, "报名管理.xls", "数据", RegisterMgmtRespVO.class,
                BeanUtils.toBean(list, RegisterMgmtRespVO.class));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody RegisterMgmtAuditReqVO reqVO) {
        return success(registerMgmtService.audit(reqVO));
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:confirm')")
    public CommonResult<Boolean> confirm(@Valid @RequestBody RegisterMgmtConfirmReqVO reqVO) {
        return success(registerMgmtService.confirm(reqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "招生报名统计看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:chart')")
    public CommonResult<RegisterMgmtChartRespVO> chart(@Valid BaseChartReqVO reqVO) {
        return success(registerMgmtService.chart(reqVO));
    }

    @GetMapping("/enrollCount")
    @Operation(summary = "各专业报名 / 录取人数统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:register-mgmt:chart')")
    public CommonResult<RegisterMgmtEnrollCountRespVO> enrollCount(@Valid BaseChartReqVO reqVO) {
        return success(registerMgmtService.enrollCount(reqVO));
    }


}