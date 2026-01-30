package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo.monitorPartTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo.monitorPartTypeRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo.monitorPartTypeSaveReqVO;
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

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.monitorparttype.monitorPartTypeDO;
import cn.iocoder.yudao.module.park.service.park.basicAssociation.monitorparttype.monitorPartTypeService;

@Tag(name = "管理后台 - 监测部件类别")
@RestController
@RequestMapping("/park/monitor-part-type")
@Validated
public class monitorPartTypeController {

    @Resource
    private monitorPartTypeService monitorPartTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件类别")
    @PreAuthorize("@ss.hasPermission('park:monitor-part-type:create')")
    public CommonResult<Long> createmonitorPartType(@Valid @RequestBody monitorPartTypeSaveReqVO createReqVO) {
        return success(monitorPartTypeService.createmonitorPartType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件类别")
    @PreAuthorize("@ss.hasPermission('park:monitor-part-type:update')")
    public CommonResult<Boolean> updatemonitorPartType(@Valid @RequestBody monitorPartTypeSaveReqVO updateReqVO) {
        monitorPartTypeService.updatemonitorPartType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件类别")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:monitor-part-type:delete')")
    public CommonResult<Boolean> deletemonitorPartType(@RequestParam("id") Long id) {
        monitorPartTypeService.deletemonitorPartType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件类别")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:monitor-part-type:query')")
    public CommonResult<monitorPartTypeRespVO> getmonitorPartType(@RequestParam("id") Long id) {
        monitorPartTypeDO monitorPartType = monitorPartTypeService.getmonitorPartType(id);
        return success(BeanUtils.toBean(monitorPartType, monitorPartTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件类别分页")
    @PreAuthorize("@ss.hasPermission('park:monitor-part-type:query')")
    public CommonResult<PageResult<monitorPartTypeRespVO>> getmonitorPartTypePage(@Valid monitorPartTypePageReqVO pageReqVO) {
        PageResult<monitorPartTypeDO> pageResult = monitorPartTypeService.getmonitorPartTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, monitorPartTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件类别 Excel")
    @PreAuthorize("@ss.hasPermission('park:monitor-part-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportmonitorPartTypeExcel(@Valid monitorPartTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<monitorPartTypeDO> list = monitorPartTypeService.getmonitorPartTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件类别.xls", "数据", monitorPartTypeRespVO.class,
                        BeanUtils.toBean(list, monitorPartTypeRespVO.class));
    }

}