package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.collectiontimeperiod;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod.CollectionTimePeriodOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod.CollectionTimePeriodPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectiontimeperiod.CollectionTimePeriodSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.CollectionTimePeriodDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 收运时段字典 Service 接口
 *
 * @author 亘川智城
 */
public interface CollectionTimePeriodService {

    /**
     * 创建收运时段字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCollectionTimePeriod(@Valid CollectionTimePeriodSaveReqVO createReqVO);

    /**
     * 更新收运时段字典
     *
     * @param updateReqVO 更新信息
     */
    void updateCollectionTimePeriod(@Valid CollectionTimePeriodSaveReqVO updateReqVO);

    /**
     * 删除收运时段字典
     *
     * @param id 编号
     */
    void deleteCollectionTimePeriod(Long id);

    /**
     * 获得收运时段字典
     *
     * @param id 编号
     * @return 收运时段字典
     */
    CollectionTimePeriodDO getCollectionTimePeriod(Long id);

    /**
     * 获得收运时段字典分页
     *
     * @param pageReqVO 分页查询
     * @return 收运时段字典分页
     */
    PageResult<CollectionTimePeriodDO> getCollectionTimePeriodPage(CollectionTimePeriodPageReqVO pageReqVO);

    /**
     * 获得收运时段下拉框选项
     * @return 下拉框选项列表
     */
    List<CollectionTimePeriodOptionVO> getCollectionTimePeriodOptions();
}