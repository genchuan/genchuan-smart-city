package cn.iocoder.yudao.module.studentmgmt.service.treatmgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.treatmgmt.TreatMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.treatmgmt.TreatMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 就诊管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TreatMgmtServiceImpl implements TreatMgmtService {

    @Resource
    private TreatMgmtMapper treatMgmtMapper;

    @Override
    public Long createTreatMgmt(TreatMgmtSaveReqVO createReqVO) {
        // 插入
        TreatMgmtDO treatMgmt = BeanUtils.toBean(createReqVO, TreatMgmtDO.class);
        treatMgmtMapper.insert(treatMgmt);

        // 返回
        return treatMgmt.getId();
    }

    @Override
    public void updateTreatMgmt(TreatMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateTreatMgmtExists(updateReqVO.getId());
        // 更新
        TreatMgmtDO updateObj = BeanUtils.toBean(updateReqVO, TreatMgmtDO.class);
        treatMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteTreatMgmt(Long id) {
        // 校验存在
        validateTreatMgmtExists(id);
        // 删除
        treatMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteTreatMgmtListByIds(List<Long> ids) {
        // 删除
        treatMgmtMapper.deleteByIds(ids);
        }


    private void validateTreatMgmtExists(Long id) {
        if (treatMgmtMapper.selectById(id) == null) {
            throw exception(TREAT_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public TreatMgmtDO getTreatMgmt(Long id) {
        return treatMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<TreatMgmtDO> getTreatMgmtPage(TreatMgmtPageReqVO pageReqVO) {
        return treatMgmtMapper.selectPage(pageReqVO);
    }

}