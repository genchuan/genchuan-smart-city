package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.collectionfrequency;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency.CollectionFrequencyOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency.CollectionFrequencyPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency.CollectionFrequencySaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointOptionVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.CollectionFrequencyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 收运频次字典 Service 接口
 *
 * @author 亘川智城
 */
public interface CollectionFrequencyService {

    /**
     * 创建收运频次字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCollectionFrequency(@Valid CollectionFrequencySaveReqVO createReqVO);

    /**
     * 更新收运频次字典
     *
     * @param updateReqVO 更新信息
     */
    void updateCollectionFrequency(@Valid CollectionFrequencySaveReqVO updateReqVO);

    /**
     * 删除收运频次字典
     *
     * @param id 编号
     */
    void deleteCollectionFrequency(Long id);

    /**
     * 获得收运频次字典
     *
     * @param id 编号
     * @return 收运频次字典
     */
    CollectionFrequencyDO getCollectionFrequency(Long id);

    /**
     * 获得收运频次字典分页
     *
     * @param pageReqVO 分页查询
     * @return 收运频次字典分页
     */
    PageResult<CollectionFrequencyDO> getCollectionFrequencyPage(CollectionFrequencyPageReqVO pageReqVO);

    /**
     * 获得收运频次字典下拉框选项
     * @return 下拉框选项列表
     */
    List<CollectionFrequencyOptionVO> getCollectionFrequencyOptions();
}