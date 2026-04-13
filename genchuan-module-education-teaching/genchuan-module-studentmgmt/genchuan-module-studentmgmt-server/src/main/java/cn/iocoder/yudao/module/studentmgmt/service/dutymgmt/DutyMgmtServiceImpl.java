package cn.iocoder.yudao.module.studentmgmt.service.dutymgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dutymgmt.DutyMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.dutymgmt.DutyMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 值班管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DutyMgmtServiceImpl implements DutyMgmtService {

    @Resource
    private DutyMgmtMapper dutyMgmtMapper;

    @Override
    public Long createDutyMgmt(DutyMgmtSaveReqVO createReqVO) {
        // 插入
        DutyMgmtDO dutyMgmt = BeanUtils.toBean(createReqVO, DutyMgmtDO.class);
        dutyMgmtMapper.insert(dutyMgmt);

        // 返回
        return dutyMgmt.getId();
    }

    @Override
    public void updateDutyMgmt(DutyMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateDutyMgmtExists(updateReqVO.getId());
        // 更新
        DutyMgmtDO updateObj = BeanUtils.toBean(updateReqVO, DutyMgmtDO.class);
        dutyMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteDutyMgmt(Long id) {
        // 校验存在
        validateDutyMgmtExists(id);
        // 删除
        dutyMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteDutyMgmtListByIds(List<Long> ids) {
        // 删除
        dutyMgmtMapper.deleteByIds(ids);
        }


    private void validateDutyMgmtExists(Long id) {
        if (dutyMgmtMapper.selectById(id) == null) {
            throw exception(DUTY_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public DutyMgmtDO getDutyMgmt(Long id) {
        return dutyMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<DutyMgmtDO> getDutyMgmtPage(DutyMgmtPageReqVO pageReqVO) {
        return dutyMgmtMapper.selectPage(pageReqVO);
    }

}