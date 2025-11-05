package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompext;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo.MonCompExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo.MonCompExtSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompext.MonCompExtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测部件扩展配置 Service 接口
 *
 * @author 亘川智城
 */
public interface MonCompExtService {

    /**
     * 创建监测部件扩展配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonCompExt(@Valid MonCompExtSaveReqVO createReqVO);

    /**
     * 更新监测部件扩展配置
     *
     * @param updateReqVO 更新信息
     */
    void updateMonCompExt(@Valid MonCompExtSaveReqVO updateReqVO);

    /**
     * 删除监测部件扩展配置
     *
     * @param id 编号
     */
    void deleteMonCompExt(Long id);

    /**
     * 获得监测部件扩展配置
     *
     * @param id 编号
     * @return 监测部件扩展配置
     */
    MonCompExtDO getMonCompExt(Long id);

    /**
     * 获得监测部件扩展配置分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件扩展配置分页
     */
    PageResult<MonCompExtDO> getMonCompExtPage(MonCompExtPageReqVO pageReqVO);

}