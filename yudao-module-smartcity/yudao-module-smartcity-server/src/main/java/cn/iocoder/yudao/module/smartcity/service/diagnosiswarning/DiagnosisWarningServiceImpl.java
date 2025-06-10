package cn.iocoder.yudao.module.smartcity.service.diagnosiswarning;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.diagnosiswarning.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.diagnosiswarning.DiagnosisWarningDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.diagnosiswarning.DiagnosisWarningMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 故障诊断和预警 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class DiagnosisWarningServiceImpl implements DiagnosisWarningService {

    @Resource
    private DiagnosisWarningMapper diagnosisWarningMapper;

    @Override
    public Long createDiagnosisWarning(DiagnosisWarningSaveReqVO createReqVO) {
        // 插入
        DiagnosisWarningDO diagnosisWarning = BeanUtils.toBean(createReqVO, DiagnosisWarningDO.class);
        diagnosisWarningMapper.insert(diagnosisWarning);
        // 返回
        return diagnosisWarning.getId();
    }

    @Override
    public void updateDiagnosisWarning(DiagnosisWarningSaveReqVO updateReqVO) {
        // 校验存在
        validateDiagnosisWarningExists(updateReqVO.getId());
        // 更新
        DiagnosisWarningDO updateObj = BeanUtils.toBean(updateReqVO, DiagnosisWarningDO.class);
        diagnosisWarningMapper.updateById(updateObj);
    }

    @Override
    public void deleteDiagnosisWarning(Long id) {
        // 校验存在
        validateDiagnosisWarningExists(id);
        // 删除
        diagnosisWarningMapper.deleteById(id);
    }

    private void validateDiagnosisWarningExists(Long id) {
        if (diagnosisWarningMapper.selectById(id) == null) {
            throw exception(DIAGNOSIS_WARNING_NOT_EXISTS);
        }
    }

    @Override
    public DiagnosisWarningDO getDiagnosisWarning(Long id) {
        return diagnosisWarningMapper.selectById(id);
    }

    @Override
    public PageResult<DiagnosisWarningDO> getDiagnosisWarningPage(DiagnosisWarningPageReqVO pageReqVO) {
        return diagnosisWarningMapper.selectPage(pageReqVO);
    }

}