package cn.iocoder.yudao.module.envirhealth.service.river.cleaningtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.cleaningtype.CleaningTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.cleaningtype.CleaningTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.CleaningTypeDO;
import jakarta.validation.Valid;

/**
 * 保洁类型字典表 Service 接口
 *
 * @author 芋道源码
 */
public interface CleaningTypeService {

    /**
     * 创建保洁类型字典表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCleaningType(@Valid CleaningTypeSaveReqVO createReqVO);

    /**
     * 更新保洁类型字典表
     *
     * @param updateReqVO 更新信息
     */
    void updateCleaningType(@Valid CleaningTypeSaveReqVO updateReqVO);

    /**
     * 删除保洁类型字典表
     *
     * @param id 编号
     */
    void deleteCleaningType(Long id);

    /**
     * 获得保洁类型字典表
     *
     * @param id 编号
     * @return 保洁类型字典表
     */
    CleaningTypeDO getCleaningType(Long id);

    /**
     * 获得保洁类型字典表分页
     *
     * @param pageReqVO 分页查询
     * @return 保洁类型字典表分页
     */
    PageResult<CleaningTypeDO> getCleaningTypePage(CleaningTypePageReqVO pageReqVO);

}