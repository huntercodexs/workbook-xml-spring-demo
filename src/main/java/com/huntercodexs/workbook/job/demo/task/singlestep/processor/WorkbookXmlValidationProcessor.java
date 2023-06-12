package com.huntercodexs.workbook.job.demo.task.singlestep.processor;

import com.huntercodexs.workbook.job.demo.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkbookXmlValidationProcessor implements ItemProcessor<ProductDto, ProductDto> {

    public ProductDto process(ProductDto productDto) throws Exception {

        if (productDto.getName().equals("")) {
            log.error("WorkbookXmlValidationProcessor say: (process) Missing item name: " + productDto.getName());
            return null;
        }

        return productDto;
    }
}
