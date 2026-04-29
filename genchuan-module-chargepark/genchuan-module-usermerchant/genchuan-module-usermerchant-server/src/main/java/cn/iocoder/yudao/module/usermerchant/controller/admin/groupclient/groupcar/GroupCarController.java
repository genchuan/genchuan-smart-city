package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar;

import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo.GroupInfoImportExcelVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarApproveReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarRebindReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarRejectReqVO;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.UserCarUnbindReqVO;
import io.swagger.v3.oas.annotations.Parameters;
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

import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupcar.GroupCarDO;
import cn.iocoder.yudao.module.usermerchant.service.groupclient.groupcar.GroupCarService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 集团车辆")
@RestController
@RequestMapping("/usermerchant/group-car")
@Validated
public class GroupCarController {

    @Resource
    private GroupCarService groupCarService;

    @GetMapping("/page")
    @Operation(summary = "获得集团车辆分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:query')")
    public CommonResult<PageResult<GroupCarPageRespVO>> getGroupCarPage(@Valid GroupCarPageReqVO pageReqVO) {
        PageResult<GroupCarDO> pageResult = groupCarService.getGroupCarPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GroupCarPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建集团车辆")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:create')")
    public CommonResult<Boolean> createGroupCar(@Valid @RequestBody GroupCarSaveReqVO createReqVO) {
        return success(groupCarService.createGroupCar(createReqVO));
    }

    @PostMapping("/import")
    @Operation(summary = "导入集团车辆")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<Boolean> importExcel(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<GroupCarImportExcelVO> list = ExcelUtils.read(file, GroupCarImportExcelVO.class);
        return success(groupCarService.importGroups(list, updateSupport));
    }

    @GetMapping("/export")
    @Operation(summary = "导出集团车辆")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGroupCarExcel(@Valid GroupCarPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GroupCarDO> list = groupCarService.getGroupCarPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "集团车辆.xls", "数据", GroupCarPageRespVO.class,
                BeanUtils.toBean(list, GroupCarPageRespVO.class));
    }

    @PutMapping("/approve")
    @Operation(summary = "审核通过")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:approve')")
    public CommonResult<Boolean> approveGroupCar(@Valid @RequestBody GroupCarAuditReqVO reqVO) {
        reqVO.setStatus("已绑定");
        groupCarService.auditGroupCar(reqVO);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "审核驳回")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:reject')")
    public CommonResult<Boolean> rejectGroupCar(@Valid @RequestBody GroupCarAuditReqVO reqVO) {
        reqVO.setStatus("已驳回");
        groupCarService.auditGroupCar(reqVO);
        return success(true);
    }

    @PutMapping("/unbind")
    @Operation(summary = "车辆解绑")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:unbind')")
    public CommonResult<Boolean> unbindGroupCar(@Valid @RequestBody GroupCarAuditReqVO reqVO) {
        reqVO.setStatus("已解绑");
        groupCarService.auditGroupCar(reqVO);
        return success(true);
    }

    @PutMapping("/rebind")
    @Operation(summary = "车辆重绑")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:rebind')")
    public CommonResult<Boolean> rebindGroupCar(@Valid @RequestBody GroupCarAuditReqVO reqVO) {
        reqVO.setStatus("待审核");
        groupCarService.auditGroupCar(reqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得集团车辆")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:query')")
    public CommonResult<GroupCarPageRespVO> getGroupCar(@RequestParam("id") Long id) {
        GroupCarDO groupCar = groupCarService.getGroupCar(id);
        return success(BeanUtils.toBean(groupCar, GroupCarPageRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新集团车辆")
    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:update')")
    public CommonResult<Boolean> updateGroupCar(@Valid @RequestBody GroupCarUpdateReqVO updateReqVO) {
        groupCarService.updateGroupCar(updateReqVO);
        return success(true);
    }
//————————————————————

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除集团车辆")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:group-car:delete')")
//    public CommonResult<Boolean> deleteGroupCar(@RequestParam("id") Long id) {
//        groupCarService.deleteGroupCar(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除集团车辆")
//                @PreAuthorize("@ss.hasPermission('usermerchant:group-car:delete')")
//    public CommonResult<Boolean> deleteGroupCarList(@RequestParam("ids") List<Long> ids) {
//        groupCarService.deleteGroupCarListByIds(ids);
//        return success(true);
//    }

}