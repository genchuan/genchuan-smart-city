package cn.iocoder.yudao.module.industry.service.park.user.parkvisitor;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo.ParkVisitorPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkvisitor.vo.ParkVisitorSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkvisitor.ParkVisitorDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkvisitor.ParkVisitorMapper;
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
 * 访客 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkVisitorServiceImpl implements ParkVisitorService {

    @Resource
    private ParkVisitorMapper parkVisitorMapper;

    @Override
    public Long createParkVisitor(ParkVisitorSaveReqVO createReqVO) {
        // 插入
        ParkVisitorDO parkVisitor = BeanUtils.toBean(createReqVO, ParkVisitorDO.class);
        parkVisitorMapper.insert(parkVisitor);
        // 返回
        return parkVisitor.getId();
    }

    @Override
    public void updateParkVisitor(ParkVisitorSaveReqVO updateReqVO) {
        // 校验存在
        validateParkVisitorExists(updateReqVO.getId());
        // 更新
        ParkVisitorDO updateObj = BeanUtils.toBean(updateReqVO, ParkVisitorDO.class);
        parkVisitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkVisitor(Long id) {
        // 校验存在
        validateParkVisitorExists(id);
        // 删除
        parkVisitorMapper.deleteById(id);
    }

    private void validateParkVisitorExists(Long id) {
        if (parkVisitorMapper.selectById(id) == null) {
            throw exception(PARK_VISITOR_NOT_EXISTS);
        }
    }

    @Override
    public ParkVisitorDO getParkVisitor(Long id) {
        return parkVisitorMapper.selectById(id);
    }

    @Override
    public PageResult<ParkVisitorDO> getParkVisitorPage(ParkVisitorPageReqVO pageReqVO) {
        return parkVisitorMapper.selectPage(pageReqVO);
    }

}
