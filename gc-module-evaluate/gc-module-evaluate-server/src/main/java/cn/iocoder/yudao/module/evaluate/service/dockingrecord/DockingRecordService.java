package cn.iocoder.yudao.module.evaluate.service.dockingrecord;

import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo.DockingRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo.DockingRecordSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.dockingrecord.DockingRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 系统对接记录 Service 接口
 *
 * @author 亘川智城
 */
public interface DockingRecordService {

    /**
     * 创建系统对接记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDockingRecord(@Valid DockingRecordSaveReqVO createReqVO);

    /**
     * 更新系统对接记录
     *
     * @param updateReqVO 更新信息
     */
    void updateDockingRecord(@Valid DockingRecordSaveReqVO updateReqVO);

    /**
     * 删除系统对接记录
     *
     * @param id 编号
     */
    void deleteDockingRecord(Long id);

    /**
     * 获得系统对接记录
     *
     * @param id 编号
     * @return 系统对接记录
     */
    DockingRecordDO getDockingRecord(Long id);

    /**
     * 获得系统对接记录分页
     *
     * @param pageReqVO 分页查询
     * @return 系统对接记录分页
     */
    PageResult<DockingRecordDO> getDockingRecordPage(DockingRecordPageReqVO pageReqVO);

}