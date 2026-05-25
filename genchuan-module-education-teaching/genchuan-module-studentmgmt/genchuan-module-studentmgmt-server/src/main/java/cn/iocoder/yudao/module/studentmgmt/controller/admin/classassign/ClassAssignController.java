package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.classassign.ClassAssignDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.classassign.ClassAssignService;
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

@Tag(name = "学生管理后台 - 分班管理")
@RestController
@RequestMapping("/studentmgmt/class-assign")
@Validated
public class ClassAssignController {

    @Resource
    private ClassAssignService classAssignService;
    @Resource
    private DictDataApi dictDataApi;

    @PostMapping("/create")
    @Operation(summary = "创建分班管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:create')")
    public CommonResult<Long> createClassAssign(@Valid @RequestBody ClassAssignSaveReqVO createReqVO) {
        return success(classAssignService.createClassAssign(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分班管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:update')")
    public CommonResult<Boolean> updateClassAssign(@Valid @RequestBody ClassAssignSaveReqVO updateReqVO) {
        classAssignService.updateClassAssign(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分班管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:delete')")
    public CommonResult<Boolean> deleteClassAssign(@RequestParam("id") Long id) {
        classAssignService.deleteClassAssign(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除分班管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:delete')")
    public CommonResult<Boolean> deleteClassAssignList(@RequestParam("ids") List<Long> ids) {
        classAssignService.deleteClassAssignListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分班管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:query')")
    public CommonResult<ClassAssignRespVO> getClassAssign(@RequestParam("id") Long id) {
        ClassAssignDO classAssign = classAssignService.getClassAssign(id);
        return success(BeanUtils.toBean(classAssign, ClassAssignRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得分班管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:query')")
    public CommonResult<PageResult<ClassAssignRespVO>> getClassAssignPage(@Valid ClassAssignPageReqVO pageReqVO) {
        PageResult<ClassAssignDO> pageResult = classAssignService.getClassAssignPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ClassAssignRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出分班管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportClassAssignExcel(@Valid ClassAssignPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ClassAssignDO> list = classAssignService.getClassAssignPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.CLASS_ASSIGN_STATUS.getType());
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
        ExcelUtils.write(response, "分班管理.xls", "数据", ClassAssignRespVO.class,
                        BeanUtils.toBean(list, ClassAssignRespVO.class));
    }

    @PostMapping("/config")
    @Operation(summary = "配置")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:config')")
    public CommonResult<Boolean> config(@Valid @RequestBody ClassAssignConfigReqVO reqVO) {
        return success(classAssignService.config(reqVO));
    }
    @PutMapping("/assign")
    @Operation(summary = "分班")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:assign')")
    public CommonResult<Boolean> assign(@Valid @RequestBody ClassAssignAssignReqVO reqVO) {
        return success(classAssignService.assign(reqVO));
    }
    @PutMapping("/confirm")
    @Operation(summary = "确认")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:confirm')")
    public CommonResult<Boolean> confirm(@Valid @RequestBody ClassAssignConfirmReqVO reqVO) {
        return success(classAssignService.confirm(reqVO));
    }
    @GetMapping("/chart")
    @Operation(summary = "新生分班分布看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:chart')")
    public CommonResult<ClassAssignChartRespVO> chart(@Valid BaseChartReqVO reqVO) {
        return success(classAssignService.chart(reqVO));
    }
    @GetMapping("/classDistribution")
    @Operation(summary = "班级人数 / 专业分班占比统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:class-assign:chart')")
    public CommonResult<ClassAssignDistributionRespVo> classDistribution(@Valid BaseChartReqVO reqVO) {
        return success(classAssignService.classDistribution(reqVO));
    }

}