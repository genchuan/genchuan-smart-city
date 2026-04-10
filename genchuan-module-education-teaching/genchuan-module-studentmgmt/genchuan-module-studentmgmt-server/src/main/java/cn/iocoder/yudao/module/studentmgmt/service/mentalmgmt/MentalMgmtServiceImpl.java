package cn.iocoder.yudao.module.studentmgmt.service.mentalmgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.mentalmgmt.MentalMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.mentalmgmt.MentalMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 心理管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MentalMgmtServiceImpl implements MentalMgmtService {

    @Resource
    private MentalMgmtMapper mentalMgmtMapper;

    @Override
    public Long createMentalMgmt(MentalMgmtSaveReqVO createReqVO) {
        // 插入
        MentalMgmtDO mentalMgmt = BeanUtils.toBean(createReqVO, MentalMgmtDO.class);
        mentalMgmtMapper.insert(mentalMgmt);

        // 返回
        return mentalMgmt.getId();
    }

    @Override
    public void updateMentalMgmt(MentalMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateMentalMgmtExists(updateReqVO.getId());
        // 更新
        MentalMgmtDO updateObj = BeanUtils.toBean(updateReqVO, MentalMgmtDO.class);
        mentalMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteMentalMgmt(Long id) {
        // 校验存在
        validateMentalMgmtExists(id);
        // 删除
        mentalMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteMentalMgmtListByIds(List<Long> ids) {
        // 删除
        mentalMgmtMapper.deleteByIds(ids);
        }


    private void validateMentalMgmtExists(Long id) {
        if (mentalMgmtMapper.selectById(id) == null) {
            throw exception(MENTAL_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public MentalMgmtDO getMentalMgmt(Long id) {
        return mentalMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<MentalMgmtDO> getMentalMgmtPage(MentalMgmtPageReqVO pageReqVO) {
        return mentalMgmtMapper.selectPage(pageReqVO);
    }

}