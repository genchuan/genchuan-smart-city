package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.abnormaltype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypeOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.abnormaltype.AbnormalTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency.CollectionFrequencyOptionVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.AbnormalTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

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

    /**
     * 获得垃圾异常类型下拉框选项
     * @return 下拉框选项列表
     */
    List<AbnormalTypeOptionVO> getAbnormalTypeOptions();
}