package cn.iocoder.yudao.module.datacenter.service.businessguidance.industrystandard;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.industrystandard.IndustryStandardInfoDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.businessguidance.industrystandard.IndustryStandardInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 行业规范信息 Service 实现类
 *
 * @author Gyh
 */
@Service
@Validated
@Slf4j
public class IndustryStandardInfoServiceImpl implements IndustryStandardInfoService {

    @Resource
    private IndustryStandardInfoMapper industryStandardInfoMapper;

    // 规范编号格式正则表达式
    private static final Pattern STANDARD_NO_PATTERN = Pattern.compile("^(GB/T|CJ/T|JGJ|CJJ)\\s\\d{5}-\\d{4}$");

    @Override
    public Long createIndustryStandardInfo(IndustryStandardInfoCreateReqVO createReqVO) {
        // 校验规范编号格式
        validateStandardNoFormatInternal(createReqVO.getStandardNo());
        // 校验规范编号唯一性
        validateStandardNoUnique(createReqVO.getStandardNo(), null);
        // 校验时间逻辑
        validateTimeLogic(createReqVO.getImplementationTime());

        // 插入行业规范信息
        IndustryStandardInfoDO industryStandardInfo = BeanUtils.toBean(createReqVO, IndustryStandardInfoDO.class);
        // 设置系统字段
        industryStandardInfo.setStandardId(generateStandardId());
        industryStandardInfo.setStandardStatus(calculateInitialStatus(createReqVO.getImplementationTime()));
        industryStandardInfo.setUploadUserId(getCurrentUserId());
        industryStandardInfo.setUploadTime(LocalDateTime.now());
        industryStandardInfo.setUpdateUserId(getCurrentUserId());
        industryStandardInfo.setUpdateTime(LocalDateTime.now());

        industryStandardInfoMapper.insert(industryStandardInfo);
        log.info("[createIndustryStandardInfo][创建行业规范信息成功：规范编号={}]", createReqVO.getStandardNo());
        return industryStandardInfo.getId();
    }

    @Override
    public void updateIndustryStandardInfo(IndustryStandardInfoUpdateReqVO updateReqVO) {
        // 校验存在
        IndustryStandardInfoDO industryStandardInfo = validateIndustryStandardInfoExists(updateReqVO.getId());
        // 校验规范编号格式
        validateStandardNoFormatInternal(updateReqVO.getStandardNo());
        // 校验规范编号唯一性（排除自身）
        validateStandardNoUnique(updateReqVO.getStandardNo(), updateReqVO.getId());
        // 校验时间逻辑
        validateTimeLogic(updateReqVO.getImplementationTime());
        // 校验状态（仅未实施状态可修改）
        validateCanUpdate(industryStandardInfo.getStandardStatus());

        // 更新行业规范信息
        IndustryStandardInfoDO updateObj = BeanUtils.toBean(updateReqVO, IndustryStandardInfoDO.class);
        updateObj.setUpdateUserId(getCurrentUserId());
        updateObj.setUpdateTime(LocalDateTime.now());
        // 重新计算状态
        updateObj.setStandardStatus(calculateStatus(updateObj.getImplementationTime(), updateObj.getReplaceOldNo()));

        industryStandardInfoMapper.updateById(updateObj);
        log.info("[updateIndustryStandardInfo][更新行业规范信息成功：ID={}]", updateReqVO.getId());
    }

    @Override
    public void deleteIndustryStandardInfo(Long id) {
        // 校验存在
        IndustryStandardInfoDO industryStandardInfo = validateIndustryStandardInfoExists(id);
        // 校验状态（仅未实施状态可删除）
        validateCanDelete(industryStandardInfo.getStandardStatus());
        // 校验是否被引用（预留）

        // 删除
        industryStandardInfoMapper.deleteById(id);
        log.info("[deleteIndustryStandardInfo][删除行业规范信息成功：ID={}]", id);
    }

    @Override
    public IndustryStandardInfoDO getIndustryStandardInfo(Long id) {
        return industryStandardInfoMapper.selectById(id);
    }

