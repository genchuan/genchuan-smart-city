package cn.iocoder.yudao.module.datacenter.service.grid.data.mnggridattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo.MngGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo.MngGridAttrSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.mnggridattr.MngGridAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理网格属性 Service 接口
 *
 * @author zhucongquan
 */
public interface MngGridAttrService {

    /**
     * 创建管理网格属性
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMngGridAttr(@Valid MngGridAttrSaveReqVO createReqVO);

    /**
     * 更新管理网格属性
     *
     * @param updateReqVO 更新信息
     */
    void updateMngGridAttr(@Valid MngGridAttrSaveReqVO updateReqVO);

    /**
     * 删除管理网格属性
     *
     * @param id 编号
     */
    void deleteMngGridAttr(Long id);

    /**
     * 获得管理网格属性
     *
     * @param id 编号
     * @return 管理网格属性
     */
    MngGridAttrDO getMngGridAttr(Long id);

    /**
     * 获得管理网格属性分页
     *
     * @param pageReqVO 分页查询
     * @return 管理网格属性分页
     */
    PageResult<MngGridAttrDO> getMngGridAttrPage(MngGridAttrPageReqVO pageReqVO);

}