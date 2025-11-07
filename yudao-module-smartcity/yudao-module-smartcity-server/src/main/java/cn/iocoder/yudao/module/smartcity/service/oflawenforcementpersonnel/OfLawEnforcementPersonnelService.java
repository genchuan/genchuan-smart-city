package cn.iocoder.yudao.module.smartcity.service.oflawenforcementpersonnel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.oflawenforcementpersonnel.vo.OfLawEnforcementPersonnelPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.oflawenforcementpersonnel.vo.OfLawEnforcementPersonnelSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.oflawenforcementpersonnel.OfLawEnforcementPersonnelDO;
import jakarta.validation.Valid;

/**
 * 执法人员管理 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface OfLawEnforcementPersonnelService {

    /**
     * 创建执法人员管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOfLawEnforcementPersonnel(@Valid OfLawEnforcementPersonnelSaveReqVO createReqVO);

    /**
     * 更新执法人员管理
     *
     * @param updateReqVO 更新信息
     */
    void updateOfLawEnforcementPersonnel(@Valid OfLawEnforcementPersonnelSaveReqVO updateReqVO);

    /**
     * 删除执法人员管理
     *
     * @param id 编号
     */
    void deleteOfLawEnforcementPersonnel(Long id);

    /**
     * 获得执法人员管理
     *
     * @param id 编号
     * @return 执法人员管理
     */
    OfLawEnforcementPersonnelDO getOfLawEnforcementPersonnel(Long id);

    /**
     * 获得执法人员管理分页
     *
     * @param pageReqVO 分页查询
     * @return 执法人员管理分页
     */
    PageResult<OfLawEnforcementPersonnelDO> getOfLawEnforcementPersonnelPage(OfLawEnforcementPersonnelPageReqVO pageReqVO);

}