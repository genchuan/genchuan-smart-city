package cn.iocoder.yudao.module.studentmgmt.service.checkin;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.checkin.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.checkin.CheckInDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 报到管理 Service 接口
 *
 * @author 芋道源码
 */
public interface CheckInService {

    /**
     * 创建报到管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCheckIn(@Valid CheckInSaveReqVO createReqVO);

    /**
     * 更新报到管理
     *
     * @param updateReqVO 更新信息
     */
    void updateCheckIn(@Valid CheckInSaveReqVO updateReqVO);

    /**
     * 删除报到管理
     *
     * @param id 编号
     */
    void deleteCheckIn(Long id);

    /**
    * 批量删除报到管理
    *
    * @param ids 编号
    */
    void deleteCheckInListByIds(List<Long> ids);

    /**
     * 获得报到管理
     *
     * @param id 编号
     * @return 报到管理
     */
    CheckInDO getCheckIn(Long id);

    /**
     * 获得报到管理分页
     *
     * @param pageReqVO 分页查询
     * @return 报到管理分页
     */
    PageResult<CheckInDO> getCheckInPage(CheckInPageReqVO pageReqVO);

    Boolean supply(@Valid CheckInSupplyReqVO reqVO);

    Boolean confirm(@Valid CheckInConfirmReqVO reqVO);

    Boolean audit(@Valid CheckInConfirmReqVO reqVO);

    CheckInChartRespVO chart(@Valid CheckInChartReqVO reqVO);

    CheckInChartIndexRespVO checkinIndex(@Valid CheckInChartReqVO reqVO);
}