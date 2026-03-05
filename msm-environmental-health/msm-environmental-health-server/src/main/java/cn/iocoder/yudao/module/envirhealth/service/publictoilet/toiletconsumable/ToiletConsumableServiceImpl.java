package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletconsumable;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable.ToiletConsumablePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable.ToiletConsumableSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletConsumableDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletConsumableMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 公厕耗材配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ToiletConsumableServiceImpl implements ToiletConsumableService {

    @Resource
    private ToiletConsumableMapper toiletConsumableMapper;

    @Override
    public Long createToiletConsumable(ToiletConsumableSaveReqVO createReqVO) {
        // 插入
        ToiletConsumableDO toiletConsumable = BeanUtils.toBean(createReqVO, ToiletConsumableDO.class);
        toiletConsumableMapper.insert(toiletConsumable);
        // 返回
        return toiletConsumable.getId();
    }

    @Override
    public void updateToiletConsumable(ToiletConsumableSaveReqVO updateReqVO) {
        // 校验存在
        validateToiletConsumableExists(updateReqVO.getId());
        // 更新
        ToiletConsumableDO updateObj = BeanUtils.toBean(updateReqVO, ToiletConsumableDO.class);
        toiletConsumableMapper.updateById(updateObj);
    }

    @Override
    public void deleteToiletConsumable(Long id) {
        // 校验存在
        validateToiletConsumableExists(id);
        // 删除
        toiletConsumableMapper.deleteById(id);
    }

    private void validateToiletConsumableExists(Long id) {
        if (toiletConsumableMapper.selectById(id) == null) {
            throw exception(TOILET_CONSUMABLE_NOT_EXISTS);
        }
    }

    @Override
    public ToiletConsumableDO getToiletConsumable(Long id) {
        return toiletConsumableMapper.selectById(id);
    }

    @Override
    public PageResult<ToiletConsumableDO> getToiletConsumablePage(ToiletConsumablePageReqVO pageReqVO) {
        return toiletConsumableMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ToiletConsumableDetailDO> getToiletConsumableDetailPage(ToiletConsumablePageReqVO pageReqVO) {
        Long total = toiletConsumableMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<ToiletConsumableDetailDO> list = toiletConsumableMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}