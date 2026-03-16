package cn.iocoder.yudao.module.evaluate.service.calcway;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway.vo.CalcWayPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway.vo.CalcWaySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.calcway.CalcWayDO;
import jakarta.validation.Valid;

/**
 * 计算方式字典 Service 接口
 *
 * @author 亘川智城
 */
public interface CalcWayService {

    /**
     * 创建计算方式字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCalcWay(@Valid CalcWaySaveReqVO createReqVO);

    /**
     * 更新计算方式字典
     *
     * @param updateReqVO 更新信息
     */
    void updateCalcWay(@Valid CalcWaySaveReqVO updateReqVO);

    /**
     * 删除计算方式字典
     *
     * @param id 编号
     */
    void deleteCalcWay(Long id);

    /**
     * 获得计算方式字典
     *
     * @param id 编号
     * @return 计算方式字典
     */
    CalcWayDO getCalcWay(Long id);

    /**
     * 获得计算方式字典分页
     *
     * @param pageReqVO 分页查询
     * @return 计算方式字典分页
     */
    PageResult<CalcWayDO> getCalcWayPage(CalcWayPageReqVO pageReqVO);

}