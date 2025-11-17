package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo.PublicSnapShotPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo.PublicSnapShotRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo.PublicSnapShotSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publicsnapshot.PublicSnapShotDO;
import cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publicsnapshot.PublicSnapShotService;

@Tag(name = "管理后台 - 随手拍数据")
@RestController
@RequestMapping("/datacenter/public-snap-shot")
@Validated
public class PublicSnapShotController {

    @Resource
    private PublicSnapShotService publicSnapShotService;

    @PostMapping("/create")
    @Operation(summary = "创建随手拍数据")
    @PreAuthorize("@ss.hasPermission('datacenter:public-snap-shot:create')")
    public CommonResult<Long> createPublicSnapShot(@Valid @RequestBody PublicSnapShotSaveReqVO createReqVO) {
        return success(publicSnapShotService.createPublicSnapShot(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新随手拍数据")
    @PreAuthorize("@ss.hasPermission('datacenter:public-snap-shot:update')")
    public CommonResult<Boolean> updatePublicSnapShot(@Valid @RequestBody PublicSnapShotSaveReqVO updateReqVO) {
        publicSnapShotService.updatePublicSnapShot(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除随手拍数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-snap-shot:delete')")
    public CommonResult<Boolean> deletePublicSnapShot(@RequestParam("id") Long id) {
        publicSnapShotService.deletePublicSnapShot(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得随手拍数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-snap-shot:query')")
    public CommonResult<PublicSnapShotRespVO> getPublicSnapShot(@RequestParam("id") Long id) {
        PublicSnapShotDO publicSnapShot = publicSnapShotService.getPublicSnapShot(id);
        return success(BeanUtils.toBean(publicSnapShot, PublicSnapShotRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得随手拍数据分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-snap-shot:query')")
    public CommonResult<PageResult<PublicSnapShotRespVO>> getPublicSnapShotPage(@Valid PublicSnapShotPageReqVO pageReqVO) {
        PageResult<PublicSnapShotDO> pageResult = publicSnapShotService.getPublicSnapShotPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicSnapShotRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出随手拍数据 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-snap-shot:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicSnapShotExcel(@Valid PublicSnapShotPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicSnapShotDO> list = publicSnapShotService.getPublicSnapShotPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "随手拍数据.xls", "数据", PublicSnapShotRespVO.class,
                        BeanUtils.toBean(list, PublicSnapShotRespVO.class));
    }

}