package cn.iocoder.yudao.module.datacenter.service.bizmngmatterminor;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizmngmatterminor.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizmngmatterminor.BizMngMatterMinorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理事项小类 Service 接口
 *
 * @author 亘川智城
 */
public interface BizMngMatterMinorService {

    /**
     * 创建管理事项小类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizMngMatterMinor(@Valid BizMngMatterMinorSaveReqVO createReqVO);

    /**
     * 更新管理事项小类
     *
     * @param updateReqVO 更新信息
     */
    void updateBizMngMatterMinor(@Valid BizMngMatterMinorSaveReqVO updateReqVO);

    /**
     * 删除管理事项小类
     *
     * @param id 编号
     */
    void deleteBizMngMatterMinor(Long id);

    /**
     * 获得管理事项小类
     *
     * @param id 编号
     * @return 管理事项小类
     */
    BizMngMatterMinorDO getBizMngMatterMinor(Long id);

    /**
     * 获得管理事项小类分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项小类分页
     */
    PageResult<BizMngMatterMinorDO> getBizMngMatterMinorPage(BizMngMatterMinorPageReqVO pageReqVO);

}