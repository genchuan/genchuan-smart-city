package cn.iocoder.yudao.module.smartcity.service.environmentalsafety;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo.EnvironmentalSafetyPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo.EnvironmentalSafetySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsafety.EnvironmentalSafetyDO;
import jakarta.validation.Valid;

/**
 * 环卫设施安全 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface EnvironmentalSafetyService {

    /**
     * 创建环卫设施安全
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEnvironmentalSafety(@Valid EnvironmentalSafetySaveReqVO createReqVO);

    /**
     * 更新环卫设施安全
     *
     * @param updateReqVO 更新信息
     */
    void updateEnvironmentalSafety(@Valid EnvironmentalSafetySaveReqVO updateReqVO);

    /**
     * 删除环卫设施安全
     *
     * @param id 编号
     */
    void deleteEnvironmentalSafety(Long id);

    /**
     * 获得环卫设施安全
     *
     * @param id 编号
     * @return 环卫设施安全
     */
    EnvironmentalSafetyDO getEnvironmentalSafety(Long id);

    /**
     * 获得环卫设施安全分页
     *
     * @param pageReqVO 分页查询
     * @return 环卫设施安全分页
     */
    PageResult<EnvironmentalSafetyDO> getEnvironmentalSafetyPage(EnvironmentalSafetyPageReqVO pageReqVO);

}