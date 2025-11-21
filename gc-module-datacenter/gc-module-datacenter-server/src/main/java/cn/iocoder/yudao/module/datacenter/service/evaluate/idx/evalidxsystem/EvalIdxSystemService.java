package cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxsystem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxsystem.vo.EvalIdxSystemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxsystem.vo.EvalIdxSystemSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxsystem.EvalIdxSystemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 指标体系管理 Service 接口
 *
 * @author zcq
 */
public interface EvalIdxSystemService {

    /**
     * 创建指标体系管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalIdxSystem(@Valid EvalIdxSystemSaveReqVO createReqVO);

    /**
     * 更新指标体系管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalIdxSystem(@Valid EvalIdxSystemSaveReqVO updateReqVO);

    /**
     * 删除指标体系管理
     *
     * @param id 编号
     */
    void deleteEvalIdxSystem(Long id);

    /**
     * 获得指标体系管理
     *
     * @param id 编号
     * @return 指标体系管理
     */
    EvalIdxSystemDO getEvalIdxSystem(Long id);

    /**
     * 获得指标体系管理分页
     *
     * @param pageReqVO 分页查询
     * @return 指标体系管理分页
     */
    PageResult<EvalIdxSystemDO> getEvalIdxSystemPage(EvalIdxSystemPageReqVO pageReqVO);

}