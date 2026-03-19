package cn.iocoder.yudao.module.envirhealth.service.dictionary.collectiontimeperiod;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.collectiontimeperiod.vo.CollectionTimePeriodOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.collectiontimeperiod.vo.CollectionTimePeriodPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.collectiontimeperiod.vo.CollectionTimePeriodSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.CollectionTimePeriodDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.CollectionTimePeriodMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.COLLECTION_TIME_PERIOD_NOT_EXISTS;

/**
 * 收运时段字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CollectionTimePeriodServiceImpl implements CollectionTimePeriodService {

    @Resource
    private CollectionTimePeriodMapper collectionTimePeriodMapper;

    @Override
    public Long createCollectionTimePeriod(CollectionTimePeriodSaveReqVO createReqVO) {
        // 插入
        CollectionTimePeriodDO collectionTimePeriod = BeanUtils.toBean(createReqVO, CollectionTimePeriodDO.class);
        collectionTimePeriodMapper.insert(collectionTimePeriod);
        // 返回
        return collectionTimePeriod.getId();
    }

    @Override
    public void updateCollectionTimePeriod(CollectionTimePeriodSaveReqVO updateReqVO) {
        // 校验存在
        validateCollectionTimePeriodExists(updateReqVO.getId());
        // 更新
        CollectionTimePeriodDO updateObj = BeanUtils.toBean(updateReqVO, CollectionTimePeriodDO.class);
        collectionTimePeriodMapper.updateById(updateObj);
    }

    @Override
    public void deleteCollectionTimePeriod(Long id) {
        // 校验存在
        validateCollectionTimePeriodExists(id);
        // 删除
        collectionTimePeriodMapper.deleteById(id);
    }

    private void validateCollectionTimePeriodExists(Long id) {
        if (collectionTimePeriodMapper.selectById(id) == null) {
            throw exception(COLLECTION_TIME_PERIOD_NOT_EXISTS);
        }
    }

    @Override
    public CollectionTimePeriodDO getCollectionTimePeriod(Long id) {
        return collectionTimePeriodMapper.selectById(id);
    }

    @Override
    public PageResult<CollectionTimePeriodDO> getCollectionTimePeriodPage(CollectionTimePeriodPageReqVO pageReqVO) {
        return collectionTimePeriodMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CollectionTimePeriodOptionVO> getCollectionTimePeriodOptions() {

        List<CollectionTimePeriodDO> list;
        list = collectionTimePeriodMapper.selectList(
                new LambdaQueryWrapperX<CollectionTimePeriodDO>()
                        .eq(CollectionTimePeriodDO::getDeleted, 0)
                        .orderByDesc(CollectionTimePeriodDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, collectionTimePeriodDO -> {
            CollectionTimePeriodOptionVO vo = new CollectionTimePeriodOptionVO();
            vo.setLabel(collectionTimePeriodDO.getPeriodName());
            vo.setValue(collectionTimePeriodDO.getPeriodCode());
            return vo;
        });
    }
}