package com.yinuo.socket.util;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.apache.log4j.Logger;

import java.util.function.Consumer;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-31
 */
public class Runner {
    static final Logger LOGGER = Logger.getLogger(Runner.class);

    public static void run(Class clazz) {
        Consumer<Vertx> runner = vertx -> {
            try {
                vertx.deployVerticle(clazz.getName(), new DeploymentOptions().setWorker(true));
            } catch (Throwable t) {
                LOGGER.error(t);
            }
        };

        Vertx vertx = Vertx.vertx(new VertxOptions().setClustered(false));
        runner.accept(vertx);
    }
}
