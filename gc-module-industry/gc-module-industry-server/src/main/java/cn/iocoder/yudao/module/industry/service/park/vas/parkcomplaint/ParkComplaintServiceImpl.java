package cn.iocoder.yudao.module.industry.service.park.vas.parkcomplaint;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint.vo.ParkComplaintPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint.vo.ParkComplaintSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkcomplaint.ParkComplaintDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkcomplaint.ParkComplaintMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 投诉记录 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkComplaintServiceImpl implements ParkComplaintService {

    @Resource
    private ParkComplaintMapper parkComplaintMapper;

    @Override
    public Long createParkComplaint(ParkComplaintSaveReqVO createReqVO) {
        // 插入
        ParkComplaintDO parkComplaint = BeanUtils.toBean(createReqVO, ParkComplaintDO.class);
        parkComplaintMapper.insert(parkComplaint);
        // 返回
        return parkComplaint.getId();
    }

    @Override
    public void updateParkComplaint(ParkComplaintSaveReqVO updateReqVO) {
        // 校验存在
        validateParkComplaintExists(updateReqVO.getId());
        // 更新
        ParkComplaintDO updateObj = BeanUtils.toBean(updateReqVO, ParkComplaintDO.class);
        parkComplaintMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkComplaint(Long id) {
        // 校验存在
        validateParkComplaintExists(id);
        // 删除
        parkComplaintMapper.deleteById(id);
    }

    private void validateParkComplaintExists(Long id) {
        if (parkComplaintMapper.selectById(id) == null) {
            throw exception(PARK_COMPLAINT_NOT_EXISTS);
        }
    }

    @Override
    public ParkComplaintDO getParkComplaint(Long id) {
        return parkComplaintMapper.selectById(id);
    }

    @Override
    public PageResult<ParkComplaintDO> getParkComplaintPage(ParkComplaintPageReqVO pageReqVO) {
        return parkComplaintMapper.selectPage(pageReqVO);
    }

}
