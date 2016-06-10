package com.yinuo.socket;

import com.yinuo.socket.constant.Constants;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Bill Lv on 5/31/16.
 */
@RunWith(VertxUnitRunner.class)
public class TcpServerVerticleTest {
    static final Logger LOGGER = Logger.getLogger(TcpServerVerticleTest.class);

    Vertx vertx;

    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(TcpServerVerticle.class.getName(), new DeploymentOptions().setWorker(true),
                context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testApplication(TestContext context) {
        final Async async = context.async();

        vertx.createNetClient().connect(Constants.PORT, "localhost",
                result -> {
                    if (result.succeeded()) {
                        NetSocket socket = result.result();

                        // test register [V1.0.0,460001515535328,1,abcd,2016-06-03 21:20:15,1-2,356511170035899,8,T1]
                        socket.write("[V1.0.0,460001515535328,1,abcd,2016-06-03 21:20:15,1-2,356511170035899,8,T1]");

                        // test heart-beat check [V1.0.0,460001515535328,1,abcd,2016-06-03 21:20:15,1-2,356511170035899,8,T2]
                        socket.write("[V1.0.0,460001515535328,1,abcd,2016-06-03 21:20:15,1-2,356511170035899,8,T2]");
                        // 设置快速拨号 [V1.0.0,460001515535328,1,abcd,2016-06-03 21:20:15,356511170035899,S70,13896689660,13654673567,13654673568,13654673569]
                        socket.write("[V1.0.0,460001515535328,1,abcd,2016-06-03 21:20:15,356511170035899,S70,13896689660,13654673567,13654673568,13654673569]");

                        // 心率数据
//                        socket.write("[V1.0.0,460001515535328,1,abcd,2016-06-03 21:20:15,1-2,356511170035899,8,T28,mt_pulse,78,1267511609]");
//                        sleep();

                        socket.handler(buffer -> {
                            LOGGER.info("Net client receiving: " + buffer.toString("UTF-8"));
                        });

                        async.complete();
                    } else {
                        LOGGER.info("Failed to connect " + result.cause());
                    }
                });
    }

    private void sleep() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            LOGGER.error("interrupted exception");
        }
    }

}