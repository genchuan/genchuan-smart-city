package cn.iocoder.yudao.module.datacenter.service.relmngmattermajmin;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.relmngmattermajmin.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.relmngmattermajmin.RelMngMatterMajMinDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理事项大小类关联 Service 接口
 *
 * @author 亘川智城
 */
public interface RelMngMatterMajMinService {

    /**
     * 创建管理事项大小类关联
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRelMngMatterMajMin(@Valid RelMngMatterMajMinSaveReqVO createReqVO);

    /**
     * 更新管理事项大小类关联
     *
     * @param updateReqVO 更新信息
     */
    void updateRelMngMatterMajMin(@Valid RelMngMatterMajMinSaveReqVO updateReqVO);

    /**
     * 删除管理事项大小类关联
     *
     * @param id 编号
     */
    void deleteRelMngMatterMajMin(Long id);

    /**
     * 获得管理事项大小类关联
     *
     * @param id 编号
     * @return 管理事项大小类关联
     */
    RelMngMatterMajMinDO getRelMngMatterMajMin(Long id);

    /**
     * 获得管理事项大小类关联分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项大小类关联分页
     */
    PageResult<RelMngMatterMajMinDO> getRelMngMatterMajMinPage(RelMngMatterMajMinPageReqVO pageReqVO);

}