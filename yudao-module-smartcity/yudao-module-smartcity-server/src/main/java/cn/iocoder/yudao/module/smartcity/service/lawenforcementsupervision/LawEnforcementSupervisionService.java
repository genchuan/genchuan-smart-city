package cn.iocoder.yudao.module.smartcity.service.lawenforcementsupervision;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo.LawEnforcementSupervisionPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementsupervision.vo.LawEnforcementSupervisionSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementsupervision.LawEnforcementSupervisionDO;
import jakarta.validation.Valid;

/**
 * 执法监督 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface LawEnforcementSupervisionService {

    /**
     * 创建执法监督
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLawEnforcementSupervision(@Valid LawEnforcementSupervisionSaveReqVO createReqVO);

    /**
     * 更新执法监督
     *
     * @param updateReqVO 更新信息
     */
    void updateLawEnforcementSupervision(@Valid LawEnforcementSupervisionSaveReqVO updateReqVO);

    /**
     * 删除执法监督
     *
     * @param id 编号
     */
    void deleteLawEnforcementSupervision(Long id);

    /**
     * 获得执法监督
     *
     * @param id 编号
     * @return 执法监督
     */
    LawEnforcementSupervisionDO getLawEnforcementSupervision(Long id);

    /**
     * 获得执法监督分页
     *
     * @param pageReqVO 分页查询
     * @return 执法监督分页
     */
    PageResult<LawEnforcementSupervisionDO> getLawEnforcementSupervisionPage(LawEnforcementSupervisionPageReqVO pageReqVO);

}