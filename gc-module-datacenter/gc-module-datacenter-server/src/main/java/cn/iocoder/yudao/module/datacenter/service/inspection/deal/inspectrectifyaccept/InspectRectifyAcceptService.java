package cn.iocoder.yudao.module.datacenter.service.inspection.deal.inspectrectifyaccept;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo.InspectRectifyAcceptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo.InspectRectifyAcceptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifyaccept.InspectRectifyAcceptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检整改结果验收 Service 接口
 *
 * @author zcq
 */
public interface InspectRectifyAcceptService {

    /**
     * 创建巡查巡检整改结果验收
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectRectifyAccept(@Valid InspectRectifyAcceptSaveReqVO createReqVO);

    /**
     * 更新巡查巡检整改结果验收
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectRectifyAccept(@Valid InspectRectifyAcceptSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检整改结果验收
     *
     * @param id 编号
     */
    void deleteInspectRectifyAccept(Long id);

    /**
     * 获得巡查巡检整改结果验收
     *
     * @param id 编号
     * @return 巡查巡检整改结果验收
     */
    InspectRectifyAcceptDO getInspectRectifyAccept(Long id);

    /**
     * 获得巡查巡检整改结果验收分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检整改结果验收分页
     */
    PageResult<InspectRectifyAcceptDO> getInspectRectifyAcceptPage(InspectRectifyAcceptPageReqVO pageReqVO);

}