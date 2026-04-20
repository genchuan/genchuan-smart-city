package cn.iocoder.yudao.module.studentmgmt.service.violatemgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 违纪管理 Service 接口
 *
 * @author 芋道源码
 */
public interface ViolateMgmtService {

    /**
     * 创建违纪管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createViolateMgmt(@Valid ViolateMgmtSaveReqVO createReqVO);

    /**
     * 更新违纪管理
     *
     * @param updateReqVO 更新信息
     */
    void updateViolateMgmt(@Valid ViolateMgmtSaveReqVO updateReqVO);

    /**
     * 删除违纪管理
     *
     * @param id 编号
     */
    void deleteViolateMgmt(Long id);

    /**
    * 批量删除违纪管理
    *
    * @param ids 编号
    */
    void deleteViolateMgmtListByIds(List<Long> ids);

    /**
     * 获得违纪管理
     *
     * @param id 编号
     * @return 违纪管理
     */
    ViolateMgmtDO getViolateMgmt(Long id);

    /**
     * 获得违纪管理分页
     *
     * @param pageReqVO 分页查询
     * @return 违纪管理分页
     */
    PageResult<ViolateMgmtDO> getViolateMgmtPage(ViolateMgmtPageReqVO pageReqVO);

    boolean auditViolateMgmtListByIds(List<Long> ids, Long userId);

    Boolean push(Long id, Long userId);

    PageResult<ViolateMgmtPageRespVO> getViolateMgmtPageVo(@Valid ViolateMgmtPageReqVO pageReqVO);

    Boolean warn(Long id, Long userId);

    ViolateDashboardVO chart(@Valid ViolateChartReqVO reqVO);

    ViolateCountDashboardVO violateCount(@Valid ViolateChartReqVO reqVO);

}