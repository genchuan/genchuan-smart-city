package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagetype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo.GarbageTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetype.vo.GarbageTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetype.GarbageTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 垃圾品类字典 Service 接口
 *
 * @author 芋道源码
 */
public interface GarbageTypeService {

    /**
     * 创建垃圾品类字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGarbageType(@Valid GarbageTypeSaveReqVO createReqVO);

    /**
     * 更新垃圾品类字典
     *
     * @param updateReqVO 更新信息
     */
    void updateGarbageType(@Valid GarbageTypeSaveReqVO updateReqVO);

    /**
     * 删除垃圾品类字典
     *
     * @param id 编号
     */
    void deleteGarbageType(Long id);

    /**
     * 获得垃圾品类字典
     *
     * @param id 编号
     * @return 垃圾品类字典
     */
    GarbageTypeDO getGarbageType(Long id);

    /**
     * 获得垃圾品类字典分页
     *
     * @param pageReqVO 分页查询
     * @return 垃圾品类字典分页
     */
    PageResult<GarbageTypeDO> getGarbageTypePage(GarbageTypePageReqVO pageReqVO);

}