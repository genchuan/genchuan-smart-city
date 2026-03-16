package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.collectionfrequency;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency.CollectionFrequencyOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency.CollectionFrequencyPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency.CollectionFrequencySaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.CollectionFrequencyDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.CollectionFrequencyMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.COLLECTION_FREQUENCY_NOT_EXISTS;

/**
 * 收运频次字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CollectionFrequencyServiceImpl implements CollectionFrequencyService {

    @Resource
    private CollectionFrequencyMapper collectionFrequencyMapper;

    @Override
    public Long createCollectionFrequency(CollectionFrequencySaveReqVO createReqVO) {
        // 插入
        CollectionFrequencyDO collectionFrequency = BeanUtils.toBean(createReqVO, CollectionFrequencyDO.class);
        collectionFrequencyMapper.insert(collectionFrequency);
        // 返回
        return collectionFrequency.getId();
    }

    @Override
    public void updateCollectionFrequency(CollectionFrequencySaveReqVO updateReqVO) {
        // 校验存在
        validateCollectionFrequencyExists(updateReqVO.getId());
        // 更新
        CollectionFrequencyDO updateObj = BeanUtils.toBean(updateReqVO, CollectionFrequencyDO.class);
        collectionFrequencyMapper.updateById(updateObj);
    }

    @Override
    public void deleteCollectionFrequency(Long id) {
        // 校验存在
        validateCollectionFrequencyExists(id);
        // 删除
        collectionFrequencyMapper.deleteById(id);
    }

    private void validateCollectionFrequencyExists(Long id) {
        if (collectionFrequencyMapper.selectById(id) == null) {
            throw exception(COLLECTION_FREQUENCY_NOT_EXISTS);
        }
    }

    @Override
    public CollectionFrequencyDO getCollectionFrequency(Long id) {
        return collectionFrequencyMapper.selectById(id);
    }

    @Override
    public PageResult<CollectionFrequencyDO> getCollectionFrequencyPage(CollectionFrequencyPageReqVO pageReqVO) {
        return collectionFrequencyMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CollectionFrequencyOptionVO> getCollectionFrequencyOptions() {

        List<CollectionFrequencyDO> list;
        list = collectionFrequencyMapper.selectList(
                new LambdaQueryWrapperX<CollectionFrequencyDO>()
                        .eq(CollectionFrequencyDO::getDeleted, 0)
                        .orderByDesc(CollectionFrequencyDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, collectionFrequencyDO -> {
            CollectionFrequencyOptionVO vo = new CollectionFrequencyOptionVO();
            vo.setLabel(collectionFrequencyDO.getFrequencyName());
            vo.setValue(collectionFrequencyDO.getFrequencyCode());
            return vo;
        });
    }
}