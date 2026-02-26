package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.abnormaltype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.AbnormalTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 垃圾异常类型字典 Service 接口
 *
 * @author 芋道源码
 */
public interface AbnormalTypeService {

    /**
     * 创建垃圾异常类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAbnormalType(@Valid AbnormalTypeSaveReqVO createReqVO);

    /**
     * 更新垃圾异常类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateAbnormalType(@Valid AbnormalTypeSaveReqVO updateReqVO);

    /**
     * 删除垃圾异常类型字典
     *
     * @param id 编号
     */
    void deleteAbnormalType(Long id);

    /**
     * 获得垃圾异常类型字典
     *
     * @param id 编号
     * @return 垃圾异常类型字典
     */
    AbnormalTypeDO getAbnormalType(Long id);

    /**
     * 获得垃圾异常类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 垃圾异常类型字典分页
     */
    PageResult<AbnormalTypeDO> getAbnormalTypePage(AbnormalTypePageReqVO pageReqVO);

}