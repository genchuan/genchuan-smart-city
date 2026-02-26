package cn.iocoder.yudao.module.envir.service.river;

import cn.iocoder.yudao.module.envir.dal.dataobject.publicinstitution.PublicInstitutionDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.river.RiverDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.river.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.river.RiverMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 河道 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RiverServiceImpl implements RiverService {

    @Resource
    private RiverMapper riverMapper;

    @Override
    public Long createRiver(RiverSaveReqVO createReqVO) {
        // 插入
        RiverDO river = BeanUtils.toBean(createReqVO, RiverDO.class);
        riverMapper.insert(river);
        // 返回
        return river.getId();
    }

    @Override
    public void updateRiver(RiverSaveReqVO updateReqVO) {
        // 校验存在
        validateRiverExists(updateReqVO.getId());
        // 更新
        RiverDO updateObj = BeanUtils.toBean(updateReqVO, RiverDO.class);
        riverMapper.updateById(updateObj);
    }

    @Override
    public void deleteRiver(Long id) {
        // 校验存在
        validateRiverExists(id);
        // 删除
        riverMapper.deleteById(id);
    }

    private void validateRiverExists(Long id) {
        if (riverMapper.selectById(id) == null) {
            throw exception(RIVER_NOT_EXISTS);
        }
    }

    @Override
    public RiverDO getRiver(Long id) {
        return riverMapper.selectById(id);
    }

    @Override
    public PageResult<RiverDO> getRiverPage(RiverPageReqVO pageReqVO) {
        return riverMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RiverDetailDO> getRiverListDetail() {
        return riverMapper.selectListDetail();
    }
}