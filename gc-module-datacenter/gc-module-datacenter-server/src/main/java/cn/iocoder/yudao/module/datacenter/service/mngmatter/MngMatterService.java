package cn.iocoder.yudao.module.datacenter.service.mngmatter;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmatter.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmatter.MngMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理事项统计 Service 接口
 *
 * @author 亘川智城
 */
public interface MngMatterService {

    /**
     * 创建管理事项统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMngMatter(@Valid MngMatterSaveReqVO createReqVO);

    /**
     * 更新管理事项统计
     *
     * @param updateReqVO 更新信息
     */
    void updateMngMatter(@Valid MngMatterSaveReqVO updateReqVO);

    /**
     * 删除管理事项统计
     *
     * @param id 编号
     */
    void deleteMngMatter(Long id);

    /**
     * 获得管理事项统计
     *
     * @param id 编号
     * @return 管理事项统计
     */
    MngMatterDO getMngMatter(Long id);

    /**
     * 获得管理事项统计分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项统计分页
     */
    PageResult<MngMatterDO> getMngMatterPage(MngMatterPageReqVO pageReqVO);

}