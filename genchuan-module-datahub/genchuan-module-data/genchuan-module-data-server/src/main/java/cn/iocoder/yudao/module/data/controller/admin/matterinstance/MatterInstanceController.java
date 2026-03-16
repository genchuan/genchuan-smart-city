package cn.iocoder.yudao.module.data.controller.admin.matterinstance;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstancePageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstanceRespVO;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstanceSaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.matterinstance.vo.MatterInstanceUpdateStatusNameReqVO;
import cn.iocoder.yudao.module.data.dal.dataobject.matterinstance.MatterInstanceDO;
import cn.iocoder.yudao.module.data.service.matterinstance.MatterInstanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 管理事项实例")
@RestController
@RequestMapping("/data/matter-instance")
@Validated
public class MatterInstanceController {

    @Resource
    private MatterInstanceService matterInstanceService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项实例")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:create')")
    public CommonResult<Long> creatematterInstance(@Valid @RequestBody MatterInstanceSaveReqVO createReqVO) {
        return success(matterInstanceService.creatematterInstance(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项实例")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:update')")
    public CommonResult<Boolean> updatematterInstance(@Valid @RequestBody MatterInstanceSaveReqVO updateReqVO) {
        matterInstanceService.updatematterInstance(updateReqVO);
        return success(true);
    }

    @PostMapping("/update-status-name-batch")
    @Operation(summary = "批量更新事项实例的状态名称")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:update')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Integer> updateInstanceStatusNameBatch(@Valid @RequestBody MatterInstanceUpdateStatusNameReqVO updateReqVO) {
        Integer count = matterInstanceService.updateInstanceStatusNameBatch(updateReqVO);
        return success(count);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项实例")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:matter-instance:delete')")
    public CommonResult<Boolean> deletematterInstance(@RequestParam("id") Long id) {
        matterInstanceService.deletematterInstance(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项实例")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:query')")
    public CommonResult<MatterInstanceRespVO> getmatterInstance(@RequestParam("id") Long id) {
        MatterInstanceDO matterInstance = matterInstanceService.getmatterInstance(id);
        return success(BeanUtils.toBean(matterInstance, MatterInstanceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项实例分页")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:query')")
    public CommonResult<PageResult<MatterInstanceRespVO>> getmatterInstancePage(@Valid MatterInstancePageReqVO pageReqVO) {
        PageResult<MatterInstanceDO> pageResult = matterInstanceService.getmatterInstancePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MatterInstanceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportmatterInstanceExcel(@Valid MatterInstancePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MatterInstanceDO> list = matterInstanceService.getmatterInstancePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项实例.xls", "数据", MatterInstanceRespVO.class,
                        BeanUtils.toBean(list, MatterInstanceRespVO.class));
    }

    @PostMapping("/import-excel")
    @Operation(summary = "导入管理事项实例 Excel")
    @PreAuthorize("@ss.hasPermission('data:matter-instance:import')")
    @ApiAccessLog(operateType = IMPORT)
    public CommonResult<String> importMatterInstanceExcel(@RequestParam("file") MultipartFile file) throws IOException {
        // 调用服务层进行导入
        String importResult = matterInstanceService.importMatterInstanceExcel(file);
        return success(importResult);
    }

}