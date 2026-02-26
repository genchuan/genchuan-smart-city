package cn.iocoder.yudao.module.envirhealth.service.roadcleaning.roadcleaning;

import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.RoadCleaningPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.RoadCleaningSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.RoadCleaningDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadCleaningDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 道路清扫计划 Service 接口
 *
 * @author 芋道源码
 */
public interface RoadCleaningService {

    /**
     * 创建道路清扫计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoadCleaning(@Valid RoadCleaningSaveReqVO createReqVO);

    /**
     * 更新道路清扫计划
     *
     * @param updateReqVO 更新信息
     */
    void updateRoadCleaning(@Valid RoadCleaningSaveReqVO updateReqVO);

    /**
     * 删除道路清扫计划
     *
     * @param id 编号
     */
    void deleteRoadCleaning(Long id);

    /**
     * 获得道路清扫计划
     *
     * @param id 编号
     * @return 道路清扫计划
     */
    RoadCleaningDO getRoadCleaning(Long id);

    /**
     * 获得道路清扫计划分页
     *
     * @param pageReqVO 分页查询
     * @return 道路清扫计划分页
     */
    PageResult<RoadCleaningDO> getRoadCleaningPage(RoadCleaningPageReqVO pageReqVO);

    PageResult<RoadCleaningDetailDO> getRoadCleaningDetailPage(RoadCleaningPageReqVO pageReqVO);

}