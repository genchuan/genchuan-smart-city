package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.relmngcompsymbol;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo.RelMngCompSymbolPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo.RelMngCompSymbolSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.relmngcompsymbol.RelMngCompSymbolDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件图示关联 Service 接口
 *
 * @author 亘川智城
 */
public interface RelMngCompSymbolService {

    /**
     * 创建管理部件图示关联
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRelMngCompSymbol(@Valid RelMngCompSymbolSaveReqVO createReqVO);

    /**
     * 更新管理部件图示关联
     *
     * @param updateReqVO 更新信息
     */
    void updateRelMngCompSymbol(@Valid RelMngCompSymbolSaveReqVO updateReqVO);

    /**
     * 删除管理部件图示关联
     *
     * @param id 编号
     */
    void deleteRelMngCompSymbol(Long id);

    /**
     * 获得管理部件图示关联
     *
     * @param id 编号
     * @return 管理部件图示关联
     */
    RelMngCompSymbolDO getRelMngCompSymbol(Long id);

    /**
     * 获得管理部件图示关联分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件图示关联分页
     */
    PageResult<RelMngCompSymbolDO> getRelMngCompSymbolPage(RelMngCompSymbolPageReqVO pageReqVO);

}