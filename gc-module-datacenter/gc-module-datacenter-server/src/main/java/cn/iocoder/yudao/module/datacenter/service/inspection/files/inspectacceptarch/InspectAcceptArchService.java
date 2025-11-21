package cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectacceptarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo.InspectAcceptArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo.InspectAcceptArchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectacceptarch.InspectAcceptArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检验收档案 Service 接口
 *
 * @author zcq
 */
public interface InspectAcceptArchService {

    /**
     * 创建巡查巡检验收档案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectAcceptArch(@Valid InspectAcceptArchSaveReqVO createReqVO);

    /**
     * 更新巡查巡检验收档案
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectAcceptArch(@Valid InspectAcceptArchSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检验收档案
     *
     * @param id 编号
     */
    void deleteInspectAcceptArch(Long id);

    /**
     * 获得巡查巡检验收档案
     *
     * @param id 编号
     * @return 巡查巡检验收档案
     */
    InspectAcceptArchDO getInspectAcceptArch(Long id);

    /**
     * 获得巡查巡检验收档案分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检验收档案分页
     */
    PageResult<InspectAcceptArchDO> getInspectAcceptArchPage(InspectAcceptArchPageReqVO pageReqVO);

}