package cn.iocoder.yudao.module.smartcity.service.drainagepermitapply;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagepermitapply.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagepermitapply.DrainagePermitApplyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.drainagepermitapply.DrainagePermitApplyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 排水许可证申请 Service 实现类
 *
 * @author 超级管理员
 */
@Service
@Validated
public class DrainagePermitApplyServiceImpl implements DrainagePermitApplyService {

//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
//        // 将List转为JSON字符串存储
//        ps.setString(i, JSON.toJSONString(parameter));
//    }
//
//    @Override
//    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        // 从JSON字符串解析回List
//        String json = rs.getString(columnName);
//        return JSON.parseArray(json, String.class);
//    }

    @Resource
    private DrainagePermitApplyMapper drainagePermitApplyMapper;

    @Override
    public Long createDrainagePermitApply(DrainagePermitApplySaveReqVO createReqVO) {
        // 插入
//        DrainagePermitApplyDO drainagePermitApply = BeanUtils.toBean(createReqVO, DrainagePermitApplyDO.class);
//        drainagePermitApplyMapper.insert(drainagePermitApply);
        // 返回
//        return drainagePermitApply.getId();

        DrainagePermitApplyDO entity = new DrainagePermitApplyDO();
        BeanUtils.copyProperties(createReqVO, entity);

        // 处理数组转字符串
        if (createReqVO.getWaterQualityReport() != null) {
            entity.setWaterQualityReport(String.join(",", createReqVO.getWaterQualityReport()));
        }

        drainagePermitApplyMapper.insert(entity);
        return entity.getId();
    }

    @Override
    public void updateDrainagePermitApply(DrainagePermitApplySaveReqVO updateReqVO) {
        // 校验存在
        validateDrainagePermitApplyExists(updateReqVO.getId());
        // 更新
        DrainagePermitApplyDO updateObj = BeanUtils.toBean(updateReqVO, DrainagePermitApplyDO.class);
        drainagePermitApplyMapper.updateById(updateObj);
    }

    @Override
    public void deleteDrainagePermitApply(Long id) {
        // 校验存在
        validateDrainagePermitApplyExists(id);
        // 删除
        drainagePermitApplyMapper.deleteById(id);
    }

    private void validateDrainagePermitApplyExists(Long id) {
        if (drainagePermitApplyMapper.selectById(id) == null) {
            throw exception(DRAINAGE_PERMIT_APPLY_NOT_EXISTS);
        }
    }

    @Override
    public DrainagePermitApplyDO getDrainagePermitApply(Long id) {
        return drainagePermitApplyMapper.selectById(id);
    }

    @Override
    public PageResult<DrainagePermitApplyDO> getDrainagePermitApplyPage(DrainagePermitApplyPageReqVO pageReqVO) {
        return drainagePermitApplyMapper.selectPage(pageReqVO);
    }



}