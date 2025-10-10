package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.policyinterpretation.PolicyInterpretationInfoDO;
import cn.iocoder.yudao.module.datacenter.service.businessguidance.policyinterpretation.PolicyInterpretationInfoService;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 政策解读信息 Controller
 *
 * @author Gyh
 */
@Tag(name = "管理后台 - 政策解读信息")
@RestController
@RequestMapping("/datacenter/policy-interpretation-info")
public class PolicyInterpretationInfoController {

    @Resource
    private PolicyInterpretationInfoService policyInterpretationInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建政策解读信息")
    public CommonResult<Long> createPolicyInterpretationInfo(@Valid @RequestBody PolicyInterpretationInfoCreateReqVO createReqVO) {
        return success(policyInterpretationInfoService.createPolicyInterpretationInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政策解读信息")
    public CommonResult<Boolean> updatePolicyInterpretationInfo(@Valid @RequestBody PolicyInterpretationInfoUpdateReqVO updateReqVO) {
        policyInterpretationInfoService.updatePolicyInterpretationInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政策解读信息")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deletePolicyInterpretationInfo(@RequestParam("id") Long id) {
        policyInterpretationInfoService.deletePolicyInterpretationInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策解读信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<PolicyInterpretationInfoRespVO> getPolicyInterpretationInfo(@RequestParam("id") Long id) {
        PolicyInterpretationInfoDO policyInterpretationInfo = policyInterpretationInfoService.getPolicyInterpretationInfo(id);
        return success(convert(policyInterpretationInfo));
    }

    @GetMapping("/page")
    @Operation(summary = "获得政策解读信息分页")
    public CommonResult<PageResult<PolicyInterpretationInfoRespVO>> getPolicyInterpretationInfoPage(@Valid PolicyInterpretationInfoPageReqVO pageReqVO) {
        PageResult<PolicyInterpretationInfoDO> pageResult = policyInterpretationInfoService.getPolicyInterpretationInfoPage(pageReqVO);
        return success(new PageResult<>(convertList(pageResult.getList()), pageResult.getTotal()));
    }

    @GetMapping("/list")
    @Operation(summary = "获得政策解读信息列表")
    public CommonResult<List<PolicyInterpretationInfoRespVO>> getPolicyInterpretationInfoList() {
        List<PolicyInterpretationInfoDO> list = policyInterpretationInfoService.getPolicyInterpretationInfoList();
        return success(convertList(list));
    }

    @GetMapping("/list-published")
    @Operation(summary = "获得所有已发布的政策解读列表")
    public CommonResult<List<PolicyInterpretationInfoRespVO>> getPublishedPolicyInterpretationInfoList() {
        List<PolicyInterpretationInfoDO> list = policyInterpretationInfoService.getPublishedPolicyInterpretationInfoList();
        return success(convertList(list));
    }

    @GetMapping("/list-by-policy-id")
    @Operation(summary = "根据关联政策ID查询已发布的解读列表")
    @Parameter(name = "relatedPolicyId", description = "关联政策ID", required = true)
    public CommonResult<List<PolicyInterpretationInfoRespVO>> getPublishedInterpretationByPolicyId(@RequestParam("relatedPolicyId") String relatedPolicyId) {
        List<PolicyInterpretationInfoDO> list = policyInterpretationInfoService.getPublishedInterpretationByPolicyId(relatedPolicyId);
        return success(convertList(list));
    }

    @GetMapping("/list-by-type")
    @Operation(summary = "根据解读类型查询解读列表")
    @Parameter(name = "interpretationType", description = "解读类型", required = true)
    public CommonResult<List<PolicyInterpretationInfoRespVO>> getPolicyInterpretationInfoListByType(@RequestParam("interpretationType") String interpretationType) {
        List<PolicyInterpretationInfoDO> list = policyInterpretationInfoService.getPolicyInterpretationInfoListByType(interpretationType);
        return success(convertList(list));
    }

    @PutMapping("/update-status")
    @Operation(summary = "更新解读状态")
    public CommonResult<Boolean> updateInterpretationStatus(@Valid @RequestBody PolicyInterpretationInfoUpdateStatusReqVO updateStatusReqVO) {
        policyInterpretationInfoService.updateInterpretationStatus(
                updateStatusReqVO.getId(),
                updateStatusReqVO.getInterpretationStatus(),
                updateStatusReqVO.getOffShelfReason());
        return success(true);
    }

    @PutMapping("/off-shelf-batch")
    @Operation(summary = "批量下架解读")
    public CommonResult<Boolean> offShelfInterpretationBatch(@Valid @RequestBody PolicyInterpretationInfoOffShelfBatchReqVO offShelfBatchReqVO) {
        policyInterpretationInfoService.offShelfInterpretationBatch(
                offShelfBatchReqVO.getIds(),
                offShelfBatchReqVO.getOffShelfReason());
        return success(true);
    }

    @GetMapping("/check-title-unique")
    @Operation(summary = "检查解读标题是否唯一")
    public CommonResult<Boolean> checkInterpretationTitleUnique(@RequestParam("interpretationTitle") String interpretationTitle,
                                                                @RequestParam(value = "id", required = false) Long id) {
        boolean isUnique = policyInterpretationInfoService.checkInterpretationTitleUnique(interpretationTitle, id);
        return success(isUnique);
    }

    // ========== 转换方法 ==========

    private PolicyInterpretationInfoRespVO convert(PolicyInterpretationInfoDO policyInterpretationInfo) {
        if (policyInterpretationInfo == null) {
            return null;
        }
        PolicyInterpretationInfoRespVO respVO = new PolicyInterpretationInfoRespVO();
        respVO.setId(policyInterpretationInfo.getId());
        respVO.setInterpretationId(policyInterpretationInfo.getInterpretationId());
        respVO.setInterpretationTitle(policyInterpretationInfo.getInterpretationTitle());
        respVO.setRelatedPolicyType(policyInterpretationInfo.getRelatedPolicyType());
        respVO.setRelatedPolicyId(policyInterpretationInfo.getRelatedPolicyId());
        respVO.setRelatedPolicyName(policyInterpretationInfo.getRelatedPolicyName());
        respVO.setInterpretationContent(policyInterpretationInfo.getInterpretationContent());
        respVO.setInterpretationType(policyInterpretationInfo.getInterpretationType());
        respVO.setIssueUserId(policyInterpretationInfo.getIssueUserId());
        respVO.setIssueUserName(policyInterpretationInfo.getIssueUserName());
        respVO.setIssueTime(policyInterpretationInfo.getIssueTime());
        respVO.setInterpretationStatus(policyInterpretationInfo.getInterpretationStatus());
        respVO.setOffShelfReason(policyInterpretationInfo.getOffShelfReason());
        respVO.setUpdateUserId(policyInterpretationInfo.getUpdateUserId());
        respVO.setUpdateTime(policyInterpretationInfo.getUpdateTime());
        respVO.setExtendCategory(policyInterpretationInfo.getExtendCategory());
        respVO.setCreateTime(policyInterpretationInfo.getCreateTime());
        return respVO;
    }

    private List<PolicyInterpretationInfoRespVO> convertList(List<PolicyInterpretationInfoDO> list) {
        return list.stream().map(this::convert).collect(java.util.stream.Collectors.toList());
    }
}