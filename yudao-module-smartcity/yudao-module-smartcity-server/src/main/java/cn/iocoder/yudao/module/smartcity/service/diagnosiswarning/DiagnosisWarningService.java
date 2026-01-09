package cn.iocoder.yudao.module.smartcity.service.diagnosiswarning;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.diagnosiswarning.vo.DiagnosisWarningPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.diagnosiswarning.vo.DiagnosisWarningSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.diagnosiswarning.DiagnosisWarningDO;
import jakarta.validation.Valid;

/**
 * 故障诊断和预警 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface DiagnosisWarningService {

    /**
     * 创建故障诊断和预警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDiagnosisWarning(@Valid DiagnosisWarningSaveReqVO createReqVO);

    /**
     * 更新故障诊断和预警
     *
     * @param updateReqVO 更新信息
     */
    void updateDiagnosisWarning(@Valid DiagnosisWarningSaveReqVO updateReqVO);

    /**
     * 删除故障诊断和预警
     *
     * @param id 编号
     */
    void deleteDiagnosisWarning(Long id);

    /**
     * 获得故障诊断和预警
     *
     * @param id 编号
     * @return 故障诊断和预警
     */
    DiagnosisWarningDO getDiagnosisWarning(Long id);

    /**
     * 获得故障诊断和预警分页
     *
     * @param pageReqVO 分页查询
     * @return 故障诊断和预警分页
     */
    PageResult<DiagnosisWarningDO> getDiagnosisWarningPage(DiagnosisWarningPageReqVO pageReqVO);

}