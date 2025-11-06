package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emermatcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo.EmerMatCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo.EmerMatCatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emermatcat.EmerMatCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 应急物资分类配置 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerMatCatService {

    /**
     * 创建应急物资分类配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerMatCat(@Valid EmerMatCatSaveReqVO createReqVO);

    /**
     * 更新应急物资分类配置
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerMatCat(@Valid EmerMatCatSaveReqVO updateReqVO);

    /**
     * 删除应急物资分类配置
     *
     * @param id 编号
     */
    void deleteEmerMatCat(Long id);

    /**
     * 获得应急物资分类配置
     *
     * @param id 编号
     * @return 应急物资分类配置
     */
    EmerMatCatDO getEmerMatCat(Long id);

    /**
     * 获得应急物资分类配置分页
     *
     * @param pageReqVO 分页查询
     * @return 应急物资分类配置分页
     */
    PageResult<EmerMatCatDO> getEmerMatCatPage(EmerMatCatPageReqVO pageReqVO);

}