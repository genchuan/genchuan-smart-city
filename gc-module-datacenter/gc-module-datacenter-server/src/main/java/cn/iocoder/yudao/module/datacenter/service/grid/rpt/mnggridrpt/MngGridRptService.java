package cn.iocoder.yudao.module.datacenter.service.grid.rpt.mnggridrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt.vo.MngGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt.vo.MngGridRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.mnggridrpt.MngGridRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理网格统计 Service 接口
 *
 * @author zhucongquan
 */
public interface MngGridRptService {

    /**
     * 创建管理网格统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMngGridRpt(@Valid MngGridRptSaveReqVO createReqVO);

    /**
     * 更新管理网格统计
     *
     * @param updateReqVO 更新信息
     */
    void updateMngGridRpt(@Valid MngGridRptSaveReqVO updateReqVO);

    /**
     * 删除管理网格统计
     *
     * @param id 编号
     */
    void deleteMngGridRpt(Long id);

    /**
     * 获得管理网格统计
     *
     * @param id 编号
     * @return 管理网格统计
     */
    MngGridRptDO getMngGridRpt(Long id);

    /**
     * 获得管理网格统计分页
     *
     * @param pageReqVO 分页查询
     * @return 管理网格统计分页
     */
    PageResult<MngGridRptDO> getMngGridRptPage(MngGridRptPageReqVO pageReqVO);

}