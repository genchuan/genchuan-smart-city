package cn.iocoder.yudao.module.facility.service.road.roadwarn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadwarn.RoadWarnDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 预警 Service 接口
 *
 * @author 亘川智城
 */
public interface RoadWarnService {

    /**
     * 创建预警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    List<Long> createWarn(@Valid RoadWarnSaveReqVO createReqVO);

    /**
     * 更新预警
     *
     * @param updateReqVO 更新信息
     */
    void updateWarn(@Valid WarnSaveReqVO updateReqVO);

    /**
     * 删除预警
     *
     * @param id 编号
     */
    void deleteWarn(Long id);

    /**
     * 获得预警
     *
     * @param id 编号
     * @return 预警
     */
    RoadWarnDO getWarn(Long id);

    /**
     * 获得预警分页
     *
     * @param pageReqVO 分页查询
     * @return 预警分页
     */
    PageResult<RoadWarnDO> getWarnPage(WarnPageReqVO pageReqVO);

    PageResult<RoadWarnPageRespVO> pageRoadWarn(RoadWarnPageReqVO reqVO);
}
