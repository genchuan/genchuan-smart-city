package cn.iocoder.yudao.module.smartcity.service.enforcementpublicity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity.vo.EnforcementPublicityPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity.vo.EnforcementPublicitySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.enforcementpublicity.EnforcementPublicityDO;
import jakarta.validation.Valid;

/**
 * 执法公示 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface EnforcementPublicityService {

    /**
     * 创建执法公示
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEnforcementPublicity(@Valid EnforcementPublicitySaveReqVO createReqVO);

    /**
     * 更新执法公示
     *
     * @param updateReqVO 更新信息
     */
    void updateEnforcementPublicity(@Valid EnforcementPublicitySaveReqVO updateReqVO);

    /**
     * 删除执法公示
     *
     * @param id 编号
     */
    void deleteEnforcementPublicity(Long id);

    /**
     * 获得执法公示
     *
     * @param id 编号
     * @return 执法公示
     */
    EnforcementPublicityDO getEnforcementPublicity(Long id);

    /**
     * 获得执法公示分页
     *
     * @param pageReqVO 分页查询
     * @return 执法公示分页
     */
    PageResult<EnforcementPublicityDO> getEnforcementPublicityPage(EnforcementPublicityPageReqVO pageReqVO);

}