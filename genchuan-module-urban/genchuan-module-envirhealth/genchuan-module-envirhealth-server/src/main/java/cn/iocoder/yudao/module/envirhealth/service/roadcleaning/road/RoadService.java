package cn.iocoder.yudao.module.envirhealth.service.roadcleaning.road;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road.RoadPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road.RoadSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 道路 Service 接口
 *
 * @author 芋道源码
 */
public interface RoadService {

    /**
     * 创建道路
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoad(@Valid RoadSaveReqVO createReqVO);

    /**
     * 更新道路
     *
     * @param updateReqVO 更新信息
     */
    void updateRoad(@Valid RoadSaveReqVO updateReqVO);

    /**
     * 删除道路
     *
     * @param id 编号
     */
    void deleteRoad(Long id);

    /**
     * 获得道路
     *
     * @param id 编号
     * @return 道路
     */
    RoadDO getRoad(Long id);

    /**
     * 获得道路分页
     *
     * @param pageReqVO 分页查询
     * @return 道路分页
     */
    PageResult<RoadDO> getRoadPage(RoadPageReqVO pageReqVO);

    /**
     * 获得道路名称下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getRoadNameOptions();
}