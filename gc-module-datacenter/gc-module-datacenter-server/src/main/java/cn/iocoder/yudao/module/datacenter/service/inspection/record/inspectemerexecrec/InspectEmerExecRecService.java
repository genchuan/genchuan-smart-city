package cn.iocoder.yudao.module.datacenter.service.inspection.record.inspectemerexecrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec.vo.InspectEmerExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec.vo.InspectEmerExecRecSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectemerexecrec.InspectEmerExecRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 应急巡查执行记录 Service 接口
 *
 * @author zcq
 */
public interface InspectEmerExecRecService {

    /**
     * 创建应急巡查执行记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectEmerExecRec(@Valid InspectEmerExecRecSaveReqVO createReqVO);

    /**
     * 更新应急巡查执行记录
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectEmerExecRec(@Valid InspectEmerExecRecSaveReqVO updateReqVO);

    /**
     * 删除应急巡查执行记录
     *
     * @param id 编号
     */
    void deleteInspectEmerExecRec(Long id);

    /**
     * 获得应急巡查执行记录
     *
     * @param id 编号
     * @return 应急巡查执行记录
     */
    InspectEmerExecRecDO getInspectEmerExecRec(Long id);

    /**
     * 获得应急巡查执行记录分页
     *
     * @param pageReqVO 分页查询
     * @return 应急巡查执行记录分页
     */
    PageResult<InspectEmerExecRecDO> getInspectEmerExecRecPage(InspectEmerExecRecPageReqVO pageReqVO);

}