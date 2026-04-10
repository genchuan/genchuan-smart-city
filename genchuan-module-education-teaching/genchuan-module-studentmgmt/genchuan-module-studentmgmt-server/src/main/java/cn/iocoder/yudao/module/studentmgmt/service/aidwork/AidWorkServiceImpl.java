package cn.iocoder.yudao.module.studentmgmt.service.aidwork;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.aidwork.AidWorkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.aidwork.AidWorkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 奖助勤贷 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AidWorkServiceImpl implements AidWorkService {

    @Resource
    private AidWorkMapper aidWorkMapper;

    @Override
    public Long createAidWork(AidWorkSaveReqVO createReqVO) {
        // 插入
        AidWorkDO aidWork = BeanUtils.toBean(createReqVO, AidWorkDO.class);
        aidWorkMapper.insert(aidWork);

        // 返回
        return aidWork.getId();
    }

    @Override
    public void updateAidWork(AidWorkSaveReqVO updateReqVO) {
        // 校验存在
        validateAidWorkExists(updateReqVO.getId());
        // 更新
        AidWorkDO updateObj = BeanUtils.toBean(updateReqVO, AidWorkDO.class);
        aidWorkMapper.updateById(updateObj);
    }

    @Override
    public void deleteAidWork(Long id) {
        // 校验存在
        validateAidWorkExists(id);
        // 删除
        aidWorkMapper.deleteById(id);
    }

    @Override
        public void deleteAidWorkListByIds(List<Long> ids) {
        // 删除
        aidWorkMapper.deleteByIds(ids);
        }


    private void validateAidWorkExists(Long id) {
        if (aidWorkMapper.selectById(id) == null) {
            throw exception(AID_WORK_NOT_EXISTS);
        }
    }

    @Override
    public AidWorkDO getAidWork(Long id) {
        return aidWorkMapper.selectById(id);
    }

    @Override
    public PageResult<AidWorkDO> getAidWorkPage(AidWorkPageReqVO pageReqVO) {
        return aidWorkMapper.selectPage(pageReqVO);
    }

}