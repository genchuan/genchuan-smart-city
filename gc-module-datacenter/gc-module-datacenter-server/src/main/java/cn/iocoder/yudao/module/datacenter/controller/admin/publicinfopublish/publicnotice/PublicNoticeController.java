package cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice;

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo.PublicNoticePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo.PublicNoticeRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo.PublicNoticeSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicnotice.PublicNoticeDO;
import cn.iocoder.yudao.module.datacenter.service.publicinfopublish.publicnotice.PublicNoticeService;

@Tag(name = "管理后台 - 通知公告发布")
@RestController
@RequestMapping("/datacenter/public-notice")
@Validated
public class PublicNoticeController {

    @Resource
    private PublicNoticeService publicNoticeService;

    @PostMapping("/create")
    @Operation(summary = "创建通知公告发布")
    @PreAuthorize("@ss.hasPermission('datacenter:public-notice:create')")
    public CommonResult<Long> createPublicNotice(@Valid @RequestBody PublicNoticeSaveReqVO createReqVO) {
        return success(publicNoticeService.createPublicNotice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通知公告发布")
    @PreAuthorize("@ss.hasPermission('datacenter:public-notice:update')")
    public CommonResult<Boolean> updatePublicNotice(@Valid @RequestBody PublicNoticeSaveReqVO updateReqVO) {
        publicNoticeService.updatePublicNotice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通知公告发布")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-notice:delete')")
    public CommonResult<Boolean> deletePublicNotice(@RequestParam("id") Long id) {
        publicNoticeService.deletePublicNotice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通知公告发布")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-notice:query')")
    public CommonResult<PublicNoticeRespVO> getPublicNotice(@RequestParam("id") Long id) {
        PublicNoticeDO publicNotice = publicNoticeService.getPublicNotice(id);
        return success(BeanUtils.toBean(publicNotice, PublicNoticeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得通知公告发布分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-notice:query')")
    public CommonResult<PageResult<PublicNoticeRespVO>> getPublicNoticePage(@Valid PublicNoticePageReqVO pageReqVO) {
        PageResult<PublicNoticeDO> pageResult = publicNoticeService.getPublicNoticePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicNoticeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通知公告发布 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-notice:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicNoticeExcel(@Valid PublicNoticePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicNoticeDO> list = publicNoticeService.getPublicNoticePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "通知公告发布.xls", "数据", PublicNoticeRespVO.class,
                        BeanUtils.toBean(list, PublicNoticeRespVO.class));
    }

}