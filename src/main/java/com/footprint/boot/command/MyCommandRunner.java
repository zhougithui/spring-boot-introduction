package com.footprint.boot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyCommandRunner.class);

    public void run(String... args) throws Exception {
        logger.info(args != null && args.length>0 ? args[0] : "NONE");
    }
}
