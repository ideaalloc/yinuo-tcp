package com.yinuo.socket.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-06-05
 */
public class DataSourceConfig {
    static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    static class DruidConfigHolder {
        static final DataSourceConfig INSTANCE = new DataSourceConfig();
    }

    public static DataSourceConfig getInstance() {
        return DruidConfigHolder.INSTANCE;
    }

    private DataSourceConfig() {
        Properties jdbcProps = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            jdbcProps.load(input);
        } catch (IOException ex) {
            LOGGER.error("load jdbc config file error");
            throw new RuntimeException("fatal error, caused by jdbc config error");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("close jdbc config file error");
                }
            }
        }
        final String driverClassName = jdbcProps.getProperty("jdbc.driverClassName");
        final String url = jdbcProps.getProperty("jdbc.url");
        final String username = jdbcProps.getProperty("jdbc.username");
        final String password = jdbcProps.getProperty("jdbc.password");

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        Properties prop = new Properties();
        try {
            input = getClass().getClassLoader().getResourceAsStream("druid.properties");
            prop.load(input);
        } catch (IOException ex) {
            LOGGER.error("load druid config file error");
            throw new RuntimeException("fatal error, caused by druid config error");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("close druid config file error");
                }
            }
        }

        dataSource.configFromPropety(prop);
        this.dataSource = dataSource;
    }

    DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }
}
