package com.huntercodexs.workbook.job.demo.mapper;

import com.huntercodexs.workbook.job.demo.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component
public class WorkbookXmlMapper implements RowMapper<ProductDto> {

    @Override
    public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProductDto productDto = new ProductDto();

        try {
            productDto.setId(rs.getInt("id"));
            productDto.setName(rs.getString("name"));
            productDto.setDescription(rs.getString("description"));
            productDto.setPrice(rs.getString("price"));
        } catch (RuntimeException re) {
            log.error("Mapper say: (mapRow) Exception: " + re.getMessage());
            throw new RuntimeException(re.getMessage());
        }

        log.info("Mapper say: (mapRow) workbookXmlDto: " + productDto);

        return productDto;
    }
}
