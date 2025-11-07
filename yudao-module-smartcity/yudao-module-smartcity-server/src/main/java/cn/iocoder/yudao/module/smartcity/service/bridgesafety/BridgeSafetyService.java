package cn.iocoder.yudao.module.smartcity.service.bridgesafety;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.bridgesafety.vo.BridgeSafetyPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.bridgesafety.vo.BridgeSafetySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.bridgesafety.BridgeSafetyDO;
import jakarta.validation.Valid;

/**
 * 桥梁安全 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface BridgeSafetyService {

    /**
     * 创建桥梁安全
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBridgeSafety(@Valid BridgeSafetySaveReqVO createReqVO);

    /**
     * 更新桥梁安全
     *
     * @param updateReqVO 更新信息
     */
    void updateBridgeSafety(@Valid BridgeSafetySaveReqVO updateReqVO);

    /**
     * 删除桥梁安全
     *
     * @param id 编号
     */
    void deleteBridgeSafety(Long id);

    /**
     * 获得桥梁安全
     *
     * @param id 编号
     * @return 桥梁安全
     */
    BridgeSafetyDO getBridgeSafety(Long id);

    /**
     * 获得桥梁安全分页
     *
     * @param pageReqVO 分页查询
     * @return 桥梁安全分页
     */
    PageResult<BridgeSafetyDO> getBridgeSafetyPage(BridgeSafetyPageReqVO pageReqVO);

}