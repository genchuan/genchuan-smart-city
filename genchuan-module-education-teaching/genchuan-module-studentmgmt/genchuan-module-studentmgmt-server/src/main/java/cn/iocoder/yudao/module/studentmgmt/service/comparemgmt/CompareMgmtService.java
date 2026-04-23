package cn.iocoder.yudao.module.studentmgmt.service.comparemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.comparemgmt.CompareMgmtDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 评比管理 Service 接口
 *
 * @author 芋道源码
 */
public interface CompareMgmtService {

    /**
     * 创建评比管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompareMgmt(@Valid CompareMgmtSaveReqVO createReqVO);

    /**
     * 更新评比管理
     *
     * @param updateReqVO 更新信息
     * @return
     */
    boolean updateCompareMgmt(@Valid CompareMgmtUpdateReqVO updateReqVO);

    /**
     * 删除评比管理
     *
     * @param id 编号
     */
    void deleteCompareMgmt(Long id);

    /**
    * 批量删除评比管理
    *
    * @param ids 编号
    */
    void deleteCompareMgmtListByIds(List<Long> ids);

    /**
     * 获得评比管理
     *
     * @param id 编号
     * @return 评比管理
     */
    CompareMgmtDO getCompareMgmt(Long id);

    /**
     * 获得评比管理分页
     *
     * @param pageReqVO 分页查询
     * @return 评比管理分页
     */
    PageResult<CompareMgmtDO> getCompareMgmtPage(CompareMgmtPageReqVO pageReqVO);

    boolean score(@Valid CompareMgmtScoreReqVO reqVO);

    boolean award(@Valid CompareMgmtAwardReqVO reqVO);

    CompareMgmtChartRespVO chart(@Valid CompareMgmtChartReqVO reqVO);

    CompareMgmtScoreRankRespVO scoreRank(@Valid CompareMgmtChartReqVO reqVO);
}