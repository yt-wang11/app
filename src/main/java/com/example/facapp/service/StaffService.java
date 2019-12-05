package com.example.facapp.service;

import com.example.facapp.common.ParameterBind;
import com.example.facapp.constant.SQL;
import com.example.facapp.dto.StaffDto;
import com.example.facapp.model.StaffModel;
import com.example.facapp.repository.StaffRepository;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author 姜涛
 * @create 2018-04-14 14:15
 * @desc 订单service
 **/
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*分页查询订单列表*/
    public List<StaffDto> findAllByPage(int limit, int offset, String seach){
        List<StaffModel> staffModels;
        if (!StringUtils.isEmpty(seach)) {
            if (isNumericZidai(seach)) {
                staffModels = staffRepository.findAllById(Integer.valueOf(seach));
            } else {
                staffModels = staffRepository.findByNameLike("%" + seach + "%");
            }
        } else {
            Map param = new HashMap();
            param.put("limit", limit);
            param.put("offset", offset);
            staffModels = jdbcTemplate.query(ParameterBind.bind(SQL.FIND_ORDER_BY_CJ, param), new BeanPropertyRowMapper<>(StaffModel.class));
        }

        List<StaffDto> result = new ArrayList<>();
        for (StaffModel staffModel : staffModels) {
            StaffDto staffDto = new StaffDto();
            staffDto.setId(staffModel.getId());
            staffDto.setName(staffModel.getName());
            staffDto.setAge(staffModel.getAge());
            staffDto.setSex(staffModel.getSex());
            staffDto.setWorkdepartment(staffModel.getWorkdepartment());
            staffDto.setIdcard(staffModel.getIdcard());
            staffDto.setEducation(staffModel.getEducation());
            staffDto.setTitle(staffModel.getTitle());
            staffDto.setEntrytime(staffModel.getEntrytime().toString());
            staffDto.setContractlife(staffModel.getContractlife());
            staffDto.setWorkchangerecord(staffModel.getWorkchangerecord());
            staffDto.setRewardsandpunishmentrecords(staffModel.getRewardsandpunishmentrecords());
            staffDto.setRankevaluationrecord(staffModel.getRankevaluationrecord());
            result.add(staffDto);
        }


        return result;
    }

    public static boolean isNumericZidai(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public Long count(String search){
        Map param = new HashMap();
        if (!StringUtils.isEmpty(search)){
            param.put("Name", search);
            return jdbcTemplate.queryForObject(ParameterBind.bind(SQL.FIND_ORDER_COUNT_BY_ID, param), Long.class);
        }else {
            return jdbcTemplate.queryForObject(ParameterBind.bind(SQL.FIND_ORDER_COUNT, param), Long.class);
        }
    }

    public boolean delete(String ids){
        if (ids.lastIndexOf(",") == ids.length()-1){
            ids = ids.substring(0, ids.length()-1);
        }
        Map param = new HashMap();
        param.put("ids", ids);
        int update = jdbcTemplate.update(ParameterBind.bind(SQL.DELETE_ORDER_BY_XHS, param));
        return update!=0;
    }

    public boolean insert(StaffDto staffDto){
        StaffModel staffModel = new StaffModel();
        staffModel.setId(staffDto.getId());
        staffModel.setName(staffDto.getName());
        staffModel.setAge(staffDto.getAge());
        staffModel.setSex(staffDto.getSex());
        staffModel.setWorkdepartment(staffDto.getWorkdepartment());
        staffModel.setIdcard(staffDto.getIdcard());
        staffModel.setEducation(staffDto.getEducation());
        staffModel.setTitle(staffDto.getTitle());
        staffModel.setEntrytime(Timestamp.valueOf(staffDto.getEntrytime() + " 00:00:00"));
        staffModel.setContractlife(staffDto.getContractlife());
        staffModel.setWorkchangerecord(staffDto.getWorkchangerecord());
        staffModel.setRewardsandpunishmentrecords(staffDto.getRewardsandpunishmentrecords());
        staffModel.setRankevaluationrecord(staffDto.getRankevaluationrecord());
        staffRepository.saveAndFlush(staffModel);

        return true;
    }

    public Map<String, Object> show(Integer id){
        Map param = new HashMap();
        param.put("id", id);
        return jdbcTemplate.queryForMap(ParameterBind.bind(SQL.SHOW_ORDER_BY_XH, param));
    }

    public void export(String ids, HttpServletResponse response){
        // 生成提示信息，
        List<Integer> idList = new ArrayList<>();
        for (String idStr : ids.split(",")) {
            if (StringUtils.isEmpty(idStr)) continue;
            idList.add(Integer.valueOf(idStr));
        }
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            codedFileName = java.net.URLEncoder.encode("员工信息", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename*=utf-8'zh_cn'" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook();
            //产生工作表对象
            HSSFSheet sheet = workbook.createSheet();
            //设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth(15);
            //设置自动换行
            sheet.setFitToPage(true);
            // 设置标题
            HSSFCellStyle titleStyle = workbook.createCellStyle();
            // 居中显示
            titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            // 标题字体
            HSSFFont titleFont = workbook.createFont();
            // 字体大小
            titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titleStyle.setFont(titleFont);
            HSSFCellStyle contentStyle = workbook.createCellStyle();
            contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            HSSFFont contentFont = workbook.createFont();
            contentFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
            contentStyle.setFont(contentFont);
            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            String[] headers = new String[] { "员工号", "姓名", "年龄", "性别", "工作部门",
                    "身份证", "学历", "职称",  "入职时间", "合同年限", "工作变动", "奖惩记录", "职级评定记录"};
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
                cell.setCellStyle(titleStyle);
            }
            //生成数据
            List<StaffModel> list = staffRepository.findByIdIn(idList);
            int rowCount = 1;
            if (!Objects.isNull(list) && !list.isEmpty() ) {
                for (int i = 0; i < list.size(); i++, rowCount++) {
                    HSSFRow dataRow = sheet.createRow(rowCount);
                    StaffModel model = list.get(i);
                    // 员工号
                    HSSFCell cell0 = dataRow.createCell(0);
                    cell0.setCellValue(model.getId());
                    cell0.setCellStyle(contentStyle);

                    // 姓名
                    HSSFCell cell1 = dataRow.createCell(1);
                    cell1.setCellValue(Objects.isNull(model.getName()) ? "" : model.getName());
                    cell1.setCellStyle(contentStyle);

                    // 年龄
                    HSSFCell cell2 = dataRow.createCell(2);
                    cell2.setCellValue(Objects.isNull(model.getAge()) ? "" : model.getAge().toString());
                    cell2.setCellStyle(contentStyle);
                    cell2.setAsActiveCell();

                    // 性别
                    HSSFCell cell3 = dataRow.createCell(3);
                    cell3.setCellValue(Objects.isNull(model.getSex()) ? "" : model.getSex());
                    cell3.setCellStyle(contentStyle);
                    cell3.setAsActiveCell();

                    // 工作部门
                    HSSFCell cell4 = dataRow.createCell(4);
                    cell4.setCellValue(Objects.isNull(model.getWorkdepartment()) ? "" : model.getWorkdepartment());
                    cell4.setCellStyle(contentStyle);

                    // "身份证", "学历", "职称",  "入职时间", "合同年限", "工作变动", "奖惩记录", "职级评定记录"
                    HSSFCell cell5 = dataRow.createCell(5);
                    cell5.setCellValue(Objects.isNull(model.getIdcard()) ? "" : model.getIdcard());
                    cell5.setCellStyle(contentStyle);

                    HSSFCell cell6 = dataRow.createCell(6);
                    cell6.setCellValue(Objects.isNull(model.getEducation()) ? "" : model.getEducation());
                    cell6.setCellStyle(contentStyle);

                    HSSFCell cell7 = dataRow.createCell(7);
                    cell7.setCellValue(Objects.isNull(model.getTitle()) ? "" : model.getTitle());
                    cell7.setCellStyle(contentStyle);

                    HSSFCell cell8 = dataRow.createCell(8);
                    cell8.setCellValue(Objects.isNull(model.getEntrytime()) ? "" : model.getEntrytime().toString()
                            .substring(0, model.getEntrytime().toString().indexOf(".")));
                    cell8.setCellStyle(contentStyle);

                    HSSFCell cell9 = dataRow.createCell(9);
                    cell9.setCellValue(Objects.isNull(model.getContractlife()) ? "" : model.getContractlife());
                    cell9.setCellStyle(contentStyle);

                    HSSFCell cell10 = dataRow.createCell(10);
                    cell10.setCellValue(Objects.isNull(model.getWorkchangerecord()) ? "" : model.getWorkchangerecord());
                    cell10.setCellStyle(contentStyle);

                    HSSFCell cell11 = dataRow.createCell(11);
                    cell11.setCellValue(Objects.isNull(model.getRewardsandpunishmentrecords()) ? "" : model.getRewardsandpunishmentrecords());
                    cell11.setCellStyle(contentStyle);

                    HSSFCell cell12 = dataRow.createCell(12);
                    cell12.setCellValue(Objects.isNull(model.getRankevaluationrecord()) ? "" : model.getRankevaluationrecord());
                    cell12.setCellStyle(contentStyle);

                }
            }
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fOut != null;
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
