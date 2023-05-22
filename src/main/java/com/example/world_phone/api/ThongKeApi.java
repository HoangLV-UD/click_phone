package com.example.world_phone.api;

import com.example.world_phone.dto.respone.ThongKeDto;
import com.example.world_phone.repo.ThongKeRepo;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ThongKeApi {

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ThongKeRepo thongKeRepo;

    @GetMapping("/top-product-sale/{month}/{year}")
    public List<ThongKeDto> thongKe(@PathVariable("month") Integer month, @PathVariable("year") Integer year){
        return thongKeRepo.findStockAkhirPerProductIn(month, year);
    }

    @GetMapping(value = "/export-list/{month}/{year}")
    @ResponseBody
    public ResponseEntity<byte[]> exportList(@PathVariable("month") Integer month, @PathVariable("year") Integer year) {
        try {
            List<ThongKeDto> list = new ArrayList<>();
            list = thongKeRepo.findStockAkhirPerProductIn(month, year);

            Resource resource = new ClassPathResource("excel_template/list.xlsx");
            InputStream ips = resource.getInputStream();

            Workbook wb  = WorkbookFactory.create(ips);
            Sheet sheet = wb.getSheetAt(0);
            Row defaultRow = sheet.getRow(5); // lấy row thứ 5 từ file excel
            CellStyle cellStyle = defaultRow.getRowStyle(); // lấy style từ row gốc
            for (int i = 0; i < list.size(); i++) {
                int rowindex = 5 + i;
                Row row = sheet.getRow(rowindex);
                if(row == null) {
                    row = sheet.createRow(rowindex); //tao the row neu row kh ton tai
                    row.setRowStyle(cellStyle);
                    for (int j = 0; j < 6; j++) {
                        Cell newCell =  row.createCell(j);
                        newCell.setCellStyle(defaultRow.getCell(j).getCellStyle());
                    }

                }
                row.getCell(0).setCellValue(i + 1);
                row.getCell(1).setCellValue(list.get(i).getNameProduct());
                row.getCell(2).setCellValue(list.get(i).getColorProduct());
                row.getCell(3).setCellValue(list.get(i).getRomProduct());
                row.getCell(4).setCellValue(list.get(i).getPriceProduct());
                System.out.println("i "+i+ ": "+list.get(i).getQuantityDaBan());
                if(list.get(i).getQuantityDaBan() != null) {
                    row.getCell(5).setCellValue(list.get(i).getQuantityDaBan());
                }
            }
            ServletOutputStream out = response.getOutputStream();
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment; filename="+ "Danh_sach_dien_thoai_da_ban"+ ".xlsx");
            wb.write(out);
            wb.close();
            out.close();
            byte[] excelData = response.toString().getBytes();
//            return response;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("file.xlsx").build());

            return ResponseEntity.ok().headers(headers).body(excelData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(response.toString().getBytes());
        }
    }
}
