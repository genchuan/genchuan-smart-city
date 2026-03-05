package cn.iocoder.yudao.module.industry.service.park.asset.entryexit;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo.ParkEntryExitPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.entryexit.vo.ParkEntryExitSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.entryexit.ParkEntryExitDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.asset.entryexit.ParkEntryExitMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 出入口信息 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkEntryExitServiceImpl implements ParkEntryExitService {

    @Resource
    private ParkEntryExitMapper parkEntryExitMapper;

    @Override
    public Long createParkEntryExit(ParkEntryExitSaveReqVO createReqVO) {
        // 插入
        ParkEntryExitDO parkEntryExit = BeanUtils.toBean(createReqVO, ParkEntryExitDO.class);
        parkEntryExitMapper.insert(parkEntryExit);
        // 返回
        return parkEntryExit.getId();
    }

    @Override
    public void updateParkEntryExit(ParkEntryExitSaveReqVO updateReqVO) {
        // 校验存在
        validateParkEntryExitExists(updateReqVO.getId());
        // 更新
        ParkEntryExitDO updateObj = BeanUtils.toBean(updateReqVO, ParkEntryExitDO.class);
        parkEntryExitMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkEntryExit(Long id) {
        // 校验存在
        validateParkEntryExitExists(id);
        // 删除
        parkEntryExitMapper.deleteById(id);
    }

    private void validateParkEntryExitExists(Long id) {
        if (parkEntryExitMapper.selectById(id) == null) {
            throw exception(PARK_ENTRY_EXIT_NOT_EXISTS);
        }
    }

    @Override
    public ParkEntryExitDO getParkEntryExit(Long id) {
        return parkEntryExitMapper.selectById(id);
    }

    @Override
    public PageResult<ParkEntryExitDO> getParkEntryExitPage(ParkEntryExitPageReqVO pageReqVO) {
        return parkEntryExitMapper.selectPage(pageReqVO);
    }

}