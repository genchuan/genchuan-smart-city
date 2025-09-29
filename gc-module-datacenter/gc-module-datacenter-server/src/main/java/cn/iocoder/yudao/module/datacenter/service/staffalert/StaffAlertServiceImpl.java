package cn.iocoder.yudao.module.datacenter.service.staffalert;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.staffalert.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.staffalert.StaffAlertDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.staffalert.StaffAlertMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 人员异常报警 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class StaffAlertServiceImpl implements StaffAlertService {

    @Resource
    private StaffAlertMapper staffAlertMapper;

    @Override
    public Long createStaffAlert(StaffAlertSaveReqVO createReqVO) {
        // 插入
        StaffAlertDO staffAlert = BeanUtils.toBean(createReqVO, StaffAlertDO.class);
        staffAlertMapper.insert(staffAlert);
        // 返回
        return staffAlert.getId();
    }

    @Override
    public void updateStaffAlert(StaffAlertSaveReqVO updateReqVO) {
        // 校验存在
        validateStaffAlertExists(updateReqVO.getId());
        // 更新
        StaffAlertDO updateObj = BeanUtils.toBean(updateReqVO, StaffAlertDO.class);
        staffAlertMapper.updateById(updateObj);
    }

    @Override
    public void deleteStaffAlert(Long id) {
        // 校验存在
        validateStaffAlertExists(id);
        // 删除
        staffAlertMapper.deleteById(id);
    }

    private void validateStaffAlertExists(Long id) {
        if (staffAlertMapper.selectById(id) == null) {
            throw exception(STAFF_ALERT_NOT_EXISTS);
        }
    }

    @Override
    public StaffAlertDO getStaffAlert(Long id) {
        return staffAlertMapper.selectById(id);
    }

    @Override
    public PageResult<StaffAlertDO> getStaffAlertPage(StaffAlertPageReqVO pageReqVO) {
        return staffAlertMapper.selectPage(pageReqVO);
    }

    @Override
    public List<StaffAlertDO> getStaffAlertList() {
        return staffAlertMapper.selectList();
    }

}