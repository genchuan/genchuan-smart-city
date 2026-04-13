package cn.iocoder.yudao.module.studentmgmt.service.moralresource;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralresource.MoralResourceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 德育资源 Service 接口
 *
 * @author 芋道源码
 */
public interface MoralResourceService {

    /**
     * 创建德育资源
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMoralResource(@Valid MoralResourceSaveReqVO createReqVO);

    /**
     * 更新德育资源
     *
     * @param updateReqVO 更新信息
     */
    void updateMoralResource(@Valid MoralResourceSaveReqVO updateReqVO);

    /**
     * 删除德育资源
     *
     * @param id 编号
     */
    void deleteMoralResource(Long id);

    /**
    * 批量删除德育资源
    *
    * @param ids 编号
    */
    void deleteMoralResourceListByIds(List<Long> ids);

    /**
     * 获得德育资源
     *
     * @param id 编号
     * @return 德育资源
     */
    MoralResourceDO getMoralResource(Long id);

    /**
     * 获得德育资源分页
     *
     * @param pageReqVO 分页查询
     * @return 德育资源分页
     */
    PageResult<MoralResourceDO> getMoralResourcePage(MoralResourcePageReqVO pageReqVO);

}