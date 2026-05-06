package cn.iocoder.yudao.module.studentmgmt.service.promotemgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.promotemgmt.PromoteMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 宣传管理 Service 接口
 *
 * @author 芋道源码
 */
public interface PromoteMgmtService {

    /**
     * 创建宣传管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPromoteMgmt(@Valid PromoteMgmtSaveReqVO createReqVO);

    /**
     * 更新宣传管理
     *
     * @param updateReqVO 更新信息
     */
    void updatePromoteMgmt(@Valid PromoteMgmtSaveReqVO updateReqVO);

    /**
     * 删除宣传管理
     *
     * @param id 编号
     */
    void deletePromoteMgmt(Long id);

    /**
    * 批量删除宣传管理
    *
    * @param ids 编号
    */
    void deletePromoteMgmtListByIds(List<Long> ids);

    /**
     * 获得宣传管理
     *
     * @param id 编号
     * @return 宣传管理
     */
    PromoteMgmtDO getPromoteMgmt(Long id);

    /**
     * 获得宣传管理分页
     *
     * @param pageReqVO 分页查询
     * @return 宣传管理分页
     */
    PageResult<PromoteMgmtDO> getPromoteMgmtPage(PromoteMgmtPageReqVO pageReqVO);

    Boolean execute(@Valid PromoteMgmtExecuteReqVO reqVO);

    PromoteMgmtChartRespVO chart(@Valid PromoteMgmtChartReqVO reqVO);

    PromoteCountReqVO promoteCount(@Valid PromoteMgmtChartReqVO reqVO);
}