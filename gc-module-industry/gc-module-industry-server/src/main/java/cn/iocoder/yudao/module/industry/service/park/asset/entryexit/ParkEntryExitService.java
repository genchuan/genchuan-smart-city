package cn.iocoder.yudao.module.industry.service.park.asset.entryexit;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo.ParkEntryExitPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo.ParkEntryExitSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.entryexit.ParkEntryExitDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 出入口信息 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkEntryExitService {

    /**
     * 创建出入口信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkEntryExit(@Valid ParkEntryExitSaveReqVO createReqVO);

    /**
     * 更新出入口信息
     *
     * @param updateReqVO 更新信息
     */
    void updateParkEntryExit(@Valid ParkEntryExitSaveReqVO updateReqVO);

    /**
     * 删除出入口信息
     *
     * @param id 编号
     */
    void deleteParkEntryExit(Long id);

    /**
     * 获得出入口信息
     *
     * @param id 编号
     * @return 出入口信息
     */
    ParkEntryExitDO getParkEntryExit(Long id);

    /**
     * 获得出入口信息分页
     *
     * @param pageReqVO 分页查询
     * @return 出入口信息分页
     */
    PageResult<ParkEntryExitDO> getParkEntryExitPage(ParkEntryExitPageReqVO pageReqVO);

}