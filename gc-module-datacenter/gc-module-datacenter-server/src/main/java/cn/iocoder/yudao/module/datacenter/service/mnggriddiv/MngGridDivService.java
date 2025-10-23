package cn.iocoder.yudao.module.datacenter.service.mnggriddiv;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.mnggriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mnggriddiv.MngGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理网格划分 Service 接口
 *
 * @author zcq
 */
public interface MngGridDivService {

    /**
     * 创建管理网格划分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMngGridDiv(@Valid MngGridDivSaveReqVO createReqVO);

    /**
     * 更新管理网格划分
     *
     * @param updateReqVO 更新信息
     */
    void updateMngGridDiv(@Valid MngGridDivSaveReqVO updateReqVO);

    /**
     * 删除管理网格划分
     *
     * @param id 编号
     */
    void deleteMngGridDiv(Long id);

    /**
     * 获得管理网格划分
     *
     * @param id 编号
     * @return 管理网格划分
     */
    MngGridDivDO getMngGridDiv(Long id);

    /**
     * 获得管理网格划分分页
     *
     * @param pageReqVO 分页查询
     * @return 管理网格划分分页
     */
    PageResult<MngGridDivDO> getMngGridDivPage(MngGridDivPageReqVO pageReqVO);

}