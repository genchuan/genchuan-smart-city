package cn.iocoder.yudao.module.datacenter.service.businessguidance.nationalpolicy;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.nationalpolicy.NationalPolicyInfoDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.businessguidance.nationalpolicy.NationalPolicyInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 国家政策信息 Service 实现类
 *
 * @author Gyh
 */
@Service
@Validated
public class NationalPolicyInfoServiceImpl implements NationalPolicyInfoService {

    @Resource
    private NationalPolicyInfoMapper nationalPolicyInfoMapper;

    @Override
    public Long createNationalPolicyInfo(NationalPolicyInfoCreateReqVO createReqVO) {
        // 校验代码唯一性
        validateCodeUnique(createReqVO.getPolicyNo(), null, null, null);

        // 插入国家政策信息
        NationalPolicyInfoDO nationalPolicyInfo = BeanUtils.toBean(createReqVO, NationalPolicyInfoDO.class);
        nationalPolicyInfoMapper.insert(nationalPolicyInfo);
        return nationalPolicyInfo.getId();
    }

    @Override
    public void updateNationalPolicyInfo(NationalPolicyInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateNationalPolicyInfoExists(updateReqVO.getId());
        // 校验代码唯一性
        validateCodeUnique(updateReqVO.getPolicyNo(), null, null, updateReqVO.getId());

        // 更新国家政策信息
        NationalPolicyInfoDO updateObj = BeanUtils.toBean(updateReqVO, NationalPolicyInfoDO.class);
        nationalPolicyInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteNationalPolicyInfo(Long id) {
        // 校验存在
        validateNationalPolicyInfoExists(id);
        // 删除
        nationalPolicyInfoMapper.deleteById(id);
    }

    @Override
    public NationalPolicyInfoDO getNationalPolicyInfo(Long id) {
        return nationalPolicyInfoMapper.selectById(id);
    }

    @Override
    public PageResult<NationalPolicyInfoDO> getNationalPolicyInfoPage(NationalPolicyInfoPageReqVO pageReqVO) {
        return nationalPolicyInfoMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<NationalPolicyInfoDO>()
                .likeIfPresent(NationalPolicyInfoDO::getPolicyName, pageReqVO.getPolicyName())
                .likeIfPresent(NationalPolicyInfoDO::getPolicyNo, pageReqVO.getPolicyNo())
                .eqIfPresent(NationalPolicyInfoDO::getPolicyType, pageReqVO.getPolicyType())
                .eqIfPresent(NationalPolicyInfoDO::getPolicyStatus, pageReqVO.getPolicyStatus())
                .likeIfPresent(NationalPolicyInfoDO::getIssueDept, pageReqVO.getIssueDept())
                .orderByDesc(NationalPolicyInfoDO::getCreateTime));
    }

    @Override
    public List<NationalPolicyInfoDO> getNationalPolicyInfoList() {
        return nationalPolicyInfoMapper.selectList();
    }

    @Override
    public List<NationalPolicyInfoDO> getValidNationalPolicyInfoList() {
        return nationalPolicyInfoMapper.selectList(new LambdaQueryWrapperX<NationalPolicyInfoDO>()
                .eq(NationalPolicyInfoDO::getPolicyStatus, "有效"));
    }

    @Override
    public List<NationalPolicyInfoDO> getEnabledMidCategoriesByBigCode(String eventBigCode) {
        // 国家政策不需要层级结构，返回空列表或根据实际需求调整
        return List.of();
    }

    @Override
    public List<NationalPolicyInfoDO> getEnabledSmallCategoriesByMidCode(String eventMidCode) {
        // 国家政策不需要层级结构，返回空列表或根据实际需求调整
        return List.of();
    }

    @Override
    public void updateEnableStatus(Long id, String enableStatus) {
        // 校验存在
        NationalPolicyInfoDO nationalPolicyInfo = validateNationalPolicyInfoExists(id);

        // 更新状态
        NationalPolicyInfoDO updateObj = new NationalPolicyInfoDO();
        updateObj.setId(id);
        updateObj.setPolicyStatus(enableStatus);
        nationalPolicyInfoMapper.updateById(updateObj);
    }

    @Override
    public boolean checkCodeUnique(String policyNo, String eventMidCode, String eventSmallCode, Long id) {
        // 国家政策主要校验政策编号唯一性
        if (policyNo != null) {
            NationalPolicyInfoDO exist = nationalPolicyInfoMapper.selectByPolicyNo(policyNo);
            return exist == null || Objects.equals(exist.getId(), id);
        }
        return true;
    }

    @Override
    public List<NationalPolicyInfoDO> getNationalPolicyInfoListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        return nationalPolicyInfoMapper.selectList(new LambdaQueryWrapperX<NationalPolicyInfoDO>()
                .in(NationalPolicyInfoDO::getId, ids));
    }

    @Override
    public NationalPolicyInfoDO getNationalPolicyInfoBySmallCode(String eventSmallCode) {
        // 国家政策没有小类代码概念，返回null或根据实际需求调整
        return null;
    }

    // ========== 私有方法 ==========

    /**
     * 校验国家政策信息是否存在
     */
    private NationalPolicyInfoDO validateNationalPolicyInfoExists(Long id) {
        NationalPolicyInfoDO nationalPolicyInfo = nationalPolicyInfoMapper.selectById(id);
        if (nationalPolicyInfo == null) {
            throw exception(NATIONAL_POLICY_INFO_NOT_EXISTS);
        }
        return nationalPolicyInfo;
    }

    /**
     * 校验代码唯一性
     */
    private void validateCodeUnique(String policyNo, String eventMidCode, String eventSmallCode, Long id) {
        if (!checkCodeUnique(policyNo, eventMidCode, eventSmallCode, id)) {
            throw exception(NATIONAL_POLICY_INFO_NO_DUPLICATE);
        }
    }
}
