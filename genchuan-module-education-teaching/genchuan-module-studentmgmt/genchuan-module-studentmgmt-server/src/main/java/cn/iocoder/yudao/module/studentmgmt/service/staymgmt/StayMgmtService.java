package cn.iocoder.yudao.module.studentmgmt.service.staymgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.staymgmt.StayMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 留宿管理 Service 接口
 *
 * @author 芋道源码
 */
public interface StayMgmtService {

    /**
     * 创建留宿管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStayMgmt(@Valid StayMgmtSaveReqVO createReqVO);

    /**
     * 更新留宿管理
     *
     * @param updateReqVO 更新信息
     */
    void updateStayMgmt(@Valid StayMgmtSaveReqVO updateReqVO);

    /**
     * 删除留宿管理
     *
     * @param id 编号
     */
    void deleteStayMgmt(Long id);

    /**
    * 批量删除留宿管理
    *
    * @param ids 编号
    */
    void deleteStayMgmtListByIds(List<Long> ids);

    /**
     * 获得留宿管理
     *
     * @param id 编号
     * @return 留宿管理
     */
    StayMgmtDO getStayMgmt(Long id);

    /**
     * 获得留宿管理分页
     *
     * @param pageReqVO 分页查询
     * @return 留宿管理分页
     */
    PageResult<StayMgmtDO> getStayMgmtPage(StayMgmtPageReqVO pageReqVO);

    Boolean confirm(@Valid StayMgmtConfirmReqVO reqVO);

    Boolean audit(@Valid StayMgmtConfirmReqVO reqVO);

    StayMgmtChartRespVO chart(@Valid StayMgmtChartReqVO reqVO);

    StayMgmtStayCountRespVO stayCount(@Valid StayMgmtStayCountReqVO reqVO);
}