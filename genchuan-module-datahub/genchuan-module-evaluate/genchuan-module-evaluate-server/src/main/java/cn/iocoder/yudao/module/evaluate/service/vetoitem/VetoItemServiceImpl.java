package cn.iocoder.yudao.module.evaluate.service.vetoitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo.VetoItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo.VetoItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.vetoitem.VetoItemDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.vetoitem.VetoItemMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.VETO_ITEM_NOT_EXISTS;

/**
 * 否决项 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class VetoItemServiceImpl implements VetoItemService {

    @Resource
    private VetoItemMapper vetoItemMapper;

    @Override
    public Long createVetoItem(VetoItemSaveReqVO createReqVO) {
        // 插入
        VetoItemDO vetoItem = BeanUtils.toBean(createReqVO, VetoItemDO.class);
        vetoItemMapper.insert(vetoItem);

        // 返回
        return vetoItem.getId();
    }

    @Override
    public void updateVetoItem(VetoItemSaveReqVO updateReqVO) {
        // 校验存在
        validateVetoItemExists(updateReqVO.getId());
        // 更新
        VetoItemDO updateObj = BeanUtils.toBean(updateReqVO, VetoItemDO.class);
        vetoItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteVetoItem(Long id) {
        // 校验存在
        validateVetoItemExists(id);
        // 删除
        vetoItemMapper.deleteById(id);
    }

    @Override
        public void deleteVetoItemListByIds(List<Long> ids) {
        // 删除
        vetoItemMapper.deleteByIds(ids);
        }


    private void validateVetoItemExists(Long id) {
        if (vetoItemMapper.selectById(id) == null) {
            throw exception(VETO_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public VetoItemDO getVetoItem(Long id) {
        return vetoItemMapper.selectById(id);
    }

    @Override
    public PageResult<VetoItemDO> getVetoItemPage(VetoItemPageReqVO pageReqVO) {
        return vetoItemMapper.selectPage(pageReqVO);
    }

}