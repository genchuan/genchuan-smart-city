package cn.iocoder.yudao.module.studentmgmt.service.bedmgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt.BedMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 床位管理 Service 接口
 *
 * @author 芋道源码
 */
public interface BedMgmtService {

    /**
     * 创建床位管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBedMgmt(@Valid BedMgmtSaveReqVO createReqVO);

    /**
     * 更新床位管理
     *
     * @param updateReqVO 更新信息
     */
    void updateBedMgmt(@Valid BedMgmtSaveReqVO updateReqVO);

    /**
     * 删除床位管理
     *
     * @param id 编号
     */
    void deleteBedMgmt(Long id);

    /**
    * 批量删除床位管理
    *
    * @param ids 编号
    */
    void deleteBedMgmtListByIds(List<Long> ids);

    /**
     * 获得床位管理
     *
     * @param id 编号
     * @return 床位管理
     */
    BedMgmtDO getBedMgmt(Long id);

    /**
     * 获得床位管理分页
     *
     * @param pageReqVO 分页查询
     * @return 床位管理分页
     */
    PageResult<BedMgmtDO> getBedMgmtPage(BedMgmtPageReqVO pageReqVO);

    boolean assign(@Valid BedMgmtAssignReqVO reqVO);

    boolean adjust(@Valid BedMgmtAdjustReqVO reqVO);

    BedMgmtChartRespVO chart(@Valid BedMgmtChartReqVO reqVO);

    BedMgmtBedDistributionRespVO bedDistribution();

    BedMgmtBedIndexRespVO bedIndex();
}