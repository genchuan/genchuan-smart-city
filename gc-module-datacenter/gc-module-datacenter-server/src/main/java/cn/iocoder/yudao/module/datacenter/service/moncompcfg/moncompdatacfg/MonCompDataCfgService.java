package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompdatacfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo.MonCompDataCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo.MonCompDataCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompdatacfg.MonCompDataCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测部件数据配置 Service 接口
 *
 * @author 亘川智城
 */
public interface MonCompDataCfgService {

    /**
     * 创建监测部件数据配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonCompDataCfg(@Valid MonCompDataCfgSaveReqVO createReqVO);

    /**
     * 更新监测部件数据配置
     *
     * @param updateReqVO 更新信息
     */
    void updateMonCompDataCfg(@Valid MonCompDataCfgSaveReqVO updateReqVO);

    /**
     * 删除监测部件数据配置
     *
     * @param id 编号
     */
    void deleteMonCompDataCfg(Long id);

    /**
     * 获得监测部件数据配置
     *
     * @param id 编号
     * @return 监测部件数据配置
     */
    MonCompDataCfgDO getMonCompDataCfg(Long id);

    /**
     * 获得监测部件数据配置分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件数据配置分页
     */
    PageResult<MonCompDataCfgDO> getMonCompDataCfgPage(MonCompDataCfgPageReqVO pageReqVO);

}