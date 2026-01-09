package cn.iocoder.yudao.module.smartcity.service.roadcollapsesafety;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.roadcollapsesafety.vo.RoadCollapseSafetyPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.roadcollapsesafety.vo.RoadCollapseSafetySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.roadcollapsesafety.RoadCollapseSafetyDO;
import jakarta.validation.Valid;

/**
 * 路面塌陷安全 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface RoadCollapseSafetyService {

    /**
     * 创建路面塌陷安全
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoadCollapseSafety(@Valid RoadCollapseSafetySaveReqVO createReqVO);

    /**
     * 更新路面塌陷安全
     *
     * @param updateReqVO 更新信息
     */
    void updateRoadCollapseSafety(@Valid RoadCollapseSafetySaveReqVO updateReqVO);

    /**
     * 删除路面塌陷安全
     *
     * @param id 编号
     */
    void deleteRoadCollapseSafety(Long id);

    /**
     * 获得路面塌陷安全
     *
     * @param id 编号
     * @return 路面塌陷安全
     */
    RoadCollapseSafetyDO getRoadCollapseSafety(Long id);

    /**
     * 获得路面塌陷安全分页
     *
     * @param pageReqVO 分页查询
     * @return 路面塌陷安全分页
     */
    PageResult<RoadCollapseSafetyDO> getRoadCollapseSafetyPage(RoadCollapseSafetyPageReqVO pageReqVO);

}