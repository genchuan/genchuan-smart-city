package cn.iocoder.yudao.module.smartcity.service.drainagepermitapply;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagepermitapply.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagepermitapply.DrainagePermitApplyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.apache.ibatis.type.JdbcType;

/**
 * 排水许可证申请 Service 接口
 *
 * @author 超级管理员
 */
public interface DrainagePermitApplyService {

//    void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException;
//
//    List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException;

    /**
     * 创建排水许可证申请
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDrainagePermitApply(@Valid DrainagePermitApplySaveReqVO createReqVO);

    /**
     * 更新排水许可证申请
     *
     * @param updateReqVO 更新信息
     */
    void updateDrainagePermitApply(@Valid DrainagePermitApplySaveReqVO updateReqVO);

    /**
     * 删除排水许可证申请
     *
     * @param id 编号
     */
    void deleteDrainagePermitApply(Long id);

    /**
     * 获得排水许可证申请
     *
     * @param id 编号
     * @return 排水许可证申请
     */
    DrainagePermitApplyDO getDrainagePermitApply(Long id);

    /**
     * 获得排水许可证申请分页
     *
     * @param pageReqVO 分页查询
     * @return 排水许可证申请分页
     */
    PageResult<DrainagePermitApplyDO> getDrainagePermitApplyPage(DrainagePermitApplyPageReqVO pageReqVO);


}