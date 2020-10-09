package com.hoangdocuc.controller.email;

import com.hoangdocuc.dto.ProductDTO;
import com.hoangdocuc.service.IProductService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class CreateExcelController {
    @Autowired
    private IProductService iProductService;

    @GetMapping(value = {"/excel"})
    public String createExcel(@RequestParam("email") String email) throws IOException {
        List<ProductDTO> list = iProductService.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");
        int rownum = 0;
        Cell cell;
        Row row;
        HSSFCellStyle style = createStyleForTitle(workbook);
        row = sheet.createRow(rownum);
        // Id
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Id");
        cell.setCellStyle(style);
        // Name
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Name");
        cell.setCellStyle(style);
        // Image
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Image");
        cell.setCellStyle(style);
        // Price
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Price");
        cell.setCellStyle(style);
        // Quantity
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Quantity");
        cell.setCellStyle(style);

        for (ProductDTO dto : list) {
            rownum++;
            row = sheet.createRow(rownum);

            // Id (A)
            cell = row.createCell(0, CellType.STRING);
            if(dto.getId()!=null) {
                cell.setCellValue(dto.getId());
            }else
            {
                cell.setCellValue("");
            }
            // Name (B)
            cell = row.createCell(1, CellType.STRING);
            if(dto.getName()!=null) {
                cell.setCellValue(dto.getName());
            }else {
                cell.setCellValue("");
            }
            // Image (C)
            cell = row.createCell(2, CellType.STRING);
            if(dto.getImage()!=null) {
                cell.setCellValue(dto.getImage());
            }else {
                cell.setCellValue("");
            }
            // Price (D)
            cell = row.createCell(3, CellType.STRING);
            if(dto.getPrice()!=null) {
                cell.setCellValue(dto.getPrice());
            } else {
                cell.setCellValue("");
            }
            // Quantity (D)
            cell = row.createCell(4, CellType.STRING);
            if(dto.getQuantity()!=null) {
                cell.setCellValue(dto.getQuantity());
            }else {
                cell.setCellValue("");
            }
        }
        File file = new File("C:/demo/product.xls");
        file.getParentFile().mkdirs();
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
        return "redirect:/sendemail?email="+email;
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}
