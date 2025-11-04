package cn.iocoder.yudao.module.datacenter.service.grid.code.evalgridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.evalgridcode.vo.EvalGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.evalgridcode.vo.EvalGridCodeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.evalgridcode.EvalGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价网格编码 Service 接口
 *
 * @author zcq
 */
public interface EvalGridCodeService {

    /**
     * 创建评价网格编码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalGridCode(@Valid EvalGridCodeSaveReqVO createReqVO);

    /**
     * 更新评价网格编码
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalGridCode(@Valid EvalGridCodeSaveReqVO updateReqVO);

    /**
     * 删除评价网格编码
     *
     * @param id 编号
     */
    void deleteEvalGridCode(Long id);

    /**
     * 获得评价网格编码
     *
     * @param id 编号
     * @return 评价网格编码
     */
    EvalGridCodeDO getEvalGridCode(Long id);

    /**
     * 获得评价网格编码分页
     *
     * @param pageReqVO 分页查询
     * @return 评价网格编码分页
     */
    PageResult<EvalGridCodeDO> getEvalGridCodePage(EvalGridCodePageReqVO pageReqVO);

}