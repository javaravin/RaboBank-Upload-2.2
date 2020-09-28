package com.rabobank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * @author Ravi Naganaboyina
 * @TilesConfiguration class declared for tiles configuration
 */
@Configuration
public class TilesConfiguration {

    private static final String TILES_CONFIG="WEB-INF/tiles.xml";

    @Bean
    public UrlBasedViewResolver tilesViewResolver() {
        UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();

        tilesViewResolver.setViewClass(TilesView.class);

        return tilesViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();

        String[] defs = {TILES_CONFIG};

        tilesConfigurer.setDefinitions(defs);

        return tilesConfigurer;
    }
}
