package cn.iocoder.yudao.module.smartcity.controller.admin.personnelinformation;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.personnelinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelinformation.PersonnelInformationDO;
import cn.iocoder.yudao.module.smartcity.service.personnelinformation.PersonnelInformationService;

@Tag(name = "管理后台 - 人员信息")
@RestController
@RequestMapping("/smartcity/personnel-information")
@Validated
public class PersonnelInformationController {

    @Resource
    private PersonnelInformationService personnelInformationService;

    @PostMapping("/create")
    @Operation(summary = "创建人员信息")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-information:create')")
    public CommonResult<Long> createPersonnelInformation(@Valid @RequestBody PersonnelInformationSaveReqVO createReqVO) {
        return success(personnelInformationService.createPersonnelInformation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人员信息")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-information:update')")
    public CommonResult<Boolean> updatePersonnelInformation(@Valid @RequestBody PersonnelInformationSaveReqVO updateReqVO) {
        personnelInformationService.updatePersonnelInformation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人员信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-information:delete')")
    public CommonResult<Boolean> deletePersonnelInformation(@RequestParam("id") Long id) {
        personnelInformationService.deletePersonnelInformation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人员信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-information:query')")
    public CommonResult<PersonnelInformationRespVO> getPersonnelInformation(@RequestParam("id") Long id) {
        PersonnelInformationDO personnelInformation = personnelInformationService.getPersonnelInformation(id);
        return success(BeanUtils.toBean(personnelInformation, PersonnelInformationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人员信息分页")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-information:query')")
    public CommonResult<PageResult<PersonnelInformationRespVO>> getPersonnelInformationPage(@Valid PersonnelInformationPageReqVO pageReqVO) {
        PageResult<PersonnelInformationDO> pageResult = personnelInformationService.getPersonnelInformationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PersonnelInformationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人员信息 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:personnel-information:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPersonnelInformationExcel(@Valid PersonnelInformationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PersonnelInformationDO> list = personnelInformationService.getPersonnelInformationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "人员信息.xls", "数据", PersonnelInformationRespVO.class,
                        BeanUtils.toBean(list, PersonnelInformationRespVO.class));
    }

}