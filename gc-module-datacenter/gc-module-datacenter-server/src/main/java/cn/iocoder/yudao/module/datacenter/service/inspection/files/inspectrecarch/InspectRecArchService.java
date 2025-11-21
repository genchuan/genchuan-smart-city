package cn.iocoder.yudao.module.datacenter.service.inspection.files.inspectrecarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch.vo.InspectRecArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrecarch.vo.InspectRecArchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrecarch.InspectRecArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检记录档案 Service 接口
 *
 * @author zcq
 */
public interface InspectRecArchService {

    /**
     * 创建巡查巡检记录档案
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectRecArch(@Valid InspectRecArchSaveReqVO createReqVO);

    /**
     * 更新巡查巡检记录档案
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectRecArch(@Valid InspectRecArchSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检记录档案
     *
     * @param id 编号
     */
    void deleteInspectRecArch(Long id);

    /**
     * 获得巡查巡检记录档案
     *
     * @param id 编号
     * @return 巡查巡检记录档案
     */
    InspectRecArchDO getInspectRecArch(Long id);

    /**
     * 获得巡查巡检记录档案分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检记录档案分页
     */
    PageResult<InspectRecArchDO> getInspectRecArchPage(InspectRecArchPageReqVO pageReqVO);

}