package cn.iocoder.yudao.module.datacenter.service.grid.rpt.evalgridrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo.EvalGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo.EvalGridRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.evalgridrpt.EvalGridRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价网格统计 Service 接口
 *
 * @author zhucongquan
 */
public interface EvalGridRptService {

    /**
     * 创建评价网格统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalGridRpt(@Valid EvalGridRptSaveReqVO createReqVO);

    /**
     * 更新评价网格统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalGridRpt(@Valid EvalGridRptSaveReqVO updateReqVO);

    /**
     * 删除评价网格统计
     *
     * @param id 编号
     */
    void deleteEvalGridRpt(Long id);

    /**
     * 获得评价网格统计
     *
     * @param id 编号
     * @return 评价网格统计
     */
    EvalGridRptDO getEvalGridRpt(Long id);

    /**
     * 获得评价网格统计分页
     *
     * @param pageReqVO 分页查询
     * @return 评价网格统计分页
     */
    PageResult<EvalGridRptDO> getEvalGridRptPage(EvalGridRptPageReqVO pageReqVO);

}