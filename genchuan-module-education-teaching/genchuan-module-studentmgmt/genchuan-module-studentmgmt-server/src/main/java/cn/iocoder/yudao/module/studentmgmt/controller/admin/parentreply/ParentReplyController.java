package cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.parentreply.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.parentreply.ParentReplyDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.parentreply.ParentReplyService;
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

@Tag(name = "管理后台 - 家长回复")
@RestController
@RequestMapping("/studentmgmt/parent-reply")
@Validated
public class ParentReplyController {

    @Resource
    private ParentReplyService parentReplyService;
    @Resource
    private DictDataApi dictDataApi;

    @PostMapping("/create")
    @Operation(summary = "创建家长回复")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:create')")
    public CommonResult<Long> createParentReply(@Valid @RequestBody ParentReplySaveReqVO createReqVO) {
        return success(parentReplyService.createParentReply(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新家长回复")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:update')")
    public CommonResult<Boolean> updateParentReply(@Valid @RequestBody ParentReplySaveReqVO updateReqVO) {
        parentReplyService.updateParentReply(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除家长回复")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:delete')")
    public CommonResult<Boolean> deleteParentReply(@RequestParam("id") Long id) {
        parentReplyService.deleteParentReply(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除家长回复")
                @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:delete')")
    public CommonResult<Boolean> deleteParentReplyList(@RequestParam("ids") List<Long> ids) {
        parentReplyService.deleteParentReplyListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得家长回复")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:query')")
    public CommonResult<ParentReplyRespVO> getParentReply(@RequestParam("id") Long id) {
        ParentReplyDO parentReply = parentReplyService.getParentReply(id);
        return success(BeanUtils.toBean(parentReply, ParentReplyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得家长回复分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:query')")
    public CommonResult<PageResult<ParentReplyRespVO>> getParentReplyPage(@Valid ParentReplyPageReqVO pageReqVO) {
        PageResult<ParentReplyDO> pageResult = parentReplyService.getParentReplyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParentReplyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出家长回复 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParentReplyExcel(@Valid ParentReplyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParentReplyDO> list = parentReplyService.getParentReplyPage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> replyReadDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.PARENT_REPLY_READ_STATUS.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.PARENT_REPLY_STATUS.getType());
        list = list.stream().map(item -> {
            String readStatus = item.getReadStatus();
            if (replyReadDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : replyReadDictDataList.getData()) {
                    if (dictData.getValue().equals(readStatus)) {
                        readStatus = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setReadStatus(readStatus);
            String status = item.getReplyStatus();
            if (statusDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : statusDictDataList.getData()) {
                    if (dictData.getValue().equals(status)) {
                        status = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setReplyStatus(status);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "家长回复.xls", "数据", ParentReplyRespVO.class,
                        BeanUtils.toBean(list, ParentReplyRespVO.class));
    }
    @PutMapping("/read")
    @Operation(summary = "标记已读")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:read')")
    public CommonResult<Boolean> read(@Valid @RequestBody ParentReplyReadReqVO reqVO) {
        return success(parentReplyService.read(reqVO));
    }
    @PutMapping("/reply")
    @Operation(summary = "老师回复")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:reply')")
    public CommonResult<Boolean> reply(@Valid @RequestBody ParentReplyReplyReqVO reqVO) {
        return success(parentReplyService.reply(reqVO));
    }
    @PutMapping("/submit")
    @Operation(summary = "家长提交回复")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:submit')")
    public CommonResult<Boolean> submit(@Valid @RequestBody ParentReplySubmitReqVO reqVO) {
        return success(parentReplyService.submit(reqVO));
    }
    @GetMapping("/chart")
    @Operation(summary = "家长回复统计看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:chart')")
    public CommonResult<ParentReplyChartRespVO> chart(@Valid BaseChartReqVO reqVO) {
        return success(parentReplyService.chart(reqVO));
    }
    @GetMapping("/chart/index")
    @Operation(summary = "家长回复核心指标")
    @PreAuthorize("@ss.hasPermission('studentmgmt:parent-reply:chart')")
    public CommonResult<ParentReplyChartIndexRespVO> index(@Valid BaseChartReqVO reqVO) {
        return success(parentReplyService.index(reqVO));
    }

}