package cn.iocoder.yudao.module.datacenter.service.evaluate.evalobject;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject.vo.EvalObjectPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject.vo.EvalObjectSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalobject.EvalObjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价对象管理 Service 接口
 *
 * @author zcq
 */
public interface EvalObjectService {

    /**
     * 创建评价对象管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalObject(@Valid EvalObjectSaveReqVO createReqVO);

    /**
     * 更新评价对象管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalObject(@Valid EvalObjectSaveReqVO updateReqVO);

    /**
     * 删除评价对象管理
     *
     * @param id 编号
     */
    void deleteEvalObject(Long id);

    /**
     * 获得评价对象管理
     *
     * @param id 编号
     * @return 评价对象管理
     */
    EvalObjectDO getEvalObject(Long id);

    /**
     * 获得评价对象管理分页
     *
     * @param pageReqVO 分页查询
     * @return 评价对象管理分页
     */
    PageResult<EvalObjectDO> getEvalObjectPage(EvalObjectPageReqVO pageReqVO);

}