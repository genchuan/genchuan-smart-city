package cn.iocoder.yudao.module.datacenter.service.emerplan.emerplancat;


import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo.EmerPlanCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerplancat.vo.EmerPlanCatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerplancat.EmerPlanCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


/**
 * 预案分类配置 Service 接口
 *
 * @author 亘川智城
 */
public interface EmerPlanCatService {

    /**
     * 创建预案分类配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmerPlanCat(@Valid EmerPlanCatSaveReqVO createReqVO);

    /**
     * 更新预案分类配置
     *
     * @param updateReqVO 更新信息
     */
    void updateEmerPlanCat(@Valid EmerPlanCatSaveReqVO updateReqVO);

    /**
     * 删除预案分类配置
     *
     * @param id 编号
     */
    void deleteEmerPlanCat(Long id);

    /**
     * 获得预案分类配置
     *
     * @param id 编号
     * @return 预案分类配置
     */
    EmerPlanCatDO getEmerPlanCat(Long id);

    /**
     * 获得预案分类配置分页
     *
     * @param pageReqVO 分页查询
     * @return 预案分类配置分页
     */
    PageResult<EmerPlanCatDO> getEmerPlanCatPage(EmerPlanCatPageReqVO pageReqVO);

}