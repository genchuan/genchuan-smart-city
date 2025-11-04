package cn.iocoder.yudao.module.datacenter.service.grid.data.unitgridattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr.vo.UnitGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.unitgridattr.vo.UnitGridAttrSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.unitgridattr.UnitGridAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 单元网格属性 Service 接口
 *
 * @author zhucongquan
 */
public interface UnitGridAttrService {

    /**
     * 创建单元网格属性
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUnitGridAttr(@Valid UnitGridAttrSaveReqVO createReqVO);

    /**
     * 更新单元网格属性
     *
     * @param updateReqVO 更新信息
     */
    void updateUnitGridAttr(@Valid UnitGridAttrSaveReqVO updateReqVO);

    /**
     * 删除单元网格属性
     *
     * @param id 编号
     */
    void deleteUnitGridAttr(Long id);

    /**
     * 获得单元网格属性
     *
     * @param id 编号
     * @return 单元网格属性
     */
    UnitGridAttrDO getUnitGridAttr(Long id);

    /**
     * 获得单元网格属性分页
     *
     * @param pageReqVO 分页查询
     * @return 单元网格属性分页
     */
    PageResult<UnitGridAttrDO> getUnitGridAttrPage(UnitGridAttrPageReqVO pageReqVO);

}