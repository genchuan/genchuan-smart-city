package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo.PublicOnlineInterviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo.PublicOnlineInterviewRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo.PublicOnlineInterviewSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publiconlineinterview.PublicOnlineInterviewDO;
import cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publiconlineinterview.PublicOnlineInterviewService;

@Tag(name = "管理后台 - 在线访谈")
@RestController
@RequestMapping("/datacenter/public-online-interview")
@Validated
public class PublicOnlineInterviewController {

    @Resource
    private PublicOnlineInterviewService publicOnlineInterviewService;

    @PostMapping("/create")
    @Operation(summary = "创建在线访谈")
    @PreAuthorize("@ss.hasPermission('datacenter:public-online-interview:create')")
    public CommonResult<Long> createPublicOnlineInterview(@Valid @RequestBody PublicOnlineInterviewSaveReqVO createReqVO) {
        return success(publicOnlineInterviewService.createPublicOnlineInterview(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新在线访谈")
    @PreAuthorize("@ss.hasPermission('datacenter:public-online-interview:update')")
    public CommonResult<Boolean> updatePublicOnlineInterview(@Valid @RequestBody PublicOnlineInterviewSaveReqVO updateReqVO) {
        publicOnlineInterviewService.updatePublicOnlineInterview(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除在线访谈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-online-interview:delete')")
    public CommonResult<Boolean> deletePublicOnlineInterview(@RequestParam("id") Long id) {
        publicOnlineInterviewService.deletePublicOnlineInterview(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得在线访谈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-online-interview:query')")
    public CommonResult<PublicOnlineInterviewRespVO> getPublicOnlineInterview(@RequestParam("id") Long id) {
        PublicOnlineInterviewDO publicOnlineInterview = publicOnlineInterviewService.getPublicOnlineInterview(id);
        return success(BeanUtils.toBean(publicOnlineInterview, PublicOnlineInterviewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得在线访谈分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-online-interview:query')")
    public CommonResult<PageResult<PublicOnlineInterviewRespVO>> getPublicOnlineInterviewPage(@Valid PublicOnlineInterviewPageReqVO pageReqVO) {
        PageResult<PublicOnlineInterviewDO> pageResult = publicOnlineInterviewService.getPublicOnlineInterviewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicOnlineInterviewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出在线访谈 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-online-interview:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicOnlineInterviewExcel(@Valid PublicOnlineInterviewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicOnlineInterviewDO> list = publicOnlineInterviewService.getPublicOnlineInterviewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "在线访谈.xls", "数据", PublicOnlineInterviewRespVO.class,
                        BeanUtils.toBean(list, PublicOnlineInterviewRespVO.class));
    }

}