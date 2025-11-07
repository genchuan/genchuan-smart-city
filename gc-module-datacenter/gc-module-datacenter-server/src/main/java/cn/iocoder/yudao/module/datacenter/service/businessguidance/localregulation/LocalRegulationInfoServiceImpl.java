package cn.iocoder.yudao.module.datacenter.service.businessguidance.localregulation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.localregulation.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.localregulation.LocalRegulationInfoDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.businessguidance.localregulation.LocalRegulationInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 地方法规信息 Service 实现类
 *
 * @author Gyh
 */
@Service
@Validated
@Slf4j
public class LocalRegulationInfoServiceImpl implements LocalRegulationInfoService {

    @Resource
    private LocalRegulationInfoMapper localRegulationInfoMapper;

    @Override
    public Long createLocalRegulationInfo(LocalRegulationInfoCreateReqVO createReqVO) {
        // 校验法规编号唯一性
        validateRegulationNoUnique(createReqVO.getRegulationNo(), null);

        // 校验时间逻辑
        validateTimeLogic(createReqVO.getEffectiveTime(), createReqVO.getExpireTime());

        // 插入地方法规信息
        LocalRegulationInfoDO localRegulationInfo = BeanUtils.toBean(createReqVO, LocalRegulationInfoDO.class);
        // 设置系统字段
        localRegulationInfo.setRegulationId(generateRegulationId());
        localRegulationInfo.setRegulationStatus(calculateInitialStatus(createReqVO.getEffectiveTime()));
        localRegulationInfo.setUploadUserId(getCurrentUserId());
        localRegulationInfo.setUploadTime(LocalDateTime.now());
        localRegulationInfo.setUpdateUserId(getCurrentUserId());
        localRegulationInfo.setUpdateTime(LocalDateTime.now());

        localRegulationInfoMapper.insert(localRegulationInfo);
        log.info("[createLocalRegulationInfo][创建地方法规信息成功：法规编号={}]", createReqVO.getRegulationNo());
        return localRegulationInfo.getId();
    }

    @Override
    public void updateLocalRegulationInfo(LocalRegulationInfoUpdateReqVO updateReqVO) {
        // 校验存在
        LocalRegulationInfoDO localRegulationInfo = validateLocalRegulationInfoExists(updateReqVO.getId());
        // 校验法规编号唯一性（排除自身）
        validateRegulationNoUnique(updateReqVO.getRegulationNo(), updateReqVO.getId());
        // 校验时间逻辑
        validateTimeLogic(updateReqVO.getEffectiveTime(), updateReqVO.getExpireTime());
        // 校验状态（仅未生效状态可修改）
        validateCanUpdate(localRegulationInfo.getRegulationStatus());

        // 更新地方法规信息
        LocalRegulationInfoDO updateObj = BeanUtils.toBean(updateReqVO, LocalRegulationInfoDO.class);
        updateObj.setUpdateUserId(getCurrentUserId());
        updateObj.setUpdateTime(LocalDateTime.now());
        // 重新计算状态
        updateObj.setRegulationStatus(calculateStatus(updateObj.getEffectiveTime(), updateObj.getExpireTime()));

        localRegulationInfoMapper.updateById(updateObj);
        log.info("[updateLocalRegulationInfo][更新地方法规信息成功：ID={}]", updateReqVO.getId());
    }

    @Override
    public void deleteLocalRegulationInfo(Long id) {
        // 校验存在
        LocalRegulationInfoDO localRegulationInfo = validateLocalRegulationInfoExists(id);
        // 校验状态（仅未生效状态可删除）
        validateCanDelete(localRegulationInfo.getRegulationStatus());
        // 校验是否被引用（预留）

        // 删除
        localRegulationInfoMapper.deleteById(id);
        log.info("[deleteLocalRegulationInfo][删除地方法规信息成功：ID={}]", id);
    }

    @Override
    public LocalRegulationInfoDO getLocalRegulationInfo(Long id) {
        return localRegulationInfoMapper.selectById(id);
    }

