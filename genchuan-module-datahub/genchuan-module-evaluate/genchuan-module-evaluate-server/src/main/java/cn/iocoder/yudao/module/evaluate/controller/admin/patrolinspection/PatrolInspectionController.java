package cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo.PatrolInspectionCreateRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo.PatrolInspectionPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo.PatrolInspectionRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo.PatrolInspectionSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.patrolinspection.PatrolInspectionDO;
import cn.iocoder.yudao.module.evaluate.service.patrolinspection.PatrolInspectionService;
import cn.iocoder.yudao.module.evaluate.service.commentrule.CommentRuleService;
import cn.iocoder.yudao.module.evaluate.service.commentstatistic.CommentStatisticService;
import cn.iocoder.yudao.module.evaluate.service.image.ImageUploadService;
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
import java.util.*;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 巡查巡检")
@RestController
@RequestMapping("/evaluate/patrol-inspection")
@Validated
public class PatrolInspectionController {

    @Resource
    private PatrolInspectionService patrolInspectionService;

    @Resource
    private CommentRuleService commentRuleService;

    @Resource
    private CommentStatisticService commentStatisticService;

    @Resource
    private ImageUploadService imageUploadService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检")
    @PreAuthorize("@ss.hasPermission('evaluate:patrol-inspection:create')")
    public CommonResult<PatrolInspectionCreateRespVO> createPatrolInspection(@Valid PatrolInspectionSaveReqVO createReqVO) {
        String imageUrl = null;
        // 上传图片
        if (createReqVO.getFiles() != null && createReqVO.getFiles().length > 0) {
            var uploadResults = imageUploadService.batchUploadImages(createReqVO.getFiles());
            if (!uploadResults.isEmpty()) {
                imageUrl = uploadResults.get(0).getUrl();
                createReqVO.setImageUrl(imageUrl);
            }
        }
        Long id = patrolInspectionService.createPatrolInspection(createReqVO);

        // 返回创建结果
        PatrolInspectionCreateRespVO respVO = new PatrolInspectionCreateRespVO();
        respVO.setId(id);
        respVO.setImageUrl(imageUrl);
        return success(respVO);
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检")
    @PreAuthorize("@ss.hasPermission('evaluate:patrol-inspection:update')")
    public CommonResult<Boolean> updatePatrolInspection(@Valid PatrolInspectionSaveReqVO updateReqVO) {
        // 上传新图片
        if (updateReqVO.getFiles() != null && updateReqVO.getFiles().length > 0) {
            var uploadResults = imageUploadService.batchUploadImages(updateReqVO.getFiles());
            if (!uploadResults.isEmpty()) {
                updateReqVO.setImageUrl(uploadResults.get(0).getUrl());
            }
        }
        patrolInspectionService.updatePatrolInspection(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:patrol-inspection:delete')")
    public CommonResult<Boolean> deletePatrolInspection(@RequestParam("id") Long id) {
        patrolInspectionService.deletePatrolInspection(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:patrol-inspection:query')")
    public CommonResult<PatrolInspectionRespVO> getPatrolInspection(@RequestParam("id") Long id) {
        PatrolInspectionDO patrolInspection = patrolInspectionService.getPatrolInspection(id);
        return success(BeanUtils.toBean(patrolInspection, PatrolInspectionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检分页")
    @PreAuthorize("@ss.hasPermission('evaluate:patrol-inspection:query')")
    public CommonResult<PageResult<PatrolInspectionRespVO>> getPatrolInspectionPage(@Valid PatrolInspectionPageReqVO pageReqVO) {
        PageResult<PatrolInspectionDO> pageResult = patrolInspectionService.getPatrolInspectionPage(pageReqVO);
        // 填充 ruleId 并更新统计表
        List<PatrolInspectionRespVO> list = BeanUtils.toBean(pageResult.getList(), PatrolInspectionRespVO.class);
        for (PatrolInspectionRespVO respVO : list) {
            if (respVO.getSystemId() != null && respVO.getItemId() != null) {
                Long ruleId = commentRuleService.getCommentRuleIdBySystemIdAndItemId(respVO.getSystemId(), respVO.getItemId());
                respVO.setRuleId(ruleId);
                // 更新统计表中的 ruleId
                commentStatisticService.updateRuleId(respVO.getSystemId(), respVO.getItemId(), respVO.getObjectId(), ruleId, respVO.getAddressCoding());
            }
        }
        return success(new PageResult<>(list, pageResult.getTotal()));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:patrol-inspection:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPatrolInspectionExcel(@Valid PatrolInspectionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PatrolInspectionDO> list = patrolInspectionService.getPatrolInspectionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检.xls", "数据", PatrolInspectionRespVO.class,
                        BeanUtils.toBean(list, PatrolInspectionRespVO.class));
    }

}