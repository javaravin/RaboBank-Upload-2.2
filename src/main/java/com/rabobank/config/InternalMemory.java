package com.rabobank.config;

import com.rabobank.model.FileData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ravi Naganaboying
 * @InternalMemory This class contains Internal memory declaration.
 */
@Configuration
public class InternalMemory {


    @Bean
    public List<FileData> internalMemoryFile() {
        return new ArrayList<>();
    }
}
