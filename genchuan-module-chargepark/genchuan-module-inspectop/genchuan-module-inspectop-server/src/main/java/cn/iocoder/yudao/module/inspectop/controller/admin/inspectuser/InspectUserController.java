package cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.inspectop.dal.mysql.inspectuser.InspectUserMapper;
import cn.iocoder.yudao.module.inspectop.framework.ImportRespVO;
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
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;

import cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectuser.InspectUserDO;
import cn.iocoder.yudao.module.inspectop.service.inspectuser.InspectUserService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "巡查巡检 - 巡检人员")
@RestController
@RequestMapping("/inspectop/inspect-user")
@Validated
public class InspectUserController {

    @Resource
    private InspectUserService inspectUserService;

    @PostMapping("/create")
    @Operation(summary = "创建巡检人员")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:create')")
    public CommonResult<Long> createInspectUser(@Valid @RequestBody InspectUserSaveReqVO createReqVO) {
        return success(inspectUserService.createInspectUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡检人员")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:update')")
    public CommonResult<Boolean> updateInspectUser(@Valid @RequestBody InspectUserSaveReqVO updateReqVO) {
        inspectUserService.updateInspectUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡检人员")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:delete')")
    public CommonResult<Boolean> deleteInspectUser(@RequestParam("id") Long id) {
        inspectUserService.deleteInspectUser(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除巡检人员")
                @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:delete')")
    public CommonResult<Boolean> deleteInspectUserList(@RequestParam("ids") List<Long> ids) {
        inspectUserService.deleteInspectUserListByIds(ids);
        return success(true);
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检人员分页")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:query')")
    public CommonResult<PageResult<InspectUserRespVO>> getInspectUserPage(@Valid InspectUserPageReqVO pageReqVO) {
        // 使用新的Service方法
        PageResult<InspectUserRespVO> pageResult = inspectUserService.getInspectUserPageWithTaskCount(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡检人员")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:query')")
    public CommonResult<InspectUserRespVO> getInspectUser(@RequestParam("id") Long id) {
        // 调用新的Service方法
        InspectUserRespVO inspectUserRespVO = inspectUserService.getInspectUserWithTaskCount(id);
        return success(inspectUserRespVO);
    }

    @PostMapping("/import")
    @Operation(summary = "导入巡检人员")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:import')")
    public CommonResult<ImportRespVO> importInspectUser(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") Boolean updateSupport) throws IOException {

        // 检查文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException("请选择要导入的文件");
        }

        // 检查文件格式
        String filename = file.getOriginalFilename();
        if (filename != null && !(filename.endsWith(".xls") || filename.endsWith(".xlsx"))) {
            throw new RuntimeException("请上传Excel文件（.xls 或 .xlsx格式）");
        }

        ImportRespVO respVO = inspectUserService.importInspectUser(file, updateSupport);
        return success(respVO);
    }

    @PutMapping("/enable")
    @Operation(summary = "启用巡检人员")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:enable')")
    public CommonResult<Boolean> enableInspectUser(@Valid @RequestBody InspectUserStatusReqVO reqVO) {
        // 状态值 "1" 对应 "启用"
        inspectUserService.enableInspectUser(reqVO.getId());
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用巡检人员")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:disable')")
    public CommonResult<Boolean> disableInspectUser(@Valid @RequestBody InspectUserStatusReqVO reqVO) {
        // 状态值 "2" 对应 "禁用"
        inspectUserService.disableInspectUser(reqVO.getId());
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取巡检人员统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:chart')")
    public CommonResult<InspectUserChartRespVO> getInspectUserChart() {
        InspectUserChartRespVO chartData = inspectUserService.getInspectUserChart();
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检人员 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectUserExcel(@Valid InspectUserPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        // 1. 获取数据列表
        List<InspectUserDO> list = inspectUserService.getInspectUserPage(pageReqVO).getList();
        // 2. 将DO列表转换为RespVO列表（这是原有逻辑）
        List<InspectUserRespVO> voList = BeanUtils.toBean(list, InspectUserRespVO.class);

        // 【新增】3. 对VO列表中的字典值进行转换（数字 -> 中文）
        convertDictValues(voList);

        // 4. 导出 Excel
        ExcelUtils.write(response, "巡检人员.xls", "数据", InspectUserRespVO.class, voList);
    }

    /**
     * 【新增】转换字典值为中文显示
     * 此方法会修改传入的 voList 中每个对象的 status 和 onlineStatus 字段。
     * @param voList 巡检人员响应VO列表
     */
    private void convertDictValues(List<InspectUserRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }
        for (InspectUserRespVO vo : voList) {
            // 转换人员状态
            vo.setStatus(convertUserStatus(vo.getStatus()));
            // 转换在线状态
            vo.setOnlineStatus(convertUserOnlineStatus(vo.getOnlineStatus()));
        }
    }

    /**
     * 【新增】转换巡检人员状态字典值
     * 根据您提供的映射：1-正常、2-禁用
     * @param statusCode 状态编码（例如 "1", "2"）
     * @return 对应的中文状态描述
     */
    private String convertUserStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "1":
                return "正常";
            case "2":
                return "禁用";
            default:
                // 如果遇到未知编码，可以选择返回原编码或空字符串，这里返回原编码以便排查。
                return statusCode;
        }
    }

    /**
     * 【新增】转换巡检人员在线状态字典值
     * 根据您提供的映射：1-在线、2-离线
     * @param onlineStatusCode 在线状态编码（例如 "1", "2"）
     * @return 对应的中文状态描述
     */
    private String convertUserOnlineStatus(String onlineStatusCode) {
        if (onlineStatusCode == null) {
            return "";
        }
        switch (onlineStatusCode.trim()) {
            case "1":
                return "在线";
            case "2":
                return "离线";
            default:
                // 如果遇到未知编码，可以选择返回原编码或空字符串，这里返回原编码以便排查。
                return onlineStatusCode;
        }
    }

}