    @Override
    public PageResult<LocalRegulationInfoDO> getLocalRegulationInfoPage(LocalRegulationInfoPageReqVO pageReqVO) {
        return localRegulationInfoMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<LocalRegulationInfoDO>()
                .likeIfPresent(LocalRegulationInfoDO::getRegulationName, pageReqVO.getRegulationName())
                .likeIfPresent(LocalRegulationInfoDO::getRegulationNo, pageReqVO.getRegulationNo())
                .eqIfPresent(LocalRegulationInfoDO::getRegulationType, pageReqVO.getRegulationType())
                .eqIfPresent(LocalRegulationInfoDO::getRegulationStatus, pageReqVO.getRegulationStatus())
                .eqIfPresent(LocalRegulationInfoDO::getAdminCode, pageReqVO.getAdminCode())
                .likeIfPresent(LocalRegulationInfoDO::getAdminName, pageReqVO.getAdminName())
                .likeIfPresent(LocalRegulationInfoDO::getIssueDept, pageReqVO.getIssueDept())
                .orderByDesc(LocalRegulationInfoDO::getIssueTime));
    }

    @Override
    public List<LocalRegulationInfoDO> getLocalRegulationInfoList() {
        return localRegulationInfoMapper.selectList();
    }

    @Override
    public List<LocalRegulationInfoDO> getValidLocalRegulationInfoList() {
        return localRegulationInfoMapper.selectList(new LambdaQueryWrapperX<LocalRegulationInfoDO>()
                .eq(LocalRegulationInfoDO::getRegulationStatus, "有效")
                .orderByDesc(LocalRegulationInfoDO::getIssueTime));
    }

    @Override
    public List<LocalRegulationInfoDO> getExpiringLocalRegulationInfoList() {
        return localRegulationInfoMapper.selectExpiringRegulations();
    }

    @Override
    public boolean checkRegulationNoUnique(String regulationNo, Long id) {
        if (id == null) {
            return !localRegulationInfoMapper.existsByRegulationNo(regulationNo);
        } else {
            return !localRegulationInfoMapper.existsByRegulationNoExcludeSelf(regulationNo, id);
        }
    }

    @Override
    public void updateRegulationStatusBatch(List<Long> ids, String regulationStatus) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        for (Long id : ids) {
            LocalRegulationInfoDO updateObj = new LocalRegulationInfoDO();
            updateObj.setId(id);
            updateObj.setRegulationStatus(regulationStatus);
            updateObj.setUpdateTime(LocalDateTime.now());
            localRegulationInfoMapper.updateById(updateObj);
        }

        log.info("[updateRegulationStatusBatch][批量更新法规状态成功：数量={}, 状态={}]", ids.size(), regulationStatus);
    }

    @Override
    public void autoUpdateRegulationStatus() {
        log.info("[autoUpdateRegulationStatus][开始自动更新法规状态]");

        // 查询需要更新状态的法规
        List<LocalRegulationInfoDO> regulations = localRegulationInfoMapper.selectRegulationsNeedStatusUpdate();
        if (regulations.isEmpty()) {
            log.info("[autoUpdateRegulationStatus][没有需要更新状态的法规]");
            return;
        }

        int updatedCount = 0;
        LocalDate today = LocalDate.now();

        for (LocalRegulationInfoDO regulation : regulations) {
            String newStatus = calculateStatus(regulation.getEffectiveTime(), regulation.getExpireTime());
            if (!newStatus.equals(regulation.getRegulationStatus())) {
                LocalRegulationInfoDO updateObj = new LocalRegulationInfoDO();
                updateObj.setId(regulation.getId());
                updateObj.setRegulationStatus(newStatus);
                updateObj.setUpdateTime(LocalDateTime.now());
                localRegulationInfoMapper.updateById(updateObj);
                updatedCount++;
                log.info("[autoUpdateRegulationStatus][更新法规状态：ID={}, 原状态={}, 新状态={}]",
                        regulation.getId(), regulation.getRegulationStatus(), newStatus);
            }
        }

        log.info("[autoUpdateRegulationStatus][自动更新法规状态完成：共更新{}条记录]", updatedCount);
    }

    @Override
    public Long createRegulationRevision(LocalRegulationInfoCreateRevisionReqVO createRevisionReqVO) {
        // 校验原法规存在
        LocalRegulationInfoDO originalRegulation = validateLocalRegulationInfoExists(createRevisionReqVO.getOriginalId());
        // 校验新法规编号唯一性
        validateRegulationNoUnique(createRevisionReqVO.getRegulationNo(), null);

        // 创建修订版本
        LocalRegulationInfoDO revision = BeanUtils.toBean(createRevisionReqVO, LocalRegulationInfoDO.class);
        revision.setRegulationId(generateRegulationId());
        revision.setRegulationStatus(calculateInitialStatus(createRevisionReqVO.getEffectiveTime()));
        revision.setUploadUserId(getCurrentUserId());
        revision.setUploadTime(LocalDateTime.now());
        revision.setUpdateUserId(getCurrentUserId());
        revision.setUpdateTime(LocalDateTime.now());

        localRegulationInfoMapper.insert(revision);

        // 标记原法规为已修订
        LocalRegulationInfoDO updateOriginal = new LocalRegulationInfoDO();
        updateOriginal.setId(createRevisionReqVO.getOriginalId());
        updateOriginal.setRegulationStatus("已修订");
        updateOriginal.setUpdateTime(LocalDateTime.now());
        localRegulationInfoMapper.updateById(updateOriginal);

        log.info("[createRegulationRevision][创建法规修订版本成功：原法规ID={}, 新法规编号={}]",
                createRevisionReqVO.getOriginalId(), createRevisionReqVO.getRegulationNo());
        return revision.getId();
    }

    @Override
    public List<LocalRegulationInfoDO> getLocalRegulationInfoListByAdminCode(String adminCode) {
        return localRegulationInfoMapper.selectByAdminCode(adminCode);
    }

    @Override
    public List<LocalRegulationInfoDO> getLocalRegulationInfoListByNationalPolicyId(String nationalPolicyId) {
        return localRegulationInfoMapper.selectByRelatedNationalPolicyId(nationalPolicyId);
    }

    @Override
    public List<LocalRegulationInfoDO> getRegulationRevisionVersions(String regulationNo) {
        return localRegulationInfoMapper.selectRevisionVersions(regulationNo);
    }

    // ========== 私有方法 ==========

    /**
     * 校验地方法规信息是否存在
     */
    private LocalRegulationInfoDO validateLocalRegulationInfoExists(Long id) {
        LocalRegulationInfoDO localRegulationInfo = localRegulationInfoMapper.selectById(id);
        if (localRegulationInfo == null) {
            throw exception(LOCAL_REGULATION_INFO_NOT_EXISTS);
        }
        return localRegulationInfo;
    }

    /**
     * 校验法规编号唯一性
     */
    private void validateRegulationNoUnique(String regulationNo, Long id) {
        if (!checkRegulationNoUnique(regulationNo, id)) {
            throw exception(LOCAL_REGULATION_INFO_NO_DUPLICATE);
        }
    }

    /**
     * 校验时间逻辑
     */
    private void validateTimeLogic(LocalDate effectiveTime, LocalDate expireTime) {
        if (expireTime != null && effectiveTime != null && expireTime.isBefore(effectiveTime)) {
            throw exception(LOCAL_REGULATION_INFO_TIME_INVALID);
        }
    }

    /**
     * 校验是否可以更新（仅未生效状态可更新）
     */
    private void validateCanUpdate(String regulationStatus) {
        if (!"未生效".equals(regulationStatus)) {
            throw exception(LOCAL_REGULATION_INFO_CANNOT_UPDATE);
        }
    }

    /**
     * 校验是否可以删除（仅未生效状态可删除）
     */
    private void validateCanDelete(String regulationStatus) {
        if (!"未生效".equals(regulationStatus)) {
            throw exception(LOCAL_REGULATION_INFO_CANNOT_DELETE);
        }
    }

    /**
     * 生成法规ID（UUID）
     */
    private String generateRegulationId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 计算初始状态
     */
    private String calculateInitialStatus(LocalDate effectiveTime) {
        LocalDate today = LocalDate.now();
        if (effectiveTime.isAfter(today)) {
            return "未生效";
        } else {
            return "有效";
        }
    }

    /**
     * 计算法规状态
     */
    private String calculateStatus(LocalDate effectiveTime, LocalDate expireTime) {
        LocalDate today = LocalDate.now();

        if (effectiveTime.isAfter(today)) {
            return "未生效";
        } else if (expireTime != null && expireTime.isBefore(today)) {
            return "已废止";
        } else {
            return "有效";
        }
    }

    /**
     * 获取当前用户ID（模拟）
     */
    private String getCurrentUserId() {
        // 实际项目中从SecurityContext获取
        return "admin";
    }
}