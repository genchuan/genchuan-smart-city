package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.mngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo.MngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo.MngCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.mngcomp.MngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件统计 Service 接口
 *
 * @author 亘川智城
 */
public interface MngCompService {

    /**
     * 创建管理部件统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMngComp(@Valid MngCompSaveReqVO createReqVO);

    /**
     * 更新管理部件统计
     *
     * @param updateReqVO 更新信息
     */
    void updateMngComp(@Valid MngCompSaveReqVO updateReqVO);

    /**
     * 删除管理部件统计
     *
     * @param id 编号
     */
    void deleteMngComp(Long id);

    /**
     * 获得管理部件统计
     *
     * @param id 编号
     * @return 管理部件统计
     */
    MngCompDO getMngComp(Long id);

    /**
     * 获得管理部件统计分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件统计分页
     */
    PageResult<MngCompDO> getMngCompPage(MngCompPageReqVO pageReqVO);

}