package cn.iocoder.yudao.module.smartcity.service.lawenforcement;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement.vo.LawEnforcementPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcement.vo.LawEnforcementSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcement.LawEnforcementDO;
import jakarta.validation.Valid;

/**
 * 城市管理执法 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface LawEnforcementService {

    /**
     * 创建城市管理执法
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLawEnforcement(@Valid LawEnforcementSaveReqVO createReqVO);

    /**
     * 更新城市管理执法
     *
     * @param updateReqVO 更新信息
     */
    void updateLawEnforcement(@Valid LawEnforcementSaveReqVO updateReqVO);

    /**
     * 删除城市管理执法
     *
     * @param id 编号
     */
    void deleteLawEnforcement(Long id);

    /**
     * 获得城市管理执法
     *
     * @param id 编号
     * @return 城市管理执法
     */
    LawEnforcementDO getLawEnforcement(Long id);

    /**
     * 获得城市管理执法分页
     *
     * @param pageReqVO 分页查询
     * @return 城市管理执法分页
     */
    PageResult<LawEnforcementDO> getLawEnforcementPage(LawEnforcementPageReqVO pageReqVO);

}