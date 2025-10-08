package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.localregulation.LocalRegulationInfoDO;
import cn.iocoder.yudao.module.datacenter.service.businessguidance.localregulation.LocalRegulationInfoService;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 地方法规信息 Controller
 *
 * @author Gyh
 */
@Tag(name = "管理后台 - 地方法规信息")
@RestController
@RequestMapping("/datacenter/local-regulation-info")
public class LocalRegulationInfoController {

    @Resource
    private LocalRegulationInfoService localRegulationInfoService;

    // ========== 增删改查核心方法 ==========

    @PostMapping("/create")
    @Operation(summary = "创建地方法规信息")
    public CommonResult<Long> createLocalRegulationInfo(@Valid @RequestBody LocalRegulationInfoCreateReqVO createReqVO) {
        return success(localRegulationInfoService.createLocalRegulationInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新地方法规信息")
    public CommonResult<Boolean> updateLocalRegulationInfo(@Valid @RequestBody LocalRegulationInfoUpdateReqVO updateReqVO) {
        localRegulationInfoService.updateLocalRegulationInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除地方法规信息")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteLocalRegulationInfo(@RequestParam("id") Long id) {
        localRegulationInfoService.deleteLocalRegulationInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得地方法规信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<LocalRegulationInfoRespVO> getLocalRegulationInfo(@RequestParam("id") Long id) {
        LocalRegulationInfoDO localRegulationInfo = localRegulationInfoService.getLocalRegulationInfo(id);
        return success(convert(localRegulationInfo));
    }

    @GetMapping("/page")
    @Operation(summary = "获得地方法规信息分页")
    public CommonResult<PageResult<LocalRegulationInfoRespVO>> getLocalRegulationInfoPage(@Valid LocalRegulationInfoPageReqVO pageReqVO) {
        PageResult<LocalRegulationInfoDO> pageResult = localRegulationInfoService.getLocalRegulationInfoPage(pageReqVO);
        return success(new PageResult<>(convertList(pageResult.getList()), pageResult.getTotal()));
    }

    @GetMapping("/list")
    @Operation(summary = "获得地方法规信息列表")
    public CommonResult<List<LocalRegulationInfoRespVO>> getLocalRegulationInfoList() {
        List<LocalRegulationInfoDO> list = localRegulationInfoService.getLocalRegulationInfoList();
        return success(convertList(list));
    }

    // ========== 业务功能方法 ==========

    @GetMapping("/list-valid")
    @Operation(summary = "获得所有有效的地方法规列表")
    public CommonResult<List<LocalRegulationInfoRespVO>> getValidLocalRegulationInfoList() {
        List<LocalRegulationInfoDO> list = localRegulationInfoService.getValidLocalRegulationInfoList();
        return success(convertList(list));
    }

    @GetMapping("/list-expiring")
    @Operation(summary = "获得即将失效的地方法规列表（距失效时间≤30天）")
    public CommonResult<List<LocalRegulationInfoRespVO>> getExpiringLocalRegulationInfoList() {
        List<LocalRegulationInfoDO> list = localRegulationInfoService.getExpiringLocalRegulationInfoList();
        return success(convertList(list));
    }

    @PostMapping("/create-revision")
    @Operation(summary = "创建法规修订版本")
    public CommonResult<Long> createRegulationRevision(@Valid @RequestBody LocalRegulationInfoCreateRevisionReqVO createRevisionReqVO) {
        return success(localRegulationInfoService.createRegulationRevision(createRevisionReqVO));
    }

    @GetMapping("/list-by-admin-code")
    @Operation(summary = "根据行政区划代码查询地方法规列表")
    @Parameter(name = "adminCode", description = "行政区划代码", required = true)
    public CommonResult<List<LocalRegulationInfoRespVO>> getLocalRegulationInfoListByAdminCode(@RequestParam("adminCode") String adminCode) {
        List<LocalRegulationInfoDO> list = localRegulationInfoService.getLocalRegulationInfoListByAdminCode(adminCode);
        return success(convertList(list));
    }

    @GetMapping("/list-by-national-policy")
    @Operation(summary = "根据国家政策ID查询关联的地方法规列表")
    @Parameter(name = "nationalPolicyId", description = "国家政策ID", required = true)
    public CommonResult<List<LocalRegulationInfoRespVO>> getLocalRegulationInfoListByNationalPolicyId(@RequestParam("nationalPolicyId") String nationalPolicyId) {
        List<LocalRegulationInfoDO> list = localRegulationInfoService.getLocalRegulationInfoListByNationalPolicyId(nationalPolicyId);
        return success(convertList(list));
    }

    @GetMapping("/revision-versions")
    @Operation(summary = "查询法规的所有修订版本")
    @Parameter(name = "regulationNo", description = "法规编号", required = true)
    public CommonResult<List<LocalRegulationInfoRespVO>> getRegulationRevisionVersions(@RequestParam("regulationNo") String regulationNo) {
        List<LocalRegulationInfoDO> list = localRegulationInfoService.getRegulationRevisionVersions(regulationNo);
        return success(convertList(list));
    }

    @GetMapping("/check-regulation-no-unique")
    @Operation(summary = "检查法规编号是否唯一")
    public CommonResult<Boolean> checkRegulationNoUnique(@RequestParam("regulationNo") String regulationNo,
                                                         @RequestParam(value = "id", required = false) Long id) {
        boolean isUnique = localRegulationInfoService.checkRegulationNoUnique(regulationNo, id);
        return success(isUnique);
    }

    @PutMapping("/update-status-batch")
    @Operation(summary = "批量更新法规状态")
    public CommonResult<Boolean> updateRegulationStatusBatch(@Valid @RequestBody LocalRegulationInfoUpdateStatusReqVO updateStatusReqVO) {
        localRegulationInfoService.updateRegulationStatusBatch(updateStatusReqVO.getIds(), updateStatusReqVO.getRegulationStatus());
        return success(true);
    }

    // ========== 转换方法 ==========

    private LocalRegulationInfoRespVO convert(LocalRegulationInfoDO localRegulationInfo) {
        if (localRegulationInfo == null) {
            return null;
        }
        LocalRegulationInfoRespVO respVO = new LocalRegulationInfoRespVO();
        respVO.setId(localRegulationInfo.getId());
        respVO.setRegulationId(localRegulationInfo.getRegulationId());
        respVO.setRegulationNo(localRegulationInfo.getRegulationNo());
        respVO.setRegulationName(localRegulationInfo.getRegulationName());
        respVO.setRegulationType(localRegulationInfo.getRegulationType());
        respVO.setAdminCode(localRegulationInfo.getAdminCode());
        respVO.setAdminName(localRegulationInfo.getAdminName());
        respVO.setIssueDept(localRegulationInfo.getIssueDept());
        respVO.setIssueTime(localRegulationInfo.getIssueTime());
        respVO.setEffectiveTime(localRegulationInfo.getEffectiveTime());
        respVO.setExpireTime(localRegulationInfo.getExpireTime());
        respVO.setRevisionBasis(localRegulationInfo.getRevisionBasis());
        respVO.setRelatedNationalPolicyId(localRegulationInfo.getRelatedNationalPolicyId());
        respVO.setRegulationFilePath(localRegulationInfo.getRegulationFilePath());
        respVO.setRegulationAbstract(localRegulationInfo.getRegulationAbstract());
        respVO.setRegulationStatus(localRegulationInfo.getRegulationStatus());
        respVO.setUploadUserId(localRegulationInfo.getUploadUserId());
        respVO.setUploadTime(localRegulationInfo.getUploadTime());
        respVO.setUpdateUserId(localRegulationInfo.getUpdateUserId());
        respVO.setUpdateTime(localRegulationInfo.getUpdateTime());
        respVO.setExtendCategory(localRegulationInfo.getExtendCategory());
        respVO.setCreateTime(localRegulationInfo.getCreateTime());
        return respVO;
    }

    private List<LocalRegulationInfoRespVO> convertList(List<LocalRegulationInfoDO> list) {
        return list.stream().map(this::convert).collect(java.util.stream.Collectors.toList());
    }
}