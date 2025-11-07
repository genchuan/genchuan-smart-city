package cn.iocoder.yudao.module.datacenter.service.grid.data.evalgridattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr.vo.EvalGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr.vo.EvalGridAttrSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.evalgridattr.EvalGridAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价网格属性 Service 接口
 *
 * @author zhucongquan
 */
public interface EvalGridAttrService {

    /**
     * 创建评价网格属性
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalGridAttr(@Valid EvalGridAttrSaveReqVO createReqVO);

    /**
     * 更新评价网格属性
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalGridAttr(@Valid EvalGridAttrSaveReqVO updateReqVO);

    /**
     * 删除评价网格属性
     *
     * @param id 编号
     */
    void deleteEvalGridAttr(Long id);

    /**
     * 获得评价网格属性
     *
     * @param id 编号
     * @return 评价网格属性
     */
    EvalGridAttrDO getEvalGridAttr(Long id);

    /**
     * 获得评价网格属性分页
     *
     * @param pageReqVO 分页查询
     * @return 评价网格属性分页
     */
    PageResult<EvalGridAttrDO> getEvalGridAttrPage(EvalGridAttrPageReqVO pageReqVO);

}