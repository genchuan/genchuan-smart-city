package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.point;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.PointDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 点位 Service 接口
 *
 * @author 芋道源码
 */
public interface PointService {

    /**
     * 创建点位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPoint(@Valid PointSaveReqVO createReqVO);

    /**
     * 更新点位
     *
     * @param updateReqVO 更新信息
     */
    void updatePoint(@Valid PointSaveReqVO updateReqVO);

    /**
     * 删除点位
     *
     * @param id 编号
     */
    void deletePoint(Long id);

    /**
     * 获得点位
     *
     * @param id 编号
     * @return 点位
     */
    PointDO getPoint(Long id);

    /**
     * 获得点位分页
     *
     * @param pageReqVO 分页查询
     * @return 点位分页
     */
    PageResult<PointDO> getPointPage(PointPageReqVO pageReqVO);

}