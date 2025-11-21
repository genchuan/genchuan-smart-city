package cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectrectifyarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo.InspectRectifyArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo.InspectRectifyArchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrectifyarch.InspectRectifyArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检整改档案 Service 接口
 *
 * @author zcq
 */
public interface InspectRectifyArchService {

    /**
     * 创建巡查巡检整改档案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectRectifyArch(@Valid InspectRectifyArchSaveReqVO createReqVO);

    /**
     * 更新巡查巡检整改档案
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectRectifyArch(@Valid InspectRectifyArchSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检整改档案
     *
     * @param id 编号
     */
    void deleteInspectRectifyArch(Long id);

    /**
     * 获得巡查巡检整改档案
     *
     * @param id 编号
     * @return 巡查巡检整改档案
     */
    InspectRectifyArchDO getInspectRectifyArch(Long id);

    /**
     * 获得巡查巡检整改档案分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检整改档案分页
     */
    PageResult<InspectRectifyArchDO> getInspectRectifyArchPage(InspectRectifyArchPageReqVO pageReqVO);

}