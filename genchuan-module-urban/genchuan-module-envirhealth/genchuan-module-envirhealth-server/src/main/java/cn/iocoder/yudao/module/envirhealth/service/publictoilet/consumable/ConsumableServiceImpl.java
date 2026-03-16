package cn.iocoder.yudao.module.envirhealth.service.publictoilet.consumable;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.consumable.ConsumablePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.consumable.ConsumableSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ConsumableDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ConsumableMapper;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.CONSUMABLE_NOT_EXISTS;

/**
 * 耗材字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ConsumableServiceImpl implements ConsumableService {

    @Resource
    private ConsumableMapper consumableMapper;

    @Override
    public Long createConsumable(ConsumableSaveReqVO createReqVO) {
        // 插入
        ConsumableDO consumable = BeanUtils.toBean(createReqVO, ConsumableDO.class);
        consumableMapper.insert(consumable);
        // 返回
        return consumable.getId();
    }

    @Override
    public void updateConsumable(ConsumableSaveReqVO updateReqVO) {
        // 校验存在
        validateConsumableExists(updateReqVO.getId());
        // 更新
        ConsumableDO updateObj = BeanUtils.toBean(updateReqVO, ConsumableDO.class);
        consumableMapper.updateById(updateObj);
    }

    @Override
    public void deleteConsumable(Long id) {
        // 校验存在
        validateConsumableExists(id);
        // 删除
        consumableMapper.deleteById(id);
    }

    private void validateConsumableExists(Long id) {
        if (consumableMapper.selectById(id) == null) {
            throw exception(CONSUMABLE_NOT_EXISTS);
        }
    }

    @Override
    public ConsumableDO getConsumable(Long id) {
        return consumableMapper.selectById(id);
    }

    @Override
    public PageResult<ConsumableDO> getConsumablePage(ConsumablePageReqVO pageReqVO) {
        return consumableMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getConsumableOptions() {

        List<ConsumableDO> list;
        list = consumableMapper.selectList(
                new LambdaQueryWrapperX<ConsumableDO>()
                        .eq(ConsumableDO::getDeleted, 0)
                        .orderByDesc(ConsumableDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, consumableDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(consumableDO.getConsumableName());
            vo.setValue(consumableDO.getConsumableId());
            return vo;
        });
    }
}