package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.nationalpolicy.NationalPolicyInfoDO;
import cn.iocoder.yudao.module.datacenter.service.businessguidance.nationalpolicy.NationalPolicyInfoService;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 国家政策信息 Controller
 *
 * @author Gyh
 */
@Tag(name = "管理后台 - 国家政策信息")
@RestController
@RequestMapping("/datacenter/national-policy-info")
public class NationalPolicyInfoController {

    @Resource
    private NationalPolicyInfoService nationalPolicyInfoService;

    // ========== 增删改查核心方法 ==========

    @PostMapping("/create")
    @Operation(summary = "创建国家政策信息")
    public CommonResult<Long> createNationalPolicyInfo(@Valid @RequestBody NationalPolicyInfoCreateReqVO createReqVO) {
        return success(nationalPolicyInfoService.createNationalPolicyInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新国家政策信息")
    public CommonResult<Boolean> updateNationalPolicyInfo(@Valid @RequestBody NationalPolicyInfoUpdateReqVO updateReqVO) {
        nationalPolicyInfoService.updateNationalPolicyInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除国家政策信息")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteNationalPolicyInfo(@RequestParam("id") Long id) {
        nationalPolicyInfoService.deleteNationalPolicyInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得国家政策信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<NationalPolicyInfoRespVO> getNationalPolicyInfo(@RequestParam("id") Long id) {
        NationalPolicyInfoDO nationalPolicyInfo = nationalPolicyInfoService.getNationalPolicyInfo(id);
        return success(convert(nationalPolicyInfo));
    }

    @GetMapping("/page")
    @Operation(summary = "获得国家政策信息分页")
    public CommonResult<PageResult<NationalPolicyInfoRespVO>> getNationalPolicyInfoPage(@Valid NationalPolicyInfoPageReqVO pageReqVO) {
        PageResult<NationalPolicyInfoDO> pageResult = nationalPolicyInfoService.getNationalPolicyInfoPage(pageReqVO);
        return success(new PageResult<>(convertList(pageResult.getList()), pageResult.getTotal()));
    }

    @GetMapping("/list")
    @Operation(summary = "获得国家政策信息列表")
    public CommonResult<List<NationalPolicyInfoRespVO>> getNationalPolicyInfoList() {
        List<NationalPolicyInfoDO> list = nationalPolicyInfoService.getNationalPolicyInfoList();
        return success(convertList(list));
    }

    // ========== 业务功能方法 ==========

    @GetMapping("/list-valid")
    @Operation(summary = "获得所有有效的国家政策列表")
    public CommonResult<List<NationalPolicyInfoRespVO>> getValidNationalPolicyInfoList() {
        List<NationalPolicyInfoDO> list = nationalPolicyInfoService.getValidNationalPolicyInfoList();
        return success(convertList(list));
    }

    @PutMapping("/update-enable-status")
    @Operation(summary = "更新启用状态")
    public CommonResult<Boolean> updateEnableStatus(@Valid @RequestBody NationalPolicyInfoUpdateStatusReqVO updateStatusReqVO) {
        nationalPolicyInfoService.updateEnableStatus(updateStatusReqVO.getId(), updateStatusReqVO.getEnableStatus());
        return success(true);
    }

    @GetMapping("/check-policy-no-unique")
    @Operation(summary = "检查政策编号是否唯一")
    public CommonResult<Boolean> checkPolicyNoUnique(@RequestParam("policyNo") String policyNo,
                                                     @RequestParam(value = "id", required = false) Long id) {
        boolean isUnique = nationalPolicyInfoService.checkCodeUnique(policyNo, null, null, id);
        return success(isUnique);
    }

    // ========== 转换方法 ==========

    private NationalPolicyInfoRespVO convert(NationalPolicyInfoDO nationalPolicyInfo) {
        if (nationalPolicyInfo == null) {
            return null;
        }
        NationalPolicyInfoRespVO respVO = new NationalPolicyInfoRespVO();
        respVO.setId(nationalPolicyInfo.getId());
        respVO.setPolicyId(nationalPolicyInfo.getPolicyId());
        respVO.setPolicyNo(nationalPolicyInfo.getPolicyNo());
        respVO.setPolicyName(nationalPolicyInfo.getPolicyName());
        respVO.setPolicyType(nationalPolicyInfo.getPolicyType());
        respVO.setIssueDept(nationalPolicyInfo.getIssueDept());
        respVO.setIssueTime(nationalPolicyInfo.getIssueTime());
        respVO.setEffectiveTime(nationalPolicyInfo.getEffectiveTime());
        respVO.setExpireTime(nationalPolicyInfo.getExpireTime());
        respVO.setPolicyFilePath(nationalPolicyInfo.getPolicyFilePath());
        respVO.setPolicyAbstract(nationalPolicyInfo.getPolicyAbstract());
        respVO.setPolicyStatus(nationalPolicyInfo.getPolicyStatus());
        respVO.setUploadUserId(nationalPolicyInfo.getUploadUserId());
        respVO.setUploadTime(nationalPolicyInfo.getUploadTime());
        respVO.setUpdateUserId(nationalPolicyInfo.getUpdateUserId());
        respVO.setUpdateTime(nationalPolicyInfo.getUpdateTime());
        respVO.setExtendCategory(nationalPolicyInfo.getExtendCategory());
        respVO.setCreateTime(nationalPolicyInfo.getCreateTime());
        return respVO;
    }

    private List<NationalPolicyInfoRespVO> convertList(List<NationalPolicyInfoDO> list) {
        return list.stream().map(this::convert).collect(java.util.stream.Collectors.toList());
    }
}