    @Override
    public PageResult<IndustryStandardInfoDO> getIndustryStandardInfoPage(IndustryStandardInfoPageReqVO pageReqVO) {
        return industryStandardInfoMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<IndustryStandardInfoDO>()
                .likeIfPresent(IndustryStandardInfoDO::getStandardName, pageReqVO.getStandardName())
                .likeIfPresent(IndustryStandardInfoDO::getStandardNo, pageReqVO.getStandardNo())
                .eqIfPresent(IndustryStandardInfoDO::getIndustryField, pageReqVO.getIndustryField())
                .eqIfPresent(IndustryStandardInfoDO::getStandardStatus, pageReqVO.getStandardStatus())
                .likeIfPresent(IndustryStandardInfoDO::getIssueOrganization, pageReqVO.getIssueOrganization())
                .likeIfPresent(IndustryStandardInfoDO::getRelatedBusinessModule, pageReqVO.getRelatedBusinessModule())
                .orderByDesc(IndustryStandardInfoDO::getIssueTime));
    }

    @Override
    public List<IndustryStandardInfoDO> getIndustryStandardInfoList() {
        return industryStandardInfoMapper.selectList();
    }

    @Override
    public List<IndustryStandardInfoDO> getImplementingIndustryStandardInfoList() {
        return industryStandardInfoMapper.selectList(new LambdaQueryWrapperX<IndustryStandardInfoDO>()
                .eq(IndustryStandardInfoDO::getStandardStatus, "实施中")
                .orderByDesc(IndustryStandardInfoDO::getImplementationTime));
    }

    @Override
    public List<IndustryStandardInfoDO> getReplacingIndustryStandardInfoList() {
        return industryStandardInfoMapper.selectList(new LambdaQueryWrapperX<IndustryStandardInfoDO>()
                .isNotNull(IndustryStandardInfoDO::getReplaceOldNo)
                .le(IndustryStandardInfoDO::getImplementationTime, LocalDate.now().plusDays(30))
                .eq(IndustryStandardInfoDO::getStandardStatus, "实施中")
                .orderByAsc(IndustryStandardInfoDO::getImplementationTime));
    }

    @Override
    public boolean checkStandardNoUnique(String standardNo, Long id) {
        if (id == null) {
            return !industryStandardInfoMapper.existsByStandardNo(standardNo);
        } else {
            return !industryStandardInfoMapper.existsByStandardNoExcludeSelf(standardNo, id);
        }
    }

    @Override
    public boolean validateStandardNoFormat(String standardNo) {
        return STANDARD_NO_PATTERN.matcher(standardNo).matches();
    }

    @Override
    public void updateStandardStatusBatch(List<Long> ids, String standardStatus) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        for (Long id : ids) {
            IndustryStandardInfoDO updateObj = new IndustryStandardInfoDO();
            updateObj.setId(id);
            updateObj.setStandardStatus(standardStatus);
            updateObj.setUpdateTime(LocalDateTime.now());
            industryStandardInfoMapper.updateById(updateObj);
        }

        log.info("[updateStandardStatusBatch][批量更新规范状态成功：数量={}, 状态={}]", ids.size(), standardStatus);
    }

    @Override
    public void autoUpdateStandardStatus() {
        log.info("[autoUpdateStandardStatus][开始自动更新规范状态]");

        // 查询需要更新状态的规范
        List<IndustryStandardInfoDO> standards = industryStandardInfoMapper.selectList(new LambdaQueryWrapperX<IndustryStandardInfoDO>()
                .and(wrapper -> wrapper
                        .and(sub -> sub
                                .eq(IndustryStandardInfoDO::getStandardStatus, "未实施")
                                .le(IndustryStandardInfoDO::getImplementationTime, LocalDate.now()))
                        .or(sub -> sub
                                .eq(IndustryStandardInfoDO::getStandardStatus, "实施中")
                                .isNotNull(IndustryStandardInfoDO::getReplaceOldNo))
                ));

        if (standards.isEmpty()) {
            log.info("[autoUpdateStandardStatus][没有需要更新状态的规范]");
            return;
        }

        int updatedCount = 0;
        LocalDate today = LocalDate.now();

        for (IndustryStandardInfoDO standard : standards) {
            String newStatus = calculateStatus(standard.getImplementationTime(), standard.getReplaceOldNo());
            if (!newStatus.equals(standard.getStandardStatus())) {
                IndustryStandardInfoDO updateObj = new IndustryStandardInfoDO();
                updateObj.setId(standard.getId());
                updateObj.setStandardStatus(newStatus);
                updateObj.setUpdateTime(LocalDateTime.now());
                industryStandardInfoMapper.updateById(updateObj);
                updatedCount++;
                log.info("[autoUpdateStandardStatus][更新规范状态：ID={}, 原状态={}, 新状态={}]",
                        standard.getId(), standard.getStandardStatus(), newStatus);
            }
        }

        log.info("[autoUpdateStandardStatus][自动更新规范状态完成：共更新{}条记录]", updatedCount);
    }

    @Override
    public Long createStandardReplace(IndustryStandardInfoCreateReplaceReqVO createReplaceReqVO) {
        // 校验原规范存在
        IndustryStandardInfoDO originalStandard = validateIndustryStandardInfoExists(createReplaceReqVO.getOriginalId());
        // 校验新规范编号格式
        validateStandardNoFormatInternal(createReplaceReqVO.getStandardNo());
        // 校验新规范编号唯一性
        validateStandardNoUnique(createReplaceReqVO.getStandardNo(), null);

        // 创建替代版本
        IndustryStandardInfoDO replaceStandard = BeanUtils.toBean(createReplaceReqVO, IndustryStandardInfoDO.class);
        replaceStandard.setStandardId(generateStandardId());
        replaceStandard.setStandardStatus(calculateInitialStatus(createReplaceReqVO.getImplementationTime()));
        replaceStandard.setUploadUserId(getCurrentUserId());
        replaceStandard.setUploadTime(LocalDateTime.now());
        replaceStandard.setUpdateUserId(getCurrentUserId());
        replaceStandard.setUpdateTime(LocalDateTime.now());

        industryStandardInfoMapper.insert(replaceStandard);

        // 标记原规范为已替代
        IndustryStandardInfoDO updateOriginal = new IndustryStandardInfoDO();
        updateOriginal.setId(createReplaceReqVO.getOriginalId());
        updateOriginal.setStandardStatus("已替代");
        updateOriginal.setReplaceOldNo(createReplaceReqVO.getStandardNo()); // 记录被哪个规范替代
        updateOriginal.setUpdateTime(LocalDateTime.now());
        industryStandardInfoMapper.updateById(updateOriginal);

        log.info("[createStandardReplace][创建规范替代版本成功：原规范ID={}, 新规范编号={}]",
                createReplaceReqVO.getOriginalId(), createReplaceReqVO.getStandardNo());
        return replaceStandard.getId();
    }

    @Override
    public List<IndustryStandardInfoDO> getIndustryStandardInfoListByIndustryField(String industryField) {
        return industryStandardInfoMapper.selectList(new LambdaQueryWrapperX<IndustryStandardInfoDO>()
                .eq(IndustryStandardInfoDO::getIndustryField, industryField)
                .orderByDesc(IndustryStandardInfoDO::getIssueTime));
    }

    @Override
    public List<IndustryStandardInfoDO> getIndustryStandardInfoListByBusinessModule(String businessModule) {
        return industryStandardInfoMapper.selectList(new LambdaQueryWrapperX<IndustryStandardInfoDO>()
                .like(IndustryStandardInfoDO::getRelatedBusinessModule, businessModule)
                .eq(IndustryStandardInfoDO::getStandardStatus, "实施中")
                .orderByDesc(IndustryStandardInfoDO::getIssueTime));
    }

    @Override
    public List<IndustryStandardInfoDO> getStandardReplaceVersions(String standardNo) {
        return industryStandardInfoMapper.selectList(new LambdaQueryWrapperX<IndustryStandardInfoDO>()
                .like(IndustryStandardInfoDO::getStandardNo, standardNo)
                .orderByDesc(IndustryStandardInfoDO::getImplementationTime));
    }

    @Override
    public List<String> getInterpretationFiles(Long id) {
        IndustryStandardInfoDO industryStandardInfo = validateIndustryStandardInfoExists(id);
        if (industryStandardInfo.getInterpretationFilePath() == null ||
                industryStandardInfo.getInterpretationFilePath().trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(industryStandardInfo.getInterpretationFilePath().split(";"));
    }

    // ========== 私有方法 ==========

    /**
     * 校验行业规范信息是否存在
     */
    private IndustryStandardInfoDO validateIndustryStandardInfoExists(Long id) {
        IndustryStandardInfoDO industryStandardInfo = industryStandardInfoMapper.selectById(id);
        if (industryStandardInfo == null) {
            throw exception(INDUSTRY_STANDARD_INFO_NOT_EXISTS);
        }
        return industryStandardInfo;
    }

    /**
     * 校验规范编号唯一性
     */
    private void validateStandardNoUnique(String standardNo, Long id) {
        if (!checkStandardNoUnique(standardNo, id)) {
            throw exception(INDUSTRY_STANDARD_INFO_NO_DUPLICATE);
        }
    }

    /**
     * 校验规范编号格式（内部方法，解决冲突）
     */
    private void validateStandardNoFormatInternal(String standardNo) {
        if (!validateStandardNoFormat(standardNo)) {
            throw exception(INDUSTRY_STANDARD_INFO_FORMAT_INVALID);
        }
    }

    /**
     * 校验时间逻辑
     */
    private void validateTimeLogic(LocalDate implementationTime) {
        LocalDate today = LocalDate.now();
        if (implementationTime.isBefore(today.minusDays(1))) {
            throw exception(INDUSTRY_STANDARD_INFO_TIME_INVALID);
        }
    }

    /**
     * 校验是否可以更新（仅未实施状态可更新）
     */
    private void validateCanUpdate(String standardStatus) {
        if (!"未实施".equals(standardStatus)) {
            throw exception(INDUSTRY_STANDARD_INFO_CANNOT_UPDATE);
        }
    }

    /**
     * 校验是否可以删除（仅未实施状态可删除）
     */
    private void validateCanDelete(String standardStatus) {
        if (!"未实施".equals(standardStatus)) {
            throw exception(INDUSTRY_STANDARD_INFO_CANNOT_DELETE);
        }
    }

    /**
     * 生成规范ID（UUID）
     */
    private String generateStandardId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 计算初始状态
     */
    private String calculateInitialStatus(LocalDate implementationTime) {
        LocalDate today = LocalDate.now();
        if (implementationTime.isAfter(today)) {
            return "未实施";
        } else {
            return "实施中";
        }
    }

    /**
     * 计算规范状态
     */
    private String calculateStatus(LocalDate implementationTime, String replaceOldNo) {
        LocalDate today = LocalDate.now();

        if (implementationTime.isAfter(today)) {
            return "未实施";
        } else if (replaceOldNo != null && !replaceOldNo.trim().isEmpty()) {
            return "已替代";
        } else {
            return "实施中";
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