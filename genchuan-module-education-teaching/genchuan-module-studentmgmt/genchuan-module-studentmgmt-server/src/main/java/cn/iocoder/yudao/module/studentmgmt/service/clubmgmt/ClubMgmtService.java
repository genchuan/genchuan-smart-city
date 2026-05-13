package cn.iocoder.yudao.module.studentmgmt.service.clubmgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.clubmgmt.ClubMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 社团管理 Service 接口
 *
 * @author 芋道源码
 */
public interface ClubMgmtService {

    /**
     * 创建社团管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createClubMgmt(@Valid ClubMgmtSaveReqVO createReqVO);

    /**
     * 更新社团管理
     *
     * @param updateReqVO 更新信息
     */
    void updateClubMgmt(@Valid ClubMgmtSaveReqVO updateReqVO);

    /**
     * 删除社团管理
     *
     * @param id 编号
     */
    void deleteClubMgmt(Long id);

    /**
    * 批量删除社团管理
    *
    * @param ids 编号
    */
    void deleteClubMgmtListByIds(List<Long> ids);

    /**
     * 获得社团管理
     *
     * @param id 编号
     * @return 社团管理
     */
    ClubMgmtDO getClubMgmt(Long id);

    /**
     * 获得社团管理分页
     *
     * @param pageReqVO 分页查询
     * @return 社团管理分页
     */
    PageResult<ClubMgmtDO> getClubMgmtPage(ClubMgmtPageReqVO pageReqVO);

    boolean audit(@Valid ClubMgmtAuditReqVO reqVO);

    boolean archive(@Valid ClubMgmtArchiveReqVO reqVO);

    ClubMgmtChartRespVO chart(@Valid ClubMgmtChartReqVO reqVO);

    ClubMgmtClubDistributionRespVO clubDistribution(@Valid ClubMgmtChartReqVO reqVO);

    boolean venueApply(@Valid ClubMgmtVenueApplyReqVO reqVO);

    PageResult<ClubMgmtRespVO> getClubMgmtJoinPage(@Valid ClubMgmtPageReqVO pageReqVO);
}