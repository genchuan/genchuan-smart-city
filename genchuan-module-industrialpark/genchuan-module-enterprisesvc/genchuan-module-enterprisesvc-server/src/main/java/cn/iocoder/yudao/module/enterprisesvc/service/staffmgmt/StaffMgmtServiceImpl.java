package cn.iocoder.yudao.module.enterprisesvc.service.staffmgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.enterprisesvc.controller.admin.staffmgmt.vo.*;
import cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.staffmgmt.StaffMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.enterprisesvc.dal.mysql.staffmgmt.StaffMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.enterprisesvc.enums.ErrorCodeConstants.*;

/**
 * 企业员工 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class StaffMgmtServiceImpl implements StaffMgmtService {

    @Resource
    private StaffMgmtMapper staffMgmtMapper;

    @Override
    public Long createStaffMgmt(StaffMgmtSaveReqVO createReqVO) {
        // 插入
        StaffMgmtDO staffMgmt = BeanUtils.toBean(createReqVO, StaffMgmtDO.class);
        staffMgmtMapper.insert(staffMgmt);

        // 返回
        return staffMgmt.getId();
    }

    @Override
    public void updateStaffMgmt(StaffMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateStaffMgmtExists(updateReqVO.getId());
        // 更新
        StaffMgmtDO updateObj = BeanUtils.toBean(updateReqVO, StaffMgmtDO.class);
        staffMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteStaffMgmt(Long id) {
        // 校验存在
        validateStaffMgmtExists(id);
        // 删除
        staffMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteStaffMgmtListByIds(List<Long> ids) {
        // 删除
        staffMgmtMapper.deleteByIds(ids);
        }


    private void validateStaffMgmtExists(Long id) {
        if (staffMgmtMapper.selectById(id) == null) {
            throw exception(STAFF_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public StaffMgmtDO getStaffMgmt(Long id) {
        return staffMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<StaffMgmtDO> getStaffMgmtPage(StaffMgmtPageReqVO pageReqVO) {
        return staffMgmtMapper.selectPage(pageReqVO);
    }

}