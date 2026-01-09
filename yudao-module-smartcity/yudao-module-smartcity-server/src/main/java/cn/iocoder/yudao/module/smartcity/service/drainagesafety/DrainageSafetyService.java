package cn.iocoder.yudao.module.smartcity.service.drainagesafety;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety.vo.DrainageSafetyPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagesafety.vo.DrainageSafetySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagesafety.DrainageSafetyDO;
import jakarta.validation.Valid;

/**
 * 排水安全 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface DrainageSafetyService {

    /**
     * 创建排水安全
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDrainageSafety(@Valid DrainageSafetySaveReqVO createReqVO);

    /**
     * 更新排水安全
     *
     * @param updateReqVO 更新信息
     */
    void updateDrainageSafety(@Valid DrainageSafetySaveReqVO updateReqVO);

    /**
     * 删除排水安全
     *
     * @param id 编号
     */
    void deleteDrainageSafety(Long id);

    /**
     * 获得排水安全
     *
     * @param id 编号
     * @return 排水安全
     */
    DrainageSafetyDO getDrainageSafety(Long id);

    /**
     * 获得排水安全分页
     *
     * @param pageReqVO 分页查询
     * @return 排水安全分页
     */
    PageResult<DrainageSafetyDO> getDrainageSafetyPage(DrainageSafetyPageReqVO pageReqVO);

}