package cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal.vo.BasicIllegalPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal.vo.BasicIllegalRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.basicillegal.vo.BasicIllegalSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.basicillegal.BasicIllegalDO;
import cn.iocoder.yudao.module.smartcity.service.basicillegal.BasicIllegalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 违建基本信息")
@RestController
@RequestMapping("/smartcity/basic-illegal")
@Validated
public class BasicIllegalController {

    @Resource
    private BasicIllegalService basicIllegalService;

    @PostMapping("/create")
    @Operation(summary = "创建违建基本信息")
    @PreAuthorize("@ss.hasPermission('smartcity:basic-illegal:create')")
    public CommonResult<Long> createBasicIllegal(@Valid @RequestBody BasicIllegalSaveReqVO createReqVO) {
        return success(basicIllegalService.createBasicIllegal(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违建基本信息")
    @PreAuthorize("@ss.hasPermission('smartcity:basic-illegal:update')")
    public CommonResult<Boolean> updateBasicIllegal(@Valid @RequestBody BasicIllegalSaveReqVO updateReqVO) {
        basicIllegalService.updateBasicIllegal(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违建基本信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:basic-illegal:delete')")
    public CommonResult<Boolean> deleteBasicIllegal(@RequestParam("id") Long id) {
        basicIllegalService.deleteBasicIllegal(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得违建基本信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:basic-illegal:query')")
    public CommonResult<BasicIllegalRespVO> getBasicIllegal(@RequestParam("id") Long id) {
        BasicIllegalDO basicIllegal = basicIllegalService.getBasicIllegal(id);
        return success(BeanUtils.toBean(basicIllegal, BasicIllegalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违建基本信息分页")
    @PreAuthorize("@ss.hasPermission('smartcity:basic-illegal:query')")
    public CommonResult<PageResult<BasicIllegalRespVO>> getBasicIllegalPage(@Valid BasicIllegalPageReqVO pageReqVO) {
        PageResult<BasicIllegalDO> pageResult = basicIllegalService.getBasicIllegalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BasicIllegalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出违建基本信息 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:basic-illegal:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBasicIllegalExcel(@Valid BasicIllegalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BasicIllegalDO> list = basicIllegalService.getBasicIllegalPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "违建基本信息.xls", "数据", BasicIllegalRespVO.class,
                        BeanUtils.toBean(list, BasicIllegalRespVO.class));
    }

}