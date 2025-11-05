package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcompsymbollib;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo.BizMngCompSymbolLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo.BizMngCompSymbolLibSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompsymbollib.BizMngCompSymbolLibDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件图示符号库 Service 接口
 *
 * @author 亘川智城
 */
public interface BizMngCompSymbolLibService {

    /**
     * 创建管理部件图示符号库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizMngCompSymbolLib(@Valid BizMngCompSymbolLibSaveReqVO createReqVO);

    /**
     * 更新管理部件图示符号库
     *
     * @param updateReqVO 更新信息
     */
    void updateBizMngCompSymbolLib(@Valid BizMngCompSymbolLibSaveReqVO updateReqVO);

    /**
     * 删除管理部件图示符号库
     *
     * @param id 编号
     */
    void deleteBizMngCompSymbolLib(Long id);

    /**
     * 获得管理部件图示符号库
     *
     * @param id 编号
     * @return 管理部件图示符号库
     */
    BizMngCompSymbolLibDO getBizMngCompSymbolLib(Long id);

    /**
     * 获得管理部件图示符号库分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件图示符号库分页
     */
    PageResult<BizMngCompSymbolLibDO> getBizMngCompSymbolLibPage(BizMngCompSymbolLibPageReqVO pageReqVO);

}