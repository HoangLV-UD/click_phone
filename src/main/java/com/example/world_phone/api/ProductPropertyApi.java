package com.example.world_phone.api;


import com.example.world_phone.dto.request.imei.ExcelRequest;
import com.example.world_phone.dto.request.orderinvoice.OrderInvoiceRequest;
import com.example.world_phone.dto.request.product_property.ProductPropertyRequest;
import com.example.world_phone.dto.respone.order_detail.OrderDetailRespone;
import com.example.world_phone.dto.respone.product.ProductPropertyRespone;
import com.example.world_phone.service.IProductPropertyService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-property")
public class ProductPropertyApi {

    private final IProductPropertyService service;


    @GetMapping("{id}")
    public ResponseEntity<?> findByID(@PathVariable("id") Long id){
        OrderDetailRespone respone = service.findById(id);
        return ResponseEntity.ok().body(respone);
    }



    @RequestMapping(value = "/{romId}/{colorId}" , method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity<?> updatImei(@PathVariable("romId") Long romId
            , @PathVariable("colorId") Long colorId
            , @ModelAttribute MultipartFile  formData) throws IOException {
        Cell cellFile;
        Row rowFile;
        InputStream input = formData.getInputStream();
        if(!formData.getOriginalFilename().endsWith("xlsx")){
            return ResponseEntity.badRequest().body("false");
        }else{
            XSSFWorkbook workbook = new XSSFWorkbook(input);
            XSSFSheet worksheet = workbook.getSheetAt(0);
            rowFile = worksheet.getRow(1);
            if(rowFile != null){
                cellFile = rowFile.getCell(1000);
                if(!cellFile.toString().equals("DSIM")  ){
                    System.out.println("sai file mấu");
                    return ResponseEntity.internalServerError().body("false");
                }
            }else{
                return ResponseEntity.internalServerError().body("false");
            }
            List<String> list = new ArrayList<>();
            for(int i=0;i<worksheet.getPhysicalNumberOfRows() ;i++) {
                XSSFRow row = worksheet.getRow(i);
                if(list.size() > 0){
                    boolean check = true;
                    for (String a : list){
                        if(a.equals(row.getCell(0).getRawValue())){
                            check = false;
                            break;
                        }
                    }
                    if(check){
                        list.add(row.getCell(0).getRawValue());
                    }
                }else {
                    list.add(row.getCell(0).getRawValue());
                }
            }
            if(service.addImei(String.valueOf(romId), String.valueOf(colorId), list)){
                return ResponseEntity.ok().body(new OrderInvoiceRequest());
            }
        }
        return ResponseEntity.badRequest().body("false");
    }

    @PostMapping("")
    public ResponseEntity<?> getProductProperty(@RequestBody ProductPropertyRequest request){
        List<ProductPropertyRespone> list = new ArrayList<>();
        if(request.getColorId() == null){
            list = service.findByRom(Long.valueOf(request.getRomId()));
            if(list.size() > 0){
                return ResponseEntity.ok().body(list);
            }
        }else {
            list = service.findByRomAndColor(request.getRomId(), request.getColorId());
            if(list.size() > 0){
                return ResponseEntity.ok().body(list.get(0));
            }
        }

        return ResponseEntity.ok().body("false");
    }

    @PutMapping("")
    public ResponseEntity<?> udpateProductProperty(@RequestBody ProductPropertyRequest request){
        ProductPropertyRespone respone = service.updateProductProperty(request);
        if(respone != null){
            return ResponseEntity.ok().body(respone);
        }
        return ResponseEntity.badRequest().body("false");
    }

    @PutMapping("/status")
    public ResponseEntity<?> updateProductPropertyStatus(@RequestBody ProductPropertyRequest request){
        String check = service.udpateStatusProductProperty(request);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }
        return ResponseEntity.badRequest().body("false");
    }

}
