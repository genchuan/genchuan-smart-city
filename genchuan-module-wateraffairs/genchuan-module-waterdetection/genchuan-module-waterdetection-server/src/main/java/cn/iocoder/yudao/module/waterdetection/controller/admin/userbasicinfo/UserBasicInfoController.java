package cn.iocoder.yudao.module.waterdetection.controller.admin.userbasicinfo;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.userbasicinfo.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.userbasicinfo.UserBasicInfoDO;
import cn.iocoder.yudao.module.waterdetection.service.userbasicinfo.UserBasicInfoService;

@Tag(name = "管理后台 - 用户基础信息登记")
@RestController
@RequestMapping("/waterdetection/user-basic-info")
@Validated
public class UserBasicInfoController {

    @Resource
    private UserBasicInfoService userBasicInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建用户基础信息登记")
    @PreAuthorize("@ss.hasPermission('waterdetection:user-basic-info:create')")
    public CommonResult<Long> createUserBasicInfo(@Valid @RequestBody UserBasicInfoSaveReqVO createReqVO) {
        return success(userBasicInfoService.createUserBasicInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户基础信息登记")
    @PreAuthorize("@ss.hasPermission('waterdetection:user-basic-info:update')")
    public CommonResult<Boolean> updateUserBasicInfo(@Valid @RequestBody UserBasicInfoSaveReqVO updateReqVO) {
        userBasicInfoService.updateUserBasicInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户基础信息登记")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:user-basic-info:delete')")
    public CommonResult<Boolean> deleteUserBasicInfo(@RequestParam("id") Long id) {
        userBasicInfoService.deleteUserBasicInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户基础信息登记")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:user-basic-info:query')")
    public CommonResult<UserBasicInfoRespVO> getUserBasicInfo(@RequestParam("id") Long id) {
        UserBasicInfoDO userBasicInfo = userBasicInfoService.getUserBasicInfo(id);
        return success(BeanUtils.toBean(userBasicInfo, UserBasicInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户基础信息登记分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:user-basic-info:query')")
    public CommonResult<PageResult<UserBasicInfoRespVO>> getUserBasicInfoPage(@Valid UserBasicInfoPageReqVO pageReqVO) {
        PageResult<UserBasicInfoDO> pageResult = userBasicInfoService.getUserBasicInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UserBasicInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户基础信息登记 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:user-basic-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserBasicInfoExcel(@Valid UserBasicInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserBasicInfoDO> list = userBasicInfoService.getUserBasicInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用户基础信息登记.xls", "数据", UserBasicInfoRespVO.class,
                        BeanUtils.toBean(list, UserBasicInfoRespVO.class));
    }

}