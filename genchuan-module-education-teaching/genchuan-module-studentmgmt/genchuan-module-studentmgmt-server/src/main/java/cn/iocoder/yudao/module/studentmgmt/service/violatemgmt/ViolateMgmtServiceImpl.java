package cn.iocoder.yudao.module.studentmgmt.service.violatemgmt;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.violatemgmt.ViolateMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 违纪管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ViolateMgmtServiceImpl implements ViolateMgmtService {

    @Resource
    private ViolateMgmtMapper violateMgmtMapper;

    @Override
    public Long createViolateMgmt(ViolateMgmtSaveReqVO createReqVO) {
        // 插入
        ViolateMgmtDO violateMgmt = BeanUtils.toBean(createReqVO, ViolateMgmtDO.class);
        violateMgmtMapper.insert(violateMgmt);

        // 返回
        return violateMgmt.getId();
    }

    @Override
    public void updateViolateMgmt(ViolateMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateViolateMgmtExists(updateReqVO.getId());
        // 更新
        ViolateMgmtDO updateObj = BeanUtils.toBean(updateReqVO, ViolateMgmtDO.class);
        violateMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteViolateMgmt(Long id) {
        // 校验存在
        validateViolateMgmtExists(id);
        // 删除
        violateMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteViolateMgmtListByIds(List<Long> ids) {
        // 删除
        violateMgmtMapper.deleteByIds(ids);
        }


    private void validateViolateMgmtExists(Long id) {
        if (violateMgmtMapper.selectById(id) == null) {
            throw exception(VIOLATE_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public ViolateMgmtDO getViolateMgmt(Long id) {
        return violateMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<ViolateMgmtDO> getViolateMgmtPage(ViolateMgmtPageReqVO pageReqVO) {
        return violateMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    public boolean auditViolateMgmtListByIds(List<Long> ids, Long userId) {
        String status = "2";
        Integer i = violateMgmtMapper.auditViolateMgmtListByIds(ids, status, userId);
        if (i > 0 && i == ids.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean push(Long id, Long userId) {
        ViolateMgmtDO violateMgmtDO = violateMgmtMapper.selectById(id);
        if (violateMgmtDO != null) {
            violateMgmtDO.setPushTime(LocalDateTime.now());
        }
        return null;
    }

}