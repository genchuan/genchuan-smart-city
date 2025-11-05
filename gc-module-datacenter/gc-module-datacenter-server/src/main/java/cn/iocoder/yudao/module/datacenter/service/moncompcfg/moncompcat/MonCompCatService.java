package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo.MonCompCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo.MonCompCatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcat.MonCompCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测部件分类配置 Service 接口
 *
 * @author 亘川智城
 */
public interface MonCompCatService {

    /**
     * 创建监测部件分类配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonCompCat(@Valid MonCompCatSaveReqVO createReqVO);

    /**
     * 更新监测部件分类配置
     *
     * @param updateReqVO 更新信息
     */
    void updateMonCompCat(@Valid MonCompCatSaveReqVO updateReqVO);

    /**
     * 删除监测部件分类配置
     *
     * @param id 编号
     */
    void deleteMonCompCat(Long id);

    /**
     * 获得监测部件分类配置
     *
     * @param id 编号
     * @return 监测部件分类配置
     */
    MonCompCatDO getMonCompCat(Long id);

    /**
     * 获得监测部件分类配置分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件分类配置分页
     */
    PageResult<MonCompCatDO> getMonCompCatPage(MonCompCatPageReqVO pageReqVO);

}