package cn.iocoder.yudao.module.evaluate.service.scope;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope.vo.ScopePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope.vo.ScopeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.scope.ScopeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 范围字典 Service 接口
 *
 * @author 亘川智城
 */
public interface ScopeService {

    /**
     * 创建范围字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createScope(@Valid ScopeSaveReqVO createReqVO);

    /**
     * 更新范围字典
     *
     * @param updateReqVO 更新信息
     */
    void updateScope(@Valid ScopeSaveReqVO updateReqVO);

    /**
     * 删除范围字典
     *
     * @param id 编号
     */
    void deleteScope(Long id);

    /**
     * 获得范围字典
     *
     * @param id 编号
     * @return 范围字典
     */
    ScopeDO getScope(Long id);

    /**
     * 获得范围字典分页
     *
     * @param pageReqVO 分页查询
     * @return 范围字典分页
     */
    PageResult<ScopeDO> getScopePage(ScopePageReqVO pageReqVO);

}