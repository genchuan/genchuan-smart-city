package cn.iocoder.yudao.module.studentmgmt.service.repairmgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.repairmgmt.RepairMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.repairmgmt.RepairMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 报修管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RepairMgmtServiceImpl implements RepairMgmtService {

    @Resource
    private RepairMgmtMapper repairMgmtMapper;

    @Override
    public Long createRepairMgmt(RepairMgmtSaveReqVO createReqVO) {
        // 插入
        RepairMgmtDO repairMgmt = BeanUtils.toBean(createReqVO, RepairMgmtDO.class);
        repairMgmtMapper.insert(repairMgmt);

        // 返回
        return repairMgmt.getId();
    }

    @Override
    public void updateRepairMgmt(RepairMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateRepairMgmtExists(updateReqVO.getId());
        // 更新
        RepairMgmtDO updateObj = BeanUtils.toBean(updateReqVO, RepairMgmtDO.class);
        repairMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteRepairMgmt(Long id) {
        // 校验存在
        validateRepairMgmtExists(id);
        // 删除
        repairMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteRepairMgmtListByIds(List<Long> ids) {
        // 删除
        repairMgmtMapper.deleteByIds(ids);
        }


    private void validateRepairMgmtExists(Long id) {
        if (repairMgmtMapper.selectById(id) == null) {
            throw exception(REPAIR_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public RepairMgmtDO getRepairMgmt(Long id) {
        return repairMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<RepairMgmtDO> getRepairMgmtPage(RepairMgmtPageReqVO pageReqVO) {
        return repairMgmtMapper.selectPage(pageReqVO);
    }

}