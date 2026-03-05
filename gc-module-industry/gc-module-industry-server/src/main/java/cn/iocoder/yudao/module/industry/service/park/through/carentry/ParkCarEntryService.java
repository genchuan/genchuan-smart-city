package cn.iocoder.yudao.module.industry.service.park.through.carentry;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo.ParkCarEntryPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo.ParkCarEntrySaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carentry.ParkCarEntryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 入场记录 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkCarEntryService {

    /**
     * 创建入场记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkCarEntry(@Valid ParkCarEntrySaveReqVO createReqVO);

    /**
     * 更新入场记录
     *
     * @param updateReqVO 更新信息
     */
    void updateParkCarEntry(@Valid ParkCarEntrySaveReqVO updateReqVO);

    /**
     * 删除入场记录
     *
     * @param id 编号
     */
    void deleteParkCarEntry(Long id);

    /**
     * 获得入场记录
     *
     * @param id 编号
     * @return 入场记录
     */
    ParkCarEntryDO getParkCarEntry(Long id);

    /**
     * 获得入场记录分页
     *
     * @param pageReqVO 分页查询
     * @return 入场记录分页
     */
    PageResult<ParkCarEntryDO> getParkCarEntryPage(ParkCarEntryPageReqVO pageReqVO);

}