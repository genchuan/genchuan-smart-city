package cn.iocoder.yudao.module.industry.service.park.through.carentry;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo.ParkCarEntryPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carentry.vo.ParkCarEntrySaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carentry.ParkCarEntryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.through.carentry.ParkCarEntryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.PARK_CAR_ENTRY_NOT_EXISTS;

/**
 * 入场记录 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkCarEntryServiceImpl implements ParkCarEntryService {

    @Resource
    private ParkCarEntryMapper parkCarEntryMapper;

    @Override
    public Long createParkCarEntry(ParkCarEntrySaveReqVO createReqVO) {
        // 插入
        ParkCarEntryDO parkCarEntry = BeanUtils.toBean(createReqVO, ParkCarEntryDO.class);
        parkCarEntryMapper.insert(parkCarEntry);
        // 返回
        return parkCarEntry.getId();
    }

    @Override
    public void updateParkCarEntry(ParkCarEntrySaveReqVO updateReqVO) {
        // 校验存在
        validateParkCarEntryExists(updateReqVO.getId());
        // 更新
        ParkCarEntryDO updateObj = BeanUtils.toBean(updateReqVO, ParkCarEntryDO.class);
        parkCarEntryMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkCarEntry(Long id) {
        // 校验存在
        validateParkCarEntryExists(id);
        // 删除
        parkCarEntryMapper.deleteById(id);
    }

    private void validateParkCarEntryExists(Long id) {
        if (parkCarEntryMapper.selectById(id) == null) {
            throw exception(PARK_CAR_ENTRY_NOT_EXISTS);
        }
    }

    @Override
    public ParkCarEntryDO getParkCarEntry(Long id) {
        return parkCarEntryMapper.selectById(id);
    }

    @Override
    public PageResult<ParkCarEntryDO> getParkCarEntryPage(ParkCarEntryPageReqVO pageReqVO) {
        return parkCarEntryMapper.selectPage(pageReqVO);
    }

}