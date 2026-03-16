package cn.iocoder.yudao.module.evaluate.service.calcway;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway.vo.CalcWayPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.calcway.vo.CalcWaySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.calcway.CalcWayDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.calcway.CalcWayMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.CALC_WAY_NOT_EXISTS;

/**
 * 计算方式字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CalcWayServiceImpl implements CalcWayService {

    @Resource
    private CalcWayMapper calcWayMapper;

    @Override
    public Long createCalcWay(CalcWaySaveReqVO createReqVO) {
        // 插入
        CalcWayDO calcWay = BeanUtils.toBean(createReqVO, CalcWayDO.class);
        calcWayMapper.insert(calcWay);
        // 返回
        return calcWay.getId();
    }

    @Override
    public void updateCalcWay(CalcWaySaveReqVO updateReqVO) {
        // 校验存在
        validateCalcWayExists(updateReqVO.getId());
        // 更新
        CalcWayDO updateObj = BeanUtils.toBean(updateReqVO, CalcWayDO.class);
        calcWayMapper.updateById(updateObj);
    }

    @Override
    public void deleteCalcWay(Long id) {
        // 校验存在
        validateCalcWayExists(id);
        // 删除
        calcWayMapper.deleteById(id);
    }

    private void validateCalcWayExists(Long id) {
        if (calcWayMapper.selectById(id) == null) {
            throw exception(CALC_WAY_NOT_EXISTS);
        }
    }

    @Override
    public CalcWayDO getCalcWay(Long id) {
        return calcWayMapper.selectById(id);
    }

    @Override
    public PageResult<CalcWayDO> getCalcWayPage(CalcWayPageReqVO pageReqVO) {
        return calcWayMapper.selectPage(pageReqVO);
    }

}