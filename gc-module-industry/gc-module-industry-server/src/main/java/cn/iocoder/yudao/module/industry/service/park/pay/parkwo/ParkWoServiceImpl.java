package cn.iocoder.yudao.module.industry.service.park.pay.parkwo;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoCreateReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkwo.ParkWoDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkwo.ParkWoMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.PARK_WO_NOT_EXISTS;


/**
 * 停车订单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkWoServiceImpl implements ParkWoService {

    @Resource
    private ParkWoMapper parkWoMapper;

    @Override
    public Long createParkWo(ParkWoCreateReqVO createReqVO) {
        //一、完善字段
        //1.去除id，让数据库自动填充
        createReqVO.setId(null);
        //2.wo_id 和 wo_no 用UUID  ,32 位 UUID
//        createReqVO.setWoId(UUID.randomUUID().toString().replace("-", ""));
        createReqVO.setWoNo(UUID.randomUUID().toString().replace("-", ""));
        //3.TODO 完善和停车场名称关联，点击停车场名称可以跳转到对应停车场

        // 插入
        ParkWoDO parkWo = BeanUtils.toBean(createReqVO, ParkWoDO.class);
        parkWoMapper.insert(parkWo);
        // 返回
        return parkWo.getId();
    }

    @Override
    public void updateParkWo(ParkWoSaveReqVO updateReqVO) {
        // 校验存在
        validateParkWoExists(updateReqVO.getId());
        // 更新
        ParkWoDO updateObj = BeanUtils.toBean(updateReqVO, ParkWoDO.class);
        parkWoMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkWo(Long id) {
        // 校验存在
        validateParkWoExists(id);
        // 删除
        parkWoMapper.deleteById(id);
    }

    private void validateParkWoExists(Long id) {
        if (parkWoMapper.selectById(id) == null) {
            throw exception(new ErrorCode(500,"该数据不存在于后端数据库"));
        }
    }

    @Override
    public ParkWoDO getParkWo(Long id) {
        return parkWoMapper.selectById(id);
    }

    @Override
    public PageResult<ParkWoDO> getParkWoPage(ParkWoPageReqVO pageReqVO) {
        return parkWoMapper.selectPage(pageReqVO);
    }

}
