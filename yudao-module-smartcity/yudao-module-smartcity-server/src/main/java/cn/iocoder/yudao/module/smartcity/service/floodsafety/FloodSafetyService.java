package cn.iocoder.yudao.module.smartcity.service.floodsafety;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety.vo.FloodSafetyPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety.vo.FloodSafetySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.floodsafety.FloodSafetyDO;
import jakarta.validation.Valid;

/**
 * 内涝安全 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface FloodSafetyService {

    /**
     * 创建内涝安全
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFloodSafety(@Valid FloodSafetySaveReqVO createReqVO);

    /**
     * 更新内涝安全
     *
     * @param updateReqVO 更新信息
     */
    void updateFloodSafety(@Valid FloodSafetySaveReqVO updateReqVO);

    /**
     * 删除内涝安全
     *
     * @param id 编号
     */
    void deleteFloodSafety(Long id);

    /**
     * 获得内涝安全
     *
     * @param id 编号
     * @return 内涝安全
     */
    FloodSafetyDO getFloodSafety(Long id);

    /**
     * 获得内涝安全分页
     *
     * @param pageReqVO 分页查询
     * @return 内涝安全分页
     */
    PageResult<FloodSafetyDO> getFloodSafetyPage(FloodSafetyPageReqVO pageReqVO);

}