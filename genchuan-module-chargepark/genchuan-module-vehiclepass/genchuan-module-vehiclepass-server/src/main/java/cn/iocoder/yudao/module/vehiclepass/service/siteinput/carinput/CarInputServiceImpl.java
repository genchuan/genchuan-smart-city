package cn.iocoder.yudao.module.vehiclepass.service.siteinput.carinput;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputAuditReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputConfirmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputCorrectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo.CarInputSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.carinput.CarInputDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.siteinput.carinput.CarInputMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.INPUT_NOT_EXISTS;


/**
 * 车辆录入 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CarInputServiceImpl implements CarInputService {

    @Resource
    private CarInputMapper inputMapper;

    @Override
    public Long createInput(CarInputSaveReqVO createReqVO) {
        // 插入
        CarInputDO input = BeanUtils.toBean(createReqVO, CarInputDO.class);
        inputMapper.insert(input);

        // 返回
        return input.getId();
    }

    @Override
    public void updateInput(CarInputSaveReqVO updateReqVO) {
        // 校验存在
        validateInputExists(updateReqVO.getId());
        // 更新
        CarInputDO updateObj = BeanUtils.toBean(updateReqVO, CarInputDO.class);
        inputMapper.updateById(updateObj);
    }

    @Override
    public void deleteInput(Long id) {
        // 校验存在
        validateInputExists(id);
        // 删除
        inputMapper.deleteById(id);
    }

    @Override
    public void deleteInputListByIds(List<Long> ids) {
        // 删除
        inputMapper.deleteByIds(ids);
    }


    private void validateInputExists(Long id) {
        if (inputMapper.selectById(id) == null) {
            throw exception(INPUT_NOT_EXISTS);
        }
    }

    @Override
    public CarInputDO getInput(Long id) {
        return inputMapper.selectById(id);
    }

    @Override
    public PageResult<CarInputDO> getInputPage(CarInputPageReqVO pageReqVO) {
        return inputMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CarInputRespVO> getInputPageWithJoin(CarInputPageReqVO pageReqVO) {
        // 构建分页参数
        Page<CarInputRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 调用JOIN查询
        com.baomidou.mybatisplus.core.metadata.IPage<CarInputRespVO> pageResult = inputMapper.selectPageJoin(page, pageReqVO);
        // 转换为PageResult
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public void createInputByReq(CarInputCreateReqVO createReqVO) {
        CarInputDO input = BeanUtils.toBean(createReqVO, CarInputDO.class);
        input.setStatus("待审核");
        input.setInputTime(LocalDateTime.now());
        inputMapper.insert(input);
    }

    @Override
    public void audit(CarInputAuditReqVO reqVO) {
        validateInputExists(reqVO.getId());
        CarInputDO updateObj = new CarInputDO();
        updateObj.setId(reqVO.getId());
        if ("通过".equals(reqVO.getAuditResult())) {
            updateObj.setStatus("已通过");
        } else if ("驳回".equals(reqVO.getAuditResult())) {
            updateObj.setStatus("已驳回");
        }
        updateObj.setAuditTime(LocalDateTime.now());
        updateObj.setAuditComment(reqVO.getAuditComment());
        inputMapper.updateById(updateObj);
    }

    @Override
    public void confirm(Long id) {
        validateInputExists(id);
        CarInputDO updateObj = new CarInputDO();
        updateObj.setId(id);
        updateObj.setStatus("已通过");
        inputMapper.updateById(updateObj);
    }

    @Override
    public void correct(CarInputCorrectReqVO reqVO) {
        validateInputExists(reqVO.getId());
        CarInputDO updateObj = new CarInputDO();
        updateObj.setId(reqVO.getId());
        updateObj.setPlateNo(reqVO.getPlateNo());
        updateObj.setSpaceId(reqVO.getSpaceId());
        updateObj.setAreaId(reqVO.getAreaId());
        updateObj.setRemark(reqVO.getRemark());
        inputMapper.updateById(updateObj);
    }

}