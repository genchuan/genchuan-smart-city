package cn.iocoder.yudao.module.datacenter.service.evalgriddiv;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.evalgriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evalgriddiv.EvalGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 评价网格划分 Service 接口
 *
 * @author zcq
 */
public interface EvalGridDivService {

    /**
     * 创建评价网格划分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalGridDiv(@Valid EvalGridDivSaveReqVO createReqVO);

    /**
     * 更新评价网格划分
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalGridDiv(@Valid EvalGridDivSaveReqVO updateReqVO);

    /**
     * 删除评价网格划分
     *
     * @param id 编号
     */
    void deleteEvalGridDiv(Long id);

    /**
     * 获得评价网格划分
     *
     * @param id 编号
     * @return 评价网格划分
     */
    EvalGridDivDO getEvalGridDiv(Long id);

    /**
     * 获得评价网格划分分页
     *
     * @param pageReqVO 分页查询
     * @return 评价网格划分分页
     */
    PageResult<EvalGridDivDO> getEvalGridDivPage(EvalGridDivPageReqVO pageReqVO);

}